package controller;

import java.io.IOException;
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

import beans.ProfitedEntrepreneur;
import beans.Team;
import ent_crypto.Crypto;

@WebServlet({ "/ProfitedEntrepreneurLogin", "/pe_login" })
public class ProfitedEntrepreneurLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SessionFactory sessionf;
	String password;
	String temp_password,pe_id;
	final String iv = "lsouqteimvnxbzfd";
	final String secretKey = "Entrepreneur has a decent family";
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pas = request.getParameter("password");
		Configuration cfg = new Configuration();
		cfg.configure("resources/oracle.cfg.xml");
		sessionf =cfg.buildSessionFactory(); 
		Session session = sessionf.openSession();
		HttpSession hs = request.getSession();
		hs.setAttribute("sessionfactory", sessionf);
		Criteria c = session.createCriteria(Team.class);
		
		Criterion cr = Restrictions.eq("email", email);
		c.add(cr);
		List<ProfitedEntrepreneur> pe_list = c.list();
		for(ProfitedEntrepreneur pe : pe_list){
			temp_password = pe.getPassword();
			pe_id = pe.getP_ent_id();
		}
		hs.setAttribute("sessionFactory", sessionf);
		hs.setAttribute("pe_id", pe_id);
		response.setContentType("text/html");
		Crypto crypto = new Crypto();
		password = crypto.decrypt(temp_password, iv, secretKey);
		if(pas.equals(password)){
			Cookie loginCookie = new Cookie("pe_cookie", pe_id);
			loginCookie.setMaxAge(24*60*60);
			response.addCookie(loginCookie);
			RequestDispatcher rd1 = request.getRequestDispatcher("PE_profile.jsp");
			rd1.forward(request, response);
		}else{
			response.getWriter().println("Login Failed");
		}
		session.close();
	}
}
