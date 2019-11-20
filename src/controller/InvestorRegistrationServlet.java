package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Investor;
import ent_crypto.Crypto;

@WebServlet({ "/InvestorRegistrationServlet", "/investorRegistration" })
public class InvestorRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String iv = "lsouqteimvnxbzfd";
	final String secretKey = "Entrepreneur has a decent family";
	SessionFactory sf;
	String decrypted_password;
	String password;
	
	@SuppressWarnings({ "deprecation"})
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Configuration cfg = new Configuration();
		cfg.configure("resources/oracle.cfg.xml");
		sf = cfg.buildSessionFactory();
		PrintWriter out = resp.getWriter();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String first_name = req.getParameter("first_name");
		String middle_name = req.getParameter("middle_name");
		String last_name = req.getParameter("last_name");
		String temp_password = req.getParameter("password");
		String repassword = req.getParameter("repassword");
		String dob = req.getParameter("dob");
		List<String> interested_sectors = new ArrayList<String>();
		String interested_sector_1 = req.getParameter("interested_sectors_1");
		String interested_sector_2 = req.getParameter("interested_sectors_2");
		String interested_sector_3 = req.getParameter("interested_sectors_3");
		String interested_sector_4 = req.getParameter("interested_sectors_4");
		String interested_sector_5 = req.getParameter("interested_sectors_5");
		interested_sectors.add(interested_sector_1);
		interested_sectors.add(interested_sector_2);
		interested_sectors.add(interested_sector_3);
		interested_sectors.add(interested_sector_4);
		interested_sectors.add(interested_sector_5);
		List<String> invested_startups = new ArrayList<String>();
		String invested_startups_1 = req.getParameter("invested_startups_1");
		String invested_startups_2 = req.getParameter("invested_startups_2");
		String invested_startups_3 = req.getParameter("invested_startups_3");
		invested_startups.add(invested_startups_1);
		invested_startups.add(invested_startups_2);
		invested_startups.add(invested_startups_3);
		String email = req.getParameter("email");
		String ac = null;
		Investor investor = null;
		resp.setContentType("text/html");
		Crypto crypto = new Crypto();
		String password;
		if(temp_password.equals(repassword)){
			password = crypto.encrypt(temp_password, iv, secretKey);
			if(!password.equals(null)){
				investor = new Investor(null, first_name, middle_name, last_name, email, password, dob, listToString(interested_sectors), listToString(invested_startups));
				ac = (String) session.save(investor);
				out.println(password);
				out.println("<h1>Team Registered Successfully!! :)"+ac+"</h1>");
				transaction.commit();
				session.close();
			}else{
				out.println("Password Encryption Failed!! :(");
			}
		}else{
			out.println("<h1>Team Registered Failed!! :(</h1>");
		}
		
	}
	
	public String listToString(List<String> list){
		String name = null;
		for(String s : list){
			if(list.indexOf(s)==0){
				name += s;
			}else{
				name += " || "+s;
			}
		}
		return name;
	}
	
}
