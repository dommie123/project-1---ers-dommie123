# Expense Reimbursement System (ERS)
An Employee Reimbursement System used to create, track, and update tickets for employee reimbursement. The employees can log in to the system or register for a new account. Then, they can view past tickets and create new tickets. The Financial Managers can also log in and/or register as well as approve or reject reimbursement tickets and view a catalog of every ticket recorded in the database. 

# Technologies Used
* Tomcat
* Servlets
* AJAX
* Git
* Maven
* Java
* JavaScript
* HTML
* CSS
* Bootstrap
* RDS
* SQL
* JDBC

# Features
List of features ready and TODOs for future development

* Users can log in and register for their own accounts.
* Users can submit reimbursement claims.
* Users can view a list of all of the reimbursements they have submitted
* Managers can approve or reject reimbursement claims submitted by users.
* Managers can view a list of all of the reimbursement claims submitted sorted in order of status (i.e. PENDING, APPROVED, DENIED) 

# Stretch Goals

* Users will be able to log out without being able to look at their reimbursement claims
* Users will be able to view more detailed information about a specific reimbursement claim at the click of a button
* Managers will be able to filter the list of reimbursements by status

# Getting Started
git clone https://github.com/dommie123/project-1---ers-dommie123/edit/main/README.md 

For further setup, you will need: 

* Eclipse IDE or STS (Spring Tools Suite) 4,
* Tomcat 9

When running the project, be sure to configure your Tomcat server in your IDE.
Then, run the project on the server.

You should come across this page once you've set up the project correctly.

<img src="src/main/resources/ers-demo.png" alt="Project Demo" />

Note: You may want to register a user account with the application before using it.

Once you create an account or log in, you'll see an empty table at first, as shown in the following image:

<insert image here>

That's because you haven't made any reimbursement claims yet. To make a claim,
* Click Submit Reimbursement Claim (this button is on the bottom right corner of the empty list).
* Once there, fill out any information regarding the reimbursement claim, such as the amount, type, and a description.
* If you have done this correctly, you should see that the list has been populated with the reimbursement you have created. 

If you decide to log in as a manager, you will be presented with a list of all of the reimbursements that each user has made, as shown in the following image: 

<insert image here>
  
 As a manager, you can approve or reject these reimbursement claims. To do so, 
 * Click Update Reimbursement Claim (this button is on the bottom left corner of the list). Take note of the unique ID numbers of each reimbursement as you do this.
 * You will see a search bar that says "Search Reimbursement By ID", as shown here: 
 * <insert image here>
 * Type the ID of the reimbursement you want to approve or deny, then click "Search".
 * You will then be presented with the following screen, which lists detailed information about the reimburesment:
 * <insert image here>
 * To approve this ticket, select "Yes" under "Does this reimbursement fall within company policy". To deny it, select "No".
 * Once selected, click "Update Reimbursement" on the bottom left corner of the web page.
 * The newly updated reimbursement will be sorted depending on the status you gave it.

That's all there is to it! When you're done using the program, feel free to log out using the link on the top right corner of the page and stop the server. 

# License
This project uses the following license: MIT License.
