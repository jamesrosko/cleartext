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


	<h2>ClearText | Alert Words</h2>

	You are logged in as
	<b>${username}</b>

	<c:if test="${notification != null }">
		<div class="notification">${notification}</div>
	</c:if>

	<c:if test="${error != null }">
		<span class="error">Error: ${error }</span>
	</c:if>

	Alert words which will cause a message to be sent.
	<table>
		<c:forEach items="${alertWords}" var="alertWord">
			<tr>
				<td>${alertWord}</td>
			</tr>
		</c:forEach>
	</table>


	<br />
	<br />
	<form method="post">
		Add Alert Word: <input name="alertWord" id="alertWord" value="" /> <input
			type="submit" value="Add Alert Word" />
	</form>






	<br />
	<br />
	<table>
		<tr>
			<td><a href="/cleartext/secure/index">Home</a></td>
		</tr>
	</table>


</body>
</html>