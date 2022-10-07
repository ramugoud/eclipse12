package com.fixity.supermarket.ms;


import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fixity.supermarket.dao.UserDAO;
import com.fixity.supermarket.model.User;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	UserDAO dao;


    public LoginServlet() {
        super();
         dao = new UserDAO();
         
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String user=null;
	    HttpSession hs=request.getSession(true);
	       user=(String) hs.getAttribute("uid");
		if(user==null) {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request,response);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("adminpage.jsp");
			rd.forward(request,response);	
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("un");
		String pass = request.getParameter("pw");
		User user = dao.getUser(uname);
		try {
		if(uname.equals("admin")) {
			HttpSession hs = request.getSession(true);
			hs.setAttribute("uid", uname);
			response.sendRedirect("adminpage");	
		}
		else if(uname.equals(user.getUname())) {
			HttpSession hs = request.getSession(true);
			hs.setAttribute("uid", uname);
			RequestDispatcher rd = request.getRequestDispatcher("index");
	   		rd.forward(request, response);
		}
		
		else {
			request.setAttribute("err", "invalid User name or password");
			doGet(request,response);
		}
		}
		catch(Exception e)
		{
			request.setAttribute("err", "invalid User name or password");
			doGet(request,response);
		}
	}

}
