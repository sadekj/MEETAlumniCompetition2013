<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.Database"%>
<%@ page import="entities.Round"%>
<%@ page import="entities.Group"%>
<%@ page import="entities.User"%>
<%@ page import="entities.Post"%>
<%@ page import="entities.Countdown"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp"%>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(function() {
		$("form").css("display", "none");
		$("#createPost").click(function() {
			$("form").toggle("slow");
		});
	});
</script>
<body>
<%@ include file="header.jsp" %>
	<div class="container">
<%
		if (session.getAttribute("user") != null) {
			if (request.getParameter("id") != null) {
	try {
		Round round = Database.getInstance().getRound(Integer.parseInt(request.getParameter("id")));
		Group staff = Database.getInstance().getGroup(2);
		User user = (User) session.getAttribute("user");
		if(round.getStatus().equals("Opened") || Database.getInstance().isInGroup(user, staff)){
			%><h5>Round is Open</h5><%
		Countdown countdown = Database.getInstance().getCountdown(round);
		int countdownid = 0;
		if(countdown!=null)
			countdownid=countdown.getId();
		if(!Database.getInstance().isCountdownDone(countdownid)){
	%>
	<script type="text/javascript">
		document.location.href="countdown.jsp?id=1";
		</script>
	<%
		}%>
		<%
					ArrayList<Post> posts = Database.getInstance().getAllPosts(round);
					for(Post post : posts){
						%>
		<div>
			<h2><%= post.getTitle() %></h2>
			<p>
				<%= post.getDescription() %>
			</p>
		</div>
		<%
					}
					
					if (Database.getInstance().isInGroup(user, staff)) {
		%><a id="createPost" href="#">Create Post</a>
		<form action="CreatePost" method="POST">
			<input type="hidden" name="roundid"
				value="<%=request.getParameter("id")%>"> <input type="text"
				name="title" placeholder="Title">
			<textarea rows="5" cols="5" name="description">Description</textarea>
			<input type="submit" value="Create">
		</form>
		<%
			}}else{
				%><h1>Round is Closed</h1><%
			}
				} catch (Exception e) {
					%>
		<h1>Invalid round!</h1>
		<%
				}
			} else {
				%>
		<h1>Invalid round!</h1>
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