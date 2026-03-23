package com.java_exp4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Connection {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/Product";
		String username = "root";
		String password = "admin";
		String query = "select * from Product_Details";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(url, username, password);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				System.out.println("Id: " + rs.getInt("Pro_id") +
						" , Name: " + rs.getString("Pro_name") +
						", Price: " + rs.getFloat("Pro_price"));
			}
			rs.close();
			st.close();
			c.close();
			System.out.println("Connection closed.");
			}
		catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: "
		+ e.getMessage());
			}
		catch ( SQLException e) {
			System.err.println("SQL Error: "
		+ e.getMessage());
			}
		}
}
