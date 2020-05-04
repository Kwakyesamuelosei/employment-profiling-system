CREATE TABLE employee (
	employee_id serial primary key,
	employee_firstName text,
	employee_lastName text,
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

CREATE TABLE assignproject (
    assignproject_id serial primary key,
	employee_id INTEGER,
	project_id INTEGER,
	employee_firstName text ,
	employee_lastName text ,
	employee_email text
);

create table LoggedProject (
	project_date DATE,
	emp_id int references employees (emp_id),
	project_id int references projects (project_id),
	project_hours int,
	primary key (project_date,project_id)
);

create table LoggedSick (
	sick_date DATE,
	emp_id int references employees(emp_id),
	primary key(sick_date, emp_id)
);

create table LoggedVacation (
	vacation_date DATE,
	emp_id int references employees (emp_id),
	primary key (vacation_date, emp_id)
);

--For holiday requests...

--- DATABASE FOR HOLIDAY REQUEST PLATFORM ---

DROP TABLE IF EXISTS requests cascade;
DROP TABLE IF EXISTS request_status cascade;

--- HOLIDAY REQUEST STATUS TABLE---

CREATE TABLE request_status(
    request_status_id serial primary key NOT NULL,
    req_status varchar(10)
);

--- HOLIDAY REQUESTS TABLE ---

CREATE TABLE requests(
    request_id serial primary key NOT NULL,
    requester_id int references employee(employee_id) NOT NULL,
    request_start_date date NOT NULL,
    request_report_date date NOT NULL,
    request_status_id int references request_status(request_status_id) NOT NULL default 1
);

CREATE OR REPLACE FUNCTION loggedChart (startdate DATE, enddate DATE)
RETURNS TABLE (
	logged_date DATE,
	project_name text,
	logged_Hours bigint
)
LANGUAGE plpgsql
AS $$
BEGIN
	RETURN QUERY
	 select * from (
	    select project_date, projects.title as project_name, sum(project_hours) from loggedProject
		    inner join projects on projects.project_id = loggedProject.project_id group by project_date, project_name
		union
	select sick_date,'sick' as project_name, sum(8) from loggedSick group by sick_date
 		union
	select vacation_date,'vacation' as project_name, sum(8) from loggedVacation group by vacation_date)
 		AS foo where project_date between startdate and enddate;
END;
$$;
