package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cricket.Player;
import com.cricket.PlayerManager;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out=response.getWriter(); 
        out.print("<link rel='stylesheet' type='text/css' href='style.css'>");
        out.print("<body class='background'>");
        
        List<Player> list=PlayerManager.getAllPlayerEmployees();  
        out.println("<a href='Home.html'>Add New Player</a>");
        out.print("<br>Number of Bowlers :"+PlayerManager.bowlers());
        out.print("<br>Number of Wicket-Keepers :"+PlayerManager.wicketKeepers());
        
        if(PlayerManager.bowlers()>=3 && PlayerManager.wicketKeepers()>=1)
        {
        	out.println("<br><a href='FinalTeam'>Assemble Final Team</a>");
        }
        else
        {
        	out.println("<br>Team must include minimum 3 bowlers and maximum 1 wicket keeper.");
        }
        
        out.println("<h1>Player List</h1>"); 
        
        out.print("<table border='1' width='100%' style='background-color:#FFFFE0'");  
        
        out.print("<tr><th>JerseyNo</th><th>PlayerName</th><th>Matches</th><th>Runs</th><th>Wickets</th><th>ZeroOut</th><th>Position</th><th>Edit</th><th>Delete</th></tr>");  
       
        for(Player p:list)
        {  
         out.print("<tr><td>"+p.getJerseyNumber()+"</td><td>"+p.getName()+"</td><td>"+p.getMatchesPlayed()+"</td><td>"+p.getRunsScored()+"</td><td>"+p.getWicketsTaken()+"</td><td>"+p.getOutsOnZero()+"</td><td>"+p.getType()+"</td><td><a href='EditServlet?id="+p.getJerseyNumber()+"'>edit</a></td><td><a href='DeleteServlet?id="+p.getJerseyNumber()+"'>delete</a></td></tr>");  
        }  
        out.print("</table>");  
        out.print("</body>");
          
        out.close(); 
	}

}
