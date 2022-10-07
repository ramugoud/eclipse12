package com.fixity.supermarket.ms;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fixity.supermarket.dao.ItemDAO;
import com.fixity.supermarket.model.CartItem;
import com.fixity.supermarket.model.Item;



/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	ItemDAO dao;
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
        dao=new ItemDAO();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	int m=0;
		String id=request.getParameter("id");
	 Item it = dao.getItem(id);
	 if(it!=null)
	 {
		 HttpSession hs=request.getSession();
		 List<CartItem> carts =(List<CartItem>)hs.getAttribute("cart");
	 if(carts==null)
	 {
		 carts=new Vector<CartItem>();
	 }
	 for(CartItem cart:carts)
	 {

	 	if(id.equals(cart.getItemId()))
	 	{
	 		cart.setQuantity((cart.getQuantity()+1));
	 		m=1;
	 	}

	 }
	 if(m==0) {
	 CartItem item=new CartItem(it);
	 item.setQuantity(1);
	 carts.add(item);
	 }
	 hs.setAttribute("cart",carts);
	 }
	 
	 response.sendRedirect("index");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
