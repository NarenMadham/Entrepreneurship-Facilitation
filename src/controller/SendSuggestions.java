package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendSuggestions")
public class SendSuggestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String button = (String) req.getAttribute("button");
		if(button.equals("button")){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/SendSuggestions.jsp");
			rd.forward(req, resp);
		}
	}
	
}
