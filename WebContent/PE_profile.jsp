<%@page import="beans.SuggestionRequests"%>
<%@page import="beans.ProfitedEntrepreneur"%>
<%@page import="beans.Team"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.criterion.Criterion"%>
<%@page import="beans.Investor"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="beans.Sector"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%!
Cookie[] cookies;
String team_sector;
Configuration cfg = new Configuration();
Session s;
SessionFactory sf;
Criteria criteria;
Criteria criteria2;
Criterion cr2;
String pe_id;
Restrictions r;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entrepreneur - Guidance Center Portal</title>
</head>
<body>
<%
sf = (SessionFactory) session.getAttribute("sessionFactory");
cookies = request.getCookies();

if(cookies == null){
	response.getWriter().println("Please login to continue!");
}
for(Cookie c:cookies){
	if(c.getName().equals("team_cookie")){
		if(!c.getValue().equals(null)){
%>
<table>
<tr>
<th colspan="2">
<h1>Team Profile</h1>
</th>
</tr>
<tr>
<td>
<h2>The teams that had asked you for the suggestions</h2>
<%
criteria = s.createCriteria(SuggestionRequests.class);
Criterion cr = Restrictions.eq("p_ent_id", pe_id);
criteria.add(cr);
List<SuggestionRequests> sug_list = criteria.list();

for(SuggestionRequests i : sug_list){
	criteria2 = s.createCriteria(Team.class);
	String team_id = i.getTeam_id();
	cr2 = Restrictions.eq("team_id",team_id);
	criteria2.add(cr2);
	List<Team> team_list = criteria2.list();
	for(Team t : team_list){
%>
<h3><%=t.getTeam_name() %></h3>
<h3><%=t.getIdea_description() %></h3>
<%
session.setAttribute("team_id",t.getTeam_id());
session.setAttribute("pe_id",i.getP_ent_id());
}
%>
<form action="/sendSuggestions">
<button type="submit" name = "button" value = "forward"></button>
</form>
<br/>
<br/>
<%
}
%>
</td>
</tr>
</table>
<%
		}
		else{
			out.println("<a href=\"TeamLogin.html\">please login to your account!</a>");
		}
	}
}
%>
</body>
</html>