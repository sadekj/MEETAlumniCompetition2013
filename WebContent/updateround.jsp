<%@page import="entities.Countdown"%>
<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entities.User"%>
<%@page import="entities.Round"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<%@ include file="head.jsp"%>
<title>Update Round</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
	<%
		User user = (User) session.getAttribute("user");
		if (user != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Round round = Database.getInstance().getRound(id);
				if (round != null) {
					Countdown countdown = Database.getInstance().getCountdown(round);
	%>
	<h1><%=round.getTitle() %></h1>
	<form id="UpdateRound" action="UpdateRound" method="POST">
		<input type="hidden" name="id" value="<%=round.getId()%>">
		<input type="text" name="title" placeholder="Title" value="<%=round.getTitle() %>">
		<textarea rows="5" cols="5" name="description"><%=round.getDescription() %></textarea>
		<%if (countdown!=null){ %>
		<input type="text" name="enddate" placeholder="yyyy-mm-dd" value="<%=countdown.getEndDate() %>">
		<input type="text" name="endtime" placeholder="hh:mm:ss" value="<%=countdown.getEndTime() %>">
		<%} %>
		<input type="submit" value="Save">
	</form>
	<%
		} else {
	%>
	<h1>Invalid Round</h1>
	<%
		}
			} catch (Exception e) {
	%>
	<h1>Invalid Round</h1>
	<%
		}
		} else {
	%>
	<h1>Not Logged In</h1>
	<%
		}
	%>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>