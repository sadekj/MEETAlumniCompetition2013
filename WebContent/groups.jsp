<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.Database"%>
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
		$("#createGroup").click(function() {
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
					ArrayList<Group> groups = Database.getInstance().getAllGroups();
					for (Group group :groups) {
		%>
		<div>
			<a href="group.jsp?id=<%=group.getId()%>"><%=group.getName()%></a>
		</div>
		<%
			}
					Group admin = Database.getInstance().getGroup(1);
					User user = (User) session.getAttribute("user");
					if (Database.getInstance().isInGroup(user, admin)) {
		%><a id="createGroup" href="#">Create Group</a>
		<form action="CreateGroup" method="POST">
			<input type="text" name="name" placeholder="Name">
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