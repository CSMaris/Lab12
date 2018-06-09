package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}



    public LocalDate getMaxDateRiver(int id) {
    	
    	final String sql="select max(day) as max from flow where river=?";
    	LocalDate result = null;
    	try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			if (res.next()) 
				result= res.getDate("max").toLocalDate();
			
			conn.close();
			
	}
		
		 catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		
    	
    }
    	return result;
    }
    
   public LocalDate getMinDateRiver(int id) {
    	
    	final String sql="select min(day) as min from flow where river=?";
    	LocalDate result = null;
    	try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			if (res.next()) 
				result= res.getDate("min").toLocalDate();
			
			conn.close();
			
	}
		
		 catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		
    	
    }
    	return result;
    }
   
   public int getTotRiver(int id) {
   	
   	final String sql="select count(*) as num from flow where river=? group by river";
   	int result = 0;
   	try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			if (res.next()) 
				result= res.getInt("num");
			
			conn.close();
			
	}
		
		 catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		
   	
   }
   	return result;
   }
   
   public double getAvgRiver(int id) {
	   	
	   	final String sql="select avg(flow) as avg from flow where river=?";
	   	double result = 0;
	   	try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, id);
				ResultSet res = st.executeQuery();

				if (res.next()) 
					result= res.getInt("avg");
				
				conn.close();
				
		}
			
			 catch (SQLException e) {
				//e.printStackTrace();
				throw new RuntimeException("SQL Error");
			
	   	
	   }
	   	return result;
	   }
   
   public List<Flow> getFlows(int id) {
	   	
	   	final String sql="select * from flow where river=?";
	   	List<Flow> result=new ArrayList<Flow>();
	   	try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, id);
				ResultSet res = st.executeQuery();

				while (res.next()) { 
					Flow flow=new Flow(res.getDate("day").toLocalDate(),res.getDouble("flow"),res.getInt("river"));
					result.add(flow);
				}
				
				conn.close();
				
		}
			
			 catch (SQLException e) {
				//e.printStackTrace();
				throw new RuntimeException("SQL Error");
			
	   	
	   }
	   	return result;
	   }
   
   
   
}
