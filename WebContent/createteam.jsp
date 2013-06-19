<script type="text/javascript">
	$(function() {
		$("#createTeamForm").css("display", "none");
		$("#createTeam").click(function() {
			$("#createTeamForm").toggle("slow");
		});
	});
</script>
<a id="createTeam" href="#">Create Team</a>
<form id="createTeamForm" action="CreateTeam" method="post">
	<input name="name" type="text">
	<textarea rows="5" cols="5" name="description"></textarea>
	<input type="submit" value="Create">
</form>
