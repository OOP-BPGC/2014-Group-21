package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Some IO helper functions for test
 * @author Anshul Ravichandar
 */
public class IOHelper {

	public static String getFirstEntry(String filename) {
		File file1 = new File(filename);
		Scanner scan;
		try {
			scan = new Scanner(file1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in file I/O");
			return "";
		}
		
		String retval = "";
	
		String[] splitstrings = new String[10];
		int i =1;
		if (scan.hasNextLine()){
			retval = scan.nextLine();
			splitstrings = retval.split("@@@@");
	
		}
		scan.close();
		return splitstrings[2];
	}
	
}
