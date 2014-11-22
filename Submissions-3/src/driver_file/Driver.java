package driver_file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tests.MessageBuilder;
import Base_Classes.*;

import com.google.gson.Gson;

public class Driver {
	
	static boolean hasProject;
	static Scanner scan;
	static Designation desig;
	
	public static void main(String[] args) {							
		Gson gson = new Gson();											
		String JString = "";
		String type = ""; // THE TYPE WILL BE "CORE" for core, "PH" for projecthead, and "VOL" for volunteer 
		int flag = 0;
		int tries = 1;
		int flagcred = 0;
		
		String oldDataFile = null;
		do {
			Scanner inputs = new Scanner(System.in);
			String name = ""; String usrnm = ""; String pswrd = "";
			System.out.println("Enter your name (Full Name, case sensitive) :");
			name = inputs.nextLine();
			System.out.println("Enter your username (case sensitive) :");
			usrnm = inputs.nextLine();
			System.out.println("Enter your password (case sensitive) :");
			pswrd = inputs.nextLine();
			/*
			 * TO CHECK THE USERLIST IF THE DETAILS EXIST OR NOT
			 */
			File file1 = new File("userlist.txt");
			try {
				Scanner in = new Scanner(file1);
				String op = "";
				while (in.hasNextLine()){
					op = in.nextLine();
					oldDataFile = op;
					String splits[] = op.split("@@@@");
					if (splits[1].equals(name)){
						String searcher = "\"Credentials\":[\"" + usrnm + "\",\"" +pswrd+ "\"]";
						if (splits[2].contains(searcher)){
							type = splits[3];
							JString = splits[2];
							System.out.println("Name and credential match found. Loading user details...");
							flag = 1;
							break;
						}
					}
				}
				in.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error occured while checking for data. Please check the database");
				return;
			}
			/*
			 * 
			 */
			if (flag == 1){
				break;
			}
			int tryleft = 3 - (tries++);
			if(tryleft > 0){
			System.out.println("Please enter the correct details. Tries left : "+ tryleft);
			}
		} while (tries < 4);
		/*
		 * LOOP EXITED, DATA TAKEN
		 * 
		 * NOW WE CHECK FOR VALID DATA
		 */
		if (JString == "" || type == "" || flag != 1 ){
			System.out.println("No matches found... Exiting...");
			return;
		}
		
		if (type.contains("CORE")) {
			Core a = gson.fromJson(JString, Core.class);	
			CoreMenu(a);
			String newDataFile = getNewData(oldDataFile, a);
//			String finalmessage = getNewData(oldDataFile, a);
			String vegastag = "VEGAS@SAVEG";
			String finalmessage = "MODUSER@@@@" + vegastag + oldDataFile + vegastag + newDataFile + vegastag;
			String[] delim = finalmessage.split("@@@@");
			Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).buildMessage();
			MessageHelper.SendMessage(a, mess);
			Person.ReplaceInDatabase("userlist", newDataFile, oldDataFile);
		}
		else if (type.contains("PH")) {
			ProjectHead a = gson.fromJson(JString, ProjectHead.class);		
			PHMenu(a);
			String newDataFile = getNewData(oldDataFile, a);
//			String finalmessage = getNewData(oldDataFile, a);
			String vegastag = "VEGAS@SAVEG";
			String finalmessage = "MODUSER@@@@" + vegastag + oldDataFile + vegastag + newDataFile + vegastag;
			String[] delim = finalmessage.split("@@@@");
			Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).buildMessage();
			MessageHelper.SendMessage(a, mess);
			Person.ReplaceInDatabase("userlist", newDataFile, oldDataFile);
		}
		else if (type.contains("VOL")){
			Volunteer a = gson.fromJson(JString, Volunteer.class);		
			VolunteerMenu(a);
			String newDataFile = getNewData(oldDataFile, a);
//			String finalmessage = getNewData(oldDataFile, a);
			String vegastag = "VEGAS@SAVEG";
			String finalmessage = "MODUSER@@@@" + vegastag + oldDataFile + vegastag + newDataFile + vegastag;
			String[] delim = finalmessage.split("@@@@");
			Message mess = new MessageBuilder().tag(delim[0]).body(finalmessage).buildMessage();
			MessageHelper.SendMessage(a, mess);
			Person.ReplaceInDatabase("userlist", newDataFile, oldDataFile);
		}
		
		else{
			System.out.println("Some problem occured with the software. Please" +
					" get the installation and database files checked.");
			return;
		}
		return;
	}
	
	public static void VolunteerMenu(Volunteer volunteer) {
		boolean loop = true;
		scan = new Scanner(System.in);
		int choice;
		if (volunteer.hasProject == false) {
			while(loop) {
				System.out.println("\n1. List Projects\n2. Check Messages \n3. Check Events\n4. Exit");
				choice = scan.nextInt();
				switch (choice) {
					case 1: volunteer.getProjects(volunteer.desig, "projectlist"); break;  //DONE
					case 2: volunteer.listMessages(); break;  //DONE
					case 3: volunteer.listEvents(); break; //DONE  listEvents is in Person class
					case 4: loop = false; break;
					default: System.out.println("Invalid Selection.");
				}
			}
		}
		else {
			while(loop) {
				System.out.println("\n1. List Projects\n2. Current Project Details\n3. Check Messages \n4. Check Events\n5. Exit");
				choice = scan.nextInt();
				switch (choice) {
					case 1: volunteer.getProjects(volunteer.desig, "projectlist"); break;  //DONE
					case 2: System.out.println("Your current project is : "+volunteer.getCurrentProject()); 
							break;
					case 3: volunteer.listMessages(); break;  //DONE
					case 4: volunteer.listEvents(); break; //DONE  listEvents is in Person class
					case 5: loop = false; break;
					default: System.out.println("Invalid Selection.");
				}
			}
		}
	}
	
	public static void PHMenu(ProjectHead projectHead) {
		boolean loop = true;
		scan = new Scanner(System.in);
		int choice;
		while(loop) {
			System.out.println("\n1.Approve Volunteer Request(s)\n2. List Projects\n3. Current Project Details\n4. Check Messages \n5. Check Events\n6. Exit");
			choice = scan.nextInt();
			switch (choice) {
				case 1: projectHead.VolunteerProjectRequest(); break;
				case 2: projectHead.getProjects(projectHead.desig, "projectlist"); break; //DONE
				case 3: projectHead.getProjectName(); break; //Need details apart from name
				case 4: projectHead.listMessages(); break;
				case 5: projectHead.listEvents(); break; //DONE  listEvents is in Person class
				case 6: loop = false; break;
				default: System.out.println("Invalid Selection.");
			}
		}
	}

	public static void CoreMenu(Core core) {
		boolean loop = true;
		scan = new Scanner(System.in);
		int choice;
		while(loop) {
			System.out.println("\n1. List Projects\n2. Create Project\n3. Create a new Project\n4. Schedule Event\n5. Create Member\n6. Check Messages \n7. Check Events\n8. Exit");
			choice = scan.nextInt();
			switch (choice) {
				case 1: core.getProjects(); break; //DONE
				case 2: core.CreateProject(); break; // DONE
				case 3: core.CreateMessage(); break; // DONE
				case 4: core.CreateEvent(); break;  // DONE
				case 5: core.CreateUser(); break;  //DONE
				case 6: core.listMessages(); break;  //DONE
				case 7: core.listEvents(); break;   //DONE listEvents is in Person Class
				case 8: loop = false; break; 
				default: System.out.println("Invalid Selection.");
			}
		}
	}
	public static String getNewData(String oldDataFile, Volunteer a){
		/*
		 *  To save the serdata into memory and to broadcast it so that others' data can be updated.
		 */
		if (oldDataFile == null) return null;
		Gson gson2 = new Gson();
		String newJson = gson2.toJson(a);
		String [] newSplit = oldDataFile.split("@@@@");
		String newDataFile = newSplit[0]+"@@@@"+newSplit[1]+"@@@@"+newJson+"@@@@"+newSplit[3];
		return newDataFile;
		
	}
	
	public static String getNewData(String oldDataFile, ProjectHead a){
		/*
		 *  To save the serdata into memory and to broadcast it so that others' data can be updated.
		 */
		if (oldDataFile == null) return null;
		Gson gson2 = new Gson();
		String newJson = gson2.toJson(a);
		String [] newSplit = oldDataFile.split("@@@@");
		String newDataFile = newSplit[0]+"@@@@"+newSplit[1]+"@@@@"+newJson+"@@@@"+newSplit[3];
		return newDataFile;
		
	}
	
	public static String getNewData(String oldDataFile, Core a){
		/*
		 *  To save the serdata into memory and to broadcast it so that others' data can be updated.
		 */
		if (oldDataFile == null) return null;
		Gson gson2 = new Gson();
		String newJson = gson2.toJson(a);
		String [] newSplit = oldDataFile.split("@@@@");
		String newDataFile = newSplit[0]+"@@@@"+newSplit[1]+"@@@@"+newJson+"@@@@"+newSplit[3];
		return newDataFile;
		
	}
}
