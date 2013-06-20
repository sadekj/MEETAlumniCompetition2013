<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ page import="database.Database"%>
<%@ page import="entities.Countdown"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp"%>
<link rel="stylesheet" type="text/css" href="county/css/county.css" />
<style>
body {
	text-align: center;
}

.orLet {
	color: #E06040;
}

.wLet {
	color: white;
}
</style>
<script src="county/js/county.js" type="text/javascript"></script>
<%
	try {
		Countdown countdown = Database.getInstance().getCountdown(3);
%>
<script type="text/javascript">
	$(function() {
		$('#count-down').county({
			endDateTime : new Date('<%=countdown.getEndDate()%> <%=countdown.getEndTime()%>'),
							reflection : true,
							animation : 'scroll',
							theme : 'blue'
						});

	});
</script>
<style>
#count-down {
	background-color: rgba(77, 100, 255, 0.5);
}
</style>
<title>Summer Count-down</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container" style="vertical-align: middle;">
		<div
			style="left-margin: auto; right-margin: auto; display: inline-block;">
			<div id="count-down"></div>
		</div>
		<h1 style="color: rgba(77, 100, 255, 1);">
			And <span class="orLet">M</span><span class="wLet">E</span><span
				class="orLet">E</span><span class="wLet">T</span>'s 10th summer
			begins!
		</h1>
		<%
			} catch (Exception e) {
		%>
		Invalid Count-down
		<%
			}
		%>
	</div>
</body>
</html>