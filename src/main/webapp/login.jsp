<!DOCTYPE HTML>
<html>
	<head>
		<title>Pinger</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<script src="js/validate.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="login">
			<div class="drops"></div>
			<form action="loginhandle.action" name="login" onsubmit="return validate()">
				<h1>Welcome Pinger</h1>
				<fieldset id="inputs">
					<input id="username" name="username" type="text" placeholder="Username" required>   
					<input id="password" name="password" type="password" placeholder="Password" required>
				</fieldset>
				<div id="tip"></div>
				<fieldset id="actions">
					<input type="submit" id="submit" value="Log in">
					<br>
					<br>
					<a href="#" id="register" onClick="register()">You have no account?</a>
				</fieldset>
			</form>
		</div>
	</body>
</html>