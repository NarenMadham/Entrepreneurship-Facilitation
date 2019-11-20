package beans;

public class Investor {
private String inv_id;
private String first_name;
private String middle_name;
private String last_name;
private String email;
private String password;
private String dob;
private String interested_sectors;
private String invested_startups;
public Investor(){}

public Investor(String inv_id, String first_name, String middle_name,
		String last_name, String email, String password, String dob,
		String interested_sectors,
		String invested_startups) {
	super();
	this.inv_id = inv_id;
	this.first_name = first_name;
	this.middle_name = middle_name;
	this.last_name = last_name;
	this.email = email;
	this.password = password;
	this.dob = dob;
	this.interested_sectors = interested_sectors;
	this.invested_startups = invested_startups;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getInv_id() {
	return inv_id;
}
public void setInv_id(String inv_id) {
	this.inv_id = inv_id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getMiddle_name() {
	return middle_name;
}
public void setMiddle_name(String middle_name) {
	this.middle_name = middle_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getInterested_sectors() {
	return interested_sectors;
}
public void setInterested_sectors(String interested_sectors) {
	this.interested_sectors = interested_sectors;
}
public String getInvested_startups() {
	return invested_startups;
}
public void setInvested_startups(String invested_startups) {
	this.invested_startups = invested_startups;
}

}
