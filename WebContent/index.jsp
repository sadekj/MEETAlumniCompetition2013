<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entities.Countdown"%>
<%@page import="database.Database"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<html>
<head>
<%
	boolean rCh = false;
	boolean cCh = false;
	boolean mCh = false;
	boolean cO = false;
	String title = "MEET Competition Platform";
	try {
		rCh = request.getParameter("rid") != null
				&& request.getParameter("rid").equals("liB");
		cCh = request.getParameter("co") != null
				&& request.getParameter("co").equals("t");
		mCh = request.getParameter("e") != null;
		cO = Database.getInstance().isCountdownDone(1);
		title = Database.getInstance().getTitle(1).getTitle();
		mCh = request.getParameter("e") != null;
		cO = Database.getInstance().isCountdownDone(1);
		title = Database.getInstance().getTitle(1).getTitle();
	} catch (Exception e) {
%><script>console.log(<%=e.getMessage()%>)</script>
<%
	}
%>
<%@include file="head.jsp"%>
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="county/css/county.css" />
<script src="county/js/county.js" type="text/javascript"></script>
<style>
#count-down {
	background-color: rgba(77, 100, 255, 0.5);
}
</style>
<%
	Countdown countdown = Database.getInstance().getCountdown(1);
	if (countdown != null) {
		
%>
<script type="text/javascript">
	$(function() {
		$('#count-down').county({
			endDateTime : new Date("<%=countdown.printEndDate()%> <%=countdown.getEndTime()%>"),
							reflection : true,
							animation : 'scroll',
							theme : 'blue'
						});

	});
</script>
<%
	}
	String msg = "";
	int mId;
	if (mCh) {
		try {
			mId = Integer.parseInt(request.getParameter("e"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			mId = 666;
		}
		if (mId == 0) {
			msg = "Thank you, your registration is awaiting approval. Check back later!";
		} else if (mId == 1) {
			msg = "Sign up failed.";
		} else if (mId == 2) {
			msg = "Login failed; entered username and/or password incorrect, or account hasn't been approved yet.";
		} else if (mId == 3) {
			msg = "Username and/or email already taken.";
		} else if (mId == 4) {
			msg = "Passwords don't match.";
		} else if (mId == 5) {
			msg = "Password must be 6-20 characters long.";
		} else if (mId == 6) {
			msg = "Sorry, Sign Up closed.";
		} else if (mId == 666) {
			msg = "Are you trying to crash this? Nice try, lol.";
		}
	}
%>
<title><%=title%></title>
</head>
<body>
	<%@include file="header.jsp"%>
	<%
		if (session.getAttribute("user") != null) {
	%>
	<section id="mainR">
		<h1
			style="color: #204080; text-align: center; vertical-align: middle;">AND
			YOU'RE LOGGED IN!</h1>
	</section>
	<%
		} else {
	%>
	<section id="mainL">
		<h4 id="msg"><%=msg%></h4>
		<h2 id="get">
			GET <span id="started">STARTED!</span>
		</h2>
		<section id="sB">
			<section id="oC">
				<%
					if (rCh) {
				%>
				<section class="o" id="oL">
					<a id="vA1" class="vA un" href="javascript:void(0);"
						onclick="scroll(1)">Sign Up</a>
				</section>
				<section class="o" id="oR">
					<a id="vA2" class="vA" href="javascript:void(0);"
						onclick="scroll(2)">Login</a>
				</section>
				<%
					} else {
				%>
				<section class="o" id="oL">
					<a id="vA1" class="vA" href="javascript:void(0);"
						onclick="scroll(1)">Sign Up</a>
				</section>
				<section class="o" id="oR">
					<a id="vA2" class="vA un" href="javascript:void(0);"
						onclick="scroll(2)">Login</a>
				</section>
				<%
					}
				%>
			</section>
			<section id="alert"></section>
			<section id="signUp">
				<section class="fC">
					<form class="f" action="SignUp" method="post">
						<input id="fNa" class="fE" name="fname" type="text"
							placeholder="First-name" onkeypress="cPA()" onchange="cPA()"
							onkeydown="cPA()" maxlength="15" /> <input id="lNa" class="fE"
							name="lname" type="text" placeholder="Lastname"
							onkeypress="cPA()" onchange="cPA()" onkeydown="cPA()"
							maxlength="30" /> <input id="eMa" class="fE" name="email"
							type="text" placeholder="Email" onkeypress="cPA()"
							onchange="cPA()" onkeydown="cPA()" maxlength="50" /> <input
							id="uN" class="fE" name="username" type="text"
							placeholder="Username" onkeypress="cPA()" onchange="cPA()"
							onkeydown="cPA()" maxlength="12"> <input id="p"
							class="fE" name="pass" type="password" placeholder="Password"
							onkeypress="cPA()" onchange="cPA()" onkeydown="cPA()"
							maxlength="20" /> <input id="pC" class="fE" name="passC"
							type="password" placeholder="Confirm password" onkeypress="cPA()"
							onchange="cPA()" onkeydown="cPA()" maxlength="20" /> <input
							class="btn btn-primary" id="submit" type="submit" value="Sign Up">
					</form>
				</section>
			</section>
			<section id="signIn">
				<section class="fC">
					<form class="f" action="Login" method="post">
						<input id="uNL" class="fE" name="username" type="text"
							placeholder="Username" onkeypress="sIT()" onchange="sIT()"
							onkeydown="sIT()" maxlength="12" /> <input id="pL" class="fE"
							name="pass" type="password" placeholder="Password"
							onkeypress="sIT()" onchange="sIT()" onkeydown="sIT()"
							maxlength="20" /> <input class="btn btn-primary" id="lSubmit"
							type="submit" value="Login">
					</form>
				</section>
			</section>
		</section>
		<div id="count-down"></div>
	</section>
	<%@include file="footer.jsp"%>
	<%
		}
	%>
</body>
<%
	if (session.getAttribute("user") != null) {
%>
<script type="text/javascript">
	//Setting the correct navBar element as active 
	$(document).ready($('#home').addClass('active'));
</script>
<%
	} else {
		if (rCh) {
%>
<script>
	$(document).ready($("#oR").css("background-color", "#E06040"));
	$("#signUp").hide();
	$("#signIn").show();
	$("#login").html('<a href="index.jsp">Login</a>');
	$("#sB").css("height", "180px");
</script>
<%
	} else {
%>
<script>
	$(document).ready($("#oL").css("background-color", "#E06040"));
	$("#signIn").hide();
	$("#signUp").show();
	$("#login").html('<a href="index.jsp?rid=liB">Sign Up</a>');
	$("#sB").css("height", "300px");
</script>
<%
	}
%>
<script src="script/index.js"></script>
<%
	}
%>
<%
	if (msg == "") {
%>
<script type="text/javascript">
	$("#msg").hide();
</script>
<%
	if (cO && rCh) {
%>
<script>
	$("#count-down").hide();
	$("#signUp").hide();
	$("#vA1").removeAttr("onclick");
	$("#signIn").show();
	$("#login").html('<a href="index.jsp?rid=liB">Login</a>');
	$("#sB").css("height", "180px");
</script>
<style>
#vA1:hover {
	text-decoration: line-through;
	color: rgb(100, 100, 100);
	background-color: rgba(0, 0, 0, 0);
}
</style>
<%
	} else if (cO && mCh) {
%>
<script>
	window.location = "index.jsp?rid=liB";
</script>
<%
	} else if (cO) {
%>
<script type="text/javascript">
	$("#count-down").hide();
	$("#signUp").hide();
	$("#vA1").removeAttr("onclick");
	$("#vA1").removeClass("un").addClass("un");
	$("#vA2").removeClass("un");
	$("#oR").css("background-color", "#E06040");
	$("#oL").css("background-color", "transparent");
	$("#signIn").show();
	$("#login").html('<a href="index.jsp?rid=liB">Login</a>');
	$("#sB").css("height", "180px");
</script>
<style>
#vA1:hover {
	text-decoration: line-through;
	color: rgb(100, 100, 100);
	background-color: rgba(0, 0, 0, 0);
}
</style>
<%
	}
	} else if (cO) {
%>
<script type="text/javascript">
	$("#count-down").hide();
	$("#signUp").hide();
	$("#vA1").removeAttr("onclick");
	$("#vA1").removeClass("un").addClass("un");
	$("#vA2").removeClass("un");
	$("#oR").css("background-color", "#E06040");
	$("#oL").css("background-color", "transparent");
	$("#signIn").show();
	$("#login").html('<a href="index.jsp?rid=liB">Login</a>');
	$("#sB").css("height", "180px");
</script>
<style>
#vA1:hover {
	text-decoration: line-through;
	color: rgb(100, 100, 100);
	background-color: rgba(0, 0, 0, 0);
}
</style>
<%
	}
%>
</html>