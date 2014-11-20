package tests;

import java.util.Arrays;
import com.google.gson.Gson;

import Base_Classes.Core;
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
	

	
	public void testEncodeMsg(){
		Gson gson = new Gson();
		Message m = new MessageBuilder()
		  .body("Hello")
		  .date("Test")
		  .projRestr(new String[]{"a","b"}).buildMessage();
		String jsonstr = gson.toJson(m);
		assertEquals(jsonstr,m.encodeMsg(m));
	}
	
	public void testDecodeJSON(){
		Gson gson = new Gson();
		Message m = new MessageBuilder()
		  .body("Hello")
		  .date("Test")
		  .projRestr(new String[]{"a","b"}).buildMessage();
		String jsonstr = gson.toJson(m);
		Core p = new Core();
		m.decodeMsg(jsonstr, p);
		
		assertEquals(jsonstr,gson.toJson(m.decodeJson(jsonstr)));
	}
	
}
