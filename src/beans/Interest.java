package beans;

public class Interest {
	String inv_id;
	String team_id;
	public String getInv_id() {
		return inv_id;
	}
	public void setInv_id(String inv_id) {
		this.inv_id = inv_id;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public Interest(String inv_id, String team_id) {
		super();
		this.inv_id = inv_id;
		this.team_id = team_id;
	}
	
	
	
}
