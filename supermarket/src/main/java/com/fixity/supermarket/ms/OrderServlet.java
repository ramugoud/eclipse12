package com.fixity.supermarket.ms;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fixity.supermarket.dao.OrderDAO;
import com.fixity.supermarket.model.CartItem;
import com.fixity.supermarket.model.Order;
import com.fixity.supermarket.model.OrderItem;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    HttpSession hs=request.getSession();
	    String uname=(String) hs.getAttribute("uid");
	    double total=0;
	    List<CartItem> carts=(List<CartItem>) hs.getAttribute("cart");
	    for(CartItem cart:carts)
	    {
	    	total= total+(cart.getPrice()*cart.getQuantity());
	    }
	    Order order= new Order();
	    order.setTotal(total);
	    order.setStatus("pending");
	    order.setUname(uname);
	    OrderDAO dao= new OrderDAO();
	    List<OrderItem> orderitems=new Vector<>();
	    for(CartItem cart:carts)
	    {
	    	
	    	 OrderItem oi=new OrderItem(cart);
	    	 orderitems.add(oi);
	    }
	   	    order.setOrderItems(orderitems);
	   	    
	   	    
	   	Order o= dao.saveOrder(order);
	   	
	   	if(o != null) {
	   	 o.getOrderId();
	   	hs.setAttribute("orderid",o);
	   	carts.clear();
	   	
		RequestDispatcher rd = request.getRequestDispatcher("index");
		rd.forward(request, response);
	   	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
