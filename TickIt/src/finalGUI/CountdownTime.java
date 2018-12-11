package finalGUI;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import Client.Client;

public class CountdownTime {
	Client c = new Client();
	long end;
	Date eventDate = new Date();
	long time1 = eventDate.getTime();
	Timestamp ts;
	static long difference;
	int listingId;
	
	public CountdownTime(int listingId) {
		c.start();
		this.listingId = listingId;
	}
	
	public String getStart() {
		String[] response = c.getStartEndTimes(listingId);
		end = Long.parseLong(response[2]);
		difference = (end-time1)/1000;
		String startString = outFormat(difference);
		return startString;
	}
	
	public String run(String diff) {
		long difference = inFormat(diff);
		System.out.println(difference);
		long newDiff = (difference-1);
		System.out.println(newDiff);
		String string = outFormat(newDiff);
		return string;
	}
	
	public String outFormat(long difference) {
		long hour = difference/3600;
		long min = (difference%3600)/60;
		long sec = (difference % 60);
		String out = Long.toString(hour) + ":" + Long.toString(min)
						+ ":" + Long.toString(sec);
		return out;
	}
	
	public long inFormat(String timeString) {
		String[] arr = timeString.split(":");
		
		long hour = Long.parseLong(arr[0]);
		long min = Long.parseLong(arr[1]);
		long sec = Long.parseLong(arr[2]);
		
		long diff = (hour*3600)+(min*60)+(sec);
		
		return diff;
	}
	
	public static void main(String[] args) {
		
		
		
		CountdownTime cdt = new CountdownTime(32);
		System.out.println(cdt.inFormat("02:10:32"));
		String start = cdt.getStart();
		System.out.println(start);
		String next = cdt.run(start);
		System.out.println(next);
		String next1 = cdt.run(next);
		System.out.println(next1);
		String next2 = cdt.run(next1);
		System.out.println(next2);
	}
}
