<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<head>
<title>Strava Friends - Dashboard</title>
<%@ include file="include/header.jsp"%>

<%@ include file="include/navigation.jsp"%>

<div class="container">

	<div class="row">
		<div class="col-sm-12">
			<div class="grid">

				<div class="grid-item grid-item--width2 grid-item--height2">
					<h4>Your Profile information</h4>
					<s:property value="authenticatedAthlete.firstname" />
					&nbsp;
					<s:property value="authenticatedAthlete.lastname" />
					<br>
					<s:property value="authenticatedAthlete.city" />
					,
					<s:property value="authenticatedAthlete.state" />
					,
					<s:property value="authenticatedAthlete.country" />
					<br>
				</div>

				<div class="grid-item grid-item--width2 grid-item--height2">
					<h4>Followers/Following</h4>
					Followers :
					<s:property value="authenticatedAthlete.followerCount" />
					<br> Following :
					<s:property value="authenticatedAthlete.friendCount" />
					<br> Not following back:
					<s:property value="notFollowingBackCount" />
					&nbsp;<a href="loadNotFollowingBack.action">view</a><br>
					<%--  Not friended back:
					<s:property value="notFriendedBackCount" />
					&nbsp;<a href="loadNotFriendedBack.action">view</a><br> --%>
				</div>

				<div class="grid-item grid-item--width2 grid-item--height2"></div>

				<div class="grid-item grid-item--width2 grid-item--height2">
					<h4>Friend Locations</h4>
					<a href="friendLocationList.action">Show List</a><br>
					<a href="friendLocationMap.action">View on map</a><br>
				</div>

				<div class="grid-item grid-item--width2 grid-item--height2"></div>

				<div class="grid-item grid-item--width2 grid-item--height2">
					<h4>Clubs</h4>
					Mutual Clubs:
					<s:property value="mutualClubs" />
					<br><a href="#">view detailed info</a><br><br>
					Suggested Clubs (subscribed by friends):
					<s:property value="suggestedClubs" />
					<br><a href="#">view detailed info</a><br>
				</div>

			</div>
		</div>
	</div>

</div>
</body>

<%@ include file="include/footer.jsp"%>