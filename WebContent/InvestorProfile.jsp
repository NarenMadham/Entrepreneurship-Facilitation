<%@page import="beans.Follow"%>
<%@page import="beans.ProfitedEntrepreneur"%>
<%@page import="beans.Team"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.criterion.Criterion"%>
<%@page import="beans.Recommendation"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entrepreneur - Guidance Center Platform</title>
</head>
<body>
<table>
<tr>
<th rowspan="2">
<h1><center>Investor Profile</center></h1>
</th>
</tr>
<tr>
<h1><center>Recommendations that you received from the Profited Entrepreneurs</center></h1><br/><br/>
<%
SessionFactory sf = (SessionFactory) session.getAttribute("sessionFactory");
String inv_id = (String) session.getAttribute("inv_id");
Session s =  sf.openSession();
Criteria c1 = s.createCriteria(Recommendation.class);
Criterion cr1 = Restrictions.eq("inv_id", inv_id);
c1.add(cr1);
List<Recommendation> rec_list = c1.list();
for(Recommendation rec : rec_list){
	String pe_id = rec.getPe_id();
	Criteria c2 = s.createCriteria(ProfitedEntrepreneur.class);
	Criterion cr2 = Restrictions.eq("p_ent_id", pe_id);
	c2.add(cr2);
	List<ProfitedEntrepreneur> pe_list = c2.list();
	for(ProfitedEntrepreneur pe : pe_list){
		String team_id = rec.getTeam_id();
		Criteria c3 = s.createCriteria(Team.class);
		Criterion cr3 = Restrictions.eq("team_id",team_id);
		c3.add(cr3);
		List<Team> team_list = c3.list();
		for(Team t : team_list){
%>
		<h3> The Following team was recommended by the Entrepreneur <b><%=pe.getFirst_name() +" "+ pe.getMiddle_name() +" "+ pe.getLast_name() %></b></h3><br/>
		Team Name : <h3><%=t.getTeam_name() %></h3><br/>
		Idea Sector : <h3><%=t.getIdea_sector() %></h3><br/>
		Idea Description : <h3><%=t.getIdea_description() %></h3><br/>
		<br/>
		<br/>
		<hr>
		<br/>
		
<%
		}
	}
}
%>
</tr>
<tr>
<h1><center>The Teams that are following you</center></h1>
<%
Criteria criteria1 = s.createCriteria(Follow.class);
Criterion criterion1 = Restrictions.eq("inv_id",inv_id);
criteria1.add(criterion1);
List<Follow> follow_list = criteria1.list();
for(Follow f : follow_list){
	String team_id = f.getTeam_id();
	Criteria c2 = s.createCriteria(Team.class);
	Criterion cr2 = Restrictions.eq("team_id", team_id);
	c2.add(cr2);
	List<Team> team_list = c2.list();
	for(Team t : team_list){
%>
	<h2>Team Name :</h2><h3><%=t.getTeam_name() %></h3><br/>
	<h2>Team Idea Sector :</h2><h3><%=t.getIdea_sector() %></h3><br/>
	<h2>Team Idea Description :</h2><h3><%=t.getIdea_description() %></h3><br/>
	<br/>
	<form action="/sendInterest">
	<button type="submit" name = "button" value="button">Send Interest</button>
	</form>
	<form action="/videoInteract">
	<button type="submit" name="button" value="button">Interact through video chat</button>
	</form>
	<hr>
	<br/>
	<br/> 
<%
	}
}
%>
</tr>
</table>
</body>
</html>