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
 * Servlet implementation class ViewOrders
 */
@WebServlet("/vieworders")
public class ViewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	OrderDAO dao;
    public ViewOrders() {
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
		HttpSession hs=request.getSession();

		List<Order> reject=dao.getorderstatus("rejected");
		hs.setAttribute("rejectorders",reject);
		//String s="pending";
		List<Order> orders=dao.getorderstatus("pending");
	   	hs.setAttribute("order",orders);
		List<Order> accept=dao.getorderstatus("accepted");
	   	hs.setAttribute("acceptorders",accept);
	   	RequestDispatcher rd = request.getRequestDispatcher("vieworders.jsp");
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
