<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<head>
<title>Strava Friends - Not Following you</title>
<%@ include file="include/header.jsp"%>
</head>

<body>

	<%@ include file="include/navigation.jsp"%>

	<script>
		$(document).ready(function() {
			$('#athletesNotFollowingBackTable').DataTable({
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
					id="athletesNotFollowingBackTable">

					<thead>
						<tr>
							<th>Athlete Name</th>
							<th class="nonsortable"></th>
							<th class="nonsortable"></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="athletesNotFollowingBack">
							<tr>
								<td><s:property value="firstname" /> <s:property
										value="lastname" /></td>
								<td><img class="img-responsive"
									style="height: 60px; width: 60px"
									src='<s:property value="profile" />' alt=""></td>
								<td><a
									href='https://www.strava.com/athletes/<s:property value="id"/>'
									target="_blank">View profile on STRAVA. </a></td>

							</tr>
						</s:iterator>
					</tbody>
				</table>

			</div>
			<div class="col-sm-2"></div>

		</div>
	</div>
	<!-- container -->

</body>

<%@ include file="include/footer.jsp"%>