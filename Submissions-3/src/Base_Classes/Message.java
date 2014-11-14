package Base_Classes;

import java.util.Vector;

import com.google.gson.Gson;

public class Message {

  private String Date;

  private String Body;

  private String Tag; 

  private String From;

  private Integer Min_Privilege_Level;   //CORE = 3, PROJ HEADS = 2, VOLUNTEERS = 1

  private String[] To;
  
  private String[] proj_restr; // null for no project restrictions

  public Message() {
	  super();
  }
  // No To because 
  public Message(String date, String body, String from,
		Integer min_Privilege_Level, String[] projectRestrictions) {
	super();
	Date = date;
	Body = body;
	From = from;
	Min_Privilege_Level = min_Privilege_Level;
	proj_restr = projectRestrictions;
  }
  
  public String[] getspecifiedProjects(){
		return proj_restr;
	}
	public String getDate(){
		return Date;
	}
	
	public String getBody(){
		return Body;
	}
	
	public String getTag(){
		return Tag;
	}
	
	public String getFrom(){
		return From;
	}
	
	public String[] getTo(){
		return To;
	}
	
	public int getMinPrivilege(){
		return Min_Privilege_Level;
	}

public void setDate(String date) {
	Date = date;
}

public void setBody(String body) {
	Body = body;
}

public void setTag(String tag) {
	Tag = tag;
}

public void setFrom(String from) {
	From = from;
}

public void setMin_Privilege_Level(Integer min_Privilege_Level) {
	Min_Privilege_Level = min_Privilege_Level;
}

public void setTo(String[] to) {
	To = new String[to.length];
	for(int i = 0; i< to.length; i++) {
		To[i] = to[i];
	}
	
}

public String encodeMsg(Message m) {
	Gson gson = new Gson();
	String gsonstr = gson.toJson(this);
	return gsonstr;
}

public Message decodeMsg(String json){
	Gson gson = new Gson();
	Message ret = gson.fromJson(json, Message.class);
	return ret;
}

public int addMessageToDatabase(Designation x,String finalmessage) {
	if (x == Designation.CORE){
		Core tempCore = new Core();
		return tempCore.appendToDatabase("coremessagedatabase", finalmessage);
	}
	else return 0;
}
  
  
  
  
}