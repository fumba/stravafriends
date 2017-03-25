<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<head>
<title>Strava Friends - Bike Leaderboard</title>
<%@ include file="include/header.jsp"%>
<%@ include file="include/navigation.jsp"%>

<script>
	$(document).ready(function() {
		$('#bikeLeaderBoardTable').DataTable({
			"order" : [ [ 1, "desc" ] ],
			"columnDefs" : [ {
				"targets" : 'nonsortable',
				"sortable" : false
			} ]
		});
	});
</script>


<div class="container">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">

			<table class="table table-striped table-bordered"
				id="bikeLeaderBoardTable">

				<thead>
					<tr>
						<th>Athlete Name</th>
						<th>Distance</th>
						<th class="nonsortable"></th>
						<th class="nonsortable"></th>
					</tr>
				</thead>

				<tbody>
					<s:iterator value="bikeActivitiesMap">
						<tr>
							<td><s:property value="value.athlete.firstname" /> <s:property
									value="value.athlete.lastname" /></td>
							<td><s:property value="value.distance" /></td>
							<td><img class="img-responsive table_image"
								src='<s:property value="value.athlete.profile" />' alt=""></td>

							<td><a
								href='http://www.strava.com/athletes/<s:property value="value.athlete.id"/>'
								target="_blank">View profile on STRAVA. </a></td>
						</tr>
					</s:iterator>
				</tbody>

			</table>

		</div>
		<div class="col-sm-2"></div>
	</div>
</div>
</body>
<%@ include file="include/footer.jsp"%>