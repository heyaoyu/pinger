<%@ page language="java" contentType="text/html;"%>
<%@ page language="java" import="org.hyy.mns.models.*"%>
<%@ page language="java" import="org.hyy.mns.daos.*"%>
<%@ page language="java" import="java.util.List"%>
<html>
	<head>
		<script type="text/javascript" src="js/jquery.js"></script>
  		<script type="text/javascript" src="js/jquery.validate.js"></script>
  		<script type="text/javascript" src="js/messages.js"></script>
		<script>
    		$(document).ready(function(){
      			$("#addCheckForm").validate();
    		});
  		</script>
		<title>Pinger</title>
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
		<div class="clear"></div>
		<div id="middle">
			<div id="left">
				<div id="sideBar">
					<ul>	
						<li class="addC"><a href="home.action">Back to Home</a></li>
					</ul>
				</div>
				<div class="guide">
					<p><span>G</span>uide</p>
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
				<div id="checkEditor">		
					<form id="addCheckForm" class="checkForm" method="post" action="addCheck.action">				
						Name: <input class="field required" name="checkname" type="text"/><br/>
						URL: <input class="field required" name="checkurl" type="text"/><br/>
						Frequency: 
						<select class="field"  name="checkFrequency">
							<option value="1">1</option>
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="60">60</option>
						</select> minutes<br/>
						Notify when consecutively fail: <input name="checklimit" type="text" size="3" class="field" />times.<br/>
						Other stakeholders: <input class="field" name="notifies" type="text"/><br/>
						<input class="create" type="submit" value="create"/>
					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">
			<h3>Contact us: </h3>
			<p>yaoyu,he@thomsonreuters.com</p>
			<p>yunfeng,guo@thomsonreuters.com</p>
		</div>
	</body>
</html>