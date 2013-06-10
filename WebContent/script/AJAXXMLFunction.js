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
		if (first==1)
			loadChart();
//		setTimeout("loadScores(2)", 2000);
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
	$('table').visualize().appendTo('#chartContainer').trigger('visualizeRefresh');
}
function loadChart() {
	enhance({
		loadScripts : [ {
			src : 'script/jQuery-Visualize/js/excanvas.js',
			iecondition : 'all'
		}, 'script/visualize.jQuery.js', 'script/visualize.js', ],
		loadStyles : [ 'css/visualize.css',
				'css/visualize-dark.css' ]
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
		strURL = "AddScore?team=" + teamid + "&round=" + roundid
				+ "&value=" + score + "&description=" + description;
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
		strURL = "AllUsers?status="+status;
		xhrequest.onreadystatechange = updateList;
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
}
function updateList() {
	if (xhrequest.readyState == 4 && xhrequest.status == 200) {
		var strResponse = xhrequest.responseText;
		document.getElementById("pendingContainer").innerHTML = strResponse;
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
		strURL = "UpdateUserStatus?user="+userid+"&status=Approve";
		xhrequest.open("POST", strURL, true);
		xhrequest.send(null);
	}
	setTimeout("loadList()", 3000);
}