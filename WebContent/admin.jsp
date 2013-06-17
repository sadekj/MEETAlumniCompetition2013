<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	width: 350px;
	margin: 5px;
}
.window,.window div {
	width: 350px;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="addscore.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="allteams.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="rounds.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="allusers.jsp" />
			</div>
		</div>
		<div class="window">
			<div class="innerWindow">
				<jsp:include page="groups.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>