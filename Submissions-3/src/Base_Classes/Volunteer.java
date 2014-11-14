package Base_Classes;

public class Volunteer extends Person {

	  public String CurrentProject;
	  public final Designation desig = Designation.VOLUNTEER;

	  public String CurrentEvent;

	  public void RequestProject() {
	  }

	  public void WorkForEvent() {
	  }

	  public void WorkForProject() {
	  }

	public String getCurrentProject() {
		return CurrentProject;
	}

	public void setCurrentProject(String currentProject) {
		CurrentProject = currentProject;
	}

	public String getCurrentEvent() {
		return CurrentEvent;
	}

	public void setCurrentEvent(String currentEvent) {
		CurrentEvent = currentEvent;
	}
	  
	}