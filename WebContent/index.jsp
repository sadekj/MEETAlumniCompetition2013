<html>
<head>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<section id="main">
		<h2 id="get">
			GET
			<p id="started">STARTED!</p>
		</h2>
		<section id="sB">
			<section id="oC">
				<section class="o">
					<p class="vA">Sign Up</p>
				</section>
				<section class="o">
					<p class="vA">Login</p>
				</section>
			</section>
		</section>
	</section>
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