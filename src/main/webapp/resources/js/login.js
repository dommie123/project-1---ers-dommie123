/**
 * 
 */

window.onload = function() {
	document.getElementById("registration").addEventListener("submit", function(event) {
		if (!passMatch(document.getElementById("pass").value, document.getElementById("confirmpass").value)) {
			alert("Passwords do not match! Please try again!");
			event.preventDefault();
			event.stopPropogation();
		}
	})
}

function passMatch(pass, confirmpass) {
	return pass == confirmpass;
}