<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.Database"%>
<%@ page import="entities.Group"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
	<%
		int id = Integer.parseInt(request.getParameter("id"));
		Group group = Database.getInstance().getGroup(id);
	%>
	<script type="text/javascript">
		$(function(){
			setTimeout("loadGroupMembers(<%= id%>)",100);
			setTimeout("loadUsersNotInGroups(<%= id%>)",200);
		});
	</script>
<title><%= group.getName() %></title>
</head>
<body>
<div id="Members"></div>
<button onclick="loadGroupMembers(<%= id%>)">Refresh</button>
<div id="groupActionsStatus"></div>
<div id="UsersNotInGroup"></div>
<button onclick="loadUsersNotInGroups(<%= id%>)">Refresh</button>
</body>
</html>