package tests;

import java.util.Arrays;

import junit.framework.TestCase;
import Base_Classes.Person;

public class PersonTest extends TestCase{

	public void testStringFields(){
		
		assertEquals("StringTest",new PersonBuilder()
										.Name("StringTest")
										.buildPerson()
										.getName());
	}
}
	