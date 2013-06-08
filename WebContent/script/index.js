$(document).ready($("#oL").css("background-color", "#E06040"));
$("#signIn").hide();
$('#lSubmit').attr("disabled", "disabled");
$('#submit').attr("disabled", "disabled");

function scroll(y) {
	if (y == 1) {
		$("#oR").css("background-color", "rgba(0, 0, 0, 0)");
		$("#oL").css("background-color", "#E06040");
		$('#vA2').removeClass("un").addClass("un");
		$('#vA1').removeClass("un");
		$("#signIn").css("display", "none");
		$("#alert").css("display", "none");
		$("#sB").css("height", "300px");
		setTimeout(function() {
			cPA();
			$("#signUp").css("display", "block");
		}, 350);
	} else {
		$("#oL").css("background-color", "rgba(0, 0, 0, 0)");
		$("#oR").css("background-color", "#E06040");
		$('#vA1').removeClass("un").addClass("un");
		$('#vA2').removeClass("un");
		$("#signUp").css("display", "none");
		$("#alert").css("display", "none");
		setTimeout(function() {
			sIT();
			$("#signIn").css("display", "block");
		}, 100);
		$("#sB").css("height", "180px");
	}
}

function sIT() {
	setTimeout(function() {
		checkSignIn();
	}, 100);
}

function checkSignIn() {
	if ($("#uNL").val() != ""
			&& ($("#uNL").val().length < 6 || $("#uNL").val().length > 15)) {
		$("#sB").css("height", "210px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 200);
		$('#lSubmit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Username must be 6-12 characters long";
		$("#uNL").removeClass("hFO").addClass("hFO");
	} else if ($("#pL").val() != ""
			&& ($("#pL").val().length < 6 || $("#pL").val().length > 20)) {
		$("#sB").css("height", "210px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 200);
		$('#lSubmit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Password must be 6-20 characters long";
		$("#pL").removeClass("hFO").addClass("hFO");
	} else if ($("#pL").val() != "" && $("#uNL").val() != "") {
		$("#uNL").removeClass("hFO");
		$("#pL").removeClass("hFO");
		$("#sB").css("height", "180px");
		$("#alert").css("display", "none");
		$('#lSubmit').removeAttr("disabled");
	} else {
		$("#uNL").removeClass("hFO");
		$("#pL").removeClass("hFO");
		$("#sB").css("height", "180px");
		$("#alert").css("display", "none");
		$('#lSubmit').attr("disabled", "disabled");
	}
}

function cPA() {
	setTimeout(function() {
		checkSignUp();
	}, 100);
}
function checkSignUp() {
	if ($("#fNa").val() != ""
			&& ($("#fNa").val().length < 3 || $("#fNa").val().length > 15)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 200);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "First-name must be 3-15 characters long";
		$("#fNa").removeClass("hFO").addClass("hFO");
	} else if ($("#lNa").val() != ""
			&& ($("#lNa").val().length < 3 || $("#lNa").val().length > 30)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 200);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Lastname must be 3-30 characters long";
		$("#lNa").removeClass("hFO").addClass("hFO");
	} else if ($("#eMa").val() != ""
			&& ($("#eMa").val().length < 8 || $("#eMa").val().length > 50)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 200);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Email must be 8-50 characters long";
		$("#eMa").removeClass("hFO").addClass("hFO");
	} else if ($("#uN").val() != ""
			&& ($("#uN").val().length < 6 || $("#uN").val().length > 12)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 200);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Username must be 6-12 characters long";
		$("#uN").removeClass("hFO").addClass("hFO");
	} else if ($("#p").val() != "" && $("#p").val().length >= 6
			&& $("#p").val().length <= 20) {
		$("#p").removeClass("hFO");
		$("#alert").css("display", "none");
		if (!($("#p").val() == $("#pC").val()) && $("#pC").val() != "") {
			$("#sB").css("height", "330px");
			setTimeout(function() {
				$("#alert").css("display", "table");
			}, 200);
			$('#submit').attr("disabled", "disabled");
			document.getElementById("alert").innerHTML = "Passwords don't match";
			$("#p").removeClass("hFO").addClass("hFO");
			$("#pC").removeClass("hFO").addClass("hFO");
		} else if ($("#p").val() == $("#pC").val()) {
			$("#sB").css("height", "300px");
			$("#alert").css("display", "none");
			$("#pC").removeClass("hFO");
			$('#submit').removeAttr("disabled");
		}
	} else if ($("#p").val() != "") {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 200);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Password must be 6-20 characters long";
		$("#p").removeClass("hFO").addClass("hFO");
	} else {
		$("#fNa").removeClass("hFO");
		$("#lNa").removeClass("hFO");
		$("#eMa").removeClass("hFO");
		$("#uN").removeClass("hFO");
		$("#p").removeClass("hFO");
		$("#pC").removeClass("hFO");
		$("#sB").css("height", "300px");
		$("#alert").css("display", "none");
		$('#submit').attr("disabled", "disabled");
	}
}