package Base_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ProjectHead extends Person {

	  public String ProjectName;
//	  public int projectCode;
	  public final Designation desig = Designation.PROJECT_HEAD;
	  public final int PRIVILEGELEVEL = 2;
	  public static boolean hasProject = false;
	  	
	  
	public ProjectHead() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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
//		this.projectCode = projectCode;
	}
	
	public void handleProjectRequests(){
		/*
		 * Scan files, respond according to input, then empty the contents of the file.
		 * 
		 */
		try {
			File file1 = new File("volunteerprojectrequests.txt");
			Scanner in = new Scanner(file1);
			String op = "";
			String[] splitmessage;
			Scanner scan1 = new Scanner(System.in);
			Project p; Gson gson = new Gson();
			String a;
			String message;
			while (in.hasNextLine()){
				op = in.nextLine();
				splitmessage = op.split("@@@@");
				p = gson.fromJson(splitmessage[3], Project.class);
				System.out.println("Request for Project : "+ p.Name + "From : "+ splitmessage[4]+". Y to accept, Anything else to reject");
				a = scan1.nextLine();
				if (a == "Y"){
					System.out.println("Project from " + splitmessage[4] + " accepted.");
					message = "ACCEPTED@@@@" + splitmessage[4] + "@@@@" + p.Name;
					/*
					 * Message sent to the sender class
					 */
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
	
	public void addProject (String message){
		this.appendToDatabase("projectdatabase", message);
		if (!this.hasProject){
			String[] splitstring = message.split("@@@@");
			if (this.getName() == splitstring[1]){
				this.setProjectName(splitstring[2]);
			}
		}
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public void VolunteerProjectRequest() {
	  }

	  public void ManageProject() {
	  }

	  public void CreateAndBroadcastMessage() {
	  }

	  public void ManageEvent() {
	  }

	}