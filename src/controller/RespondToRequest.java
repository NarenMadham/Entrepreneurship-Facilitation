package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import beans.Message;

@WebServlet("/respondToRequest")
public class RespondToRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		SessionFactory sf = (SessionFactory) hs.getAttribute("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String team_id = (String) hs.getAttribute("team_id");
		String p_ent_id = (String) hs.getAttribute("pe_id");
		String message_body = req.getParameter("message");
		String time = getCurrentTime();
		Message message = new Message(p_ent_id, team_id, message_body,time);
		session.save(message);
		transaction.commit();
		session.close();
		RequestDispatcher rd = req.getRequestDispatcher("AskSuggestion.jsp");
		rd.forward(req, resp);
	}
	
	private String getCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		String time;
		String am_pm;
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		if(calendar.get(Calendar.AM_PM)==0)
			am_pm = "AM";
		else
			am_pm = "PM";
		time = hour + ":" + minute + ":" + second + " "+am_pm; 
		return time;
	}
	
}
