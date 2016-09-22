<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<head>
<title>Strava Friends - Run Leaderboard</title>
<%@ include file="include/header.jsp"%>
<%@ include file="include/navigation.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">

			<div data-ng-controller="runLeaderboardController">
				<button
					data-ng-click="getDataFromServer(); setContent('include/ng/run_leaderboard_search_results.html')"">Fetch
					data using the selected criteria</button>
				<div data-ng-include="content"></div>
			</div>

		</div>
		<div class="col-sm-2"></div>
	</div>
</div>
</body>
<%@ include file="include/footer.jsp"%>