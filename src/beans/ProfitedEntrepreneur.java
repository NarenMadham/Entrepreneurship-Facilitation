package beans;

public class ProfitedEntrepreneur {
private String p_ent_id;
private String first_name;
private String middle_name;
private String last_name;
private String email;
private long phone;
private String dob;
private String contribution;
private String qualifications;
private String achievements;
private String password;
public ProfitedEntrepreneur(){}

public ProfitedEntrepreneur(String p_ent_id, String first_name, String middle_name, String last_name, String email,
		long phone, String dob, String contribution, String qualifications, String achievements, String password) {
	super();
	this.p_ent_id = p_ent_id;
	this.first_name = first_name;
	this.middle_name = middle_name;
	this.last_name = last_name;
	this.email = email;
	this.phone = phone;
	this.dob = dob;
	this.contribution = contribution;
	this.qualifications = qualifications;
	this.achievements = achievements;
	this.password = password;
}

public String getP_ent_id() {
	return p_ent_id;
}

public void setP_ent_id(String p_ent_id) {
	this.p_ent_id = p_ent_id;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getId() {
	return p_ent_id;
}
public void setId(String p_ent_id) {
	this.p_ent_id = p_ent_id;
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
public String getContribution() {
	return contribution;
}
public void setContribution(String contribution) {
	this.contribution = contribution;
}
public String getQualifications() {
	return qualifications;
}
public void setQualifications(String qualifications) {
	this.qualifications = qualifications;
}
public String getAchievements() {
	return achievements;
}
public void setAchievements(String achievements) {
	this.achievements = achievements;
}
public long getPhone() {
	return phone;
}
public void setPhone(long phone) {
	this.phone = phone;
}

}
