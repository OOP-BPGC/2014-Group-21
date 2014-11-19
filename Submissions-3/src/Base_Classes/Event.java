package Base_Classes;



import com.google.gson.Gson;

public class Event {

  public String Name;

  public String Location;

  public String Date;

  public String Organiser;

  public String ContactNumber;
  
  /*
   * 
   * SETTERS AND GETTERS
   */
  
  
  public Event() {
	super();
	// TODO Auto-generated constructor stub
  }

  public String getContactNumber() {
	return ContactNumber;
  }

  public Event(String name, String location, String date, String organiser,
		String contactNumber) {
	super();
	Name = name;
	Location = location;
	Date = date;
	Organiser = organiser;
	ContactNumber = contactNumber;
}

public String getName() {
	return Name;
}

public String getLocation() {
	return Location;
}

public String getDate() {
	return Date;
}

public String getOrganiser() {
	return Organiser;
}

public void setContactNumber(String contactNumber) {
	ContactNumber = contactNumber;
  }

  public void setName(String name) {
	Name = name;
  }

  public void setLocation(String location) {
	Location = location;
  }

  public void setDate(String date) {
	Date = date;
  }

  public void setOrganiser(String organiser) {
	Organiser = organiser;
  }

public String encodeEvent() {
	String finalmessage;
	String tag1 = "EVENT";
	String del = "@@@@";
	String tag2 = this.Organiser;
	Gson gson = new Gson();
	String tag4 = this.Name;
	String gsonfile = gson.toJson(this);
	finalmessage = tag1+del+tag2+del+tag4+del+gsonfile;
	return finalmessage;
}
/// CHANGE MADE HERE...CORE TO GENERAL???
public int addEventToDatabases(Designation x, String finalmessage) {
	if (x == Designation.CORE){
		Core tempCore = new Core();
		return tempCore.appendToDatabase("eventdatabase", finalmessage);
	}
	else return 0;
}

}