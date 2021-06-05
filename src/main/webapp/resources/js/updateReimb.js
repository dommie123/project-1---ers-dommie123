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
			
			let form = document.createElement('form');
			form.innerHTML = "<form action=\"updateReimb.ers\" method=\"post\" id=\"get-reimb\"></form>";
			form.setAttribute('action', 'updateReimb.ers');
			form.setAttribute('method', 'post');
			form.setAttribute('id', 'get-reimb');

			document.body.appendChild(form);
			console.log(reimbJSON);

			for (let i = 0; i < 8; i++) {
				let row = document.createElement('div');
				row.setAttribute('class', 'row');
				form.appendChild(row);

				let lab = document.createElement('div');
				lab.setAttribute('class', 'col-sm');
				lab.setAttribute('id', `lab${i}`);
				row.appendChild(lab);
			}

			let lab0 = document.createElement('label');
			lab0.innerText = `Amount: ${formatMoney(reimbJSON.amount)}`;
			document.getElementById('lab0').appendChild(lab0);

			let lab1 = document.createElement('label');
			let subDate = new Date(`${reimbJSON.submitted.year}-${reimbJSON.submitted.month}-${reimbJSON.submitted.dayOfMonth}`);
			lab1.innerText = `Submitted: ${subDate.getMonth() + 1}-${subDate.getDate()}-${subDate.getFullYear()}`;
			document.getElementById('lab1').appendChild(lab1);

			let lab2 = document.createElement('label');
			lab2.innerText = `Description: ${reimbJSON.description}`;
			document.getElementById('lab2').appendChild(lab2);

			let lab3 = document.createElement('label');
			lab3.innerText = `Author: ${reimbJSON.author.firstName} ${reimbJSON.author.lastName} (${reimbJSON.author.userName})`;
			document.getElementById('lab3').appendChild(lab3);

			let lab4 = document.createElement('label');
			lab4.innerText = `Type: ${reimbJSON.type}`;
			document.getElementById('lab4').appendChild(lab4);

			let lab = document.createElement('label');
			lab.innerText = 'Does this reimbursement ticket fall within company policy?';
			document.getElementById('lab5').appendChild(lab);

			let inpA = document.createElement('div');
			inpA.innerHTML = "<input type='radio' name='status' value='APPROVED' /><span> Yes</span>";
			document.getElementById('lab6').appendChild(inpA);

			let inpR = document.createElement('div');
			inpR.innerHTML = "<input type='radio' name='status' value='DENIED' /><span> No</span>";
			document.getElementById('lab6').appendChild(inpR);

			let submit = document.createElement('input');
			submit.setAttribute('type', 'submit');
			submit.setAttribute('class', 'btn btn-primary');
			submit.setAttribute('value', 'Update Reimbursement');
			submit.addEventListener("click", () => {alert("This reimbursement was successfully updated!")})
			document.getElementById('lab7').appendChild(submit);
		}
	}
}

function formatMoney(number) {
  	return number.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
}

console.log(formatMoney(10000));   // $10,000.00
console.log(formatMoney(1000000)); // $1,000,000.00