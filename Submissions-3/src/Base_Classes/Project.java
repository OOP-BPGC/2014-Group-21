package Base_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import com.google.gson.Gson;

/**
 * Contains the details of a single project, as well as the method to encode the details in 
 * preparation for transfer.
 * @author Rohit Pandey
 */

public class Project {

  public String Name;

  public String Schedule;

  private String ProjectHead;

  public int numOfVolunteers;   // CHANGE MADE FROM STRING TO int  // want to delete it.

  public String EventName;

  	
 	public Project(String name, String schedule, String projectHead,
		int volunteers, String eventName) {
	super();
	Name = name;
	Schedule = schedule;
	ProjectHead = projectHead;
	numOfVolunteers = volunteers;
	EventName = eventName;
}
	public Project() {
		// TODO Auto-generated constructor stub
	}
	public void SetAttribute() {
  	}
  //SETTERS AND GETTERS

  	public String getName() {
	  	return Name;
  	}

  	public void setName(String name) {
		Name = name;
	}

	public String getSchedule() {
		return Schedule;
	}

	public void setSchedule(String schedule) {
		Schedule = schedule;
	}

	public String getProjectHead() {
		return ProjectHead;
	}

	public void setProjectHead(String projectHead) {
		ProjectHead = projectHead;
	}

	public int getVolunteers() {
		return numOfVolunteers;
	}

	public void setVolunteers(int volunteers) {
		numOfVolunteers = volunteers;
	}

	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

// OVER SETTERS AND GETTERS
	
	/**
	 * Displays the project schedule.
	 */

	public void ViewSchedule() {
	}
	
	/**
	 * Creates a new Event.
	 */
	
	public void SetEvent() {
	}
	
	/**
	 * Encodes the details of the project in a specific format in preparation to transfer the 
	 * details.
	 * @return finalmessage
	 */

	public String encodeProject() {
		String finalmessage;
		String tag1 = "PROJECT";
		String del = "@@@@";
		String tag2 = this.Name;
		String tag3 = this.ProjectHead;
		Gson gson = new Gson();
		String gsonfile = gson.toJson(this);
		finalmessage = tag1+del+tag3+del+tag2+del+gsonfile;
		return finalmessage;
	}
	
	/**
	 * Adds a new project to the project database.
	 * @param x
	 * @param finalmessage
	 * @return
	 */
	
	public int addProjectToDatabase(Designation x,String finalmessage) {
		// TODO Auto-generated method stub
		if (x == Designation.CORE){
			Core tempCore = new Core();
			return tempCore.appendToDatabase("projectdatabase", finalmessage);
		}
//		else if (x == this.desig){
			
//		}
		else return 0;
	}

}