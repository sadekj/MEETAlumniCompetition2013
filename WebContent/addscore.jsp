<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.Database"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entities.Team"%>
<%@ page import="entities.Round"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
<title>Add Score</title>
</head>
<body>
<%
if (request.getParameter("header")!=null){
%>
	<%@ include file="header.jsp"%>
	<div class="container">
	<% } %>
	<form action="" method="post">
		<select id="team" name="team">
			<%
				ArrayList<Team> teams = Database.getInstance().getAllTeams();
				for (Team team : teams) {
			%>
			<option value="<%=team.getId()%>"><%=team.getName()%></option>
			<%
				}
			%>
		</select> <select id="round" name="round">
			<%
				ArrayList<Round> rounds = Database.getInstance().getAllRounds();
				for (Round round : rounds) {
			%>
			<option value="<%=round.getId()%>"><%=round.getTitle()%></option>
			<%
				}
			%>
		</select> <input id="score" type="text">
		<textarea id="description"></textarea>
		<input type="button" onclick="addScore()" value="Add Score">
	</form>
		<div id="status"></div>
		<%
if (request.getParameter("header")!=null){
%>
	</div>
	<%@ include file="header.jsp"%>
	<% } %>
</body>
</html>