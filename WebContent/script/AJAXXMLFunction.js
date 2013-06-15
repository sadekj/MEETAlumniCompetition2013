function getXMLHttpRequest() {
	var xhrequest = null;
	if (window.XMLHttpRequest) {
		// If IE7, Mozilla, Safari, etc: Use native object
		try {
			xhrequest = new XMLHttpRequest();
			return xhrequest;
		} catch (exception) {
			// OK, just carry on looking
		}
	} else {
		// ...otherwise, use the ActiveX control for IE5.x and IE6
		var IEControls = [ "MSXML2.XMLHttp.5.0", "MSXML2.XMLHttp.4.0",
				"MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp" ];
		for ( var i = 0; i < IEControls.length; i++) {
			try {
				xhrequest = new ActiveXObject(IEControls[i]);
				return xhrequest;
			} catch (exception) {
				// OK, just carry on looking
			}
		}
		// if we got here we didnÕt find and matches
		throw new Error("Cannot create an XMLHttpRequest");
	}
}
function loadScores(first) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "AllScores";
		xhrequest.onreadystatechange = updateScores;
		xhrequest.open("GET", strURL, true);
		xhrequest.send(null);
		$('.visualize').trigger('visualizeRefresh');
		if (first == 1)
			loadChart();
		setTimeout("loadScores(2)", 5000);
	}
}
function updateScores() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("allscores").innerHTML = strResponse;
		setTimeout("updateChart()", 2000);
	}
}
function updateChart() {
	$('.visualize').trigger('visualizeRefresh');
	document.getElementById("chartContainer").innerHTML = "";
	$('table').visualize().appendTo('#chartContainer').trigger(
			'visualizeRefresh');
}
function loadChart() {
	enhance({
		loadScripts : [ {
			src : 'script/jQuery-Visualize/js/excanvas.js',
			iecondition : 'all'
		}, 'script/visualize.jQuery.js', 'script/visualize.js', ],
		loadStyles : [ 'css/visualize.css', 'css/visualize-dark.css' ]
	});
}
function addScore() {
	var teamid = document.getElementById("team").value;
	var roundid = document.getElementById("round").value;
	var score = document.getElementById("score").value;
	var description = document.getElementById("description").value;
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "AddScore?team=" + teamid + "&round=" + roundid + "&value="
				+ score + "&description=" + description;
		xhrequest.onreadystatechange = updateStatus;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateStatus() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("status").innerHTML = strResponse;
	}
}
function loadList(status) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "AllUsers?status=" + status;
		xhrequest.onreadystatechange = updateList;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateList() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("ContainerUser").innerHTML = strResponse;
	}
}
function approve(userid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UpdateUserStatus?user=" + userid + "&status=Approve";
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadList('Pending')", 3000);
}
function enable(userid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UpdateUserStatus?user=" + userid + "&status=Approve";
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadList('Disabled')", 3000);
}
function disable(userid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UpdateUserStatus?user=" + userid + "&status=Disable";
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadList('Approved')", 3000);
}

function loadListTeam(status) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "AllTeams?status=" + status;
		xhrequest.onreadystatechange = updateListTeam;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateListTeam() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("ContainerTeam").innerHTML = strResponse;
	}
}
function approveTeam(teamid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UpdateTeamStatus?team=" + teamid + "&status=Approve";
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadListTeam('Pending')", 3000);
}
function enableTeam(teamid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UpdateTeamStatus?team=" + teamid + "&status=Approve";
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadListTeam('Disabled')", 3000);
}
function disableTeam(teamid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UpdateTeamStatus?team=" + teamid + "&status=Disable";
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadListTeam('Approved')", 3000);
}
function loadTeamMembers(id) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "MemberList?id=" + id;
		xhrequest.onreadystatechange = updateTeamMembers;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateTeamMembers() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("Members").innerHTML = strResponse;
	}
}
function loadUsersNotInTeams(id) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UsersNotInTeams?id=" + id;
		xhrequest.onreadystatechange = updateUsersNotInTeams;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateUsersNotInTeams() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("UsersNotInTeams").innerHTML = strResponse;
	}
}
function addToTeam(teamid, userid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "AddUserToTeam?teamid=" + teamid + "&userid=" + userid;
		xhrequest.onreadystatechange = teamActionsStatus;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadUsersNotInTeams(" + teamid + ")", 100);
	setTimeout("loadTeamMembers(" + teamid + ")", 200);
}
function removeFromTeam(teamid, userid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "RemoveUserFromTeam?teamid=" + teamid + "&userid=" + userid;
		xhrequest.onreadystatechange = teamActionsStatus;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadUsersNotInTeams(" + teamid + ")", 100);
	setTimeout("loadTeamMembers(" + teamid + ")", 200);
}
function teamActionsStatus() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("teamActionsStatus").innerHTML = strResponse;
	}
}

function loadGroupMembers(id) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "GroupMemberList?id=" + id;
		xhrequest.onreadystatechange = updateGroupMembers;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateGroupMembers() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("Members").innerHTML = strResponse;
	}
}
function loadUsersNotInGroups(id) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "UsersNotInGroup?id=" + id;
		xhrequest.onreadystatechange = updateUsersNotInGroups;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateUsersNotInGroups() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("UsersNotInGroup").innerHTML = strResponse;
	}
}
function addToGroup(groupid, userid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "AddUserToGroup?groupid=" + groupid + "&userid=" + userid;
		xhrequest.onreadystatechange = groupActionsStatus;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadUsersNotInGroups(" + groupid + ")", 100);
	setTimeout("loadGroupMembers(" + groupid + ")", 200);
}
function removeFromGroup(groupid, userid) {
	xhrequest = null;
	try {
		xhrequest = getXMLHttpRequest();
	} catch (error) {
		document.write("Cannot run AJAX on this browser!");
	}
	if (xhrequest != null) {
		strURL = "RemoveUserFromGroup?groupid=" + groupid + "&userid=" + userid;
		xhrequest.onreadystatechange = groupActionsStatus;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadUsersNotInGroups(" + groupid + ")", 100);
	setTimeout("loadGroupMembers(" + groupid + ")", 200);
}
function groupActionsStatus() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("groupActionsStatus").innerHTML = strResponse;
	}
}
