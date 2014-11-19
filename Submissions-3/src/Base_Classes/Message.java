package Base_Classes;

import java.util.Vector;

import com.google.gson.Gson;

public class Message {

  public String Date;

  public String Body;

  public String Tag; 

  public String From;

  public Integer Min_Privilege_Level;   //CORE = 3, PROJ HEADS = 2, VOLUNTEERS = 1

  public String[] To;

  public Message() {
	  super();
  }

  public Message(String date, String body, String from,
		Integer min_Privilege_Level) {
	super();
	Date = date;
	Body = body;
	From = from;
	Min_Privilege_Level = min_Privilege_Level;
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
	for(int i = 0; i< to.length; i++) {
		To[i] = to[i];
	}
	
}

public String encodeObj(Message m) {
	Gson gson = new Gson();
	String ret = gson.toJson(m);
	return ret;
}

public Message decodeJson (String s){
	Gson gson = new Gson();
	Message m = gson.fromJson(s, Message.class);
	return m;
}

public String encodeMsg(Message m) {
	String finalmessage;
	String tag1 = "MESSAGE";
	String del = "@@@@";
	String tag2 = this.From;
	Gson gson = new Gson();
	String tag4 = Integer.toString(this.Min_Privilege_Level);
	String gsonfile = gson.toJson(this);
	finalmessage = tag1+del+tag2+del+gsonfile+del+tag4;
	return finalmessage;
	
	// MESSAGE@@@@<SENDER'S NAME>@@@@<JSON>@@@@<MIN_PRIVILEGE_LEVEL  --> BLEH
}

/// INCOMPLETE METHOD
public void decodeMsg(String jstring, Core p){
	Message m = decodeJson(jstring);
	String mTag = m.Tag;
	String mBody = m.Body;
	switch(mTag){
		case "MESSAGE"		: p.addMessages(mBody)	;break;
		case "PROJECT"		: p.addProject(mBody)	;break;
		case "EVENT"		: p.addEvents(mBody)	;break;
		case "PERSONFILE"	: p.addUser(mBody)		;break;
		case "
	}
}

public int addMessageToDatabase(Designation x,String finalmessage) {
	if (x == Designation.CORE){
		Core tempCore = new Core();
		return tempCore.appendToDatabase("messagedatabase", finalmessage);
	}
	else return 0;
}
  
  
  
  
}