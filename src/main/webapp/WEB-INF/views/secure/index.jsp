<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Clear text</title>
<link rel="stylesheet" type="text/css"
	href="/cleartext/css/cleartext.css" />
</head>
<body>


	<h2>ClearText |</h2>

	You are logged in as
	<b>${username}</b>

	<h4>Keep kids safe by monitoring their txt conversations and be
		alerted when unsafe txting occurs.</h4>
  
  <c:if test="${notification != null }">
    <div class="notification">${notification}</div>
  </c:if>
  
	<c:if test="${error != null }">
		<span class="error">Error: ${error }</span>
	</c:if>
	<c:if test="${code == null }">
		<span class="error">Please Authorize your device now.</span>
	</c:if>


	<form method="post">
		<table>
			<tr>
				<td>Parent Phone</td>
				<td>Kid Phone</td>
			</tr>
			<tr>
				<td><input name="parentaddr" id="parentaddr"
					value="${parentaddr}" /></td>
				<td><input name="kidaddr" id="kidaddr" value="${kidaddr}" /></td>
			</tr>
		</table>
		<input type="submit" value="Update Phone Numbers" />
	</form>

	<ul>
		<li><a
			href="https://api.att.com/oauth/authorize?client_id=qrjx65n8cclqifquobdrf3zxov4utkmy&scope=IMMN,MIM&redirect_uri=https://www.neospider.com/cleartext/redirect">Authorize</a>
			<c:if test="${code == null }">
				<li>Show Messages (authenticate the device before viewing
					messages)</li>
			</c:if> <c:if test="${code != null }">
				<li><a href="/cleartext/secure/txtlog">Show Messages</a></li>
			</c:if>
		<li><a href="/cleartext/secure/alertwords">Alert Words</a></li>
		<c:if test="${code == null }">
			<li>Check For Inappropriate Activity (authenticate the device
				before viewing messages)</li>
		</c:if>
		<c:if test="${code != null }">
			<li><a href="/cleartext/secure/inappropriate">Check For
					Inappropriate Activity</a></li>
		</c:if>
	</ul>

	<br />
	<br />
	<table>
		<tr>
			<td><a href="/cleartext/secure/index">Home</a></td>
		</tr>
	</table>
</body>
</html>