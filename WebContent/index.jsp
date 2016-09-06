<%@ include file="include/taglibs.jsp"%>
<%@ include file="include/connection_check.jsp" %>

<s:action name="loadApplicationAttributes"></s:action>

<head>
<title>Strava Friends</title>
<%@ include file="include/header.jsp"%>

	<%@ include file="include/navigation.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-sm-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">Manage your strava friend list. Make new
				workout friends from all over the world.</div>
			<div class="col-sm-2"></div>
		</div>

		<div class="row">
			<div class="col-sm-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">

				<a href="${attr.StravaAppAuthorizationURL}"> <img
					alt="Connect with Strava" src="assets/img/ConnectWithStrava.png" />
				</a>

			</div>
			<div class="col-sm-2"></div>
		</div>

	</div>

</body>

<%@ include file="include/footer.jsp"%>