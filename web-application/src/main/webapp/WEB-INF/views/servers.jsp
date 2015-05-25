<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
<title>Stocks</title>
<spring:url value="/resources/css/main.css" var="resourceUrl"/>
<link media="main" rel="stylesheet" href="${resourceUrl}" type="text/css" />
	<script type="text/javascript">
	function popit(url){
	    newwindow = window.open(url, '', "status=yes, height=500; width=500; resizeable=no");
	}

    </script>

</head>
<body>
    <h1>Fix Emulator</h1>

    <hr>

    <h3>Server Sessions</h3>
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
				<tr onclick=document.location.href='./server/${item.key}'>
					<td>${item.key}</td>
					<td>${fn:length(item.value.seqIn)}</td>
					<td>${fn:length(item.value.seqOut)}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<h3>Client Sessions</h3>
    	<table class="flat-table">
    		<thead>
    			<tr>
    				<th><span>Name</span></th>
    				<th><span>SeqIn size</span></th>
    				<th><span>SeqOut size</span></th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach var="item" items="${clientSessions}">
    				<tr onclick=document.location.href='./client/${item.key}'>
    					<td>${item.key}</td>
    					<td>${fn:length(item.value.seqIn)}</td>
    					<td>${fn:length(item.value.seqOut)}</td>
    				</tr>
    			</c:forEach>
    		</tbody>
    	</table>
</body>
</html>