<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<body>
	<table id="list">
	<head>
		<caption>Server Sessions</caption>
	</head>
		<thead class="dataTableHeader">
			<tr>
				<td>Name</td>
				<td>Session</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${serverSessions}">
				<tr>
					<td>${item.key}</td>
					<td>${item.value}</td>
					<!-- td><input type="submit" name="${item.key}" value="${item.key}" /></td-->
					<!--td><input type="submit" name="action" value="${item.key}" /></td-->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<form:form method="POST" action="">
					<input type="submit" name="action" value="save" />
					</form:form>
	<p></p>
	
	<table id="list">
	<head>
		<caption>Client Sessions</caption>
	</head>
		<thead class="dataTableHeader">
			<tr>
				<td>Name</td>
				<td>Session</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${clientSessions}">
				<tr>
					<td>${item.key}</td>
					<td>${item.value}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>