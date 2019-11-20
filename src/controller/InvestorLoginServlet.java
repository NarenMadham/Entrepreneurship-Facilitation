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

import beans.Investor;
import ent_crypto.Crypto;

@WebServlet({ "/InvestorLoginServlet", "/investorLogin" })
public class InvestorLoginServlet extends HttpServlet {
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
		HttpSession hs = request.getSession();
		String email = request.getParameter("email");
		String pas = request.getParameter("password");
		Criteria c = session.createCriteria(Investor.class);
		PrintWriter out = resp.getWriter();
		String inv_id = null;
		
		Criterion cr = Restrictions.eq("email",email);
		c.add(cr);
		@SuppressWarnings("unchecked")
		List<Investor> InvestorList = c.list(); 
		for(Investor t : InvestorList){
			temp_password = t.getPassword();
			inv_id = t.getInv_id();
		}
		resp.setContentType("text/html");
		out.println(temp_password);
		Crypto crypto = new Crypto();
		hs.setAttribute("inv_id", inv_id);
		password = crypto.decrypt(temp_password, iv, secretKey);
		if(pas.equals(password)){
			Cookie loginCookie = new Cookie("inv_cookie", inv_id);
			loginCookie.setMaxAge(24*60*60);
			resp.addCookie(loginCookie);
			out.println("Login Successful!!!!  :)");
			RequestDispatcher rd = request.getRequestDispatcher("InvestorProfile.jsp");
			rd.forward(request, resp);
		}else{
			out.println("Login Failed :(");
		}
		session.close();
    }
	
}
