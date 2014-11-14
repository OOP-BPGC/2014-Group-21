package driver_file;

import java.util.Scanner;
import Base_Classes.*;
import com.google.gson.Gson;

public class Driver {
	
	static boolean hasProject;
	static Scanner scan;
	static Designation desig;
	
	public static void main(String[] args) {							
		Gson gson = new Gson();											
		String JString = null;
	/*
	 * Need to use Json to read designation and initialize the appropriate class
	 * We haven't written the method to retrieve the data from a userlist.txt file that will be stored on every
	 * system. For now, JString has been given the value null, but it will retrieve a json string from the stored list.
	 *  													
	 */
		Person per = gson.fromJson(JString, Person.class);				
		Designation d = per.getDesignation();
	/*
	 * 
	 */
		per = null;
		
		if (d == Designation.CORE) {
			Core a = gson.fromJson(JString, Core.class);			
			CoreMenu(a);
		}
		else if (d == Designation.PROJECT_HEAD) {
			ProjectHead a = gson.fromJson(JString, ProjectHead.class);		
			PHMenu(a);
		}
		else {
			Volunteer a = gson.fromJson(JString, Volunteer.class);		
			VolunteerMenu(a);
			
		}
	}
	
	public static void VolunteerMenu(Volunteer volunteer) {
		boolean loop = true;
		scan = new Scanner(System.in);
		int choice;
		if (!hasProject) {
			while(loop) {
				System.out.println("\n1. List Projects\n2. Request Project\n3. Exit");
				choice = scan.nextInt();
				switch (choice) {
					case 1: volunteer.getProjects(); break; 
					case 2: volunteer.RequestProject(); break;
					case 3: loop = false; break;
					default: System.out.println("Invalid Selection.");
				}
			}
		}
		else {
			while(loop) {
				System.out.println("\n1. List Projects\n2. Current Project Details\n3. Exit");
				choice = scan.nextInt();
				switch (choice) {
					case 1: volunteer.getProjects(); break; 
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
				case 2: projectHead.getProjects(); break;
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
			System.out.println("\n1. List Projects\n2. Create Project\n3. Create a new Project\n4. Schedule Event\n5. Manage Event\n 6. Exit");
			choice = scan.nextInt();
			switch (choice) {
				case 1: core.getProjects(); break;
				case 2: core.CreateProject(); break;
				case 3: core.CreateMessage(); break;
				case 4: core.CreateEvent(); break;
				case 5: //Need a method to modify created events
				case 6:  loop = false; break; 
				default: System.out.println("Invalid Selection.");
			}
		}
		}
}
