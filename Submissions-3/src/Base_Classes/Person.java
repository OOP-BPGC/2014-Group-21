package Base_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Person {

	
	  static final int PRIVILEGELEVEL = 1;

	  public String Name;

	  public String IDNumber;

	  public Designation designation;

	  public String PhoneNumber;
	  
	  protected String[] Credentials;
	  
	  public static boolean hasProject = false;
	  	

	public Person(String name, String iDNumber, Designation designation,
			String phoneNumber,String[] credentials) {
		super();
		Name = name;
		IDNumber = iDNumber;
		this.designation = designation;
		PhoneNumber = phoneNumber;
		Credentials = new String[2];
		for(int i =0; i<credentials.length;i++){
			this.Credentials[i] = credentials[i];
		}
	}
	
	

	public String[] getCredentials() {
		return Credentials;
	}



	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	  
	public void getProjects(Designation x, String filename) {
		// Method to list out the projects
		int i;
		List<String> stringlist;
		try {
			File file1 = new File(filename+".txt");
			Scanner in = new Scanner(file1);
			String op = "";
			Gson gson = new Gson();
			i = 1;
			stringlist = new ArrayList<String>();
			Project p;
			while (in.hasNextLine()){
				op = in.nextLine();
				stringlist.add(op);
				String[] stemp = op.split("@@@@");
				p = gson.fromJson(stemp[3], Project.class);
				System.out.println(i++ + ". Name of Project :"+ p.Name + "      || Project Leader :" + p.getProjectHead());
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR OCCURED DURING READING THE FILE");
			return;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Project file was not found. Please check the database.");
			return;
		}
		if (x == Designation.VOLUNTEER && this.hasProject == false){
			System.out.println("Do you want to apply for a project? (Press Y for yes)");
			Scanner scanner = new Scanner (System.in);
			String input = scanner.nextLine();
			int inputint = 0;
			if (input == "Y"){
				System.out.println("Enter the Project number from the above list :");
				inputint = scanner.nextInt();
				if ( inputint <= i ){
					String y = stringlist.get(i);
					String[] tstr= y.split("@@@@");
					String finalmessage = "PROJREQ";
					for (int j = 1; j<tstr.length;j++){
						finalmessage = finalmessage + "@@@@" + tstr[j];
					}
					finalmessage = finalmessage + this.Name;
					/*
					 * Pass the final message to the message class. 
					 */
				}
			}
		}
	} 
	
	public int appendToDatabase(String filename, String stringtoappend){
		
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
			} catch (FileNotFoundException e) {
				return 0;
			}
			return 1;
	  }
	
		// ADD PROJECTS IS IN INDIVIDUAL INHERITED CLASSES
		
		public void addEvents (String message){
			/*
			 *  same as above
			 */
			this.appendToDatabase("eventdatabase", message);
		}
		
		public void addProject (String message){
			this.appendToDatabase("projectdatabase", message);
			// ANOTHER INSTANCE IN PJH CLASS
		}
		
		public void addUser (String message){
			//EXTRACTS THE STRING TO bE SAVED AND THEN APPENDS IT
			String[] splitstring = message.split("@@@@");
			if(splitstring[0] == "CREATEUSER"){
				String[] app = message.split("VEGAS@SAVEG");
				String str = app[1];
				this.appendToDatabase("userlist", str);
			}
		}
		
		public void updateUserData(){
			
		}
		
		public void updateVolunteer(String message){
			try {
				String[] splitstring = message.split("@@@@");
				if (splitstring[0] == "ACCEPTED"){
					String name = splitstring[1];
					String pname = splitstring[2];
					
					/*
					 * 
					 */
					String op = "";
					File file1 = new File("userlist.txt");
					Scanner in = new Scanner(file1);
					PrintWriter f = new PrintWriter("file55.txt");
					int i = 0;
					String s = "_";
					String s1= "Neeraj Ingle";
//		    	System.out.println("LELLO");
					while(in.hasNextLine()){
						op = in.nextLine();
						if(op.contains("{\"CurrentProject\":\"_\"")){
							op = op.replace( "{\"CurrentProject\":\"_\"" , "{\"CurrentProject\":\"" + pname+ "\"");
						}
						f.println(op + "\n");
						i++;
					}
					f.close();
					in.close();
					File f5 = new File("file55.txt");
					file1.delete();
					f5.renameTo(file1);
					/*
					 * 
					 */
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Error occured when updating request results. Please get the files checked.");
			}
		}
		
		public void addVolunteerProjectRequests(String message){
			if (this.PRIVILEGELEVEL == 2){
				String[] splitmessage = message.split("@@@@");
				if (splitmessage[1] == this.Name){
					this.appendToDatabase("volunteerprojectrequests", message);
				}
				
			}
		}
		

	}