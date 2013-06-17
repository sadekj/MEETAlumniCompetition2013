<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
<script type="text/javascript">

</script>
<title>Pending Users</title>
</head>
<body onload="loadListTeam('<%= request.getParameter("status")%>')">
<div id="ContainerTeam"></div>
<button onclick="loadListTeam('<%= request.getParameter("status")%>')">Refresh</button>
<div id="Members"></div>
<%@ include file="createteam.jsp" %>
</body>
</html>