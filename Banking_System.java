package com.java_exp5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banking_System {

	public static void Fund_Transfer(int fromAccount, int toAccount, double Amount){
		String url = "jdbc:mysql://localhost:3306/Banking_systems";
	
		String username = "root";
		String password = "admin";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(url, username, password);
			c.setAutoCommit(false);
			Statement st = c.createStatement();
			PreparedStatement pst = null;
			ResultSet rs = null;
			rs = st.executeQuery("Select * from Account_Details");
			while(rs.next())
			{
				System.out.println("Id: " + rs.getInt("Account_id") +
						" , Name: " + rs.getString("Account_holder_name") +
						", Price: " + rs.getDouble("Balance"));
			}
			String query = "select Balance from Account_Details where Account_id= ? ";
			pst = c.prepareStatement(query);
			pst.setInt(1, fromAccount);
			rs = pst.executeQuery();
			if(rs.next()) {
				double Balance = rs.getDouble("Balance");
				if(Balance < Amount) {
					System.out.println("Insufficient Balance");
					return;
				}
				String Updatefromquery = "Update Account_Details set Balance = Balance - ? where Account_id= ? ";
				pst = c.prepareStatement(Updatefromquery);
				pst.setDouble(1, Amount);
				pst.setInt(2, fromAccount);
				int rowFrom = pst.executeUpdate();
				String Updatetoquery = "Update Account_Details set Balance = Balance + ? where Account_id= ? ";
				pst = c.prepareStatement(Updatetoquery);
				pst.setDouble(1, Amount);
				pst.setInt(2, toAccount);
				int rowTo = pst.executeUpdate();
				
				if(rowFrom>0 && rowTo>0) {
					c.commit();
					System.out.println("Transfer Successfull");
					}
				else {
					c.rollback();
					System.out.println("Transfer Failed");
				}
			}
				else {
					System.out.println("Account Not found");
				}
			
			rs = st.executeQuery("Select * from Account_Details");
			while(rs.next())
			{
				System.out.println("Id: " + rs.getInt("Account_id") +
						" , Name: " + rs.getString("Account_holder_name") +
						", Price: " + rs.getDouble("Balance"));
				}
			rs.close();
			st.close();
			c.close();
			System.out.println("Connection closed.");
			}
		catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: "+ e.getMessage());
			}
		catch (SQLException e) {
			System.err.println("SQL Error: "+ e.getMessage());
			}
		}
	public static void main(String[] args) {
		Fund_Transfer(1,2,1000);
		}
	}
	