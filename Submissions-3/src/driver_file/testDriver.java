package driver_file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import Base_Classes.*;

public class testDriver {

	public static void main(String[] args) throws FileNotFoundException {
		String[] cr = new String[]{"asd@gmail.com","passwordcase"};
		Core tempcore = new Core("Rohit Pandey", "2013A7PS711G" ,Designation.CORE , "9763705095",20,cr); 
		Gson js = new Gson();
		String s = js.toJson(tempcore);
		String store = s;
		System.out.println("||| "+s+ " |||");
		ProjectHead ph = new ProjectHead("RHP", "2013A7PS888G", Designation.PROJECT_HEAD,"9999", "Proj1", cr);
		s = js.toJson(ph);
		System.out.println("||| "+s+ " |||");
		Volunteer v = new Volunteer("PRH","2013APdsfds", Designation.VOLUNTEER,"976388888", "PROJ!@@",cr);
		s = js.toJson(v);
		System.out.println("||| "+s+ " |||");
		Project p = new Project("New Proj", "MWF", "Hardik",21, "Shiksha");
		s = js.toJson(p);
		System.out.println("||| "+s+ " |||");
		Project p2 = js.fromJson(s, Project.class);
		System.out.println (p2.Schedule.equals(p.Schedule));
		List<String> stringlist = new ArrayList<String>();
		stringlist.add(p2.EventName); stringlist.add(p.Name);
		System.out.println (stringlist.get(1)+"  "+stringlist.get(0));
		Person pers = js.fromJson(store, Person.class);
		String[] stringcred = pers.getCredentials();
		String[] newstr = new String[2];
		newstr[0] = stringcred[0];
		newstr[1] = stringcred[1];
		System.out.println(pers.IDNumber);
		System.out.println(newstr[0]);
		Core coreelement = createcore(store);
		System.out.println(coreelement.Name);
		String stringtester = "";
		while (true){
			stringtester = "shephard";
			break;
		}
		System.out.println(stringtester);
		int x = Integer.valueOf("34");
		System.out.println(x);
		System.out.println(tempcore.getPRIVILEGELEVEL());
		
		String test = "MUMU@@@@asdgfdsdvsVEGAS@SAGEV";
		String [] tes = test.split("@@@@");
		System.out.println(tes[0]+"_");
		System.out.println(tes[1]+"_");
		String [] tes3 = tes[1].split("VEGAS@SAGEV");
		System.out.println(tes3[0]+"_");
//		System.out.println(tes3[1]+"_");
				
		printLines("moo");
		
		
		

	}
	
	public static Core createcore(String s){
		Gson g = new Gson();
		return g.fromJson(s, Core.class);
	}
	
	public static int printLines(String filename){
		
		try {
			File file1 = new File(filename+".txt");
			Scanner in = new Scanner(file1);
			while(in.hasNextLine()){
				System.out.println(in.nextLine());
			}
			in.close();
			file1.delete();
			file1.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
		
	}
	
	public static int emptyFile(String filename, String stringtoappend){
		try {
			File file1 = new File(filename+".txt");
			Scanner in = new Scanner(file1);
			PrintWriter f = new PrintWriter("temp"+filename+".txt");
			String op = "";
			while (in.hasNextLine()){
	//			op = in.nextLine();
	//			f.println(op);
			}
			f.println(stringtoappend);
			f.close();
			in.close();
			File file2 = new File("temp"+filename+".txt");
			file1.delete();
			
		} catch (FileNotFoundException e) {
			return 0;
		}		
		return 1;
	}
	
	public static int appendToDatabase(String filename, String stringtoappend){
		
		//tempcore_db for core
		//norma db for else
		  try {
				File file1 = new File(filename+".txt");
				Scanner in = new Scanner(file1);
				PrintWriter f = new PrintWriter("temp"+filename+".txt");
				String op = "";
				while (in.hasNextLine()){
					op = in.nextLine();
					f.println(op);
				}
				f.println(stringtoappend);
				f.close();
				in.close();
				File file2 = new File("temp"+filename+".txt");
				file1.delete();
				file2.renameTo(file1);
				file2.delete();
			} catch (FileNotFoundException e) {
				return 0;
			}
			return 1;
	  }
}
