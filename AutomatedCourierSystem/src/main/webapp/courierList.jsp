<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet,java.sql.Connection,java.sql.PreparedStatement,SQL.CourierManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check by Courier</title>
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<style>
.center {
text-align: center;
}
</style>
<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">The Dependable Courier</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="Home.jsp">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="newDelivery.jsp">Transport your Package</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="courierList.jsp">Check your Route</a>
          </li>
        </ul>
        <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
      </div>
    </div>
  </nav>
</header>


	<div class="center">
		<form action="" method="post">
		<br><br><br>
			<table class="center" style="margin-left:auto;margin-right:auto;">
				<tr>
					<td align="right">Enter Courier Name : </td>
					<td align="left"><input required="required" type = "text" name = "name"></td>
				</tr>
			</table>
			<br>
			<input class="btn btn-primary" type = "submit" value="Show List"><br><br>
		</form>
		
		<%
		String name = request.getParameter("name");
        
		Connection conn = CourierManager.getConnection();
		PreparedStatement st = conn.prepareStatement("select * from CourierList where name=?");
        st.setString(1,name);
        ResultSet rs = st.executeQuery();
        if(rs.next())
        {
        	PreparedStatement ps = conn.prepareStatement("select * from "+name);
        	ResultSet rs1 = ps.executeQuery();
	        out.print("<table border='1' width='100%' style='background-color:#FFFFE0'");
	        out.print("<tr><td>Weight</td><td>Date</td><td>Area</td><td>Customer Name</td><td>Customer Mobile</td><td>Mark if Done</td></tr>");
	        while(rs1.next())
	        {
	        	out.print("<tr><td>"+rs1.getDouble(1)+"</td><td>"+rs1.getDate(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td><a href='DeleteShip?no="+rs.getString(4)+"'>Completed</a></td></tr>");
	        }//<td><a href='EditServlet?id="+rs.getInt(1)+"'>Select</a></td>
	        out.print("</table>");
        }
        else
        {
        	out.print("Name is not found in database.");
        }
        %>
		
	</div>
</body>
</html>