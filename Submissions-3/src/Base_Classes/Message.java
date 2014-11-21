package Base_Classes;

import java.util.Vector;

import com.google.gson.Gson;

/**
 * Contains methods to encode/decode objects to a JSon String, as well as compose String messages
 * in a predefined format.
 * @author Rohit Pandey
 */

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

  
  /*
   * 
   * ADDED CONSTRUCToR FOR MESSAGE BUILDER
   */
public Message(String date2, String body2, String from2,
		Integer min_Privilege_Level2, String[] proj_restr) {
	// TODO Auto-generated constructor stub
	super();
	Date = date2;
	Body = body2;
	From = from2;
	Min_Privilege_Level = min_Privilege_Level2;
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

/**
 * Converts a message object to JSon format.
 * @param m
 * @return ret
 */

public String encodeObj(Message m) {
	Gson gson = new Gson();
	String ret = gson.toJson(m);
	return ret;
}

/**
 * Converts a JSon string to a message object.
 * @param s
 * @return m
 */

public Message decodeJson (String s){
	Gson gson = new Gson();
	Message m = gson.fromJson(s, Message.class);
	return m;
}

/**
 * Compiles data into a string of predefined format.
 * @param m
 * @return finalmessage
 */

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

/**
 * Deconstructs the transmitted string.
 * @param jstring
 * @param p
 */

public void decodeMsg(String jstring, Core p){
	Message m = decodeJson(jstring);
	String mTag = m.Tag;
	String mBody = m.Body;
	switch(mTag){
		case "MESSAGE"		: p.addMessages(mBody)		;break;
		case "PROJECT"		: p.addProject(mBody)		;break;
		case "EVENT"		: p.addEvents(mBody)		;break;
		case "PERSONFILE"	: p.addUser(mBody)			;break;
		case "ACCEPTED"		: p.updateVolunteer(mBody);	;break;
		case "MODUSER"		: p.updateUserData(mBody);	;break;
		default 			: break;
	}
}

/**
 * Deconstructs the transmitted string.
 * @param jstring
 * @param p
 */

public void decodeMsg(String jstring, ProjectHead p){
	Message m = decodeJson(jstring);
	String mTag = m.Tag;
	String mBody = m.Body;
	switch(mTag){
		case "MESSAGE"		: p.addMessages(mBody)		;break;
		case "PROJECT"		: p.addProject(mBody)		;break;
		case "EVENT"		: p.addEvents(mBody)		;break;
		case "PERSONFILE"	: p.addUser(mBody)			;break;
		case "ACCEPTED"		: p.updateVolunteer(mBody)	;break;
		case "MODUSER"		: p.updateUserData(mBody);	;break;
		case "PROJREQ"		: p.addVolunteerProjectRequests(mBody) ;break; 
		default				: break;
	}
}

/**
 * Deconstructs the transmitted string.
 * @param jstring
 * @param p
 */

public void decodeMsg(String jstring, Volunteer p){
	Message m = decodeJson(jstring);
	String mTag = m.Tag;
	String mBody = m.Body;
	switch(mTag){
		case "MESSAGE"		: p.addMessages(mBody)		;break;
		case "PROJECT"		: p.addProject(mBody)		;break;
		case "EVENT"		: p.addEvents(mBody)		;break;
		case "PERSONFILE"	: p.addUser(mBody)			;break;
		case "ACCEPTED"		: p.updateVolunteer(mBody);	;break;
		case "MODUSER"		: p.updateUserData(mBody);	;break;
		default 			: break;
	}
}

/**
 * Adds a message to the message database if the user is a core member.
 * @param x
 * @param finalmessage
 * @return
 */

public int addMessageToDatabase(Designation x,String finalmessage) {
	if (x == Designation.CORE){
		Core tempCore = new Core();
		return tempCore.appendToDatabase("messagedatabase", finalmessage);
	}
	else return 0;
}
 // REMOVE AFTER UNIT TESTING 
public int addMessageToDatabaseTEST(Designation x,String finalmessage) {
	if (x == Designation.CORE){
		Core tempCore = new Core();
		return tempCore.appendToDatabase("messagedatabasetest", finalmessage);
	}
	else return 0;
}  
  
  
}