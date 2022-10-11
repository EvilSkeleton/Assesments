package com.sep8;
import java.sql.*;

public class JdbcSep8 {
	public static void main(String[] args) throws Exception{
		System.out.println("----------Welcome to the Employee Database----------");
		System.out.println("Please wait while queries are being executed...");
		Connection conn;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//create connection object
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sep8","root","Daniel25#");
			//create statement object
			String sql1="select upper(concat(first_name,' ',last_name)) as full_name from WorkerTable";
//			String sql="SELECT * FROM WorkerTable";
			String sql2="select distinct(department) from WorkerTable";
			String sql3="select first_name,position('a' in first_name) from WorkerTable";

			System.out.println("\n");
			System.out.println("Question 1 : Using JDBC, execute a SQL query to fetch “FIRST_NAME, LAST_NAME” from the Worker table using the alias name as “FULL_NAME” results in ``UPPER CASE.");
			System.out.println("Executing Query 1 : ");
			
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())
			{
				System.out.println(rs1.getString(1));
			}
			ps1.close();

			System.out.println("\n");
			System.out.println("Question 2 : Using JDBC, Execute a SQL query to fetch unique values of DEPARTMENT from Worker-Table.");
			System.out.println("Executing Query 2 : ");
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next())
			{
				System.out.println(rs2.getString(1));
			}
			ps2.close();
			
			System.out.println("\n");
			System.out.println("Question 3 : Using JDBC, Execute a SQL query to find the position of the alphabet (‘a’) in the first name column ‘Amitabh’ from the Worker-Table.");
			System.out.println("Executing Query 3 : ");
			
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ResultSet rs3 = ps3.executeQuery();
			while(rs3.next())
			{
				System.out.println(rs3.getString(1)+"\t"+rs3.getInt(2));
			}
			ps3.close();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
