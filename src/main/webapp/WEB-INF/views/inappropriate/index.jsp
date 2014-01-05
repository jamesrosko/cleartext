<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>ClearText</title>
<link rel="stylesheet" type="text/css"
	href="/cleartext/css/cleartext.css" />

<script src="/cleartext/js/jquery-1.9.0.js"></script>
<script src="/cleartext/js/application.js"></script>

<script>
	$(document).ready(function() {
	});
</script>
</head>
<body>


	<h2>ClearText | Check For Inappropriate Activity</h2>

	<div>
		You are logged in as <b>${username}</b>
	</div>

	<c:if test="${error != null }">
		<span class="error">Error: ${error }</span>
		<div>
			Please <a
				href="https://api.att.com/oauth/authorize?client_id=qrjx65n8cclqifquobdrf3zxov4utkmy&scope=IMMN,MIM&redirect_uri=https://www.neospider.com/cleartext/redirect">Authorize</a>
			your app.
		</div>
	</c:if>

	<div>
		<c:if test="${abusefound == false}">No Inappropriate Activity Was Found</c:if>
		<c:if test="${abusefound == true}">Inappropriate Activity Was Found.  Sending TXT message to ${owner}.  View the <a
				href="/cleartext/secure/txtlog/">activity log</a>
		</c:if>
	</div>

	<br />
	<br />
	<table>
		<tr>
			<td><a href="/cleartext/secure/index">Home</a></td>
		</tr>
	</table>
</body>
</html>