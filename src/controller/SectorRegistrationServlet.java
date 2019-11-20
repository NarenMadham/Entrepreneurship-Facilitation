package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import beans.Sector;

@WebServlet({ "/SectorRegistrationServlet", "/sectorRegistration" })
public class SectorRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*Configuration con = new Configuration();
		con.configure("resources/oracle.cfg.xml");
		SessionFactory sf = con.buildSessionFactory();*/
		HttpSession hs = req.getSession();
		SessionFactory sf = (SessionFactory)hs.getAttribute("sessionfactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Cookie[] cookies = req.getCookies();
		if(cookies == null){
			resp.getWriter().println("Please login to continue!");
		}
		for(Cookie c:cookies){
			if(c.getName().equals("team_cookie")||c.getName().equals("inv_cookie")){
				String name = req.getParameter("sector_name");
				String description = req.getParameter("sector_description");
				String scale = req.getParameter("sector_scale");
				Sector sector = new Sector(null, name, description, scale);
				session.save(sector);
				transaction.commit();
				resp.getWriter().println("Sector registration susccessfully completed!");
			}
		}
		
	}
}
