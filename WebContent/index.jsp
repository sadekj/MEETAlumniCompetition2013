<html>
<head>
</head>
<body>
	<%@include file="header.jsp"%>
	<h2
		style="float: middle; color: #204080; -webkit-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); -moz-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); -o-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); -ms-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); padding-top: 130px;">
		<p
			style="margin-bottom: 0; color: #E06040; display: inline-block; font-size: 60px;">ALL</p>
		YOUR PROJECTS
	</h2>
	<h1
		style="margin-right: 84px; float: middle; color: #204080; margin-bottom: 0; line-height: 1; letter-spacing: -1px; -webkit-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); -moz-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); -o-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); -ms-text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6); text-shadow: 1px 1px 8px rgba(0, 0, 0, 0.6);">
		IN
		<p style="color: #E06040; display: inline-block; font-size: 60px;">ONE</p>
		PLACE
	</h1>
	<%@include file="footer.jsp"%>
</body>
<script type="text/javascript">
	//Setting the correct navBar element as active
	$(document).ready($('#home').addClass('active'));
	$(document).ready($('#headerPadding').hide());
	$(document).ready(function() {
		$('#world').pan({
			fps : 30,
			speed : 1,
			dir : 'left'
		});
	});
</script>
</html>