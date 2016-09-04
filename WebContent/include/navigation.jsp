
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
				<li><a href="http://fumba.me/"><span
						class="glyphicon glyphicon-globe"></span> &nbsp; fumba.me</a></li>
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
	