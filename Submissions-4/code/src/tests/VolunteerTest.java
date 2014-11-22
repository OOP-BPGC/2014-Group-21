package tests;

import java.io.*;
import java.util.*;
import junit.framework.*;
import Base_Classes.Volunteer;

public class VolunteerTest extends TestCase {
	public void testAddMessage() throws FileNotFoundException{
		String s ="Is this@@@@how this should@@@@work?@@@@1";
		new Volunteer().addMessage(s);
		try {
			File file = new File ("messagedatabase.txt");
			Scanner in = new Scanner(file);
			String op;
			String[] splitmessage;
			int x = 0;
			while(in.hasNextLine())
			op = in.nextLine();
			assertEquals(s,op);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Problem in database. Exiting.");
		}
	}
}