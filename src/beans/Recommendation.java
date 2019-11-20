package beans;

public class Recommendation {
	String pe_id;
	String inv_id;
	String team_id;
	
	public Recommendation(String pe_id, String inv_id,String team_id) {
		super();
		this.pe_id = pe_id;
		this.inv_id = inv_id;
		this.team_id = team_id;
	}
	public String getPe_id() {
		return pe_id;
	}
	public void setPe_id(String pe_id) {
		this.pe_id = pe_id;
	}
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
	
}
