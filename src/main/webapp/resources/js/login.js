/**
 * 
 */

window.onload = function() {
	let items = document.getElementsByTagName("input");
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
	let warnMessage = document.createElement('p');
	warnMessage.style.color = 'red';
	warnMessage.innerText = 'This field cannot be empty!';
	document.getElementById("login").appendChild(warnMessage);
	
}

function removeAlert(index, element) {
	let items = document.getElementsByTagName('input');
	items[index].style.borderColor = '#CED4DA';
	document.removeElement(element);
}