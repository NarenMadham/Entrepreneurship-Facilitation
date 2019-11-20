package beans;

public class SuggestionRequests {
	private String team_id;
	private String p_ent_id;
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getP_ent_id() {
		return p_ent_id;
	}
	public void setP_ent_id(String p_ent_id) {
		this.p_ent_id = p_ent_id;
	}
	public SuggestionRequests(String team_id, String p_ent_id) {
		super();
		this.team_id = team_id;
		this.p_ent_id = p_ent_id;
	}
}