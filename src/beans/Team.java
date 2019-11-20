package beans;

public class Team {	
	private String team_id;
	private String team_name;
	private String username;
	private String password;
	private Long phone;
	private String email;
	private String idea_description;
	private String idea_sector;


	public Team(String team_id, String team_name, String username, String password, String idea_description,
			String idea_sector, Long phone, String email) {
		super();
		this.team_id = team_id;
		this.team_name = team_name;
		this.username = username;
		this.password = password;
		this.idea_description = idea_description;
		this.idea_sector = idea_sector;
		this.phone = phone;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getIdea_sector() {
		return idea_sector;
	}

	public void setIdea_sector(String idea_sector) {
		this.idea_sector = idea_sector;
	}

	public Team(){}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getIdea_description() {
		return idea_description;
	}
	public void setIdea_description(String idea_description) {
		this.idea_description = idea_description;
	}
}
