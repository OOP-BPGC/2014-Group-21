package Base_Classes;

/**
 *  An extension of the class Person, implements all the use cases available to, well, a 
 *  Volunteer.  Exhaustive list: Submitting a request for a project, working for an event, working
 *  for a project.
 *  @author Rohit Pandey
 */

public class Volunteer extends Person {

	  public String CurrentProject;
	  public final Designation desig = Designation.VOLUNTEER;	  
	  /**
	   * Each class corresponding to a designation, as well as all data sent and received, 
	   * has a different PRIVELEGELEVEL. The class can receive/modify data only at a 
	   * PRIVILEGELEVEL equal to or lower than the class' PRIVILEGELEVEL.
	   */	  
	  public static final int PRIVILEGELEVEL = 1;
	  /** 
	   * Whether the user has an associated project(true), or not(false).
	   */
	  public boolean hasProject;
	  
	  /**
	   * Sends a request for a project, approvable by a Project Head.
	   */

	  public void RequestProject() {
	  }
	  
	  /**
	   * Enlists the user to work for an Event.
	   */

	  public void WorkForEvent() {
	  }
	  
	  /**
	   * Enlists the user to work for an Project.
	   */
	  
	  public void WorkForProject() {
	  }

	  public Volunteer() {
		  super();
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
	  
	public Volunteer(String name, String iDNumber, Designation designation,
			String phoneNumber, String currentProject, String[] credentials) {
		super(name, iDNumber, designation, phoneNumber, credentials);
		CurrentProject = currentProject;
		this.hasProject = false;
	}
	
	/**
	 * Returns the currently associated project as a String.
	 * @return CurrentProject
	 */

	public String getCurrentProject() {
		return CurrentProject;
	}
	
	/**
	 * Sets a Project as the project associated with the user.
	 * @param currentProject
	 */

	public void setCurrentProject(String currentProject) {
		CurrentProject = currentProject;
	}
	  
	}