<%@page import="org.hibernate.criterion.MatchMode"%>
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
String team_id;
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
<h2>Ask Profitted Entrepreneur for suggestions</h2>
<%
criteria = s.createCriteria(ProfitedEntrepreneur.class);
List<ProfitedEntrepreneur> pe_list = criteria.list();
for(ProfitedEntrepreneur i : pe_list){
%>
<h3><%=i.getFirst_name()  %></h3>
<h3><%=i.getMiddle_name()  %></h3>
<h3><%=i.getLast_name()  %></h3>
<h3><%=i.getAchievements()  %></h3>
<%
session.setAttribute("pe_id",i.getP_ent_id());
session.setAttribute("email", i.getEmail());
%>
<form action="/askSuggestions">
<button type="submit" name = "button" value = "forward"></button>
</form>
<br/>
<br/>
<%
}
%>
</td>
</tr>
<tr>
<td>
<h2>Investors Interested in your Idea sector</h2>
<%
criteria2 = s.createCriteria(Investor.class);
team_id = c.getValue();
team_sector = (String) session.getAttribute("team_sector");
cr2 = Restrictions.like("interested_sectors", team_sector,MatchMode.ANYWHERE);
criteria2.add(cr2);
List<Investor> inv_list = criteria.list();
for(Investor i : inv_list){
	String inv = "inv_id";
	int abc = 0;
%>
<h3><%=i.getFirst_name()  %></h3>
<h3><%=i.getMiddle_name()  %></h3>
<h3><%=i.getLast_name()  %></h3>
<h3><%=i.getInvested_startups()  %></h3>
<br/>
<br/>
<%
session.setAttribute("inv_id"+abc, i.getInv_id());
abc++;
%>
<form action="/showInvestorDetails" method="get">
<button type="submit" name = "button" value = "followInvestor">Follow Investor</button>
<button type="submit" name = "button" value = "showInvestor">Show Investor Details</button>
</form>	
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