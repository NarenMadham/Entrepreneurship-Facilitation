<%@page import="org.hibernate.criterion.MatchMode"%>
<%@page import="beans.Investor"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Criterion"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="beans.Team"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entrepreneur - Guidance Center Portal</title>
</head>
<body>
<%
SessionFactory sf = (SessionFactory) session.getAttribute("sessionFactory");
Session s = sf.openSession();
String team_id = (String) session.getAttribute("team_id");
String pe_id = (String) session.getAttribute("pe_id");
String team_sector;
Criteria c = s.createCriteria(Team.class);
Criterion cr = Restrictions.eq("team_id", team_id);
c.add(cr);
List<Team> team_list = c.list();
for(Team t : team_list){
	team_sector = t.getIdea_sector();
}
Criteria c2 = s.createCriteria(Investor.class);
Criterion cr2 = Restrictions.like("interested_sectors", team_sector, MatchMode.ANYWHERE);
c2.add(cr2);
List<Investor> inv_list = c2.list();
for(Investor i : inv_list){
%>
	<h3><%=i.getFirst_name() %></h3>
	<h3><%=i.getMiddle_name() %></h3>
	<h3><%=i.getLast_name() %></h3>
	<h3><%=i.getInterested_sectors() %></h3>
	<h3><%=i.getInvested_startups() %></h3>
<%
	session.setAttribute("inv_id", i.getInv_id());
%>
	<form action="./recommendTeam">
	<button type="submit" name = "button" value = "recommendTeam">Recommend to this Investor</button>
	</form> 
	<br>
	<br>
	<hr>
	<br>
	<br>
<%
}
%>
</body>
</html>