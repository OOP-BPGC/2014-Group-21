/**
 * Builder pattern for Event class
 * @author Anshul Ravichandar
 */
 

package tests;

import Base_Classes.Event;

public class EventBuilder {
	
	  public String Name;

	  public String Location;

	  public String Date;

	  public String Organiser;

	  public String ContactNumber;

	  
	  public EventBuilder(){};
	  
	  public Event buildEvent(){
		 return new Event(Name, Location, Date, Organiser, ContactNumber);
	  }
	
	  public EventBuilder name(String _name){
		  this.Name = _name;
		  return this;
	  }
	  
	  public EventBuilder date(String _date){
		  this.Date = _date;
		  return this;
	  }
	  
	  public EventBuilder location(String _location){
		  this.Location = _location;
		  return this;
	  }
	  
	  public EventBuilder contactNumber(String _cont){
		  this.ContactNumber = _cont;
		  return this;
	  }
	  
	  public EventBuilder from(String _from){
		  this.Date = _from;
		  return this;
	  }
	  
	  public EventBuilder organiser(String _organiser){
		  this.Date = _organiser;
		  return this;
	  }
}
