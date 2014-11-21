package Base_Classes;

public class Volunteer extends Person {

	  public String CurrentProject;
	  public final Designation desig = Designation.VOLUNTEER;
	  public static final int PRIVILEGELEVEL = 1;
	  public boolean hasProject;

	  public void RequestProject() {
	  }

	  public void WorkForEvent() {
	  }

	  public void WorkForProject() {
	  }

	  public Volunteer() {
		  super();
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
	  
	public Volunteer(String name, String iDNumber, Designation designation,
			String phoneNumber, String currentProject, String[] credentials) {
		super(name, iDNumber, designation, phoneNumber, credentials);
		CurrentProject = currentProject;
		this.hasProject = false;
	}

	public String getCurrentProject() {
		return CurrentProject;
	}

	public void setCurrentProject(String currentProject) {
		CurrentProject = currentProject;
	}
	  
	}