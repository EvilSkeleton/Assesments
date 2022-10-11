package SQL;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CourierServ")
public class CourierServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String sid=request.getParameter("no");  
        int no=Integer.parseInt(sid);
        sid=request.getParameter("weight");
        double weight = Double.parseDouble(sid);
        sid=request.getParameter("time");
        long millis = Long.parseLong(sid);
        java.sql.Date date = new java.sql.Date(millis);
        String area=request.getParameter("area");
        String name=request.getParameter("name");
        String mob=request.getParameter("mobile");
        sid=request.getParameter("frpg");
        double frpg = Double.parseDouble(sid);
        frpg = frpg * weight;
        String courierName;
        Connection conn;
        PreparedStatement st;
        PreparedStatement ps;
        try
        {
        	conn = CourierManager.getConnection();
        	st = conn.prepareStatement("select name from CourierList where CourierNo=?");
        	st.setInt(1,no);
        	ResultSet rs = st.executeQuery();
        	rs.next();
        	courierName = rs.getString(1);
        	
        	ps = conn.prepareStatement("insert into "+courierName+" values(?,?,?,?,?)");
        	ps.setDouble(1,weight);
        	ps.setDate(2,date);
        	ps.setString(3,area);
        	ps.setString(4,name);
        	ps.setString(5,mob);
        	ps.executeUpdate();
        	
        	out.print("<head>"
        			+ "<link href=\"css/bootstrap.css\" rel=\"stylesheet\">"
        			+ "</head>");
        	
        	out.print("<header>\n"
        			+ "  <nav class=\"navbar navbar-expand-md navbar-dark fixed-top bg-dark\">\n"
        			+ "    <div class=\"container-fluid\">\n"
        			+ "      <a class=\"navbar-brand\" href=\"#\">The Dependable Courier</a>\n"
        			+ "      <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarCollapse\" aria-controls=\"navbarCollapse\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
        			+ "        <span class=\"navbar-toggler-icon\"></span>\n"
        			+ "      </button>\n"
        			+ "      <div class=\"collapse navbar-collapse\" id=\"navbarCollapse\">\n"
        			+ "        <ul class=\"navbar-nav me-auto mb-2 mb-md-0\">\n"
        			+ "          <li class=\"nav-item\">\n"
        			+ "            <a class=\"nav-link\" aria-current=\"page\" href=\"Home.jsp\">Home</a>\n"
        			+ "          </li>\n"
        			+ "          <li class=\"nav-item\">\n"
        			+ "            <a class=\"nav-link\" href=\"newDelivery.jsp\">Transport your Package</a>\n"
        			+ "          </li>\n"
        			+ "          <li class=\"nav-item\">\n"
        			+ "            <a class=\"nav-link\" href=\"courierList.jsp\">Check your Route</a>\n"
        			+ "          </li>\n"
        			+ "        </ul>\n"
        			+ "        <form class=\"d-flex\" role=\"search\">\n"
        			+ "          <input class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n"
        			+ "          <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\n"
        			+ "        </form>\n"
        			+ "      </div>\n"
        			+ "    </div>\n"
        			+ "  </nav>\n"
        			+ "</header>");
        	
        	out.print("<br><br><br>"
        			+ "<h1>Delivery Order succesfully placed.</h1>");
        	
        	out.print("<h2>You will be charged Rupees."+frpg+" to send the parcel to "+area+".</h2>");
        	
        	out.print("<a href=\"Home.jsp\">\n"
        			+ "		<button name=\"menu\">Back to Menu</button>\n"
        			+ "	</a>");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}

}
