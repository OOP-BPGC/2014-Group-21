package Base_Classes;

import java.io.File;
import javax.crypto.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import tests.MessageBuilder;
import com.google.gson.Gson;

import driver_file.Driver;

import Base_Classes.Project;

/**
 *  An extension of the class Person, implements all the use cases available to a Core member.  
 *  Exhaustive list: Retrieve a list of projects, modify details of a member, create a new
 *  event of project, create and broadcast a message, and create a new user.
 *  @author Rohit Pandey
 */

public class Core extends Person {


	  public static final Designation desig = Designation.CORE;
	  public int NumberOfMembers;
	  
	  public final int PRIVILEGELEVEL = 3;
	 
	public int getPRIVILEGELEVEL() {
		return PRIVILEGELEVEL;
	}

	public Core(String name, String iDNumber, Designation designation,
			String phoneNumber, int numberOfMembers,String[] credentials) {
		super(name, iDNumber, designation, phoneNumber,credentials );
		NumberOfMembers = numberOfMembers;
	}

	public Core() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Generates a list of projects in progress from the project database.
	 */
	
	public void getProjectsTest(String s) {
		File file1 = new File(s);
		Scanner scan;
		try {
			scan = new Scanner(file1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Some error happened in reading the database...Exiting.");
			return;
		}
		String copied = "";
		String pname = "";
		String phead = "";
		String[] splitstrings;
		int i =1;
		System.out.println("\t  Project Name\t\tProject Head");
		while (scan.hasNextLine()){
			copied = scan.nextLine();
			splitstrings = copied.split("@@@@");
			pname = splitstrings[1];
			phead = splitstrings[2];
			System.out.println("\t"+(i++)+". "+pname+"\t    "+phead);
		}
		scan.close();
		return;
	}
	
	/** 
	 * Displays a list of projects in the File projectdatabase.
	 */
	public void getProjects() {
		File file1 = new File("projectdatabase.txt");
		Scanner scan;
		try {
			scan = new Scanner(file1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Some error happened in reading the database...Exiting.");
			return;
		}
		String copied = "";
		String pname = "";
		String phead = "";
		String[] splitstrings;
		int i =1;	
		while (scan.hasNextLine()){
			if (i == 1){
				System.out.println("===================================================================");
				System.out.println("\t  Project Name\t\tProject Head");
			}
			copied = scan.nextLine();
			splitstrings = copied.split("@@@@");
			pname = splitstrings[1];
			phead = splitstrings[2];
			System.out.println("\t"+(i++)+". "+pname+"\t            "+phead);
		}
		scan.close();
		System.out.println("===================================================================");
		return;
	}
	
	/**
	 * Changes the details of a member.
	 */
	
	public void ModifyMember() {
	  }

	/**
	 * Creates a new event.
	 */
	
	  @SuppressWarnings("static-access")
	public void CreateEvent() {
		  System.out.println("\n==================================================================");
		  System.out.println("Create Event Screen. Enter the following details... \n\n");
		  Scanner in = new Scanner(System.in);
		  Event event = new Event();
		  System.out.println("Enter event name: ");
		  event.setName(in.nextLine());
		  event.setContactNumber(this.PhoneNumber);
		  System.out.println("Enter event date: ");  
		  event.setDate(in.nextLine());
		  System.out.println("Enter event location: ");
		  event.setLocation(in.nextLine());
		  event.setOrganiser(this.Name);
//		  in.close();
		   //This method needs to be written
		  String finalmessage = event.encodeEvent();
		  int y = 42;
		  if(finalmessage == null){
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  else{
			  //pass finalmessage to message sender class
			  String[] delim = finalmessage.split("@@@@");
			  Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).from(this.getCredentials()[0]).buildMessage();
			  y = MessageHelper.SendMessage(this, mess);
		  }
		  if (y==1){
			  y = event.addEventToDatabases(this.desig, finalmessage);
			  System.out.println("Message has been sent");
		  }
		  else {
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  System.out.println("\n==================================================================\n");
	  }
	  
	  /**
	   * Creates a new project.
	   */

	  public void CreateProject() {
		  System.out.println("\n\n==================================================================");
		  System.out.println("Create Project screen. Enter the following details... \n");
		  Scanner sc = new Scanner(System.in);
		  System.out.printf("Please enter the name of the project : ");
		  String name = sc.nextLine();
		  System.out.printf("Please enter the days for project : ");
		  String schedule = sc.nextLine();
//		  System.out.printf("Do you want to assign Project Head? (Y for yes and N for No) : ");
		  System.out.printf("Enter the name of the Project Manager : ");
		  String proj_head; // Need to check with the database
		  proj_head = sc.nextLine();		  
		  int numvolunteers;
		  while(true){
			  System.out.printf("Enter the number of volunteers (min = 1, max = 10 : ");
			  numvolunteers = sc.nextInt();
			  if (numvolunteers < 10 && numvolunteers > 0){
				  break;
			  }
		  }   // RIGHT NOW, ONLY METHOD TO KEEP THE NUMBER OF VOLUNTEERS HAS BEEN MADE. CHECK ON NAMES AS WELL.
		  sc.nextLine();
		  System.out.printf("Type in the first event name : ");
		  String eventName = sc.nextLine();
//		  sc.close();
		  Project proj = new Project(name,schedule,proj_head,numvolunteers,eventName);
		  String finalmessage = proj.encodeProject();
		  int y = 42;
		  if(finalmessage == null){
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  else{
			  String[] delim = finalmessage.split("@@@@");
			  Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).from(this.getCredentials()[0]).buildMessage();
			  y = MessageHelper.SendMessage(this, mess);
			  
		  }
		  if (y==1){
			  y = proj.addProjectToDatabase(this.desig,finalmessage);
			  //pass finalmessage to message sender class
			  System.out.println("Message has been sent");
		  }
		  else {
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
		  }
		  System.out.println("\n==================================================================\n");
	  }

 //REMOVE AFTER UNIT TESTING
	  public void CreateMessageTest(String s, int i) {
		  Message m = new Message();
		  Scanner sc = new Scanner(System.in);
		  
		  
		  m.setBody(s);
/*		  System.out.println("Enter the reciepients (Seperated by a comma)");
		  s = sc.nextLine();
		  String[] s1 = s.split(",");
		  m.setTo(s1);
*/		 
		  m.setFrom(this.Name);
		  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  Date date = new Date();
		  m.setDate(dateFormat.format(date).toString());
		  
		  	  
		  m.setMin_Privilege_Level(i);
		  String finalmessage = m.encodeMsg(m);
		  int y = 42;
		  if(finalmessage == null){
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  else{
			  y = m.addMessageToDatabaseTEST(this.desig,finalmessage); //Returns 1
		  }
		  if (y==1){
			  //pass finalmessage to message sender class
			  System.out.println("Message has been sent");
		  }
		  else {
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
	  }
	  
	  /**
	   * Creates and sends a new message.
	   */
	  
	  public void CreateMessage() {
		  System.out.println("\n\n==================================================================");
		  System.out.println("Create Message screen. Enter the following details... \n\n");
		  Message m = new Message();
		  Scanner sc = new Scanner(System.in);
		  String s;
		  System.out.println("Enter the Message");
		  s = sc.nextLine();
		  m.setBody(s);
/*		  System.out.println("Enter the reciepients (Seperated by a comma)");
		  s = sc.nextLine();
		  String[] s1 = s.split(",");
		  m.setTo(s1);
*/		 
		  m.setFrom(this.Name);
		  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  Date date = new Date();
		  m.setDate(dateFormat.format(date).toString());
		  int i=0;
		  System.out.println("Enter the minimum privilege level.\n\t3 --> CORE\n\t2 --> PROJECT_HEAD\n\t1 --> VOLUNTEERS\n");
		  i = Integer.parseInt(sc.nextLine());
		  if (i<1 || i>3) 
			  i =1;
		  m.setMin_Privilege_Level(i);
		  String finalmessage = m.encodeMsg(m);
		  int y = 42;
		  if(finalmessage == null){
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  else{
			  String[] delim = finalmessage.split("@@@@");
			  Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).from(this.getCredentials()[0]).buildMessage();
			  y = MessageHelper.SendMessage(this, mess);
		  }
		  if (y==1){
			  y = m.addMessageToDatabase(this.desig,finalmessage); //Returns 1
			  System.out.println("Message has been sent");
		  }
		  else {
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
		  }
		  System.out.println("\n\n==================================================================\n");

	  }

		/**
		 * Adds a string to the message database, provided the user has the appropriate 
		 * PRIVILEGELEVEL.
		 * @param message
		 */
	  
	public void addMessages (String message){
		/*
		 * MIGHT WANT TO KEEP THIS AS AN INT
		 */
		String [] splitmessage = message.split("@@@@");
		if ( Integer.valueOf(splitmessage[3]) <= this.PRIVILEGELEVEL){
			this.appendToDatabase("messagedatabase", message);	
		}
	}
	
	/**
	 * Broadcasts a message(?).
	 */
	
	public void BroadcastMessage() {
	
	}

	/**
	 * Creates a new user, adds it to the list of users, and broadcasts their existence to 
	 * everyone else.
	 */
	
	public void CreateUser() {
		// TODO to create a new user
		System.out.println("\n\n==================================================================");
		System.out.println("Create User screen. Enter the following details... ");
		String newmessage;
		Scanner scan = new Scanner(System.in);
		Gson gson = new Gson();
		String JString = "", type = "";
		String stringinput;
		System.out.println("Enter the type of user: \n1. Volunteer \n2. Project Head\nPress any other number to EXIT");
		int intinput = scan.nextInt(); scan.nextLine();
		if (intinput != 1 && intinput != 2){
			System.out.println("Exiting now...\n");
			return;
		}
		else {
			if (intinput == 1) System.out.println("\nCreating a Volunteer.\n");
			else System.out.println("\nCreating a Project Head.\n");
			System.out.println("Enter the name : ");
			String name = scan.nextLine();
			System.out.println("Enter the ID Number : ");
			String ID = scan.nextLine();
			System.out.println("Enter the Phone Number : ");
			String phNo = scan.nextLine();
			System.out.println("Enter the username : ");
			String usrnm = scan.nextLine();
			System.out.println("Enter the password : ");
			String pswrd = scan.nextLine();
			
			String[] cred = new String[]{usrnm,pswrd};
			if (intinput == 1){ 
				Volunteer a = new Volunteer(name, ID, Designation.VOLUNTEER, phNo, "_", cred);
				JString = gson.toJson(a);
				type = "VOL";
			}
			else if (intinput == 2){
				ProjectHead a = new ProjectHead(name, ID, Designation.PROJECT_HEAD,phNo,"_",cred);
				JString = gson.toJson(a);
				type = "PH";
			}
			newmessage = "PERSONFILE@@@@" + name + "@@@@" + JString + "@@@@" + type;
		}
		// NOW WE SEARCH FOR DUPLICATE ELEMENTS
		int flag;
//		scan.close();
		try {
			File file = new File("userlist.txt");
			Scanner checker = new Scanner(file);
			flag = 0;
			while (checker.hasNextLine()){
				if (checker.nextLine().contains(newmessage)){
					flag++;
				}
			}
			checker.close();
		} catch (FileNotFoundException e) {
			System.out.println("Some problem with the userlist occured. Exiting.");
			return;
		}
		String finalmessage = null;
		if (flag != 0){
			System.out.println("Duplicate entry found in userlist. Cannot create the member. Exiting...");
			return;
		}
		else {
			System.out.println("No matches found in userlist. Creating new user....");
			finalmessage = "CREATEUSER@@@@" + "VEGAS@SAVEG" + newmessage;
		}
		/*
		 * PASS THIS TO GET BROADCASTED. Making a message object.
		 */
		  int y;
		  String[] delim = finalmessage.split("@@@@");
		  Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).from(this.getCredentials()[0]).buildMessage();
		  y = MessageHelper.SendMessage(this, mess);
		  if (y == 1){
			  this.appendToDatabase("userlist", newmessage);
			  System.out.println("User has been created. Database has been updated.");
		  }
		  else{
			  System.out.println("User could not be created. Please check the system and try again.");
 
		  }
		  System.out.println("\n\n==================================================================\n");
	}

	

}