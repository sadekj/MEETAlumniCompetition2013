<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.Database"%>
<%@ page import="entities.Round"%>
<%@ page import="entities.Group"%>
<%@ page import="entities.User"%>
<%@ page import="entities.Post"%>
<%@ page import="entities.Countdown"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
<%@ include file="head.jsp"%>
<title>Round</title>
<script type="text/javascript">
	$(function() {
		$("form").css("display", "none");
		$("#createPost").click(function() {
			$("form").toggle("slow");
		});
	});
</script>
<style type="text/css">
#updateRemovePostStatus {
	position: fixed;
	display: none;
	margin: 10px auto 0 auto;
	padding: 0 2px 0 2px;
	height: 20px;
	text-align: center;
	vertical-align: middle;
	background-color: #E06040;
	color: #204080;
	-moz-border-radius: 4px;
	-o-border-radius: 4px;
	-ms-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: inset 0px 0px 12px 2px rgba(200, 200, 200, 0.4);
	-moz-box-shadow: inset 0px 0px 12px 2px rgba(200, 200, 200, 0.4);
	-o-box-shadow: inset 0px 0px 12px 2px rgba(200, 200, 200, 0.4);
	-ms-box-shadow: inset 0px 0px 12px 2px rgba(200, 200, 200, 0.4);
	box-shadow: inset 0px 0px 12px 2px rgba(200, 200, 200, 0.4);
	-webkit-transition: all 800ms ease;
	-moz-transition: all 800ms ease;
	-o-transition: all 800ms ease;
	-ms-transition: all 800ms ease;
	transition: all 800ms ease;
}
</style>
</head>
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
		boolean allowed = Database.getInstance().isInGroup(user, staff);
		if(round.getStatus().equals("Opened") || allowed){
	%><div id="updateRemovePostStatus" class="navbar-fixed-top" >
	</div><%
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
		<% if(allowed){%>
		<a href="#" onclick="removePost(<%= post.getId() %>)">Delete</a>
		<% }%>
			<h2><%= post.getTitle() %></h2>
			<p>
				<%= post.getDescription() %>
			</p>
		</div>
		<%
					}
					
					if (allowed) {
		%><a id="createPost" href="#">Create Post</a>
		<form action="CreatePost" method="POST">
			<input type="hidden" name="roundid"
				value="<%=request.getParameter("id")%>"> <input type="text"
				name="title" placeholder="Title">
			<textarea id="richtext" rows="5" cols="5" name="description">Description</textarea>
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