package com.fixity.supermarket.ms;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fixity.supermarket.dao.OrderDAO;
import com.fixity.supermarket.model.Order;

/**
 * Servlet implementation class decisionServlet
 */
@WebServlet("/decision")
public class decisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	OrderDAO dao;
    public decisionServlet() {
        super();
        // TODO Auto-generated constructor stub
    dao=new OrderDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 int msg= Integer.parseInt(request.getParameter("msg"));
	    int id= Integer.parseInt(request.getParameter("order"));
		HttpSession hs=request.getSession();
		if(msg==1)
		{
			dao.acceptedOrders(id);
			//String s="accepted";
			//List<Order> accept=dao.getorderstatus("accepted");
		   	//hs.setAttribute("acceptorders",accept);
		}
		else
		{
			dao.rejectedOrders(id);
			//String s="rejected";
		//	List<Order> reject=dao.getorderstatus("rejected");
			//hs.setAttribute("rejectorders",reject);
		}
		RequestDispatcher rd = request.getRequestDispatcher("viewpendingorders");
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
