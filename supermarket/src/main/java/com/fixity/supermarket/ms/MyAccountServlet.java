package com.fixity.supermarket.ms;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fixity.supermarket.dao.UserDAO;
import com.fixity.supermarket.model.User;

/**
 * Servlet implementation class MyAccountServlet
 */
@WebServlet(
		urlPatterns = { "/myaccount" })
public class MyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	UserDAO dao;
    public MyAccountServlet() {
    	
        super();
        dao=new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	    //ServletConfig config=getServletConfig();  
	    //String uname=config.getInitParameter("uname");
		
		
		//String uname1=request.getParameter(uid);

		HttpSession hs=request.getSession();
		String uname1=(String)hs.getAttribute("uid");
		User user=dao.getAccount(uname1);
		hs.setAttribute("user",user);
		RequestDispatcher rd = request.getRequestDispatcher("myaccount.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
