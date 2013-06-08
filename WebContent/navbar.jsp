<div class="navbar navbar-fixed-top"
	style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);">
	<div class="navbar-inner">
		<div class="container" style="width: 1170px;">
			<a class="brand" href="index.jsp"><img src="images/MEET.png" /></a>
			<ul class="nav">
				<li id="home"><a href="index.jsp">Home</a></li>
				<%
					if (session.getAttribute("user") != null) {
				%>
				<li id="rounds"><a href="rounds.jsp">Rounds</a></li>
				<%
					}
				%>
				<li id="projects"><a href="dashboard.jsp">Dashboard</a></li>
			</ul>
			<ul class="nav pull-right">
				<li id="login"><a href="index.jsp">Login</a></li>
			</ul>
		</div>
	</div>
</div>