var items = document.getElementsByTagName("input");

window.onload = function() {
	document.getElementById('login-form').addEventListener('submit', function(event) {
		for (let el of items) {
			if (el.value == "") {
				event.preventDefault();
				event.stopPropagation();
				alertUser(el);
			}
		}
	})
	
	for (let el of items) {
        el.addEventListener('focus', removeAlert);
	}
}

function alertUser(element) {
	element.style.borderColor = 'red';
	let warnMessage = document.getElementsByClassName('warn-message');
	for (let el of warnMessage) {
		el.style.color = 'red';
		el.removeAttribute("hidden");
	}
}

function removeAlert(event) {
	for (let el of items) {
		console.log(el);
		if (el.value != "") {
			el.style.borderColor = '#CED4DA';
		}
	}
	
	let divs = document.getElementsByClassName('mb-3');
	for (let el of divs) {
		let message = el.querySelector('p');
		message.setAttribute("hidden", "true");
	}
}