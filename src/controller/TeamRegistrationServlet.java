package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Team;
import ent_crypto.Crypto;

@WebServlet({ "/TeamRegistrationServlet", "/teamRegistration" })
public class TeamRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String iv = "lsouqteimvnxbzfd";
	final String secretKey = "Entrepreneur has a decent family";
	SessionFactory sf;
	String decrypted_password;
	String password;
	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Configuration cfg = new Configuration();
		cfg.configure("resources/oracle.cfg.xml");
		sf = cfg.buildSessionFactory();
		PrintWriter out = resp.getWriter();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String team_name = req.getParameter("team_name");
		String username = req.getParameter("username");
		String temp_password = req.getParameter("password");
		String repassword = req.getParameter("repassword");
		String idea_description = req.getParameter("idea_description");
		String idea_sector = req.getParameter("idea_sector");
		long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String ac = null;
		Team team = null;
		resp.setContentType("text/html");
		Crypto crypto = new Crypto();
		String password;
		if(temp_password.equals(repassword)){
			password = crypto.encrypt(temp_password, iv, secretKey);
			if(!password.equals(null)){
				team = new Team(null, team_name, username, password, idea_description, idea_sector, phone, email);
				ac = (String) session.save(team);
				out.println(password);
				out.println("<h1>Team Registered Successfully!! :)"+ac+"</h1>");
				out.println("<a href=\"TeamLogin.html\">sign in</a>");
				transaction.commit();
				session.close();
			}else{
				out.println("Password Encryption Failed!! :(");
			}
		}else{
			out.println("<h1>Team Registered Failed!! :(</h1>");
		}
	}
}
