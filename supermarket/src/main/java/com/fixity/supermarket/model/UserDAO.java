package com.fixity.supermarket.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import com.fixity.supermarket.dao.DbConnection;
import com.fixity.supermarket.model.Item;
import com.fixity.supermarket.model.User;

public class UserDAO {
	
	
	
	public boolean saveUser(User user) {
		Connection con = DbConnection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into User values(?,?,?,?,?,?)");
			ps.setString(1, user.getFname());
			ps.setString(2, user.getLname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getUname());
			ps.setString(6, user.getPassword());
			
			
			int n = ps.executeUpdate();
			if(n>0) 
				return true;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public User getUser(String username) {
		Connection conc = DbConnection.getConnection();
		User user;
		try {
			String sql = "select * from User where uname=?";
			PreparedStatement psr = conc.prepareStatement(sql);
			psr.setString(1, username);
			ResultSet rs = psr.executeQuery();
			
			if(rs.next()) {
				user = new User();		
				user.setUname(rs.getString(5));
		        return user;	
			}
		
			
		}
		catch (Exception e) {
			e.getStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public User getAccount(String name) {
		// TODO Auto-generated method stub
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from User where uname=?");
		    pst.setString(1,name);
			ResultSet rs=pst.executeQuery();
		  while(rs.next())
		  {
		    User user=new User();
		    user.setFname(rs.getString(1));
		    user.setLname(rs.getString(2));
		    user.setEmail(rs.getString(3));
		    user.setPhone(rs.getString(4));
		    user.setUname(rs.getString(5));
		    return user;
		  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
public List<User> getUsers(){
		
		List<User> users = new Vector<User>();
		
		try {
			Connection con = DbConnection.getConnection();
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from User");
			while(rs.next()) {
				User user = new User();
				 user.setFname(rs.getString(1));
				    user.setLname(rs.getString(2));
				    user.setEmail(rs.getString(3));
				    user.setPhone(rs.getString(4));	
				    users.add(user);
			}						
		}
		catch (Exception e) {
			e.getStackTrace();
			// TODO: handle exception
		}
		return users;
	}
}
