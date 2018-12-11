package Client;

import Protocol.SimpleProtocol;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

    private Socket clientSocket;			// socket connecting to server
    private static DataOutputStream outToServer;	// output stream to server
    private static BufferedReader inFromServer;	// input stream from server
    private static SimpleProtocol protocol = new SimpleProtocol();		// pack and unpack messages
    public Integer offset = -1;		// offset of messages, at the beginning it is -1. Update it to the offset of the latest message
    private String host = "172.22.175.139";		// IP address of server
    private Integer port = 50282;		// Port number of server

    public String[] getResponse(){
        try {
        	System.out.println("");
        		String[] arr = protocol.decodeMessage(inFromServer.readLine());
        		for(int i = 0; i<arr.length; i++) {
        			System.out.println(arr[i]);
        		}
            return arr;
        } catch (IOException e) {
        		System.out.println("get respose issue");
            e.printStackTrace();
            return null;
        }
    }

    public String[] signup(String user, String pass, String firstName, String lastName, String email, String phoneNumer){
       
    		String string = protocol.createMessage("sign-up", user, pass, firstName, lastName, email, phoneNumer);
        try {
            outToServer.writeBytes(string + "\n");
            String[] response = this.getResponse();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 		Send sign-in request to server, return the response to GuiSignIn
     */
    public String[] signin(String user, String pass){

        String string = protocol.createMessage("sign-in", user, pass);
        try {
            outToServer.writeBytes(string + "\n");
            String[] response = this.getResponse();
            if(response[1].equals("true")){
                System.out.println("Sign-in successful.");
            }else{
                System.out.println(response[2]);
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("issue");
            return null;
        }
    }

    /*
     * 		Send get-listings request to server
     */
    public ArrayList<String[]> get_listings(){
        String string = protocol.createMessage("get-listings");
        String[] response = null;
        ArrayList<String[]> arrl = new ArrayList<String[]>();
       
        try {
         
            outToServer.writeBytes(string + "\n");
            String[] r = protocol.decodeMessage(inFromServer.readLine());
            int no = Integer.parseInt(r[0]);
            for(int i = 0; i<no; i++) {
	            arrl.add(this.getResponse());
            }
            
        } catch (IOException e) {
            System.out.println("Unable to get listings");
            e.printStackTrace();
            return null;
        }
        return arrl;
    }

    /*
     * 		Send a message to server.
     */
    public void new_listing(String eventName, String eventDate, String eventVenue, String eventCity,
                            String eventPostcode, int listingLength){
        String string = protocol.createMessage("new-listing", eventName, eventDate, eventVenue, eventCity,
                eventPostcode, Integer.toString(listingLength));
        try {
            outToServer.writeBytes(string + "\n");
        } catch (IOException e) {
            System.out.println("Unable to create new listing.");
            e.printStackTrace();
        }
    }

    public String[] newBid(int listingId, double offer, String username) {
    		String listingIdString = Integer.toString(listingId);
    		String offerString = Double.toString(offer);
        String string = protocol.createMessage("newBid", listingIdString, offerString, username);
        try {
            outToServer.writeBytes(string + "\n");
            String[] response = this.getResponse();
            if(response[1].equals("true")){
                System.out.println("bid successful.");
            }else{
                System.out.println(response[2]);
            }
            return response;
        } catch (IOException e) {
            System.out.println("Unable to submit new bid.");
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<String[]> get_LowHigh(){
        String string = protocol.createMessage("get-lh");
        ArrayList<String[]> arrl = new ArrayList<String[]>();
       
        try {
         
            outToServer.writeBytes(string + "\n");
            String[] r = protocol.decodeMessage(inFromServer.readLine());
            int no = Integer.parseInt(r[0]);
            for(int i = 0; i<no; i++) {
	            arrl.add(this.getResponse());
            }
            
        } catch (IOException e) {
            System.out.println("Unable to get listings");
            e.printStackTrace();
            return null;
        }
        return arrl;
    }
    
    public ArrayList<String[]> get_UsersLists(String username){
        String string = protocol.createMessage("get-ul", username);
        ArrayList<String[]> arrl = new ArrayList<String[]>();
       
        try {
         
            outToServer.writeBytes(string + "\n");
            String[] r = protocol.decodeMessage(inFromServer.readLine());
            int no = Integer.parseInt(r[0]);
            for(int i = 0; i<no; i++) {
	            arrl.add(this.getResponse());
            }
            
        } catch (IOException e) {
            System.out.println("Unable to get listings");
            e.printStackTrace();
            return null;
        }
        return arrl;
    }
    
    public String[] checkUserPass(String user, String pass){

        String string = protocol.createMessage("checkUserPass", user, pass);
        try {
            outToServer.writeBytes(string + "\n");
            System.out.println("before getRespose");
            String[] response = this.getResponse();
            if(response[1].equals("true")){
                System.out.println(response[2]);
            }else{
                System.out.println(response[2]);
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("issue");
            return null;
        }
    }
    
    public String[] newListing(String username, String eventName, String eventVenue, String eventCity, 
			String eventPostcode, String eventYear, String eventMonth, String eventDay, String eventHour, 
			String eventMin, String duration, String dateVariable) {
		
    String string = protocol.createMessage("newListing", username, eventName, eventVenue, eventCity, 
    			eventPostcode, eventYear, eventMonth, eventDay, eventHour,  eventMin, duration, dateVariable);
    try {
        outToServer.writeBytes(string + "\n");
        String[] response = this.getResponse();
        if(response[1].equals("true")){
            System.out.println("list submited.");
        }else{
            System.out.println(response[2]);
        }
        return response;
    } catch (IOException e) {
        System.out.println("Unable to submit listing.");
        e.printStackTrace();
        return null;
    }
}
    
    public String[] getStartEndTimes(int listId) {
    	String listingId = Integer.toString(listId);
    	String string = protocol.createMessage("startEndTimes", listingId);
    	
    	 try {
    	        outToServer.writeBytes(string + "\n");
    	        String[] response = this.getResponse();
    	        if(response[1].equals("true")){
    	            System.out.println("Difference between now and End time given");
    	        }else{
    	            System.out.println(response[2]);
    	        }
    	        return response;
    	    } catch (IOException e) {
    	        System.out.println("Unable to get end time.");
    	        e.printStackTrace();
    	        return null;
    	    }
    }
    
    public ArrayList<String[]> getPreviousListings(String username){
    		String string = protocol.createMessage("previousListings", username);
        ArrayList<String[]> arrl = new ArrayList<String[]>();
       
        try {
         
            outToServer.writeBytes(string + "\n");
            String[] r = protocol.decodeMessage(inFromServer.readLine());
            int no = Integer.parseInt(r[0]);
            for(int i = 0; i<no; i++) {
	            arrl.add(this.getResponse());
            }
            
        } catch (IOException e) {
            System.out.println("Unable to get listings");
            e.printStackTrace();
            return null;
        }
        return arrl;
    }
    
    public String[] changePassword(String username, String password) {
		
    String string = protocol.createMessage("changePassword", username, password);
    try {
        outToServer.writeBytes(string + "\n");
        String[] response = this.getResponse();
        if(response[1].equals("true")){
            System.out.println("list submited.");
        }else{
            System.out.println(response[2]);
        }
        return response;
    } catch (IOException e) {
        System.out.println("Unable to submit listing.");
        e.printStackTrace();
        return null;
    }
}
    public String[] forgottenPassword(String username, String password) {
		
        String string = protocol.createMessage("forgottenPassword", username, password);
        try {
            outToServer.writeBytes(string + "\n");
            String[] response = this.getResponse();
            if(response[1].equals("true")){
                System.out.println("list submited.");
            }else{
                System.out.println(response[2]);
            }
            return response;
        } catch (IOException e) {
            System.out.println("Unable to submit listing.");
            e.printStackTrace();
            return null;
        }
    }
    
    public String[] checkFinished(){
    	String string = protocol.createMessage("checkFinished");
        try {
            outToServer.writeBytes(string + "\n");
            String[] response = this.getResponse();
            if(response[1].equals("true")){
                System.out.println("list submited.");
            }else{
                System.out.println(response[2]);
            }
            return response;
        } catch (IOException e) {
            System.out.println("Unable to submit listing.");
            e.printStackTrace();
            return null;
        }
    }
    
    /*
     * 		Initialize socket and input/output streams
     */
    public void start(){
        try {
            clientSocket = new Socket(this.host, this.port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(this.getResponse()[1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 		Close socket
     */
    public void stop(){
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public Socket getClientSocket() {
		return clientSocket;
	}

	public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}

