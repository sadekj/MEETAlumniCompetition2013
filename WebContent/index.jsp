<html>
<head>
<%@include file="head.jsp"%>
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
				<section class="o" id="oL">
					<a id="vA1" class="vA" href="javascript:void(0);"
						onclick="scroll(1)">Sign Up</a>
				</section>
				<section class="o" id="oR">
					<a id="vA2" class="vA un" href="javascript:void(0);"
						onclick="scroll(2)">Login</a>
				</section>
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
				<section id="lAlert"></section>
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
</script>
<script src="script/index.js"></script>
<%
	}
%>
</html>