package Base_Classes;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;


public class MessageHelper {

	public static void SendMessage(Person sender, Message m){
		
		
				String user, pwd;
				user = sender.getCredentials()[0];
				pwd = sender.getCredentials()[1];
				// Subject will be set to current date by the class that uses it
				GmailUtilities.sendEmail(m.From,"oopnirmaan@gmail.com", new Date().toString(), m.encodeObj(m), user, pwd);
			
	}
	
	
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
	

}