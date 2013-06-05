<html>
<head>
</head>
<body>
	<%@include file="header.jsp"%>
	<link rel="stylesheet" type="text/css" href="css/index.css">
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
				<section class="o" id="oL">
					<a id="vA1" class="vA" href="javascript:void(0);"
						onclick="scroll(1)">Sign Up</a>
				</section>
				<section class="o" id="oR">
					<a id="vA1" class="vA" href="javascript:void(0);"
						onclick="scroll(2)">Login</a>
				</section>
			</section>
			<section id="signUp">
				<section id="alert"></section>
				<section class="fC">
					<form class="f" action="SignUp" method="post">
						<input id="fNa" class="fE" name="fname" type="text"
							placeholder="First-name" onkeypress="cPA()" onchange="cPA()"
							maxlength="15" /> <input id="lNa" class="fE" name="lname"
							type="text" placeholder="Lastname" onkeypress="cPA()"
							onchange="cPA()" maxlength="30" /> <input id="eMa" class="fE"
							name="email" type="text" placeholder="Email" onkeypress="cPA()"
							onchange="cPA()" maxlength="50" /> <input id="uN" class="fE"
							name="username" type="text" placeholder="Username"
							onkeypress="cPA()" onchange="cPA()" maxlength="12"> <input
							id="p" class="fE" name="pass" type="password"
							placeholder="Password" onkeypress="cPA()" onchange="cPA()"
							maxlength="20" /> <input id="pC" class="fE" name="passC"
							type="password" placeholder="Confirm password" onkeypress="cPA()"
							onchange="cPA()" maxlength="20" /> <input
							class="btn btn-primary" id="submit" type="submit" value="Sign Up">
					</form>
				</section>
			</section>
			<section id="signIn">
				<section id="lAlert"></section>
				<section class="fC">
					<form class="f" action="Login" method="post">
						<input class="fE" name="username" type="text"
							placeholder="Username" /> <input class="fE" name="pass"
							type="password" placeholder="Password" /> <input
							class="btn btn-primary disabled" id="lSubmit" type="submit"
							value="Login">
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
	$(document).ready($("#oL").css("background-color", "#E06040"));
	$("#signIn").hide();

	function scroll(y) {
		if (y == 1) {
			$("#oR").css("background-color", "rgba(0, 0, 0, 0)");
			$("#oL").css("background-color", "#E06040");
			$("#signIn").hide();
			setTimeout(function() {
				$("#signUp").show();
			}, 500);
			$("#sB").css("height", "300px");
		} else {
			$("#oL").css("background-color", "rgba(0, 0, 0, 0)");
			$("#oR").css("background-color", "#E06040");
			$("#signUp").hide();
			setTimeout(function() {
				$("#signIn").show();
			}, 100);
			$("#sB").css("height", "180px");
		}
	}

	function cPA() {
		setTimeout(function() {
			checkSignUp();
		}, 100);
	}
	function checkSignUp() {
		if ($("#fNa").val() == null || $("#fNa").val().length < 3) {
			$("#sB").css("height", "330px");
			$("#alert").css("display", "table");
			$('#submit').removeClass("disabled").addClass('disabled');
			$('#submit').get(0).type = '';
			document.getElementById("alert").innerHTML = "First-name must be 3-15 characters long";
			$("#fNa").removeClass("hFO").addClass("hFO");
		} else if ($("#lNa").val() == null || $("#lNa").val().length < 3) {
			$("#fNa").removeClass("hFO");
			$("#sB").css("height", "330px");
			$("#alert").css("display", "table");
			$('#submit').removeClass("disabled").addClass('disabled');
			$('#submit').get(0).type = '';
			document.getElementById("alert").innerHTML = "Lastname must be 3-30 characters long";
			$("#lNa").removeClass("hFO").addClass("hFO");
		} else if ($("#eMa").val().length < 8) {
			$("#lNa").removeClass("hFO");
			$("#sB").css("height", "330px");
			$("#alert").css("display", "table");
			$('#submit').removeClass("disabled").addClass('disabled');
			$('#submit').get(0).type = '';
			document.getElementById("alert").innerHTML = "Email must be 8-50 characters long";
			$("#eMa").removeClass("hFO").addClass("hFO");
		} else if ($("#uN").val().length < 6) {
			$("#eMa").removeClass("hFO");
			$("#sB").css("height", "330px");
			$("#alert").css("display", "table");
			$('#submit').removeClass("disabled").addClass('disabled');
			$('#submit').get(0).type = '';
			document.getElementById("alert").innerHTML = "Username must be 6-12 characters long";
			$("#uN").removeClass("hFO").addClass("hFO");
		} else if ($("#p").val().length >= 6) {
			$("#uN").removeClass("hFO");
			if (!($("#p").val() == $("#pC").val())) {
				$("#sB").css("height", "330px");
				$("#alert").css("display", "table");
				$('#submit').removeClass("disabled").addClass('disabled');
				$('#submit').get(0).type = '';
				document.getElementById("alert").innerHTML = "Passwords don't match";
				$("#p").removeClass("hFO").addClass("hFO");
				$("#pC").removeClass("hFO").addClass("hFO");
			} else if ($("#p").val() == $("#pC").val()) {
				$("#p").removeClass("hFO");
				$("#pC").removeClass("hFO");
				$("#alert").hide();
				$('#submit').removeClass('disabled');
				$('#submit').get(0).type = 'submit';
			}
		} else {
			$("#uN").removeClass("hFO");
			$("#sB").css("height", "330px");
			$("#alert").css("display", "table");
			$('#submit').removeClass("disabled").addClass('disabled');
			$('#submit').get(0).type = '';
			document.getElementById("alert").innerHTML = "Password must be 6-20 characters long";
			$("#p").removeClass("hFO").addClass("hFO");

		}
	}
</script>
<%
	}
%>
</html>