<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.Database"%>
<%@ page import="entities.Team"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
	<%
		int id = Integer.parseInt(request.getParameter("id"));
		Team team = Database.getInstance().getTeam(id);
	%>
	<script type="text/javascript">
		$(function(){
			loadUsersNotInTeams(<%= id%>);
			setTimeout("loadTeamMembers(<%= id%>)",100);
		});
	</script>
<title>Insert title here</title>
</head>
<body>
<div id="Members"></div>
<div id="teamActionsStatus"></div>
<div id="UsersNotInTeams"></div>
<button onclick=""></button>
</body>
</html>