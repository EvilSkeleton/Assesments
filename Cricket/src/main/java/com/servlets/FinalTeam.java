package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cricket.Player;
import com.cricket.PlayerAverageScoreComparator;
import com.cricket.PlayerManager;

@WebServlet("/FinalTeam")
public class FinalTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void addBowlers(List<Player> playersSet, int nBowlers, List<Player> finalEleven){
        int i = nBowlers;
        Iterator<Player> itr = playersSet.iterator();
        List<Player> bowlers = new ArrayList<>();
        while(itr.hasNext()){
            Player p = itr.next();
            if(p.getType().equals("Bowler")){
                bowlers.add(p);
            }
        }
        Collections.sort(bowlers);
        ListIterator<Player> itr1 = bowlers.listIterator(bowlers.size());
        while(i>0){
            Player p = itr1.previous();
            finalEleven.add(p);
            i--;
        }
    }
	    
    public void addWicketers(List<Player> playersSet, List<Player> finalEleven){
        Iterator<Player> itr = playersSet.iterator();
        List<Player> wicketers = new ArrayList<>();
        while(itr.hasNext()){
            Player p = itr.next();
            if(p.getType().equals("Wicket-Keeper")){
                wicketers.add(p);
            }
        }
        Collections.sort(wicketers);
        ListIterator<Player> itr1 = wicketers.listIterator(wicketers.size());
        finalEleven.add(itr1.previous());
    }
	
	public void addBatsmans(List<Player> playersSet, int nBatsmans, List<Player> finalEleven){
        int i = nBatsmans;
        Iterator<Player> itr = playersSet.iterator();
        List<Player> batsmans = new ArrayList<>();
        while(itr.hasNext()){
            Player p = itr.next();
            if(p.getType().equals("Batsman") || p.getType().equals("All-Rounder")){
                batsmans.add(p);
            }
        }
        Collections.sort(batsmans);
        ListIterator<Player> itr1 = batsmans.listIterator(batsmans.size());
        while(i>0){
            Player p = itr1.previous();
            finalEleven.add(p);
            i--;
        }
    }
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        PlayerManager.finalTeamAvg();
        
        out.print("<link rel='stylesheet' type='text/css' href='style.css'>");
        out.print("<body class='background'>");
		out.println("Final Team");

		int bowlers = PlayerManager.bowlers();
		int wic = PlayerManager.wicketKeepers();
        
        List<Player> playerSet=PlayerManager.getAllPlayerEmployees(); 
        System.out.println(playerSet);
        try 
        {
			Connection con = PlayerManager.getConnection();
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from cricket");
//			while(rs.next()) {
//				Player p = new Player(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7));
//				playerSet.add(p);
//			}
			List<Player> finalEleven = new ArrayList<>();
			
			addBowlers(playerSet, bowlers, finalEleven);
	        if(wic >= 1){
	            addWicketers(playerSet, finalEleven);
	            addBatsmans(playerSet, 10 - bowlers, finalEleven);
	        }
	        else{
	            addBatsmans(playerSet, 11 - bowlers, finalEleven);
	        }
	        
	        Collections.sort(finalEleven,new PlayerAverageScoreComparator());
	        
//			out.println("<table>"
//					+ "<tr>"
//					+ "<th>Player ID</th>"
//					+ "<th>Player name</th>"
//					+ "<th>Matches played</th>"
//					+ "<th>Runs scored</th>"
//					+ "<th>Wickets taken</th>"
//					+ "<th>Out on zero</th>"
//					+ "<th>Player type</th>"
//					+ "</tr>");
//			Iterator<Player> itr = finalEleven.iterator();
//			while(itr.hasNext()) {
//				Player p = itr.next();
//				out.println("<tr>"
//						+ "<td>"+p.getJerseyNumber()+"</td>"
//						+ "<td>"+p.getName()+"</td>"
//						+ "<td>"+p.getMatchesPlayed()+"</td>"
//						+ "<td>"+p.getRunsScored()+"</td>"
//						+ "<td>"+p.getWicketsTaken()+"</td>"
//						+ "<td>"+p.getOutsOnZero()+"</td>"
//						+ "<td>"+p.getType()+"</td>"
//						+ "</tr>");
//			}
//			out.println("</table>");
	        
	        out.println("<h1>Player List</h1>"); 
	        
	        out.print("<table border='1' width='100%' style='background-color:#FFFFE0'");  
	        
	        out.print("<tr><th>JerseyNo</th><th>PlayerName</th><th>Matches</th><th>Runs</th><th>Wickets</th><th>ZeroOut</th><th>Position</th><th>Edit</th><th>Delete</th></tr>");  
	       
	        for(Player p:finalEleven)
	        {  
	        	out.print("<tr><td>"+p.getJerseyNumber()+"</td><td>"+p.getName()+"</td><td>"+p.getMatchesPlayed()+"</td><td>"+p.getRunsScored()+"</td><td>"+p.getWicketsTaken()+"</td><td>"+p.getOutsOnZero()+"</td><td>"+p.getType()+"</td><td><a href='EditServlet?id="+p.getJerseyNumber()+"'>edit</a></td><td><a href='DeleteServlet?id="+p.getJerseyNumber()+"'>delete</a></td></tr>");  
	        }  
	        out.print("</table>"); 
	        out.print("</body>");
	        
	        out.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
