<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="head.jsp"%>
<script type="text/javascript" src="script/AJAXXMLFunction.js"></script>
<script type="text/javascript"
	src="http://filamentgroup.github.com/EnhanceJS/enhance.js"></script>
<script type="text/javascript">
	$(function() {
		loadScores();
		enhance({
			loadScripts : [ {
				src : 'jQuery-Visualize/js/excanvas.js',
				iecondition : 'all'
			}, 'jQuery-Visualize/js/visualize.jQuery.js',
					'script/visualize.js', ],
			loadStyles : [ 'jQuery-Visualize/css/visualize.css',
					'css/visualize-dark.css' ]
		});
	});
</script>
<script type="text/javascript">
	// Run capabilities test
</script>
<title>Insert title here</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container">
	<div id="allscores"></div>




	</div>
	<%@include file="footer.jsp"%>
</body>
</html>