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

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		
		String no = request.getParameter("id");
		int id = Integer.parseInt(no);
		String name = request.getParameter("name");
		String matches = request.getParameter("match");
		int match = Integer.parseInt(matches);
		String runs = request.getParameter("runs");
		int run = Integer.parseInt(runs);
		String wicket = request.getParameter("wicket");
		int wick = Integer.parseInt(wicket);
		String zero = request.getParameter("zero");
		int zeroOut = Integer.parseInt(zero);
		String pos = request.getParameter("position");
		
		Player p = new Player();
		p.setJerseyNumber(id);
		p.setName(name);
		p.setMatchesPlayed(match);
		p.setRunsScored(run);
		p.setWicketsTaken(wick);
		p.setOutsOnZero(zeroOut);
		p.setType(pos);
		
		int status = PlayerManager.save(p);  
        
        if(status>0)
        {  
            out.print("<p>Record saved successfully!</p>");  
            out.print("<a href=\"ViewServlet\">view players</a>");
            request.getRequestDispatcher("index.html").include(request, response);  
        }
        else
        {  
            out.println("Sorry! unable to save record");  
        }  
          
        out.close();
	}
}
