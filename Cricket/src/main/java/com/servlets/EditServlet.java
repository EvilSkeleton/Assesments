package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cricket.Player;
import com.cricket.PlayerManager;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html"); 
		
        PrintWriter out=response.getWriter();  
        
        out.print("<link rel='stylesheet' type='text/css' href='style.css'>");
        out.print("<body class='background'>");
        
        out.println("<h1>Update Player</h1>"); 
        
        String sid=request.getParameter("id");  
        
        int id=Integer.parseInt(sid);  
          
        Player p=PlayerManager.getPlayerById(id);  
        
//          out.println(p.getJerseyNumber());
        out.print("<div align='center'>");
        out.print("<form action='EditServlet2' method='post'>");  
        out.print("<table>");  
        out.print("<tr><td></td><td><input type='hidden' name='no' value='"+p.getJerseyNumber()+"'/></td></tr>");  
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+p.getName()+"'></td></tr>");
        out.print("<tr><td>Matches:</td><td><input type='number' name='match' value='"+p.getMatchesPlayed()+"'/></td></tr>");
        out.print("<tr><td>Runs:</td><td><input type='number' name='runs' value='"+p.getRunsScored()+"'/></td></tr>");
        out.print("<tr><td>Wickets:</td><td><input type='number' name='wick' value='"+p.getWicketsTaken()+"'/></td></tr>");
        out.print("<tr><td>Zero Out:</td><td><input type='number' name='zero' value='"+p.getOutsOnZero()+"'/></td></tr>");
        out.print("<tr><td>Position:</td><td>");  
        out.print("<select name='position' style='width:150px'>");  
        out.print("<option selected='selected'>"+p.getType()+"</option>");
        out.print("<option value='Batsman'>Batsman</option>");  
        out.print("<option value='Bowler'>Bowler</option>");  
        out.print("<option value='Wicket-Keeper'>Wicket-Keeper</option>");  
        out.print("<option value='All-Rounder'>All-Rounder</option>");  
        out.print("</select>");  
        out.print("</td></tr>");  
        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></	td></tr>");  
        out.print("</table>");  
        out.print("</form>");  
        out.print("</div>");
        
        out.print("</body>");
          
        out.close();
	}
}
