package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

import db.Emails;
import db.userDatabase;

public class Server {
    private static ServerSocket serverSocket;
    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    public static HashMap<String, String> userNames = new HashMap<>();
    public static ArrayList<Ticket> listings = new ArrayList<Ticket>();
    public static ArrayList<String> onlineUsers = new ArrayList<String>();
    static Thread thread = new Thread();

    public static void main(int port) throws IOException, MessagingException, InterruptedException {
   
        int portNumber = port;

        try {

            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println("Server Socket initiation failed: perhaps port is already in use.");
            System.exit(1);
        }

        try {
            while (true) {
                //Server socket initiated successfully.
                System.out.println("Server Ready");
                //Connection between clientSocket and serverSocket made.
                Socket clientSocket = serverSocket.accept();
                System.out.println("c is con");
                //ThreadPool to execute the requests from the client socket.
                threadPool.execute(new ServerThread(clientSocket));
                System.out.println("out for");

//                for(int i=0; i<600000000; i++) {
//        			System.out.println("in for");
//
//        				try {
//        					ArrayList<int[]> soldList = Emails.soldList();
//        					String seller;
//        					String buyer;
//        					for(int[] el : soldList) {
//        		        			seller = Emails.emailToSeller(el[2]);
//        		        			buyer = Emails.emailToBuyer(el[2]);
//        								Email.Email.sendEmail(userDatabase.getEmailById(el[0]), "Tick-it - You're ticket sold!", seller);
//        								Email.Email.sendEmail(userDatabase.getEmailById(el[1]), "Tick-it - You won!", buyer);
//        					}
//        					ArrayList<int[]> notSoldList = Emails.notSoldList();
//        					for(int[] el : notSoldList) {
//        						String notSold = Emails.failureMessage(el[1]);
//        						Email.Email.sendEmail(userDatabase.getEmailById(el[0]), "We're sorry, your ticket didn't sell", notSold);
//        					}
//        				} catch (SQLException e) {
//        					// TODO Auto-generated catch block
//        					e.printStackTrace();
//        				}
//        			Thread.sleep(60000);
//                }
            }
        } catch (Exception e) {
            try {
                //If client server connection can't be made close the socket and thread pool.
                serverSocket.close();
                threadPool.shutdown();
            } catch (IOException io) {
                System.err.println(io.getMessage());
            }
        }
    }
}