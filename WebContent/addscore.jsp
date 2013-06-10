<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.Database"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entities.Team"%>
<%@ page import="entities.Round"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
<title>Add Score</title>
</head>
<body>
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
</body>
</html>