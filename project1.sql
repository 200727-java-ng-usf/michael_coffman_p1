CREATE USER project1_user
WITH PASSWORD 'project1';

GRANT ALL PRIVILEGES 
ON DATABASE postgres
TO project1_user;

COMMIT;

-- Convienent Drop Tables to Reset Spot
--DROP TABLE ers_reimbursements;
--DROP TABLE ers_users;
--DROP TABLE ers_reimbursement_statuses;
--DROP TABLE ers_reimbursement_types;
--DROP TABLE ers_user_roles;


-- Creating TABLES

CREATE TABLE ers_reimbursements(
	reimb_id				serial,
	amount					numeric(6,2) DEFAULT 0.00 NOT NULL,
	submitted				timestamp NOT NULL,
	resolved				timestamp NOT NULL,
	description				varchar(75) NOT NULL,
	receipt 				TEXT,
	author_id				int NOT NULL,
	resolver_id				int,
	reimb_status_id			int NOT NULL,
	reimb_type_id			int NOT NULL,
	
	CONSTRAINT ers_reimbursements_pk
	PRIMARY KEY (reimb_id),
	
	CONSTRAINT ers_users_author_id_fk
	FOREIGN KEY (author_id)
	REFERENCES ers_users (ers_user_id),
	
	CONSTRAINT ers_users_resolver_id_fk
	FOREIGN KEY (resolver_id)
	REFERENCES ers_users (ers_user_id),
	
	CONSTRAINT ers_reimbursement_statuses_fk
	FOREIGN KEY (reimb_status_id)
	REFERENCES ers_reimbursement_statuses (reimb_status_id),
	
	CONSTRAINT ers_reimbursement_types_fk
	FOREIGN KEY (reimb_type_id)
	REFERENCES ers_reimbursement_types (reimb_type_id)
);

CREATE TABLE ers_users(
	ers_user_id				serial,
	username				varchar(25) NOT NULL,
	password				varchar(256) NOT NULL,
	first_name				varchar(25) NOT NULL,
	last_name				varchar(25) NOT NULL,
	email					varchar(256) NOT NULL,
	user_role_id			int NOT NULL,
	
	CONSTRAINT ers_user_pk
	PRIMARY KEY (ers_user_id),
	
	CONSTRAINT ers_user_roles_fk
	FOREIGN KEY (user_role_id)
	REFERENCES ers_user_roles (role_id)
);

CREATE TABLE ers_reimbursement_statuses(
	reimb_status_id			serial,
	reimb_status			varchar(10) UNIQUE NOT NULL,
	
	CONSTRAINT ers_reimbursement_statuses_pk
	PRIMARY KEY (reimb_status_id)
);

CREATE TABLE ers_reimbursement_types(
	reimb_type_id			serial,
	reimb_type				varchar(10) UNIQUE NOT NULL,
	
	CONSTRAINT ers_reimbursement_types_pk
	PRIMARY KEY (reimb_type_id)
);

CREATE TABLE ers_user_roles(
	role_id					serial,
	role_name				varchar(10) UNIQUE NOT NULL,
	
	CONSTRAINT ers_user_roles_pk
	PRIMARY KEY (role_id)
);

-- Inserting CONSTANTS into ers_user_roles
INSERT INTO ers_user_roles (role_name)
	VALUES ('ADMIN'), ('MANAGER'), ('EMPLOYEE');		

INSERT INTO ers_reimbursement_types (reimb_type)
	VALUES ('LODGING'), ('TRAVEL'), ('FOOD'), ('OTHER');

INSERT INTO ers_reimbursement_statuses (reimb_status) 
	VALUES ('PENDING'), ('APPROVED'), ('DENIED');

SELECT * FROM ers_reimbursements;
SELECT * FROM ers_users;
SELECT * FROM ers_reimbursement_statuses;
SELECT * FROM ers_reimbursement_types;
SELECT * FROM ers_user_roles;

COMMIT;
