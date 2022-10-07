package com.fixity.supermarket.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import com.fixity.supermarket.model.Order;
import com.fixity.supermarket.model.OrderItem;

public class OrderDAO {
Order order=new Order();

public Order saveOrder(Order order)
{
	int n=0;
	Connection con = DbConnection.getConnection();
	try 
	{
		con.setAutoCommit(false);
		PreparedStatement ps = con.prepareStatement("insert into order_2(total,status,uname) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(3,order.getUname());
		ps.setString(2,order.getStatus());
		ps.setDouble(1,order.getTotal());
		ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
		if(rs.next()) {
			  order.setOrderId(rs.getInt(1));
		
			  ps = con.prepareStatement("insert into OrderItem_2 values(?,?,?,?,?,?)");	
				
			  for(OrderItem orderItem:order.getOrderItems())
				{
				  
				ps.setString(1,orderItem.getItemId());
				ps.setString(2,orderItem.getName());
				ps.setDouble(3,orderItem.getPrice());
				ps.setInt(4,orderItem.getQuantity());
				ps.setInt(5,order.getOrderId());
				ps.setBytes(6,orderItem.getFile());
				n=ps.executeUpdate();
			}
			  
			  
		con.commit();	  
			  
		con.setAutoCommit(true);
		}
	if(n>0)
			return order;
	
	con.rollback();
	con.setAutoCommit(true);
	
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	
	return null;
	}
    public List<Order> viewOrders()
 {
    	List<Order> orders=new Vector<>();
    	Connection con = DbConnection.getConnection();
    	try
    	{
    		PreparedStatement ps = con.prepareStatement("select * from order_2");
    		ResultSet rs = ps.executeQuery();
    		while(rs.next())
    		{
    			Order order=new Order();
    			order.setOrderId(rs.getInt(1));
    			order.setTotal(rs.getDouble(2));
    			order.setStatus(rs.getString(3));
    			ps = con.prepareStatement("select * from OrderItem_2 where orderId=?");
    			ps.setInt(1,order.getOrderId());
    			ResultSet rs1 = ps.executeQuery();
    			List<OrderItem> orderItems=new Vector<>();
    			while(rs1.next())
    			{
    				OrderItem orderItem=new OrderItem();
    				orderItem.setItemId(rs1.getString(1));
    				orderItem.setName(rs1.getString(2));
    				orderItem.setPrice(rs1.getDouble(3));
    				orderItem.setQuantity(rs1.getInt(4));
    				orderItem.setOrderId(rs1.getInt(5));
    				 Blob blob = rs.getBlob(6);
					 InputStream inputStream = blob.getBinaryStream();
		                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		                byte[] buffer = new byte[4096];
		                int bytesRead = -1;
		                 
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);                  
		                }
		                 
		                byte[] imageBytes = outputStream.toByteArray();
		               orderItem.setFile(imageBytes);
    				orderItems.add(orderItem);
    			}
    			order.setOrderItems(orderItems);
    			orders.add(order);
    		}
    	}
    	catch (Exception e) {
    		// TODO: handle exception
    	}
    	return orders;
	}
	public boolean acceptedOrders(int id) {
		//String s="accepted";
		Connection con = DbConnection.getConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("update order_2 set status=? where orderID=?");
			ps.setString(1,"accepted");
			ps.setInt(2,id);
			int n=	ps.executeUpdate();
               if(n>0)
            	   return true;
		}
		catch (Exception e) {
    		// TODO: handle exception
    	}
		return false;
	}
	public boolean rejectedOrders(int id) {
		// TODO Auto-generated method stub
		//String s1 ="rejected";
		Connection con = DbConnection.getConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("update order_2 set status=? where orderID=?");
			ps.setInt(2,id);
			ps.setString(1,"rejected");
			int n=ps.executeUpdate();
               if(n>0)
            	   return true;
		}
		catch (Exception e) {
    		// TODO: handle exception
    	}
		return false;
	}
	public List<Order> getorderstatus(String s) {
		// TODO Auto-generated method stub
		List<Order> orders=new Vector<>();
    	Connection con = DbConnection.getConnection();
    	try
    	{
    		PreparedStatement ps = con.prepareStatement("select * from order_2 where status=?");
    		ps.setString(1,s);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next())
    		{
    			Order order=new Order();
    			order.setOrderId(rs.getInt(1));
    			order.setTotal(rs.getDouble(2));
    			order.setStatus(rs.getString(3));
    			order.setUname(rs.getString(4));
    			ps = con.prepareStatement("select * from OrderItem_2 where orderId=?");
    			ps.setInt(1,order.getOrderId());
    			ResultSet rs1 = ps.executeQuery();
    			List<OrderItem> orderItems=new Vector<>();
    			while(rs1.next())
    			{
    				
    				OrderItem orderItem=new OrderItem();
    				orderItem.setItemId(rs1.getString(1));
    				orderItem.setName(rs1.getString(2));
    				orderItem.setPrice(rs1.getDouble(3));
    				orderItem.setQuantity(rs1.getInt(4));
    				orderItem.setOrderId(rs1.getInt(5));
    				 Blob blob = rs1.getBlob(6);
					 InputStream inputStream = blob.getBinaryStream();
		                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		                byte[] buffer = new byte[4096];
		                int bytesRead = -1;
		                 
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);                  
		                }
		                 
		                byte[] imageBytes = outputStream.toByteArray();
		               orderItem.setFile(imageBytes);
    				orderItems.add(orderItem);
    			}
    			order.setOrderItems(orderItems);
    			orders.add(order);
    		}
    		return orders;
    	}
    	catch (Exception e) {
    		// TODO: handle exception
    		e.getMessage();
    	}
    	return orders;
	}
	public List<Order> viewUserOrders(String uname1) {
		// TODO Auto-generated method stub
		List<Order> orders=new Vector<>();
    	Connection con = DbConnection.getConnection();
    	try
    	{
    		PreparedStatement ps = con.prepareStatement("select * from order_2 where uname=?");
    		ps.setString(1,uname1);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next())
    		{
    			Order order=new Order();
    			order.setOrderId(rs.getInt(1));
    			order.setTotal(rs.getDouble(2));
    			order.setStatus(rs.getString(3));
    			order.setUname(rs.getString(4));
    			ps = con.prepareStatement("select * from OrderItem_2 where orderId=?");
    			ps.setInt(1,order.getOrderId());
    			ResultSet rs1 = ps.executeQuery();
    			List<OrderItem> orderItems=new Vector<>();
    			while(rs1.next())
    			{
    				OrderItem orderItem=new OrderItem();
    				orderItem.setItemId(rs1.getString(1));
    				orderItem.setName(rs1.getString(2));
    				orderItem.setPrice(rs1.getDouble(3));
    				orderItem.setQuantity(rs1.getInt(4));
    				orderItem.setOrderId(rs1.getInt(5));
    				orderItem.setFile(rs1.getBytes(6));
    				orderItems.add(orderItem);
    			}
    			order.setOrderItems(orderItems);
    			orders.add(order);
    		}
    		return orders;
    	}
    	catch (Exception e) {
    		// TODO: handle exception
    		e.getMessage();
    	}
    	return orders;
	}

}


