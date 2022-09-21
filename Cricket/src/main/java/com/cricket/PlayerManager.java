package com.cricket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			String dbDriver = "com.mysql.jdbc.Driver";
	        String dbURL = "jdbc:mysql://localhost:3306/";
	        // Database name to access
	        String dbName = "cricket";
	        String dbUsername = "root";
	        String dbPassword = "Daniel25#";
	  
	        Class.forName(dbDriver);
	        conn = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	public static int save(Player p)
	{
		int status = 0;
		try 
		{
			Connection con = PlayerManager.getConnection();
			
			PreparedStatement ps = con.prepareStatement("insert into playerlist(no,name,match_played,runs_scored,wickets_taken,out_on_zero,position) values (?,?,?,?,?,?,?)");
			ps.setInt(1,p.getJerseyNumber());
			ps.setString(2,p.getName());
			ps.setInt(3,p.getMatchesPlayed());
			ps.setInt(4,p.getRunsScored());
			ps.setInt(5,p.getWicketsTaken());
			ps.setInt(6,p.getOutsOnZero());
			ps.setString(7,p.getType());

			status = ps.executeUpdate();

			con.close();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public static int update(Player p) {
		int status = 0;
		try {
			Connection con = PlayerManager.getConnection();
			PreparedStatement ps = con.prepareStatement("update playerlist set name=?,match_played=?,runs_scored=?,wickets_taken=?,out_on_zero=?,position=? where no=?");
			ps.setString(1,p.getName());
			ps.setInt(2,p.getMatchesPlayed());
			ps.setInt(3,p.getRunsScored());
			ps.setInt(4,p.getWicketsTaken());
			ps.setInt(5,p.getOutsOnZero());
			ps.setString(6,p.getType());
			ps.setInt(7,p.getJerseyNumber());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = PlayerManager.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from playerlist where no=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static Player getPlayerById(int id) {
		Player p = new Player();

		try {
			Connection con = PlayerManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from playerlist where no=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p.setJerseyNumber(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setMatchesPlayed(rs.getInt(3));
				p.setRunsScored(rs.getInt(4));
				p.setWicketsTaken(rs.getInt(5));
				p.setOutsOnZero(rs.getInt(6));
				p.setType(rs.getString(7));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return p;
	}

	public static List<Player> getAllPlayerEmployees() {
		List<Player> list = new ArrayList<Player>();

		try {
			Connection con = PlayerManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from playerlist");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Player p = new Player();
				p.setJerseyNumber(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setMatchesPlayed(rs.getInt(3));
				p.setRunsScored(rs.getInt(4));
				p.setWicketsTaken(rs.getInt(5));
				p.setOutsOnZero(rs.getInt(6));
				p.setType(rs.getString(7));
				p.setAverageScore(rs.getDouble(8));
				list.add(p);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public static int bowlers() {
		int flag=0;
		try {
			Connection con = PlayerManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from playerlist");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString(7).matches("Bowler"))
				{
					flag++;
				}
			}
			con.close();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static int wicketKeepers() {
		int flag=0;
		try {
			Connection con = PlayerManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from playerlist");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(7));
				if(rs.getString(7).matches("Wicket-Keeper"))
				{
					flag++;
				}
				System.out.println(flag);
			}
			con.close();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static int finalTeamAvg()
	{
		Player p = new Player();
		try 
		{
			Connection con = PlayerManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from playerlist");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p.setMatchesPlayed(rs.getInt(3));
				p.setRunsScored(rs.getInt(4));
				double avg= (p.getMatchesPlayed()+p.getRunsScored())/2.0;
				PreparedStatement ps1 = con.prepareStatement("update playerlist set avg="+avg+" where no="+rs.getInt(1));
				ps1.execute();
			}
			con.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
