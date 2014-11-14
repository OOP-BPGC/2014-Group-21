/*
 * 
 * Builder pattern for readability and functionality
 * 
 */

package tests;

import Base_Classes.Message;

public class MessageBuilder {
	
	 private String Date;

	  private String Body;

	  private String Tag; 

	  private String From;

	  private Integer Min_Privilege_Level;   //CORE = 3, PROJ HEADS = 2, VOLUNTEERS = 1

	  private String[] To;
	  
	  private String[] proj_restr;

	  
	  public MessageBuilder(){};
	  
	  public Message buildMessage(){
		  return new Message(Date, Body, From, Min_Privilege_Level,proj_restr);
	  }
	
	  public MessageBuilder date(String _date){
		  this.Date = _date;
		  return this;
	  }
	  
	  public MessageBuilder body(String _body){
		  this.Body = _body;
		  return this;
	  }
	  
	  public MessageBuilder tag(String _tag){
		  this.Tag = _tag;
		  return this;
	  }
	  
	  public MessageBuilder from(String _from){
		  this.Date = _from;
		  return this;
	  }
	  
	  public MessageBuilder to(String[] _to){
		  this.To = new String[_to.length];
			for(int i = 0; i< _to.length; i++) {
				this.To[i] = _to[i];
			}
		  
		  return this;
	  }
	  
	  public MessageBuilder minPrivLevel(int _minpriv){
		  this.Min_Privilege_Level = _minpriv;
		  return this;
	  }
	  
	  public MessageBuilder projRestr(String[] _plist){
		  this.proj_restr = new String[_plist.length];
			for(int i = 0; i< _plist.length; i++) {
				this.proj_restr[i] = _plist[i];
			}
		  
		  return this;
	  }

}
