package tests;

import java.util.Arrays;
import com.google.gson.Gson;
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
										.getDate());
	}
	
	public void testIntFields(){
		assertEquals(1,new MessageBuilder()
		.minPrivLevel(1)
		.buildMessage()
		.getMinPrivilege());
	}
	
	public void testStringArrayFields(){
		String[] a = new String[]{"String1","String2"};
		assertTrue(Arrays.equals(a,new MessageBuilder()
							.projRestr(a)
							.buildMessage()
							.getspecifiedProjects()));
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
	
	public void testDecodeMsg(){
		Gson gson = new Gson();
		Message m = new MessageBuilder()
		  .body("Hello")
		  .date("Test")
		  .projRestr(new String[]{"a","b"}).buildMessage();
		String jsonstr = gson.toJson(m);
		assertEquals(jsonstr,gson.toJson(m.decodeMsg(jsonstr)));
	}
}
