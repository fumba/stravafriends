<c:if
	test="${ (session.login != 'true') && not pageContext.request.requestURI.endsWith('/index.jsp') && not pageContext.request.requestURI.endsWith('/')  }">
	<c:redirect url="index.jsp" />
</c:if>

<c:if
	test="${ (session.login == 'true') and (  (pageContext.request.requestURI.endsWith('/index.jsp') ||  pageContext.request.requestURI.endsWith('/')))   }">
	<c:redirect url="connect.action">
		<c:param name="code"> ${session.code} </c:param>
	</c:redirect>
</c:if>
