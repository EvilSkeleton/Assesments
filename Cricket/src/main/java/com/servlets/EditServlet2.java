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

@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String sid=request.getParameter("no");
        int id=Integer.parseInt(sid);
        String name=request.getParameter("name");
        sid=request.getParameter("match");
        int match=Integer.parseInt(sid);
        sid=request.getParameter("runs");
        int runs=Integer.parseInt(sid);
        sid=request.getParameter("wick");
        int wick=Integer.parseInt(sid);
        sid=request.getParameter("zero");
        int zero=Integer.parseInt(sid);
        String pos=request.getParameter("position");
          
        Player p=new Player();  
        p.setJerseyNumber(id);
		p.setName(name);
		p.setMatchesPlayed(match);
		p.setRunsScored(runs);
		p.setWicketsTaken(wick);
		p.setOutsOnZero(zero);
		p.setType(pos); 
          
        int status=PlayerManager.update(p);  
        if(status>0){  
            response.sendRedirect("ViewServlet");  
        }else{  
            out.println("Sorry! unable to update record");  
        }  
          
        out.close(); 
	}

}
