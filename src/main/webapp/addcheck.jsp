<%@ page language="java" contentType="text/html;"%>
<%@ page language="java" import="org.hyy.mns.models.*"%>
<%@ page language="java" import="org.hyy.mns.daos.*"%>
<%@ page language="java" import="java.util.List"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body id="main">
	<%
		User user = (User)session.getAttribute("LOGINUSER");
		if(user==null){
			response.sendRedirect("login.jsp");
		}
	%>
		<div id="header">
			<div id="logo">
				<h1>Pinger<span>beta</span></h1>
			</div>
			<div id="nav">
				<ul>
					<li><a href="#">Dashboard</a></li>
					<li><a href="home.action">Check</a></li>
					<li><a href="#">Help</a></li>
					<li><a href="logouthandle.action">Logout</a></li>
				</ul>
			</div>
		</div>
		<div id="middle">
			<div id="left">
				<div id="sideBar">
					<ul>	
						<li class="addC"><a href="home.action">Back to Home</a></li>
					</ul>
				</div>
				<div id="gettingStarted" class="box dark">
    				<h3>Check Deploy</h3>
    				<p>On this page you can new a check to monitor the site you want.</p>
				</div>
			</div>
			<div id="right">
				<div class="rightTitle">
					<h2>Add Check</h2>
				</div>
				<div id="checkDetail">
					<form method="post" action="addCheck.action">
						Name: <input name="checkname" type="text"/><br/>
						URL: <input name="checkurl" type="text"/><br/>
						Frequency: 
						<select name="checkFrequency">
							<option value="1">1</option>
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="60">60</option>
						</select> minutes<br/>
						Notify when consecutively fail: <input name="checklimit" type="text" size="3"/>times.<br/>
						<input type="submit" value="create"/>
					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">
			<p>Designed by yunfeng.guo</p>
		</div>
	</body>
</html>