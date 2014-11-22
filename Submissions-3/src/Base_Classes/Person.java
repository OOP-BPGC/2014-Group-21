package Base_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import tests.MessageBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/** 
 * The base class inheritted by Volunteer, ProjectHead and Core. Contains use cases and 
 * attributes common to all three derived classes.
 * @author Rohit Pandey
 */

public class Person {

	/**
	 * Each class corresponding to a designation, as well as all data sent and received, 
	 * has a different PRIVELEGELEVEL. The class can receive/modify data only at a 
	 * PRIVILEGELEVEL equal to or lower than the class' PRIVILEGELEVEL.
	 */
	
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
	
	/**
	 * Returns credentials of the user as a String.
	 * @return Credentials
	 */

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
	
	/**
	 * Displays a list of all projects from the file filename. If the user is a Volunteer without 
	 * a project, provides the option to apply for a project.
	 * @param x
	 * @param filename
	 */
	
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
				System.out.println(i++ + ".  Name of Project :"+ p.Name + "      || Project Leader :" + p.getProjectHead());
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
					String[] delim = finalmessage.split("@@@@");
					Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).buildMessage();
					MessageHelper.SendMessage(this, mess);
					System.out.println("Project request sent....");

				}
			}
		}
	} 
	
	/**
	 * Appends the String stringtoappend to a text database in the file filename.
	 * @param filename
	 * @param stringtoappend
	 * @return 
	 */
	
	public int appendToDatabase(String filename, String stringtoappend){
		
		  try {
				File file1 = new File(filename+".txt");
				Scanner in = new Scanner(file1);
				PrintWriter f = new PrintWriter("temp"+filename+".txt");
				String op = "";
				while (in.hasNextLine()){
					op = in.nextLine();
					if (op.contains(stringtoappend)){
						continue;
					}
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
	/**
	 * Adds a string to the Event Database.
	 * @param message
	 */
	
		public void addEvents (String message){
			/*
			 *  same as above
			 */
			this.appendToDatabase("eventdatabase", message);
		}
		
		/**
		 * Adds a string to the Project Database.
		 * @param message
		 */
				
		public void addProject (String message){
			this.appendToDatabase("projectdatabase", message);
			// ANOTHER INSTANCE IN PJH CLASS
		}
		
		/**
		 * Creates a new User and adds the ID to the list of Users.
		 * @param message
		 */
				
		public void addUser (String message){
			//EXTRACTS THE STRING TO bE SAVED AND THEN APPENDS IT
			String[] splitstring = message.split("@@@@");
			if(splitstring[0] == "CREATEUSER"){
				String[] app = message.split("VEGAS@SAVEG");
				String str = app[1];
				this.appendToDatabase("userlist", str);
			}
		}
		
		/**
		 * Updates the User's information.
		 * @param message
		 */
		
		public void updateUserData(String message){
			String[] split1 = message.split("@@@@");
			String worker = split1[1];
			String vegastag = "VEGAS@SAVEG";
			String[] split2 = worker.split(vegastag);
			String oldstuff = split2[1]; 
			String newstuff = split2[2];
			Person.ReplaceInDatabase("userlist", newstuff, oldstuff);
		}
		
		/**
		 * Updates a volunteer's current project, if their project request gets accepted.
		 * @param message
		 */
		
		public void updateVolunteer(String message){
			try {
				String[] splitstring = message.split("@@@@");
				if (splitstring[0].equals("ACCEPTED")){
					String name = splitstring[1];
					String pname = splitstring[2];
					String jstring;
					Gson gson = new Gson();
					ProjectHead volunteer;
					/*
					 * 
					 */
					String op;
					File file1 = new File("userlist.txt");
					Scanner in = new Scanner(file1);
					PrintWriter f = new PrintWriter("file55.txt");
					int i = 0;
					String[] a;
					String sq = "_";
					while(in.hasNextLine()){
						op = in.nextLine();
						a = op.split("@@@@");
						jstring = a[2];
						if (a[1].equals(name)) {
							volunteer = gson.fromJson(jstring, ProjectHead.class);
							if (volunteer.ProjectName.equals(sq)){
								volunteer.setProjectName(pname);
								volunteer.hasProject = true;
								a[2] = gson.toJson(volunteer);
							}
						}
						op = a[0];
						for(int j = 1; j <a.length;j++){
							op = op + "@@@@" + a[j];
						}
						f.println(op);
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
		
		/**
		 * Adds Volunteer Project Requests to a database.
		 * @param message
		 */
		
		public void addVolunteerProjectRequests(String message){
			if (this.PRIVILEGELEVEL == 2){
				String[] splitmessage = message.split("@@@@");
				if (splitmessage[1] == this.Name){
					this.appendToDatabase("volunteerprojectrequests", message);
				}
				
			}
		}
		
		/**
		 * Asks for the number of messages required and displays that many, starting from the 
		 * latest received message.
		 */
		
		public void listMessages(){
			int input = -1;
			do {
				System.out.println("Enter the number of previous messages that you want to see (Only positive numbers allowed.). Enter 0 to exit.");
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				if (input == 0){
					System.out.println("You have pressed zero. Exiting to main screen.");
					return;
				}
				} while (input < 1);
				
			List<String> messagelist = new ArrayList<String>();
			try {
				File file = new File ("messagedatabase.txt");
				Scanner in = new Scanner(file);
				String op;
				String[] splitmessage;
				Gson q = new Gson();
				Message message;
				int x = 0;
				while(in.hasNextLine()){
					op = in.nextLine();
					splitmessage = op.split("@@@@");
					message = q.fromJson(splitmessage[2], Message.class);
					if(message.Min_Privilege_Level > this.PRIVILEGELEVEL) {
						continue;
					}
					messagelist.add(splitmessage[2]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Problem in database. Exiting.");
			}
			Gson gson = new Gson();
			Message  mess;
			String jstring;
			int size = messagelist.size();
			if (size == 0){
				System.out.println("No events to display in the List. " +
						"Please get the Database checked if you think this is an error.");
				return;
			}
			int j = 1;
			if (size >= 0 && input < size){
				for (int i = input; i>0; i--){
				jstring = messagelist.get(size-i);
				mess = gson.fromJson(jstring, Message.class);
				System.out.println(j++ + ".\tMessage from : "+ mess.From+"\n\tSent on : "+mess.Date+"\n\tMessage body :"+mess.Body+"\n");
				}
			}
			else {
				for (int i = size -1; i>=0; i--){
					jstring = messagelist.get(i);
					mess = gson.fromJson(jstring, Message.class);
					System.out.println(j++ + "\tMessage from : "+ mess.From+"\n\tSent on : "+mess.Date+"\n\tMessage body :"+mess.Body+"\n");
				}
			}
		}
		
		/**
		 * Replaces String stringtoreplace with String stringtoappend in the File filename.
		 * @param filename
		 * @param stringtoappend
		 * @param stringtoreplace
		 */
		
		public static int ReplaceInDatabase(String filename, String stringtoappend, String stringtoreplace){
			// Deletes multiple entries as it writes to a new file.
			try {
				File file1 = new File(filename+".txt");
				Scanner in = new Scanner(file1);
				PrintWriter f = new PrintWriter("temp"+filename+".txt");
				String op = "";
				while (in.hasNextLine()){
					op = in.nextLine();
					if (op.contains(stringtoreplace)) {
						continue;
					}
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
					System.out.println("Error occured while replacing String.");
				return 0;
			}
			return 1;
		}
		
		/**
		 * Accepts the number of most recent events to display, retrieves them from event 
		 * database, and displays them.
		 */
		// Method to list events
		public void listEvents(){
			int input = -1;
			do {
				System.out.println("Enter the number of latest events that you want to see (Only positive numbers allowed.). Enter 0 to exit.");
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				if (input == 0){
					System.out.println("You have pressed zero. Exiting to main screen.");
					return;
				}
				} while (input < 1);
				
			List<String> eventlist = new ArrayList<String>();
			try {
				File file = new File ("eventdatabase.txt");
				Scanner in = new Scanner(file);
				String op;
				String[] splitevent;
				int x = 0;
				while(in.hasNextLine()){
					op = in.nextLine();
					splitevent = op.split("@@@@");
					eventlist.add(splitevent[3]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Problem in database. Exiting.");
			}
			Gson gson = new Gson();
			Event event;
			String jstring;
			int size = eventlist.size();
			if (size == 0){
				System.out.println("No events to display in the List. " +
						"Please get the Database checked if you think this is an error.");
				return;
			}
			int j = 1;
			if (size > 0 && input < size){
				for (int i = input; i>0; i--){
				jstring = eventlist.get(size-i);
				event = gson.fromJson(jstring, Event.class);
				System.out.println(j++ + ".\t Event Name : "+event.Name+"\n\t To be held on "+event.Date+" at : "+event.Location+"\n\t Organised by : "+event.getOrganiser()+". Contact ---> "+event.getContactNumber()+"\n");
				}
			}
			else {
				for (int i = size -1; i>=0; i--){
					jstring = eventlist.get(i);
					event = gson.fromJson(jstring, Event.class);
					System.out.println(j++ + ".\t Event Name : "+event.Name+"\n\tTo be held on "+event.Date+" at : "+event.Location+"\n\tOrganised by : "+event.getOrganiser()+". Contact ---> "+event.getContactNumber());
				}
			}
		}
}
