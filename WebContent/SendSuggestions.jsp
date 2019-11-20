<%@page import="java.util.Collections"%>
<%@page import="comparator.SortMessagesByDate"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.criterion.Criterion"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="beans.Message"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entrepreneur - Guidance center Portal</title>
</head>
<body>
<form action="/respondToRequest" method="post">
<input type="text" size = "20" name = "message"/>
<input type="submit" value = "send"/> 
</form>
<textarea rows="45" cols="50">
<%
SessionFactory sf = (SessionFactory) session.getAttribute("sessionFactory");
Session s = sf.openSession();
String team_id = (String) session.getAttribute("team_id");
String pe_id = (String) session.getAttribute("pe_id");
Criteria c = s.createCriteria(Message.class);
Criterion cr = Restrictions.eq("sender",team_id);
Criterion cr2 = Restrictions.eq("receiver",team_id);
Criterion cr3 = Restrictions.eq("sender",pe_id);
Criterion cr4 = Restrictions.eq("receiver",pe_id);
c.add(cr);
c.add(cr2);
List<Message> messageList = c.list();
Collections.sort(messageList,new SortMessagesByDate());
for(Message m : messageList){
%>
<%= m.getSender() + "	" + m.getTime()+" : \n" %>
<%= m.getMessage_body() + " : \n" %>
<%
}
response.setIntHeader("Refresh", 180);
%>
</textarea>
<br/>
<br/>
<form action="/recommendTeam">
<input type="submit" value="Recommend this team to the investor" name = "button"/>
</form>
</body>
</html>