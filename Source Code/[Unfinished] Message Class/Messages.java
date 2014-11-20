import java.util.Date;
//test
import org.joda.time.DateTime;
import org.joda.time.Days;


public class Messages {

	public static void SendMessage(Person sender, Message m){
		
		if(sender.getDesignation() < 2){
			try{
				throw new Exception("You shall not pass");
			}catch(Exception e){
				System.out.println("You shall not pass");
				return;
			}
		}
		else{
				String user, pwd;
				user = sender.getCredentials()[0];
				pwd = sender.getCredentials()[1];
				// Subject will be set to current date by the class that uses it
				GmailUtilities.sendEmail(m.getFrom(), m.getTo(), m.getSubject(), m.getBody(), user, pwd);
			}
	}
	
	
	//after:2014/11/13 before:2014/11/16
	public static Message[] RetrieveRecentMessages(String updateDate){
		
		try {
			GmailUtilities gmail = new GmailUtilities();
			gmail.init();
			gmail.printRecentMessages(Days.daysBetween(new DateTime(updateDate), new DateTime(new Date())).getDays());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message[]{};
	}
	

}