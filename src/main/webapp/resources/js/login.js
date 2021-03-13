/**
 * 
 */

window.onload = function() {
	let items = document.getElementsByTag("input");
	document.getElementById('login-form').addEventListener('click', function(event) {
		for (let el of items) {
			if (el.value == "") {
				alertUser(el);
				event.preventDefault();
				event.stopPropogation();
			}
		}
	})
	
	for (let el of items) {
		el.addEventListener('focus', removeAlert);
	}
}

function getUser() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let userJSON = JSON.parse(xhttp.responseText);
			console.log(userJSON);
		}
	}
	
	xhttp.open("POST", 'http://localhost:8081/project1/resources/html/register.ers');
	
	xhttp.send();
}

function alertUser(element) {
	element.style.borderColor = 'red';
	let warnMessage = document.createElement('p');
	warnMessage.style.color = 'red';
	warnMessage.innerText = 'This field cannot be empty!';
	document.insertBefore(warnMessage, element);
	
}

function removeAlert(index, element) {
	let items = document.getElementsByTag('input');
	items[index].style.borderColor = '#CED4DA';
	document.removeElement(element);
}