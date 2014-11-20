package driver_file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
					String splits[] = op.split("@@@@");
					if (splits[1].equals(name)){
						System.out.println("Name found. Searching for credentials...");
						String searcher = "\"Credentials\":[\"" + usrnm + "\",\"" +pswrd+ "\"]";
						if (splits[2].contains(searcher)){
							type = splits[3];
							JString = splits[2];
							System.out.println("Credential match found. Loading user details...");
							flag = 1;
							break;
						}
						else{
							System.out.println("Credential match not found.");
						}
					}
					else {
						System.out.println("Name match not found.");
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
			System.out.println("Please enter the correct details. Tries left : "+ tryleft);
		} while (tries < 4);
		/*
		 * LOOP EXITED, DATA TAKEN
		 * 
		 * NOW WE CHECK FOR VALID DATA
		 */
		if (JString == "" || type == "" || flag != 1 ){
			System.out.println("No matches found... Exiting Software.");
			return;
		}
		
		if (type == "CORE") {
			Core a = gson.fromJson(JString, Core.class);			
			CoreMenu(a);
		}
		else if (type == "PH") {
			ProjectHead a = gson.fromJson(JString, ProjectHead.class);		
			PHMenu(a);
		}
		else if (type == "VOL"){
			Volunteer a = gson.fromJson(JString, Volunteer.class);		
			VolunteerMenu(a);
		}
		
		else{
			System.out.println("Some problem occured with the software. Please" +
					" get the installation and database files checked.");
		}
		return;
	}
	
	public static void VolunteerMenu(Volunteer volunteer) {
		boolean loop = true;
		scan = new Scanner(System.in);
		int choice;
		if (volunteer.hasProject == false) {
			while(loop) {
				System.out.println("\n1. List Projects\n2. Exit");
				choice = scan.nextInt();
				switch (choice) {
					case 1: volunteer.getProjects(volunteer.desig, "projectlist"); break;  //DONE
					case 2: loop = false; break;
					default: System.out.println("Invalid Selection.");
				}
			}
		}
		else {
			while(loop) {
				System.out.println("\n1. List Projects\n2. Current Project Details\n3. Exit");
				choice = scan.nextInt();
				switch (choice) {
					case 1: volunteer.getProjects(volunteer.desig, "projectlist"); break; 
					case 2: volunteer.getCurrentProject(); break;	
					case 3: loop = false; break;
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
			System.out.println("\n1.Approve Volunteer Request(s)\n2. List Projects\n3. Current Project Details\n4. Manage Project\n5. Schedule Event\n6. Exit");
			choice = scan.nextInt();
			switch (choice) {
				case 1: projectHead.VolunteerProjectRequest(); break;
				case 2: projectHead.getProjects(projectHead.desig, "projectlist"); break; //DONE
				case 3: projectHead.getProjectName(); break; //Need details apart from name
				case 4: projectHead.ManageProject(); break;
				case 5: projectHead.ManageEvent(); break;
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
			System.out.println("\n1. List Projects\n2. Create Project\n3. Create a new Project\n4. Schedule Event\n5. Create Member\n 6. Exit");
			choice = scan.nextInt();
			switch (choice) {
				case 1: core.getProjects(); break; //DONE
				case 2: core.CreateProject(); break; // DONE
				case 3: core.CreateMessage(); break; // DONE
				case 4: core.CreateEvent(); break;  // DONE
				case 5: core.CreateUser(); break;  //DONE
				case 6:  loop = false; break; 
				default: System.out.println("Invalid Selection.");
			}
		}
		}
}