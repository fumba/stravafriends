<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<s:action name="loadApplicationAttributes"></s:action>

<head>
<title>Strava Friends</title>
<%@ include file="include/header.jsp"%>

<%@ include file="include/navigation.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-sm-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			This tool allows strava athletes to analyze and manage their strava
			friending activities. The following functionalities can currently be
			performed on strava profiles.<br> <br>

			<table class="table">
				<tbody>
					<tr>
						<th scope="row"><span class="glyphicon glyphicon-menu-right"
							aria-hidden="true"></span></th>
						<td>Followers</td>
						<td>Find out who is not following you back</td>
					</tr>
					<tr>
						<th scope="row"><span class="glyphicon glyphicon-menu-right"
							aria-hidden="true"></span></th>
						<td>Run and Ride Leaderboard</td>
						<td>Find out where you rank amongst your friends.</td>
					</tr>
					<tr>
						<th scope="row"><span class="glyphicon glyphicon-menu-right"
							aria-hidden="true"></span></th>
						<td>Clubs</td>
						<td>Show clubs that are shared mutually between you and
							friends.</td>
					</tr>
					<tr>
						<th scope="row"><span class="glyphicon glyphicon-menu-right"
							aria-hidden="true"></span></th>
						<td>Friend Locations</td>
						<td>Display friend locations in a map and table format.</td>
					</tr>
				</tbody>
			</table>

		</div>
		<div class="col-sm-2"></div>
	</div>

	<div class="row">
		<div class="col-sm-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">

			<a href="${attr.StravaAppAuthorizationURL}"> <img
				alt="Connect with Strava" src="assets/img/ConnectWithStrava.png" />
			</a> <br> <br>
			<br> <span class="glyphicon glyphicon-envelope"
				aria-hidden="true"></span> <a
				href="mailto:fumba.me@gmail.com?Subject=Strava%20Friends%20Feedback"
				target="_top">fumba.me@gmail.com</a> - Let me know about your ideas,
			comments, opinions,... <br> <br>
		</div>
		<div class="col-sm-2"></div>
	</div>

</div>
</body>

<%@ include file="include/footer.jsp"%>