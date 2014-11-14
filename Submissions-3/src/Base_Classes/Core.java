package Base_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import Base_Classes.Project;

public class Core extends Person {


	  public final Designation desig = Designation.CORE;
	  public int NumberOfMembers;

	  public void ModifyMember() {
	  }

	  public void getProjects() {
			File file1 = new File("coreprojectdatabase.txt");
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
	  
	  public void CreateEvent() {
		  Scanner in = new Scanner(System.in);
		  Event event = new Event();
		  System.out.println("Enter event name: ");
		  event.setName(in.nextLine());
		  event.setContactNumber(Integer.toString(this.PhoneNumber));
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
			  y = event.addEventToDatabases(this.desig,finalmessage);
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
		  String proj_head;
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
		  while(true){
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
			  y = m.addMessageToDatabase(this.desig, finalmessage); //Returns 1
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
	  public void BroadcastMessage() {
	  }
	  
	  public int appendToDatabase(String filename, String stringtoappend){
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
				File file2 = new File("tempcoreprojlist.txt");
				file1.delete();
				file2.renameTo(file1);
			} catch (FileNotFoundException e) {
				return 0;
			}
			return 1;
	  }
	  

	

}