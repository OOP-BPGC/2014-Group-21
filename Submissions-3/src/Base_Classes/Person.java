package Base_Classes;

public class Person {

	  protected String Name;

	  protected String IDNumber;
	  public Person() {};
	  public Person(String name, String iDNumber, Designation designation,
			int phoneNumber, String[] credentials) {
		super();
		Name = name;
		IDNumber = iDNumber;
		this.designation = designation;
		PhoneNumber = phoneNumber;
		Credentials = credentials;
	}

	protected Designation designation;

	  protected int PhoneNumber;
	  
	  protected String[] Credentials;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public int getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	protected String[] getCredentials(){
		return Credentials;
	}
	
	public void setCredentials(String usr, String pwd){
		Credentials = new String[2];
		Credentials[0] = usr;
		Credentials[1] = pwd;
	}
	
	
		
	public void getProjects() {
		// TODO Auto-generated method stub
		
	} 

	}