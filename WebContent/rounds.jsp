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
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
<title>Rounds</title>
<script type="text/javascript">
	$(function() {
		$("#createRoundForm").css("display", "none");
		$("#createRound").click(function() {
			$("#createRoundForm").toggle("slow");
		});
	});
</script>
</head>
<body>
<%
if (request.getParameter("header")!=null){
%>
	<%@ include file="header.jsp"%>
	<% } %>
	<div class="container">
		<%
			if (session.getAttribute("user") != null) {
				try {
					boolean allowed = false;
					Group staff = Database.getInstance().getGroup(2);
					User user = (User) session.getAttribute("user");
					if (Database.getInstance().isInGroup(user, staff))
						allowed = true;
					ArrayList<Round> rounds = Database.getInstance().getAllRounds();
					for (Round round : rounds) {
		%>
		<div class="hero-unit">
			<h1><a href="round.jsp?id=<%=round.getId()%>"><%=round.getTitle()%></a></h1>
			<p><%= round.getDescription()%></p>
			<%
				if (round.getStatus().equals("Closed")) {
			%>
			<button onclick="openRound(<%=round.getId()%>)">Open</button>
			<%
				} else {
			%>
			<button onclick="closeRound(<%=round.getId()%>)">Close</button>
			<%
				}
			if(allowed){
				%>
				<a href="updateround.jsp?id=<%=round.getId()%>">Edit</a>
				<%
			}
			%>
		</div>
		<%
			}
					if (allowed) {
		%><a id="createRound" href="#">Create Round</a>
		<form id="createRoundForm" action="CreateRound" method="POST">
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
	<%
if (request.getParameter("header")!=null){
%>
	<%@ include file="footer.jsp"%>
	<% } %>
</body>
</html>