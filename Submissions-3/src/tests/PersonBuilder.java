package tests;

import Base_Classes.Designation;
import Base_Classes.Person;

public class PersonBuilder {

	
	  public String Name;

	  public String IDNumber;

	  public Designation designation;

	  public String PhoneNumber;
	  
	  public String[] Credentials;
	
	public PersonBuilder() {}

	public Person buildPerson(){
		return new Person(Name, IDNumber, designation, PhoneNumber, Credentials);
	}
	
	public PersonBuilder Name(String name) {
		Name = name;
		return this;
	}

	public PersonBuilder IDNumber(String iDNumber) {
		IDNumber = iDNumber;
		return this;
	}

	public PersonBuilder Designation(Designation designation) {
		this.designation = designation;
		return this;
	}

	public PersonBuilder PhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
		return this;
	}

	public PersonBuilder Credentials(String[] credentials) {
		this.Credentials = new String[2];
		this.Credentials[0] = credentials[0];
		this.Credentials[1] = credentials[1];
		return this;
	};
	
	
	
	
}
