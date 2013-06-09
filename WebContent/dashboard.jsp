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
<script type="text/javascript"
	src="jquery.tablesorter/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
	function loadSort() {
		$("#myTable").tablesorter();
	}
	$(function() {
		loadScores(1);
		setTimeout('loadSort()', 3000);
	});
</script>
<style type="text/css">
/*     	table { float: left; margin: 140px 40px 0 0;  } */
/*     	td input { border: 1px solid orange; background: yellow; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; position: absolute; padding: 8px 0; text-align: center; width: 60px; margin: -17px 0 0 4px; font-size: 1.4em; } */
/*     	td.input { padding: 0; position: relative; } */
/*     	.visualize { float: left; } */
/*     	#chart{ */
/*     		float: right; */
/*     		position: absolute; */
/*     	} */
#chartContainer {
	min-height: 500px;	
}
</style>
<title>Dashboard</title>
</head>
<body onmousemove="loadScores(2)">
	<%@include file="header.jsp"%>
	<div class="container">
		<div id="allscores" onclick="loadSort()"></div>
		<div id="chartContainer">
		</div>



	</div>
	<%@include file="footer.jsp"%>
</body>
</html>