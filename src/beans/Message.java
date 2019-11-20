package beans;

public class Message {
	String sender;
	String receiver;
	String message_body;
	String time;
	public Message(String sender, String receiver, String message_body,String time) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.message_body = message_body;
		this.time = time;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage_body() {
		return message_body;
	}
	public void setMessage_body(String message_body) {
		this.message_body = message_body;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
