<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<html>
<head>
<title>Details</title>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

	<table class="flat-table">
		<thead>
			<tr>
				<th><span>Tag</span></th>
				<th><span>Value</span></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${tags}">
				<tr>
					<td>${item.key}</td>
					<td>${item.value}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>