package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import beans.Team;
import ent_crypto.Crypto;


@WebServlet({ "/TeamLoginServlet", "/teamLogin" })
public class TeamLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SessionFactory sessionf;
	String password;
    String temp_password;
    final String iv = "lsouqteimvnxbzfd";
    final String secretKey = "Entrepreneur has a decent family";
    
    @SuppressWarnings("deprecation")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
    	Configuration cfg = new Configuration();
		cfg.configure("resources/oracle.cfg.xml");
		sessionf =cfg.buildSessionFactory(); 
		Session session = sessionf.openSession();
		String name = request.getParameter("username");
		String pas = request.getParameter("password");
		HttpSession hs = request.getSession();
		hs.setAttribute("sessionfactory", sessionf);
		Criteria c = session.createCriteria(Team.class);
		PrintWriter out = resp.getWriter();
		String team_id = null;
		
		Criterion cr = Restrictions.eq("username",name);
		c.add(cr);
		@SuppressWarnings("unchecked")
		List<Team> teamList = c.list(); 
		for(Team t : teamList){
			temp_password = t.getPassword();
			team_id = t.getTeam_id();
			hs.setAttribute("team_sector", t.getIdea_sector());
		}
		hs.setAttribute("sessionFactory", sessionf);
		hs.setAttribute("team_id", team_id);
		resp.setContentType("text/html");
		out.println(temp_password);
		Crypto crypto = new Crypto();
		password = crypto.decrypt(temp_password, iv, secretKey);
		if(pas.equals(password)){
			Cookie loginCookie = new Cookie("team_cookie",team_id);
			loginCookie.setMaxAge(24*60*60);
			resp.addCookie(loginCookie);
			out.println("Login Successful!!!!  :)");
			out.println("<br/><br/><br/><br/><br/><br/><br/><br/>");
			out.println("<a href=\"addSector.html\">Add Sector! </a>");
			RequestDispatcher rd1 = request.getRequestDispatcher("TeamProfile.jsp");
			//RequestDispatcher rd2 = request.getRequestDispatcher("/teamProfile");
			rd1.forward(request, resp);
		}else{
			out.println("Login Failed :(");
		}
		session.close();
    }
}
