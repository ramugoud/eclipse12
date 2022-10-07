package com.fixity.supermarket.ms;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fixity.supermarket.dao.ItemDAO;
import com.fixity.supermarket.model.Item;

/**
 * Servlet implementation class ViewItemServlet
 */
@WebServlet("/search")
public class ViewItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ItemDAO dao;
    public ViewItemServlet() {
    	
        super();
        dao = new ItemDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("ViewItem.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name= request.getParameter("viewitems");
		Item item = dao.getItem(name);
		request.setAttribute("item",item);
		
		RequestDispatcher rd = request.getRequestDispatcher("ViewItem.jsp");
		rd.forward(request, response);
		
			
		
		
		
	}

}
