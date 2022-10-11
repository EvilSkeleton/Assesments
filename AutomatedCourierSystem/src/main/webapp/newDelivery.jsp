<%@page import="SQL.CourierManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet,java.sql.Connection,java.sql.PreparedStatement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Delivery</title>
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
            <a class="nav-link active" href="newDelivery.jsp">Transport your Package</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="courierList.jsp">Check your Route</a>
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


	<div class="center" style="align-content: center">
		<form action="" method="post">
		<br><br><br><br><br>
		<h1>Transport a Package</h1>
			<table style="margin-left:auto;margin-right:auto;">
				<tr>
					<td align="right">Enter Customer Name : </td>
					<td align="left"><input required="required" type = "text" name = "custName"></td>
				</tr>
				<tr>
					<td align="right">Enter Customer Mobile No : </td>
					<td align="left"><input required="required" type = "text" name = "custMobile"></td>
				</tr>
				<tr>
					<td align="right">Select City : </td>
					<td align="left">
						 <select required="required" name = "city">
							<option value="">select City</option>
							<option value="Bengaluru">Bengaluru</option>
							<option value="Hyderabad">Hyderabad</option>
							<option value="Mumbai">Mumbai</option>
							<option value="Delhi">Delhi</option>
							<option value="Kolkata">Kolkata</option>
							<option value="Panaji">Panaji</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">Enter Delivery Area : </td>
					<td align="left"><input required="required" type = "text" name = "delivArea"></td>
				</tr>
				<tr>
					<td align="right">Enter Parcel Weight(in grams) : </td>
					<td align="left"><input required="required" type = "number" name = "parcWeight"></td>
				</tr>
			</table>
			<input type="checkbox" required="required">I agree to the Terms and Conditions.&nbsp;&nbsp;<br><br>
			<input class="btn btn-primary" type = "submit" value="Calculate Cost"><br><br>
		</form>
		
		<%
		String city = request.getParameter("city");
		String weight = request.getParameter("parcWeight");
		String area = request.getParameter("delivArea");
		String name = request.getParameter("custName");
		String mob = request.getParameter("custMobile");
        
		Connection conn = CourierManager.getConnection();
		PreparedStatement st = conn.prepareStatement("select * from CourierList where city=?");
        st.setString(1,city);
        ResultSet rs = st.executeQuery();
        out.print("<table border='1' width='100%' style='border:2px solid black;margin-left:auto;margin-right:auto;'");
        out.print("<tr><td>Courier No</td><td>Courier Name</td><td>City</td><td>FRPG</td><td>Time</td><td>Select One</td></tr>");
        while(rs.next())
        {
	       	long millis=System.currentTimeMillis();
        	millis=millis+rs.getLong(5);
        	out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getDouble(4)+"</td><td>"+(((((rs.getLong(5) / 1000) / 60) / 60) / 24)  % 24)+"</td><td><a href='CourierServ?no="+rs.getInt(1)+"&weight="+weight+"&time="+millis+"&area="+area+"&name="+name+"&mobile="+mob+"&frpg="+rs.getDouble(4)+"'>Select</a></td></tr>");
        }//<td><a href='EditServlet?id="+rs.getInt(1)+"'>Select</a></td>
        out.print("</table>"); 
        %>
		
		
	</div>
</body>
</html>