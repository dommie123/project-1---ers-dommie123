/**
 * 
 */

function getReimbursement() {
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = () => {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let reimbObj = JSON.parse(xhttp.responseText);
			console.log(reimbObj);
			UpdateReimb(reimbObj);
		}
	}
	
	xhttp.open("POST", "http://localhost:8080/project1/resources/html/getReimb.json");
	xhttp.send();
}

function UpdateReimb(reimbJSON) {
	if (reimbJSON != null) {
		for (let i = 0; i < 9; i++) {
			let row = document.createElement('div');
			row.setAttribute('class', 'row');
			document.getElementById('get-reimb').appendChild(row);
			
			let lab = document.createElement('div');
			lab.setAttribute('class', 'col-sm');
			lab.setAttribute('id', `lab${i}`);
			row.appendChild(lab);
			
			let row2 = document.createElement('div');
			row2.setAttribute('class', 'row');
			document.getElementById('get-reimb').appendChild(row2);
			
			let inp = document.createElement('div');
			inp.setAttribute('class', 'col-sm');
			inp.setAttribute('id', `inp${i}`);
			row2.appendChild(inp);
		}
		
		let lab0 = document.createElement('label');
		lab0.innerText = `Amount: ${reimbJSON.amount}`;
		document.getElementById('lab0').appendChild(lab0);
		
		let lab1 = document.createElement('label');
		lab1.innerText = `Submitted: ${reimbJSON.submitted}`;
		document.getElementById('lab1').appendChild(lab1);
		
		let lab2 = document.createElement('label');
		lab2.innerText = `Resolved: ${reimbJSON.amount}`;
		document.getElementById('lab2').appendChild(lab2);
		
		let lab3 = document.createElement('label');
		lab3.innerText = `Amount: ${reimbJSON.amount}`;
		document.getElementById('lab3').appendChild(lab3);
		
		let lab4 = document.createElement('label');
		lab4.innerText = `Amount: ${reimbJSON.amount}`;
		document.getElementById('lab4').appendChild(lab4);
		
		let lab5 = document.createElement('label');
		lab5.innerText = `Amount: ${reimbJSON.amount}`;
		document.getElementById('lab5').appendChild(lab5);
		
		let lab6 = document.createElement('label');
		lab6.innerText = `Amount: ${reimbJSON.amount}`;
		document.getElementById('lab6').appendChild(lab6);
		
		let lab7 = document.createElement('label');
		lab7.innerText = `Amount: ${reimbJSON.amount}`;
		document.getElementById('lab7').appendChild(lab7);
		
		let lab8 = document.createElement('label');
		lab8.innerText = `Amount: ${reimbJSON.amount}`;
		document.getElementById('lab8').appendChild(lab8);
		
		let inp0 = document.createElement('input');
		inp0.setAttribute('type');
		
	}
}