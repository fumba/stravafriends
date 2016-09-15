<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp"%>

<head>
<title>Strava Friends - Friend Locations</title>
<%@ include file="include/header.jsp"%>
</head>

<body>

	<%@ include file="include/navigation.jsp"%>

	<div class="container">
		<div class="row">

			<div class="col-sm-2"></div>
			<div class="col-sm-8">

				<table class="imagetable">
					<tr>
						<th></th>
						<th>City</th>
						<th>State</th>
						<th>Country</th>
						<th></th>
					</tr>
					<s:iterator value="friends">
						<tr>
							<td><s:property value="firstname" /> <s:property
									value="lastname" /></td>
							<td><s:property value="city" /></td>
							<td><s:property value="state" /></td>
							<td><s:property value="country" /></td>
							<td><a href="https://www.strava.com/athletes/{{id}}"
								target="_blank">View profile on STRAVA. </a></td>

						</tr>
					</s:iterator>
				</table>

			</div>
			<div class="col-sm-2"></div>

		</div>
	</div>
	<!-- container -->

</body>

<%@ include file="include/footer.jsp"%>