/**
 * 
 */

window.onload = () => {
	getUser();
	refreshList();
}
var currentSize = 0;
var size = 0;

function refreshList() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let reimbJSON = JSON.parse(xhttp.responseText);
			console.log(reimbJSON);
			size = reimbJSON.length;
			tableManip(reimbJSON);
		}
	}

	xhttp.open("POST", "http://localhost:8080/project1/resources/html/reimb.json");

	xhttp.send();
}

function tableManip(reimbJSON) {
	let reimBody = document.createElement('tbody');
	document.getElementById("reimbursements").appendChild(reimBody);

	if (size > currentSize) {
		for (let el of reimbJSON) {
			let row = document.createElement('tr');
			reimBody.appendChild(row);

			let id = document.createElement('td');
			id.innerText = reimbJSON[currentSize].id;
			let amount = document.createElement('td');
			amount.innerText = formatMoney(reimbJSON[currentSize].amount);
			let submitted = document.createElement('td');
			//let subTime = Date.UTC(reimbJSON[currentSize].submitted.hour, reimbJSON[currentSize].submitted.minute, reimbJSON[currentSize].submitted.second);
			let subDate = new Date(`${reimbJSON[currentSize].submitted.year}-${reimbJSON[currentSize].submitted.month}-${reimbJSON[currentSize].submitted.dayOfMonth}`);
			submitted.innerText = `${subDate.getMonth() + 1}-${subDate.getDate()}-${subDate.getFullYear()}`;
			let resolved = document.createElement('td');
			let resDate = '-';
			if (reimbJSON[currentSize].resolved != null) {
				resDate = new Date(`${reimbJSON[currentSize].resolved.year}-${reimbJSON[currentSize].resolved.month}-${reimbJSON[currentSize].resolved.dayOfMonth}`);
				resolved.innerText = `${resDate.getMonth() + 1}-${resDate.getDate()}-${resDate.getFullYear()}`;
				//let resTime = Date.UTC(reimbJSON[currentSize].resolved.hour, reimbJSON[currentSize].resolved.minute, reimbJSON[currentSize].resolved.second);
			}
			else {
				resolved.innerText = resDate;
			}
			let description = document.createElement('td');
			description.innerText = reimbJSON[currentSize].description;
			let receipt = document.createElement('td');
			receipt.innerText = reimbJSON[currentSize].receipt;
			let author = document.createElement('td');
			author.innerText = reimbJSON[currentSize].author;
			console.log(reimbJSON[currentSize].author);
			let resolver = document.createElement('td');
			resolver.innerText = reimbJSON[currentSize].resolver;
			let status = document.createElement('td');
			status.innerText = reimbJSON[currentSize].status;
			let type = document.createElement('td');
			type.innerText = reimbJSON[currentSize].type;

			row.appendChild(id);
			row.appendChild(type);
			row.appendChild(amount);
			row.appendChild(description);
			row.appendChild(status);
			row.appendChild(submitted);
			row.appendChild(resolved);
			//row.appendChild(description);
			//row.appendChild(receipt);
			//row.appendChild(author);
			//row.appendChild(resolver);
			//row.appendChild(status);

			size--;
			currentSize++
		}


		if (currentSize <= 0) {
			let el = document.createElement('div');
			el.setAttribute('id', 'noreimb');
			el.style.marginLeft = "5%";
			document.body.insertBefore(el, document.getElementById("buttons"));

			let spacer = document.createElement('br');
			document.body.insertBefore(spacer, el);

			let message = document.createElement('th');
			message.style.color = "red";
			message.style.marginLeft = "2em";
			message.style.marginTop = "10px";
			message.innerText = "You don't have any reimbursement claims with us! Please create one to build your list!";
			el.appendChild(message);
		}
	}
}

function getUser() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let userJSON = JSON.parse(xhttp.responseText);
			console.log(userJSON);
			DOMManip(userJSON);
		}
	}

	xhttp.open("POST", 'http://localhost:8080/project1/resources/html/login.json');

	xhttp.send();
}

function DOMManip(uJSON) {
	if (uJSON == null || uJSON == undefined) {
		window.location.href = "../html/index.html";	
	}
	document.getElementById("welcome").innerHTML = `<p id="welcome">Hello, ${uJSON.firstName}! Not you? <a href="logout.ers">Sign out!</a></p>`;
	if (uJSON.role == 'EMPLOYEE') {
		document.getElementById('table-title').innerHTML = '<div id="table-title"><h1>Your Expense Reimbursements</h1></div>';
		document.getElementById('refresh').setAttribute('hidden', true);
	}
	else if (uJSON.role == 'MANAGER') {
		document.getElementById('table-title').innerHTML = '<div id="table-title"><h1>All Expense Reimbursements</h1></div>';
		document.getElementById('refresh').removeAttribute('hidden');
	}
}

function formatMoney(number) {
  	return number.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
}