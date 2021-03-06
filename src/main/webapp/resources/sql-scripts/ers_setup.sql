-- if database breaks, execute these methods in this specific order.
drop table if exists user_roles;
drop table if exists reimb_type;
drop table if exists reimb_status;
drop table if exists reimbursements;
drop table if exists users;

-- create tables for reimb database
create table ers_user_roles (
	ers_user_role_id int primary key generated always as identity,
	user_role varchar(10) not null
);

create table ers_reimbursement_type (
	reimb_type_id int primary key generated always as identity,
	reimb_type varchar(10) not null
);

create table ers_reimbursement_status (
	reimb_status_id int primary key generated always as identity,
	reimb_status varchar(10) not null
);

create table ers_users (
	ers_users_id int primary key generated always as identity,
	ers_username varchar(50) unique not null,
	ers_pass varchar(50) not null,
	user_first_name varchar(100) not null,
	user_last_name varchar(100) not null,
	user_email varchar(150) unique not null,
	user_role_id int,
	constraint user_roles_fk foreign key(user_role_id) references ers_user_roles(ers_user_role_id)
);

create table ers_reimbursement (
	reimb_id int primary key generated always as identity,
	reimb_amount numeric not null,
	reimb_submitted timestamp not null,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author int not null,
	reimb_resolver int,
	reimb_status_id int not null,
	reimb_type_id int not null,
	constraint ers_users_fk_auth foreign key (reimb_author) references ers_users(ers_users_id),
	constraint ers_users_fk_reslvr foreign key (reimb_resolver) references ers_users(ers_users_id),
	constraint ers_reimbursement_status_fk foreign key (reimb_status_id) references ers_reimbursement_status(reimb_status_id),
	constraint ers_reimbursement_type_fk foreign key (reimb_type_id) references ers_reimbursement_type(reimb_type_id)
);

-- create the necessary callable functions for modularity
create or replace function addUser(uname varchar(32), pword varchar(64), fname varchar(32), lname varchar(32), mail varchar(32), roleid int)
returns varchar(16) as $$
begin
	insert into ers_users(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
	values(uname, pword, fname, lname, mail, roleid);
end
$$ language 'plpgsql';

create or replace function submitReimbursement(amount numeric, submitted timestamp, description varchar(250), receipt bytea, authid int, statusid int, typeid, int)
returns varchar(16) as $$
begin 
	insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_receipt, reimb_author, reimb_status_id, reimb_type_id)
	values(amount, submitted, description, receipt, authid, statusid, typeid)
end
$$ language 'plpsql';

create or replace function resolveReimbursement(resolved timestamp, resolverid int, statusid int, id int)
returns varchar(16) as $$
begin 
	update ers_reimbursement set reimb_resolved = resolved, reimb_resolver = resolverid, reimb_status_id = statusid
	where reimb_id = id;
end
$$ language 'plpsql';



-- pre-populate the role, status, and type tables to prevent null values.
insert into ers_user_roles(user_role) values('EMPLOYEE');
insert into ers_user_roles(user_role) values('MANAGER');

insert into ers_reimbursement_status(reimb_status) values('PENDING');
insert into ers_reimbursement_status(reimb_status) values('APPROVED');
insert into ers_reimbursement_status(reimb_status) values('DENIED');

insert into ers_reimbursement_type(reimb_type) values('LODGING');
insert into ers_reimbursement_type(reimb_type) values('TRAVEL');
insert into ers_reimbursement_type(reimb_type) values('FOOD');
insert into ers_reimbursement_type(reimb_type) values('OTHER');
