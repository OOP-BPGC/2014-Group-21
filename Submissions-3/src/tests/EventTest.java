package tests;

import Base_Classes.Event;

import com.google.gson.Gson;

import junit.framework.TestCase;

public class EventTest extends TestCase{
	
		public void testEncodeEvent(){
			Gson gson = new Gson();
			Event e = new EventBuilder()
			  .name("Hello")
			  .date("Test")
			  .organiser("yolo").buildEvent();
			String jsonstr = gson.toJson(e);
			assertEquals("EVENT@@@@"+e.getOrganiser()+"@@@@"+e.getName()+"@@@@"+jsonstr,e.encodeEvent());
	}
		public void testStringFields(){
			
			assertEquals("StringTest",new EventBuilder()
											.date("StringTest")
											.buildEvent()
											.getDate());
		}
		
}
