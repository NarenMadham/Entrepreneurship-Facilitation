package beans;

public class Team_PE_chat {
String message_id;
String message_body;
public Team_PE_chat(String message_id, String message_body) {
	super();
	this.message_id = message_id;
	this.message_body = message_body;
}
public String getMessage_id() {
	return message_id;
}
public void setMessage_id(String message_id) {
	this.message_id = message_id;
}
public String getMessage_body() {
	return message_body;
}
public void setMessage_body(String message_body) {
	this.message_body = message_body;
}
}
