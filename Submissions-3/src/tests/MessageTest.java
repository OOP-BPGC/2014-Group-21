package tests;

import java.util.Arrays;
import com.google.gson.Gson;

import Base_Classes.Core;
import Base_Classes.Designation;
import Base_Classes.Message;
import junit.framework.TestCase;

public class MessageTest extends TestCase{

	/**
	 * @param args
	 */
	//Takes care of all String type fields
	public void testStringFields(){
		
		assertEquals("StringTest",new MessageBuilder()
										.date("StringTest")
										.buildMessage()
										.Date);
	}
	
	
	
	public void testIntFields(){
		assertEquals(1,(int)new MessageBuilder()
		.minPrivLevel(1)
		.buildMessage()
		.Min_Privilege_Level);
		
	}
	
	public void testJSON(){
		Gson gson = new Gson();
		Message m = new MessageBuilder()
		  .body("Hello")
		  .date("Test")
		  .buildMessage();
		String jsonstr = gson.toJson(m);
		Core p = new Core();
			
		assertEquals(jsonstr,gson.toJson(m.decodeJson(jsonstr)));
	}
	
	public void testAddMsgToDb(){
		Message m = new MessageBuilder()
		  .body("Hello")
		  .date("Test")
		  .projRestr(new String[]{"a","b"}).buildMessage();
		assertTrue(!(m.addMessageToDatabaseTEST(Designation.CORE, "May the \\// be with you")==0));
	}
	
}
