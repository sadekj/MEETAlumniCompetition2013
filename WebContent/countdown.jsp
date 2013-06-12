<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="database.Database"%>
<%@ page import="entities.Countdown"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp"%>
<link rel="stylesheet" type="text/css" href="county/css/county.css" />
<script src="county/js/county.js" type="text/javascript"></script>
<% Countdown countdown = Database.getInstance().getCountdown(1); %>
<script type="text/javascript">
	$(function() {
		$('#count-down').county({
			endDateTime : new Date('<%= countdown.getEndDate()%> <%= countdown.getEndTime() %>'),
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
<title>Insert title here</title>
</head>
<body>
	<div id="count-down"></div>
</body>
</html>