DROP TABLE IF EXISTS employee cascade;

CREATE TABLE employee (
	employee_id serial primary key,
	employee_firstName text not null,
	employee_lastName text not null,
	employee_phoneNumber text,
	employee_email text not null,
	employee_address text,
	employee_role text,
	employee_dev_level text,
	employee_hire_date DATE,
	employee_onLeave BOOLEAN,
	employee_gender text,
	employee_status text
);

--- DATABASE FOR HOLIDAY ME ---

DROP TABLE IF EXISTS requests cascade;
DROP TABLE IF EXISTS request_status cascade;

--- REQUEST STATUS ---

CREATE TABLE request_status(
    request_status_id serial primary key NOT NULL,
    req_status varchar(10)
);

INSERT INTO request_status(request_status_id, req_status) VALUES (1, 'PENDING');
INSERT INTO request_status(request_status_id, req_status) VALUES (2, 'DECLINED');
INSERT INTO request_status(request_status_id, req_status) VALUES (3, 'APPROVED');

--- REQUESTS TABLE ---

CREATE TABLE requests(
    request_id serial primary key NOT NULL,
    requester_id int references employee(employee_id) NOT NULL,
    request_start_date date NOT NULL,
    request_report_date date NOT NULL,
    request_status_id int references request_status(request_status_id) NOT NULL default 1
);