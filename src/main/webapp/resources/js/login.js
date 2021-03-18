/**
 * 
 */
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
		el.addEventListener('change', removeAlert(el));
	}
}

function alertUser(element) {
	element.style.borderColor = 'red';
	let warnMessage = document.createElement('p');
	warnMessage.style.color = 'red';
	warnMessage.innerText = 'This field cannot be empty!';
//	document.getElementById("login").appendChild(warnMessage);
	element.parentNode.appendChild(warnMessage);
}

function removeAlert(event) {
	console.log(event);
	for (let el of items) {
		if (el.value != "") {
			element.style.borderColor = '#CED4DA';
		}
	}
	
	let divs = document.getElementsByClassName('mb-3');
	for (let el of divs) {
		let message = el.querySelector('p');
		el.removeChild(message);
	}
}