<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="entities.User" %>
	<%@page import="entities.Group" %>
	<%@page import="database.Database" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<jsp:include page="head.jsp" />
<title>Administration</title>
<style type="text/css">
.window {
	border: 1px solid;
	border-radius: 1em;
	float: left;
	min-height: 350px;
	float: left;
}

.window,.window .innerWindow {
	width: 570px;
	margin: 5px;
}
.window,.window div {
	max-width: 570px;
}
.window .well,.window .hero-unit {
	max-width: 430px;
}
</style>
</head>
<body>
	<div class="container">
	<jsp:include page="header.jsp" />
<%if (session.getAttribute("user") != null) {
	User creator = (User) session.getAttribute("user");
	Group admin = Database.getInstance().getGroup(1);
	Group staff = Database.getInstance().getGroup(2);
	if (Database.getInstance().isInGroup(creator, admin)) { %>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="addscore.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="groups.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="rounds.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="allteams.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="allusers.jsp" />
			</div>
				<a href="allusers.jsp?status=Pending">Pending Accounts</a>
				<a href="allusers.jsp?status=Disabled">Disabled Accounts</a>
		</div>
	<%}else if(Database.getInstance().isInGroup(creator, staff)){%>
		<jsp:include page="addscore.jsp" />
	<% }}%>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>