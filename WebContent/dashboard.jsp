<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp" %>

<head>
<title>Strava Friends - Dashboard</title>
<%@ include file="include/header.jsp"%>
</head>

<body>

	<%@ include file="include/navigation.jsp"%>

	<div class="container">

		<div class="row">
			<div class="col-sm-12">
				<div class="grid">

					<div class="grid-item grid-item--width2 grid-item--height2">
						<h4>Your Profile information</h4>
						<s:property value="stravaAthlete.firstname"/>&nbsp; <s:property value="stravaAthlete.lastname"/><br>
						<s:property value="stravaAthlete.city"/>, <s:property value="stravaAthlete.state"/>, <s:property value="stravaAthlete.country"/><br>
					</div>

					<div class="grid-item grid-item--width2 grid-item--height2">
						<h4>Followers/Following</h4>
					</div>

					<div class="grid-item grid-item--width2 grid-item--height2">
						<h4>People that do not follow back</h4>
					</div>

					<div class="grid-item grid-item--width2 grid-item--height2">
						<h4>Friend Locations</h4>
					</div>

					<div class="grid-item grid-item--width2 grid-item--height2">
						<h4>Friends Near you</h4>
					</div>

					<div class="grid-item grid-item--width2 grid-item--height2">
						<h4>Shared Clubs</h4>
					</div>

				</div>
			</div>
		</div>

	</div>


</body>

<%@ include file="include/footer.jsp"%>