package beans;

public class Follow {
	private String team_id;
	private String inv_id;
	public Follow(String team_id, String inv_id) {
		super();
		this.team_id = team_id;
		this.inv_id = inv_id;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getInv_id() {
		return inv_id;
	}
	public void setInv_id(String inv_id) {
		this.inv_id = inv_id;
	}
}