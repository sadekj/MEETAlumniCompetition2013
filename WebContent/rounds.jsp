<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.Database"%>
<%@ page import="entities.Round"%>
<%@ page import="entities.Group"%>
<%@ page import="entities.User"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<%@ include file="head.jsp"%>
<title>Rounds</title>
<script type="text/javascript">
	$(function() {
		$("form").css("display", "none");
		$("#createRound").click(function() {
			$("form").toggle("slow");
		});
	});
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<%
			if (session.getAttribute("user") != null) {
				try {
					ArrayList<Round> rounds = Database.getInstance().getAllRounds();
					for (Round round : rounds) {
		%>
		<div>
			<a href="round.jsp?id=<%=round.getId()%>"><%=round.getTitle()%></a>
		</div>
		<%
			}
					Group staff = Database.getInstance().getGroup(2);
					User user = (User) session.getAttribute("user");
					if (Database.getInstance().isInGroup(user, staff)) {
		%><a id="createRound" href="#">Create Round</a>
		<form action="CreateRound" method="POST">
			<input type="text" name="title" placeholder="Title">
			<textarea rows="5" cols="5" name="description">Description</textarea>
			<input type="submit" value="Create">
		</form>
		<%
			}
				} catch (Exception e) {
		%>
		<h1>Invalid Input!</h1>
		<%
			}
			} else {
		%>
		<h1>Not logged in!</h1>
		<%
			}
		%>
	</div>
</body>
</html>