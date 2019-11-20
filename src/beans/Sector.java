package beans;

public class Sector {
private String sec_id;
private String idea_sector;
private String sector_description;
private String scale;
public Sector(){}
public Sector(String sec_id, String idea_sector, String sector_description,
		String scale) {
	super();
	this.sec_id = sec_id;
	this.idea_sector = idea_sector;
	this.sector_description = sector_description;
	this.scale = scale;
}
public String getSec_id() {
	return sec_id;
}
public void setSec_id(String sec_id) {
	this.sec_id = sec_id;
}
public String getidea_sector() {
	return idea_sector;
}
public void setidea_sector(String idea_sector) {
	this.idea_sector = idea_sector;
}
public String getSector_description() {
	return sector_description;
}
public void setSector_description(String sector_description) {
	this.sector_description = sector_description;
}
public String getScale() {
	return scale;
}
public void setScale(String scale) {
	this.scale = scale;
}
}
