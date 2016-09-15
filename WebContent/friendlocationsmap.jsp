<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<head>
<title>Strava Friends - Friend Locations</title>
<%@ include file="include/header.jsp"%>
</head>

<body>

	<%@ include file="include/navigation.jsp"%>

	<script type="text/javascript">
		var cityListJSON = '<s:property value="friendLocations" escapeHtml="false"/>'
	</script>

	<script src="assets/js/friendmap.js"></script>

	<div class="container">
		<div class="row">
			<div class="col-sm-12">

				<div class="embed-responsive embed-responsive-16by9">
					<div id="map_canvas" class="embed-responsive-item"></div>
				</div>

			</div>
		</div>
	</div>
	<!-- container -->

	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDil8v5iG-2y1YX1xbKmPDYr3GPlFtmnSM&callback=initialize"></script>


</body>


<%@ include file="include/footer.jsp"%>