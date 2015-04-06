<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<html>
<head>
<title>Messages</title>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

	<table class="flat-table">
		<thead>
			<tr>
				<th><span>Time</span></th>
				<th><span>Message</span></th>
				<th><span>Direction</span></th>
			</tr>
		</thead>
		<tbody>
			<!-- SeqIn -->
			<c:forEach var="item" items="${session.seqIn}">
				<tr>
					<td>${item.processedTime}</td>
					<td>${item.message}</td>
					<td>In</td>
					<!-- <td><input type="submit" name="action" value="Info" /></td> -->
					<td><input type="submit" name="action" value="${item.id}" /></td>
				</tr>
			</c:forEach>
			
			<!-- SeqOut -->
			<c:forEach var="item" items="${session.seqOut}">
				<tr>
					<td>${item.processedTime}</td>
					<td>${item.message}</td>
					<td>Out</td>
					<td><input type="submit" name="action" value="${item.id}" /></td>
					<!-- <td><input type="submit" name="action" value="Info" /></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>