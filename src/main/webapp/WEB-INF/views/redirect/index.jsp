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

</head>
<body>


	<h2>ClearText | Authentication</h2>
	<div>
		You are logged in as <b>${username}</b>
	</div>

	<c:if test="${error != null }">
		<span class="error">Error: ${error }</span>
	</c:if>

	<c:if test="${username != null }">Your device is now Authenticated, go to the <a
			href="/cleartext/secure/index">main page.</a>
	</c:if>

	<div>
		Your authentication code is <b>${code }</b>
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