CREATE TABLE Patients(
	Email_patient VARCHAR NOT NULL,
	Name_patient VARCHAR,
	Surname_patient VARCHAR,
	Password_patient VARCHAR,
	User_about_patient VARCHAR,
	PRIMARY KEY(Email_patient)
);

CREATE TABLE Doctors(
	Email_doctor VARCHAR NOT NULL,
	Name_doctor VARCHAR,
	Surname_doctor VARCHAR,
	Password_doctor VARCHAR,
	User_about_doctor VARCHAR,
	User_specialty_doctor VARCHAR,
	PRIMARY KEY(Email_doctor)
);

CREATE TABLE Examinations(
	Examination_id SERIAL NOT NULL,
	Examination_type VARCHAR,
	Examination_date VARCHAR,
	Patient_email VARCHAR,
	Doctor_email VARCHAR,
	PRIMARY KEY(Examination_id),
	FOREIGN KEY(Patient_email) REFERENCES Patients(Email_patient),
	FOREIGN KEY(Doctor_email) REFERENCES Doctors(Email_doctor)
);

CREATE TABLE Messages(
	Message_id SERIAL NOT NULL,
	Message_text VARCHAR,
	Mpatient_email VARCHAR,
	Mdoctor_email VARCHAR,
	PRIMARY KEY(Message_id),
	FOREIGN KEY(Mpatient_email) REFERENCES Patients(Email_patient),
	FOREIGN KEY(Mdoctor_email) REFERENCES Doctors(Email_doctor)
);
