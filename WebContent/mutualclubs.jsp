<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<head>
<title>Strava Friends - Mutual Clubs</title>
<%@ include file="include/header.jsp"%>

<%@ include file="include/navigation.jsp"%>

<script>
	$(document).ready(function() {
		$('#mutualClubTable').DataTable();
	});
</script>

<div class="container">

	<div class="row">

		<div class="col-sm-2"></div>
		<div class="col-sm-8">

			<table class="table table-striped table-bordered"
				id="mutualClubTable">
				<thead>
					<tr>
						<th>Club Name</th>
						<th>Mutual Members</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="mutualClubs">
						<tr>
							<td><s:property value="key.name" /></td>
							<td>
								<table class="imagetable">
									<s:iterator value="value">
										<tr>
											<td><a
												href='https://www.strava.com/athletes/<s:property value="id"/>'
												target="_blank"><s:property value="firstname" /> <s:property
														value="lastname" /></a></td>
										</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<br> <br>

		</div>
		<div class="col-sm-2"></div>

	</div>

</div>
</body>

<%@ include file="include/footer.jsp"%>