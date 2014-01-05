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


	<h2>ClearText | Messages</h2>

	You are logged in as
	<b>${username}</b>

	<c:if test="${error != null }">
		<span class="error">Error: ${error }</span>
	</c:if>

	<table>
		<tr>
			<th>Time</th>
			<th>Sender</th>
			<th>Receiver</th>
			<th>Txt Message</th>
		</tr>
		<c:forEach items="${messages}" var="message">
			<tr>
				<td>${message.time}</td>
				<td>${message.sender}</td>
				<td>${message.receiver}</td>
				<td>${message.message}</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<br />
	<table>
		<tr>
			<td><a href="/cleartext/secure/index">Home</a></td>
		</tr>
	</table>


</body>
</html>