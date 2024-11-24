CREATE DATABASE eHospital;
use eHospital;

--1
CREATE TABLE Patient(
	pid INT PRIMARY KEY IDENTITY(1,1),
	name VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(30) NOT NULL, 
	isDischarge TINYINT DEFAULT 0,
	gender VARCHAR(50) NOT NULL,
	DOB DATE NOT NULL,
	contact VARCHAR(50) NOT NULL,
);

--2
CREATE TABLE PATIENTRECORD(
	recordID INT PRIMARY KEY IDENTITY(1,1),
	pid INT NOT NULL,
	temperature VARCHAR(50) NOT NULL,
	blood_pressure VARCHAR(50) NOT NULL,
	heart_rate VARCHAR(50) NOT NULL,
	FOREIGN KEY (pid) REFERENCES patient(pid)
);

--3
CREATE TABLE MEDICATION(
	mid INT PRIMARY KEY IDENTITY(1,1),
	recordID INT NOT NULL,
	MedicationName VARCHAR(50) NOT NULL,
	Dosage INT  NOT NULL,
	FOREIGN KEY (recordID) REFERENCES PATIENTRECORD(recordID)
);

--4
CREATE TABLE APPOINTMENT(
	aid INT PRIMARY KEY IDENTITY(1,1),
	recordID INT NOT NULL,
	did INT NOT NULL,
	APPDATE DATE  NOT NULL,
	tid int NOT NULL,
	status tinyint default 0 NOT NULL,
	FOREIGN KEY (recordID) REFERENCES PATIENTRECORD(recordID),
	FOREIGN KEY (did) REFERENCES DOCTOR(did),
	FOREIGN KEY (tid) REFERENCES TIMESLOT(tid),
);

--5
CREATE TABLE EMPLOYEE (
    empid INT PRIMARY KEY IDENTITY(1,1),
	name VARCHAR(50) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
	working_hours INT NOT NULL,
	gender VARCHAR(50) NOT NULL,
    experience INT NOT NULL,
	contact VARCHAR(50),
);

--6
CREATE TABLE DOCTOR(
	did INT PRIMARY KEY IDENTITY(1,1),
	empid INT NOT NULL,
	monday tinyint default 0,
	tuesday tinyint default 0,
	wednesday tinyint default 0,
	thursday tinyint default 0,
	friday tinyint default 0,
	saturday tinyint default 0,
	noOfapp INT default 0 NOT NULL,
	active tinyint default 1,
	FOREIGN KEY (empid) REFERENCES EMPLOYEE(empid)
);

--7
CREATE TABLE NURSE(
	nid INT PRIMARY KEY IDENTITY(1,1),
	empid INT NOT NULL,
	morning tinyint default 0,
	evening tinyint default 0,
	night tinyint default 0,
	active bit not null default 1,
	FOREIGN KEY (empid) REFERENCES EMPLOYEE(empid)
);


--8
CREATE TABLE RECEPTIONIST(
	rid INT PRIMARY KEY IDENTITY(1,1),
	empid INT NOT NULL,
	morning tinyint default 0,
	evening tinyint default 0,
	night tinyint default 0,
	active tinyint default 1,
	FOREIGN KEY (empid) REFERENCES EMPLOYEE(empid)
);

--9
CREATE TABLE ADMIN(
	aid INT PRIMARY KEY IDENTITY(1,1),
	name VARCHAR(50) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
);

INSERT INTO ADMIN (name,username,password) VALUES ('sara','sara','123');
select *from admin;
--ek value admin ki insert karke jana hai

--10
CREATE TABLE INVENTORYITEM (
    iid INT PRIMARY KEY IDENTITY(1,1),
    quantity INT NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE,  -- Added UNIQUE constraint to name
    Category VARCHAR(50) NOT NULL
);

--11
CREATE TABLE DischargeSummary(
	dischargeID INT PRIMARY KEY IDENTITY(1,1),
	recordId INT NOT NULL,
	instructions VARCHAR(50) NOT NULL,
	date date  NOT NULL, 
	FOREIGN KEY (recordId) REFERENCES PATIENTRECORD(recordId)
);

--12
CREATE TABLE PAYROLL(
	payid INT PRIMARY KEY IDENTITY(1,1),
	empid INT NOT NULL,
	baseSalary DECIMAL(10, 2) NOT NULL,
    hourlyRate DECIMAL(10, 2) NOT NULL,
	hours_worked INT NOT NULL,	--how much time 
	totalSalary DECIMAL(10, 2)  NOT NULL,
	approved tinyint default 0 NOT NULL,		--to see if the emp is asking his salary
	FOREIGN KEY (empid) REFERENCES employee(empid)
);

--13
CREATE TABLE timeslot (
	tid INT PRIMARY KEY IDENTITY(1,1),
    starttime TIME NOT NULL,
    endtime TIME NOT NULL
);

--14
CREATE TABLE DoctorTimesloT(
	dtid INT PRIMARY KEY IDENTITY(1,1),
	did int NOT NULL,
	tid int NOT NULL,
	FOREIGN KEY (tid) REFERENCES timeslot(tid),
	FOREIGN KEY (did) REFERENCES doctor(did)
);

--15
CREATE TABLE BILL(
	bid INT PRIMARY KEY IDENTITY(1,1),
	recordId int NOT NULL,
	payment decimal(10,2) NOT NULL,
	paymentType VARCHAR(50) NOT NULL,
	status tinyint default 0 NOT NULL,
	FOREIGN KEY (recordId) REFERENCES PATIENTRECORD(recordId)
);

--16
CREATE TABLE DoctorBookedTimesloT(
	bookedtimeslotId INT PRIMARY KEY IDENTITY(1,1),
	dtid int NOT NULL,
	did int NOT NULL,
	date DATE NOT NULL
	FOREIGN KEY (dtid) REFERENCES doctortimeslot(dtid),
	FOREIGN KEY (did) REFERENCES doctor(did)
);

select * from Patient;
select * from PATIENTRECORD;
select * from medication;
select * from BILL;
select * from APPOINTMENT;	
select * from DOCTOR;
select * from timeslot;
select * from DischargeSummary;
select * from INVENTORYITEM;
select * from Employee;
select * from Nurse;

INSERT INTO timeslot (starttime, endtime) VALUES ('08:00:00', '09:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('09:00:00', '10:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('10:00:00', '11:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('11:00:00', '12:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('12:00:00', '13:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('13:00:00', '14:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('14:00:00', '15:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('15:00:00', '16:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('16:00:00', '17:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('17:00:00', '18:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('18:00:00', '19:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('19:00:00', '20:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('20:00:00', '21:00:00');
INSERT INTO timeslot (starttime, endtime) VALUES ('21:00:00', '22:00:00');
