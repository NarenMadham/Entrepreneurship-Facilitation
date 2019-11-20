package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import beans.Follow;

@WebServlet("/sendInterest")
public class SendInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		SessionFactory sf = (SessionFactory) hs.getAttribute("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String inv_id = (String) hs.getAttribute("inv_id");
		String team_id = (String) hs.getAttribute("team_id");
		Follow follow = new Follow(team_id, inv_id);
		session.save(follow);
		transaction.commit();
		session.close();
		resp.getWriter().println("Interest Successfully sent to the team of Enterpreneurs");
	}
	
}
