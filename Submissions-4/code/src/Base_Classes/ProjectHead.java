package Base_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import tests.MessageBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 *  An extension of the class Person, implements all the use cases available to, well, a Project 
 *  Head.  Exhaustive list: Handling project requests from volunteers, managing projects, managing
 *  events, and broadcasting messages.
 *  @author Rohit Pandey
 */

public class ProjectHead extends Person {

	  public String ProjectName;
//	  public int projectCode;
	  public final Designation desig = Designation.PROJECT_HEAD;
	  /**
	   * Each class corresponding to a designation, as well as all data sent and received, 
	   * has a different PRIVELEGELEVEL. The class can receive/modify data only at a 
	   * PRIVILEGELEVEL equal to or lower than the class' PRIVILEGELEVEL.
	   */
	  public final int PRIVILEGELEVEL = 2;
	  public boolean hasProject;
	  	
	  
	public ProjectHead() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public ProjectHead(String name, String iDNumber, Designation designation,
			String phoneNumber, String projectName, String[] credentials) {
		super(name, iDNumber, designation, phoneNumber,credentials);
		ProjectName = projectName;
		this.hasProject = false;
//		this.projectCode = projectCode;
	}
	
	/**
	 * Reads through a file of project requests from volunteers and provides an option to accept 
	 * or reject the requests. If a request is accepted, a message is sent to the volunteer that 
	 * submitted the request.
	 */
	
	
	public void handleProjectRequests(){
		/*
		 * Scan files, respond according to input, then empty the contents of the file.
		 * 
		 */
		try {
			System.out.println("\n=======================================================================\n");
			System.out.println("Project Request screen. Please follow the instructions.");
			File file1 = new File("volunteerprojectrequests.txt");
			Scanner in = new Scanner(file1);
			String op = "";
			String[] splitmessage;
			Scanner scan1 = new Scanner(System.in);
			Project p; Gson gson = new Gson();
			String a;
			String finalmessage;
			while (in.hasNextLine()){
				op = in.nextLine();
				splitmessage = op.split("@@@@");
				p = gson.fromJson(splitmessage[3], Project.class);
				System.out.println("Request for Project : "+ p.Name + "From : "+ splitmessage[4]+". Y to accept, Anything else to reject");
				a = scan1.nextLine();
				if (a == "Y"){
					System.out.println("Project from " + splitmessage[4] + " accepted.");
					finalmessage = "ACCEPTED@@@@" + splitmessage[4] + "@@@@" + p.Name;
					/*
					 * Message sent to the sender class
					 */
					String[] delim = finalmessage.split("@@@@");
					Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).buildMessage();
					MessageHelper.SendMessage(this, mess);
					System.out.println("Message has been sent");
				}
				else{
					System.out.println("Project from " + splitmessage[4] + " rejected.");

				}
			}
			in.close();
			file1.delete();
			file1.createNewFile();
		} catch (JsonSyntaxException e) {
			System.out.println("Some error occured when processing files.");
		} catch (FileNotFoundException e) {
			System.out.println("Some error occured when opening files.");
		} catch (IOException e) {
			System.out.println("Some error occured when creating files.");
		}
	}
	
	/**
	 * Adds a string to the Project Database. If the user is not associated with any project, 
	 * sets this as their current project.
	 * @param message
	 */
	
	public void addProject (String message){
		this.appendToDatabase("projectdatabase", message);
		if (!this.hasProject){
			String[] splitstring = message.split("@@@@");
			if (splitstring[1].equals(this.Name)){
				this.setProjectName(splitstring[2]);
				this.hasProject = true;
				this.appendToDatabase("myproject", message);
			}
		}
	}

	/**
	 * Returns the name of the Project associated with the user as a String.
	 * @return ProjectName
	 */
	
	public String getProjectName() {
		return ProjectName;
	}

	/**
	 * Sets a Project as the project associated with the user.
	 * @param projectName
	 */
	
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	/**
	 * Modifies details of the associated project.
	 */
	
	
	public void ManageProject() {
	}
	  
	  /**
	   * Creates and...broadcasts a...message.
	   */

	public void CreateAndBroadcastMessage() {
	}

	  /**
	   * Modifies details of an Event.
	   */
	  
	public void ManageEvent() {
	}

}