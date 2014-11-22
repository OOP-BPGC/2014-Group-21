package tests;

import java.util.Arrays;

import junit.framework.TestCase;
import Base_Classes.Person;

public class PersonTest extends TestCase{

	public void testStringFields(){
		String[] cred = new String[]{"hallo","world"};
		assertEquals("StringTest",new PersonBuilder()
										.Name("StringTest")
										.Credentials(cred)
										.buildPerson()
										.getName());
	}
	
	public void testAppendToDatabase(){
		String[] cred = new String[]{"hallo","world"};
		assertEquals(1, new  PersonBuilder()
							.Name("StringTest")
							.Credentials(cred)
							.buildPerson()
							.appendToDatabase("appendTest", "testing lol"));
	}
}
	