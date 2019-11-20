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

import beans.Recommendation;

@WebServlet("/recommendTeam")
public class RecommendTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		String inv_id = (String) hs.getAttribute("inv_id");
		String pe_id = (String) hs.getAttribute("pe_id");
		String team_id = (String) hs.getAttribute("team_id");
		SessionFactory sf = (SessionFactory) hs.getAttribute("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Recommendation recommendation = new Recommendation(pe_id, inv_id,team_id);
		session.save(recommendation);
		transaction.commit();
		session.close();
		resp.getWriter().println("Team Recommended Succesfully!!");
	}	
}
