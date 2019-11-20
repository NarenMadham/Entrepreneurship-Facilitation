package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import beans.Follow;
import beans.Investor;

@WebServlet({ "/ShowInvestorDetails", "/showInvestorDetails" })
public class ShowInvestorDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		SessionFactory sf = (SessionFactory) hs.getAttribute("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String inv_id = (String) hs.getAttribute("inv_id");
		String button = req.getParameter("button");
		if(button.equals("showInvestor")){
			Criteria c = session.createCriteria(Follow.class);
			PrintWriter out = resp.getWriter();
			
			Criterion cr = Restrictions.eq("inv_id", inv_id);
			c.add(cr);
			List<Investor> followlist = c.list();
			for(Investor i : followlist){
				out.println("First_name : " + i.getFirst_name());
				out.println("Middle_name : " + i.getMiddle_name());
				out.println("Last_name : " + i.getLast_name());
				out.println("Date of Birth : " + i.getDob());
				out.println("Interested_sectors : " + i.getInterested_sectors());
				out.println("Invested_startups : " + i.getInvested_startups());
			}
		}else if(button.equals("followInvestor")){
			String team_id = (String) hs.getAttribute("team_cookie");
			Follow follow = new Follow(team_id, inv_id);
			session.save(follow);
			resp.getWriter().println("Now you are following the Entrepreneur");
			transaction.commit();
			session.close();
		}
		
		RequestDispatcher rd1 = req.getRequestDispatcher("/WEB-INF/TeamProfile.jsp");
		rd1.forward(req, resp);
	}
	
}
