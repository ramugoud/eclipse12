package com.fixity.supermarket.ms;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fixity.supermarket.dao.ItemDAO;
import com.fixity.supermarket.model.Item;




/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/additem")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("additem.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String itemId = request.getParameter("II");
		String itemname = request.getParameter("IN");
		double itemprice = Double.parseDouble(request.getParameter("IP"));
		Part filepart = request.getPart("IM");
		InputStream filecontent = filepart.getInputStream();
		
		
		Item it = new Item();
		it.setItemId(itemId);
		it.setName(itemname);
		it.setPrice(itemprice);
		it.setFile(filecontent.readAllBytes());
		
		ItemDAO dot = new ItemDAO();
		boolean b = dot.saveItem(it);
		if(b) {
			request.setAttribute("er", "Success to add Item");
		    doGet(request,response);
			//doGet(request, response);
		}
		else {
			request.setAttribute("er", "Failed to add Item");
			 doGet(request,response);
		}
		
	}

}
