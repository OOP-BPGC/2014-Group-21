package Base_Classes;

/**
 * Contains methods to send messages, and retrieve and read recent messages.
 * @author Anshul Ravichandar
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;


public class MessageHelper {

	/**
	 * Sends a message m from Person sender. The message is broadcasted to all users, but only 
	 * those with the appropriate PRIVILEGELEVEL can read it. 
	 * @param sender
	 * @param m
	 */
	
	public static int SendMessage(Person sender, Message m){
		String user, pwd;
		user = sender.getCredentials()[0];
		pwd = sender.getCredentials()[1];
		// Subject will be set to current date by the class that uses it
		try {
			
			GmailUtilities.sendEmail(user,"oopnirmaan@gmail.com", new Date().toString(), m.encodeObj(m), user, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("Error in sending data. Internet connection required. Exiting without saving data.");
			System.out.println("Error in sending email: " + e.getMessage());
			return 0;
		}
		return 1;

	}
	
	/**
	 * Sends a message m from Person sender. The message is broadcasted to all users, but only 
	 * those with the appropriate PRIVILEGELEVEL can read it. 
	 * @param from
	 * @param json
	 * @param user
	 * @param pwd
	 */
	
	public static int SendMessage(String from, String json, String user, String pwd){
		try {
			GmailUtilities.sendEmail(from,"oopnirmaan@gmail.com", new Date().toString(), json, user, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("Error in sending data. Internet connection required. Exiting without saving data.");
			System.out.println("Error in sending email: " + e.getMessage());
			return 0;
		}
		return 1;
	}
	
	/**
	 * Returns an array of Message objects of all messages upto a specified date.
	 * @param updateDate
	 * @return ret.toArray()
	 */
	//after:2014/11/13 before:2014/11/16
	public static Message[] RetrieveRecentMessages(String updateDate){
		
		try {
			List<Message> ret = new ArrayList<Message>();
			GmailUtilities gmail = new GmailUtilities();
			gmail.init();
			String[] mbodys = gmail.printRecentMessages(Days.daysBetween(new DateTime(updateDate), new DateTime(new Date())).getDays());
			for(int i=0;i<mbodys.length;i++){
				Message m = new Message();
				m.decodeJson(mbodys[i]);
				ret.add(m);
			}
			return (Message[]) ret.toArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message[]{};
	}
	
	/**
	 * Returns an array of Message objects of all messages upto a specified number of days.
	 * @param days
	 * @return ret.toArray()
	 */
	
	public static Message[] RetrieveRecentMessagesArray(int days){
		
		try {
			List<Message> ret = new ArrayList<Message>();
			GmailUtilities gmail = new GmailUtilities();
			gmail.init();
			String[] mbodys = gmail.printRecentMessages(days);
			for(int i=0;i<mbodys.length;i++){
				Message m = new Message();
				m=m.decodeJson(mbodys[i]);
				ret.add(m);
			}
			return (Message[]) ret.toArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message[]{};
	}
	
	/**
	 * Returns a string array of all messages upto a specified number of days.	  
	 * @param days 
	 * @return retval.clone()
	 */

	public static String[] RetrieveRecentMessages(int days){
		
		try {
			List<String> ret = new ArrayList<String>();
			GmailUtilities gmail = new GmailUtilities();
			gmail.init();
			String[] mbodys = gmail.printRecentMessages(days);
			for(int i=0;i<mbodys.length;i++){
				ret.add(mbodys[i]);
			}
			 String[] retval = new String[ret.size()];
		      ret.toArray(retval);
			return retval.clone();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String[]{};
	}

	
}