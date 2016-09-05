
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#stravaFriendsNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">STRAVA<small
				class="text-warning">FRIENDS</small></a>
		</div>
		<div class="collapse navbar-collapse" id="stravaFriendsNavbar">
			<ul class="nav navbar-nav navbar-right">

				<c:if test="${session.login == 'true'}">
					<li><a href="<%= request.getContextPath() %>/logout.action"><span
							class="glyphicon glyphicon-log-out"></span> Disconnect</a></li>
				</c:if>

			</ul>
		</div>
	</div>
</nav>

<script type="text/javascript">
	var url = window.location;
	// Will also work for relative and absolute hrefs
	$('ul.nav a').filter(function() {
		return this.href == url;
	}).parent().addClass('active');
</script>
