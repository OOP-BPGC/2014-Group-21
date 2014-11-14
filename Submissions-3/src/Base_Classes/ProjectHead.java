package Base_Classes;

public class ProjectHead extends Person {

	  public String ProjectName;
	  public int projectCode;
	  public final Designation desig = Designation.PROJECT_HEAD;

	  
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