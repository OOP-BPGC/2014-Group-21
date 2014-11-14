package Base_Classes;

import java.util.Vector;

import com.google.gson.Gson;

public class Project {

  public String Name;

  public String Schedule;

  private String ProjectHead;

  public int numOfVolunteers;   // CHANGE MADE FROM STRING TO int

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

	public void ViewSchedule() {
	}

	public void SetEvent() {
	}
	
	public int updateProjectDatabases(){  //HERE WE WRITE TO THE DATABASE
		return 0;
	}
	public String encodeProject() {
	
		Gson gson = new Gson();
		String jsonString = gson.toJson(this);
		
		return jsonString;
	}
	public int addProjectToDatabase(Designation x,String finalmessage) {
		// TODO Auto-generated method stub
		if (x == Designation.CORE){
			Core tempCore = new Core();
			return tempCore.appendToDatabase("coreprojectdatabase", finalmessage);
		}
		else return 0;
	}

}