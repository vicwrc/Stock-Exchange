<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<html>

<head>
<title>Stocks</title>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

	<table class="flat-table">
		<thead>
			<tr>
				<th><span>Name</span></th>
				<th><span>SeqIn size</span></th>
				<th><span>SeqOut size</span></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${serverSessions}">
				<tr>
					<td>${item.key}</td>
					<td>${fn:length(item.value.seqIn)}</td>
					<td>${fn:length(item.value.seqOut)}</td>
					<!-- td><input type="submit" name="${item.key}" value="${item.key}" /></td-->
					<td><input type="submit" name="action" value="${item.key}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>