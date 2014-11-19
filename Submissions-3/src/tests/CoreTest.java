package tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import junit.framework.TestCase;
import Base_Classes.Core;

public class CoreTest extends TestCase {

	public void testStringFields(){
		String[] cred = new String[]{"hallo","world"};
		assertEquals("StringTest",new CoreBuilder()
										.Name("StringTest")
										.Credentials(cred)
										.buildPerson()
										.getName());
	}
	
	public void testFileRead(){
		try{
			new CoreBuilder().buildCore().getProjectsTest("RandomText.txt");
				fail("Should've thrown an error");
		}catch(Exception e){
			assertTrue(true);
		}
	}
	
/*	public void testFileWrite(){
		try{
			int x = new Core().appendToDatabase("ut","AppendTest");
			new Core().appendToDatabase("ut","AppendTest");
			
			File file1 = new File("ut.txt");
			Scanner scan;
			scan = new Scanner(file1);
			
			String test;
			
			while (scan.hasNextLine()){
				test = scan.nextLine();
				assertEquals("AppendTest",test);
			}
			
		}catch(FileNotFoundException e){
			fail("File should be found");
		}
	}*/
	
	public void testCreateMessage() throws FileNotFoundException{
		
		new Core().CreateMessageTest("May the \\// be with you",3);
		// Decode and read message from file
		
	}
	
}
