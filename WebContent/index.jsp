<html>
<head>
<link rel="stylesheet" type="text/css" href="css/index.css">

</head>
<body>
	<%@include file="header.jsp"%>
	<%
		if (session.getAttribute("user") != null) {
	%>
	<section id="mainR"></section>
	<%
		} else {
	%>
	<section id="mainL">
		<h2 id="get">
			GET <span id="started">STARTED!</span>
		</h2>
		<section id="sB">
			<section id="oC">
				<section class="o">
					<p class="vA">Sign Up</p>
				</section>
				<section class="o">
					<p class="vA">Login</p>
				</section>
				<section id="alert"></section>
				<section id="lFC">
					<form id="lF" action="SignUp" method="post">
						<input class="fE" name="fname" type="text"
							placeholder="First-name" /> <input class="fE" name="lname"
							type="text" placeholder="Lastname" /> <input class="fE"
							name="email" type="text" placeholder="Email address" /> <input
							class="fE" name="username" type="text" placeholder="Username">
						<input id="p" class="fE" name="pass" type="password"
							placeholder="Password" onkeypress="cPA()" onchange="cPA()" /> <input
							id="pC" class="fE" name="passC" type="password"
							placeholder="Confirm password" onkeypress="cPA()"
							onchange="cPA()" /> <input class="btn btn-primary" id="submit"
							type="submit" value="Sign Up">
					</form>
				</section>
			</section>
		</section>
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
%>
<script type="text/javascript">
	//Setting the correct navBar element as active 
	$(document).ready($('#login').addClass('active'));
	function cPA() {
		setTimeout(function() {
			checkPasses();
		}, 100);
	}
	function checkPasses() {
		if (($("#p").val().length >= 6)) {
			if (!($("#p").val() == $("#pC").val())) {
				$("#sB").css("height", "330px");
				$("#alert").css("display", "table");
				$('#submit').addClass('disabled');
				$('#submit').get(0).type = '';
				document.getElementById("alert").innerHTML = "Please make sure the passwords match.";
			} else if ($("#p").val() == $("#pC").val()) {
				$("#alert").hide();
				$('#submit').removeClass('disabled');
				$('#submit').get(0).type = 'submit';
			}
		} else {
			$("#sB").css("height", "330px");
			$("#alert").css("display", "table");
			$('#submit').addClass('disabled');
			$('#submit').get(0).type = '';
			document.getElementById("alert").innerHTML = "Password must be at least 6 characters long!";
		}
	}
</script>
<%
	}
%>
</html>