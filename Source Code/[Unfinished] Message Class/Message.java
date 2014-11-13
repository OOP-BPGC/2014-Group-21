
public class Message {

	/**
	 * @param args
	 */
	private String date;
	private String body;
	private String tag;
	private String sub;
	private String from;
	private String to;
	private int min_priv;
	private int projSpec;
	
	public int getspecifiedProject(){
		return projSpec;
	}
	public String getSubject(){
		return sub;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getBody(){
		return body;
	}
	
	public String getTag(){
		return tag;
	}
	
	public String getFrom(){
		return from;
	}
	
	public String getTo(){
		return to;
	}
	
	public int getMinPrivilege(){
		return min_priv;
	}
	
	public Message decodeString(String s){
		Message m = new Message();
		String[] d = s.split(";");
		m.setTag(d[0]);
		m.setFrom(d[1]);
		m.setTo(d[2]);
		m.setDate(d[3]);
		m.setSpecificProject(Integer.parseInt(d[4]));
		m.setMinPrivilege(Integer.parseInt(d[5]));
		m.setBody(d[6]);
		return m;
	}
	//[Tag];[From];[To];[Date];[Proj];[Min Privilege Level];[Content]~…
	public String encodeString(Message m){
		String ret = "";
		ret += m.tag + ";";
		ret += m.from + ";";
		ret += m.to + ";";
		ret += m.date + ";";
		ret += m.projSpec + ";";
		ret += m.min_priv + ";";
		//ret += m.sub + ";";
		ret += m.body + ";";
		return ret;
	}
	
	public void setDate(String s){
		date = s;
	}
	
	public void setBody(String s){
		body = s;
	}
	
	public void setTag(String s){
		tag = s;
	}
	
	public void setFrom(String s){
		from = s;
	}
	
	public void setTo(String s){
		to = s;
	}
	
	public void setMinPrivilege(int i){
		min_priv = i;
	}
	
	public void setSubject(String s){
		sub = s;
	}
	
	public void setSpecificProject(int i){
		projSpec = 1;
	}
}
