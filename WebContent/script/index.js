//Setting the correct navBar element as active 
$(document).ready($('#login').addClass('active'));
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
		$("#login").html('<a href="index.jsp?rid=liB">Sign Up</a>');
	} else {
		$("#oL").css("background-color", "rgba(0, 0, 0, 0)");
		$("#oR").css("background-color", "#E06040");
		$('#vA1').removeClass("un").addClass("un");
		$('#vA2').removeClass("un");
		$("#signUp").css("display", "none");
		$("#alert").css("display", "none");
		$("#sB").css("height", "180px");
		setTimeout(function() {
			sIT();
			$("#signIn").css("display", "block");
		}, 200);
		$("#login").html('<a href="index.jsp">Login</a>');
	}
}

function sIT() {
	setTimeout(function() {
		checkSignIn();
	}, 100);
}

function checkSignIn() {
	var lCH1 = $("#uNL").val().length >= 6 && $("#uNL").val().length <= 15;
	var lCH2 = $("#pL").val().length >= 6 && $("#pL").val().length <= 20;
	var totaLch = lCH1 && lCH2;

	if ($("#uNL").val() != ""
			&& ($("#uNL").val().length < 6 || $("#uNL").val().length > 15)) {
		$("#sB").css("height", "210px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
		$('#lSubmit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Username must be 6-12 characters long";
		$("#uNL").removeClass("hFO").addClass("hFO");
	} else if ($("#pL").val() != ""
			&& ($("#pL").val().length < 6 || $("#pL").val().length > 20)) {
		if (lCH1) {
			$("#uNL").removeClass("hFO");
		}
		$("#sB").css("height", "210px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
		$('#lSubmit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Password must be 6-20 characters long";
		$("#pL").removeClass("hFO").addClass("hFO");
	} else if (totaLch) {
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
	var ticker = 0;
	var ch1 = $("#fNa").val().length >= 3 && $("#fNa").val().length <= 15;
	var ch2 = $("#lNa").val().length >= 3 && $("#lNa").val().length <= 30;
	var ch3 = $("#eMa").val().length >= 8 && $("#eMa").val().length <= 50;
	var ch4 = $("#uN").val().length >= 6 && $("#uN").val().length <= 12;
	var ch5 = $("#p").val().length >= 6 && $("#p").val().length <= 20;
	var totalCH = ch1 && ch2 && ch3 && ch4 && ch5;

	function lightEmUp() {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Please fill all fields properly";
		if (!ch1) {
			$("#fNa").removeClass("hFO").addClass("hFO");
		} else {
			$("#fNa").removeClass("hFO");
		}
		if (!ch2) {
			$("#lNa").removeClass("hFO").addClass("hFO");
		} else {
			$("#lNa").removeClass("hFO");
		}
		if (!ch3) {
			$("#eMa").removeClass("hFO").addClass("hFO");
		} else {
			$("#eMa").removeClass("hFO");
		}
		if (!ch4) {
			$("#uN").removeClass("hFO").addClass("hFO");
		} else {
			$("#uN").removeClass("hFO");
		}
		if ($("#p").val() == $("#pC").val()) {
			$("#pC").removeClass("hFO");
		}
	}
	if ($("#fNa").val() != "" && (!ch1)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "First-name must be 3-15 characters long";
		$("#fNa").removeClass("hFO").addClass("hFO");
	} else if ($("#lNa").val() != "" && (!ch2)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Lastname must be 3-30 characters long";
		$("#lNa").removeClass("hFO").addClass("hFO");
	} else if ($("#eMa").val() != "" && (!ch3)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Email must be 8-50 characters long";
		$("#eMa").removeClass("hFO").addClass("hFO");
	} else if ($("#uN").val() != "" && (!ch4)) {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
		$('#submit').attr("disabled", "disabled");
		document.getElementById("alert").innerHTML = "Username must be 6-12 characters long";
		$("#uN").removeClass("hFO").addClass("hFO");
	} else if ($("#p").val() != "" && ch5) {
		if ($("#pC").val() != "") {
			ticker = 1;
		}
		$("#p").removeClass("hFO");
		if (!($("#p").val() == $("#pC").val()) && $("#pC").val() != "") {
			$("#sB").css("height", "330px");
			setTimeout(function() {
				$("#alert").css("display", "table");
			}, 50);
			$('#submit').attr("disabled", "disabled");
			document.getElementById("alert").innerHTML = "Passwords don't match";
			$("#p").removeClass("hFO").addClass("hFO");
			$("#pC").removeClass("hFO").addClass("hFO");
		} else if (($("#p").val() == $("#pC").val()) && totalCH) {
			$("#fNa").removeClass("hFO");
			$("#lNa").removeClass("hFO");
			$("#eMa").removeClass("hFO");
			$("#uN").removeClass("hFO");
			$("#p").removeClass("hFO");
			$("#pC").removeClass("hFO");
			$("#sB").css("height", "300px");
			$("#alert").css("display", "none");
			$('#submit').removeAttr("disabled");
		} else if (!totalCH) {
			if (ticker != 0) {
				lightEmUp();
			}
		}
	} else if ($("#p").val() != "") {
		$("#sB").css("height", "330px");
		setTimeout(function() {
			$("#alert").css("display", "table");
		}, 50);
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