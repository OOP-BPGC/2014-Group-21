package Base_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;

import Base_Classes.Project;

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
	
	public void ModifyMember() {
	  }

	  public void CreateEvent() {
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
		  in.close();
		   //This method needs to be written
		  String finalmessage = event.encodeEvent();
		  int y = 42;
		  if(finalmessage == null){
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  else{
			  y = event.addEventToDatabases(this.desig, finalmessage);
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

	  public void CreateProject() {
		  Scanner sc = new Scanner(System.in);
		  System.out.println("Please enter the name of the project");
		  String name = sc.nextLine();
		  System.out.println("Please enter the Days for project");
		  String schedule = sc.nextLine();
//		  System.out.printf("Do you want to assign Project Head? (Y for yes and N for No) : ");
		  System.out.printf("Enter the name of the Project Manager");
		  String proj_head; // Need to check with the database
		  proj_head = sc.nextLine();
/*		  while (1 == 1){
			  char dec = sc.next().trim().charAt(0);
			  if (dec == 'Y' || dec == 'y'){
				  System.out.printf("Enter the name of the Project Head : ");
				  proj_head = sc.nextLine();
				  break;
			  }
			  else if (dec == 'n' || dec =='N'){
				  System.out.printf("Name of Poject Head not entered");
				  proj_head = "";
				  break;
			  }
			  System.out.println("Please enter name only in the correct format now");
		  }
*/		  
		  int numvolunteers;
		  while(1==1){
			  System.out.printf("Enter the number of volunteers (min = 1, max = 10 :");
			  numvolunteers = sc.nextInt();
			  if (numvolunteers < 10 && numvolunteers > 0){
				  break;
			  }
		  }   // RIGHT NOW, ONLY METHOD TO KEEP THE NUMBER OF VOLUNTEERS HAS BEEN MADE. CHECK ON NAMES AS WELL.
		  System.out.printf("Type in the first event name : ");
		  String eventName = sc.nextLine();
		  Project proj = new Project(name,schedule,proj_head,numvolunteers,eventName);
		  String finalmessage = proj.encodeProject();
		  int y = 42;
		  if(finalmessage == null){
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  else{
			  y = proj.addProjectToDatabase(this.desig,finalmessage);
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

	  public void CreateMessage() {
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
		  do {
			  System.out.printf("Enter the minimum privilege level.\n\t3 --> CORE\n\t 2-->PROJECT_HEAD\n\t1 --> VOLUNTEERS");
			  i = sc.nextInt();
			  
		  } while(i!=1 || i!=2 || i!=3);
		  
		  m.setMin_Privilege_Level(i);
		  String finalmessage = m.encodeMsg(m);
		  int y = 42;
		  if(finalmessage == null){
			  System.out.println("SOME ERROR OCCURED, TRY AGAIN");
			  return;
		  }
		  else{
			  y = m.addMessageToDatabase(this.desig,finalmessage); //Returns 1
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
	/*  
	  public void  manageEvent(){
		  System.out.println("Enter the name of the event that you want to manage.");
		  Scanner sc = new Scanner(System.in);
		  String str = sc.nextLine();
		  File file1 = new File("coreeventlist.txt");
		  Scanner scan2 = new Scanner(file1);
		  String stringtemp = "";
		  int flag = 0;
		  PrintWriter f = new PrintWriter("tempcoreeventlist.txt");
		  while(scan2.hasNextLine()){
			  stringtemp = scan2.nextLine();
			  String[] splitstream = stringtemp.split("@@@@");
			  if (splitstream[3].contains(str)){
				  
				  flag = 1;
			  }
		  }
		  
	  }
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

	public void BroadcastMessage() {
	
	}

	public void CreateUser() {
		// TODO to create a new user
		String newmessage;
		Scanner scan = new Scanner(System.in);
		Gson gson = new Gson();
		String JString, type;
		String stringinput;
		System.out.println("Enter the type of user: \n1. Volunteer \n2. Project Head\nPress any other number to EXIT");
		int intinput = scan.nextInt();
		if (intinput != 1 && intinput != 2){
			System.out.println("Exiting now...\n");
			return;
		}
		else {
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
			else{
				ProjectHead a = new ProjectHead(name, ID, Designation.PROJECT_HEAD,phNo,"_",cred);
				JString = gson.toJson(a);
				type = "PH";
			}
			newmessage = "PERSONFILE@@@@" + name + "@@@@" + JString + "@@@@" + type;
		}
		// NOW WE SEARCH FOR DUPLICATE ELEMENTS
		int flag;
		try {
			File file = new File("userlist.txt");
			Scanner checker = new Scanner(file);
			flag = 0;
			while (checker.hasNextLine()){
				if (checker.nextLine() == newmessage){
					flag++;
				}
			}
			checker.close();
		} catch (FileNotFoundException e) {
			System.out.println("Some problem with the userlist occured. Exiting.");
			return;
		}
		if (flag != 0){
			System.out.println("Duplicate entry found in userlist. Cannot create the member. Exiting...");
			return;
		}
		else {
			System.out.println("No matches found in userlist. Creating new user....");
			newmessage = "CREATEUSER@@@@" + "VEGAS@SAVEG" + newmessage;
		}
		/*
		 * PASS THIS TO GET BROADCASTED
		 */
	}

	

}