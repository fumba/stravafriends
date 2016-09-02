<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Strava Friends</title>
</head>
<body>

	<s:a href="https://www.strava.com/oauth/authorize?client_id=13317&response_type=code&redirect_uri=http://localhost:8080/stravafriends/connect&scope=write&state=mystate&approval_prompt=force">
		<img alt="Connect with Strava" src="assets/img/ConnectWithStrava.png" />
	</s:a>


	
</body>
</html>