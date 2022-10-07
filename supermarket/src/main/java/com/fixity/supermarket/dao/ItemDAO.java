package com.fixity.supermarket.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;
import java.util.Vector;

import com.fixity.supermarket.model.Item;

public class ItemDAO {
	public boolean saveItem(Item item) {
	Connection con = DbConnection.getConnection();
	try {
		PreparedStatement ps = con.prepareStatement("insert into item values(?,?,?,?)");
		ps.setString(1, item.getItemId());
		
		ps.setString(2, item.getName());
	
		ps.setDouble(3, item.getPrice());
		ps.setBytes(4,item.getFile());
		
		int n = ps.executeUpdate();
		if(n>0)
			return true;
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	
	
	return false;
	}
	
	public List<Item> getItems(){
		
		List<Item> items = new Vector<Item>();
		
		try {
			Connection con = DbConnection.getConnection();
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from item");
			while(rs.next()) {
				Item it = new Item();
				 Blob blob = rs.getBlob(4);
				 InputStream inputStream = blob.getBinaryStream();
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[4096];
	                int bytesRead = -1;
	                 
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
	                 
	                byte[] imageBytes = outputStream.toByteArray();
	                 
	                 
	                inputStream.close();
	                outputStream.close();
	    		it.setItemId(rs.getString(1));
	    		it.setName(rs.getString(2));
	    		it.setPrice(rs.getDouble(3));
	    	    it.setFile(imageBytes);
	    		items.add(it);
			}						
		}
		catch (Exception e) {
			e.getStackTrace();
			// TODO: handle exception
		}
		return items;
	}
	
	public Item getItem(String id) {
		// TODO Auto-generated method stub
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from Item  where itemId=?");
		    pst.setString(1,id);
			ResultSet rs=pst.executeQuery();
		  if(rs.next())
		  {
					Item it = new Item();
					 Blob blob = rs.getBlob(4);
					 InputStream inputStream = blob.getBinaryStream();
		                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		                byte[] buffer = new byte[4096];
		                int bytesRead = -1;
		                 
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);                  
		                }
		                 
		                byte[] imageBytes = outputStream.toByteArray();
		                 
		              
		                inputStream.close();
		                outputStream.close();
		    		it.setItemId(rs.getString(1));
		    		it.setName(rs.getString(2));
		    		it.setPrice(rs.getDouble(3));
		    	    it.setFile(imageBytes);
				return it;
		  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
