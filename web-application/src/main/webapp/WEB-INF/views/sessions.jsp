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

    <h1>Fix Emulator</h1>
    <hr>
    <h3>Session ${session.session.toString()} </h3>

<div style="width:100%">
   <div style="float:left; width:70%">
	<table class="flat-table">
		<thead>
			<tr>
				<th><span>Time</span></th>
				<th><span>Message</span></th>
				<th><span>Direction</span></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${session.getMessages()}">
				<tr onclick=document.location.href='../message/${type}/${name}/${item.id}'>
					<td>${item.processedTime}</td>
					<td>${item.message}</td>
					<td>${item.direction}</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>

	    </div>
      <div style="float:left; width=30%">TODO: add controls here</div>
      <div style="clear:both" />
    </div>


</body>
</html>