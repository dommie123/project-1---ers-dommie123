/**
 * 
 */
window.onload = getReimbursement();
let reimbObj = null;

function getReimbursement() {
	document.getElementById('warnMessage').setAttribute("hidden", true);

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = () => {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			reimbObj = JSON.parse(xhttp.responseText);
			console.log(reimbObj);
			UpdateReimb(reimbObj);
		}
	}

	xhttp.open("POST", "http://localhost:8080/project1/resources/html/getReimb.json");
	xhttp.send();
}

function UpdateReimb(reimbJSON) {
	if (reimbJSON != null) {
		if (reimbJSON.status != 'PENDING') {
			document.getElementById('warnMessage').removeAttribute("hidden");
			return;
		}
		else {
			//document.getElementById('search').setAttribute("hidden", true);
			//document.getElementById('get-reimb').removeEventListener("submit", getReimbursement);
			let header = document.createElement('header');
			header.innerHTML = '<header class="container-sm" id="message"><h6>Please review the reimbursement before updating it.</h6></header><br />';
			document.body.appendChild(header);

			for (let i = 0; i < 8; i++) {
				let row = document.createElement('div');
				row.setAttribute('class', 'row');
				document.getElementById('get-reimb').appendChild(row);

				let lab = document.createElement('div');
				lab.setAttribute('class', 'col-sm');
				lab.setAttribute('id', `lab${i}`);
				row.appendChild(lab);
			}

			let lab0 = document.createElement('label');
			lab0.innerText = `Amount: ${reimbJSON.amount}`;
			document.getElementById('lab0').appendChild(lab0);

			let lab1 = document.createElement('label');
			lab1.innerText = `Submitted: ${reimbJSON.submitted}`;
			document.getElementById('lab1').appendChild(lab1);

			let lab2 = document.createElement('label');
			lab2.innerText = `Description: ${reimbJSON.description}`;
			document.getElementById('lab2').appendChild(lab2);

			let lab3 = document.createElement('label');
			lab3.innerText = `Author: ${reimbJSON.author}`;
			document.getElementById('lab3').appendChild(lab3);

			let lab4 = document.createElement('label');
			lab4.innerText = `Type: ${reimbJSON.type}`;
			document.getElementById('lab4').appendChild(lab4);

			let lab = document.createElement('label');
			lab.innerText = 'Does this reimbursement ticket fall within company policy?';
			document.getElementById('lab5').appendChild(lab);

			let inpA = document.createElement('input');
			inpA.innerHTML = "<input type='text' name='status' value='APPROVED' /><span> Yes</span>";
			document.getElementById('lab6').appendChild(inpA);

			let inpR = document.createElement('input');
			inpR.innerHTML = "<input type='text' name='status' value='DENIED' /><span> No</span>";
			document.getElementById('lab6').appendChild(inpR);

			let submit = document.createElement('input');
			submit.setAttribute('type', 'submit');
			submit.setAttribute('class', 'btn btn-primary');
			submit.setAttribute('value', 'Update Reimbursement');
			document.getElementById('get reimb').addEventListener('submit', getReimbursement);
			document.getElementById('lab7').appendChild(submit);
		}
	}
}

function sendUpdate() {
	if (reimbObj != null) {
		// TODO write logic here
	}
}