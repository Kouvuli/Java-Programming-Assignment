DROP TABLE ENROLL_COURSE;
DROP TABLE ATTENDANCE;
DROP TABLE SCHEDULE;
DROP TABLE COURSE;
DROP TABLE STUDENT;
DROP TABLE TEACHER;


CREATE TABLE TEACHER(
    ID INT NOT NULL,
    NAME VARCHAR(100),
    CMND VARCHAR(12),
    PHONE INT,
	EMAIL VARCHAR(20),
	SALARY INT, 
	USERNAME VARCHAR(100) UNIQUE NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL ,

    CONSTRAINT PK_TEACHER PRIMARY KEY(ID)
	
);

CREATE TABLE STUDENT(
    ID INT NOT NULL,
    NAME VARCHAR(100),
    CMND VARCHAR(12),
	BIRTHDAY DATE,
    PHONE INT,
	EMAIL VARCHAR(20),
	IS_FIRST BOOLEAN,
	USERNAME VARCHAR(100) UNIQUE NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL,
	
    CONSTRAINT PK_STUDENT PRIMARY KEY(ID)
	
);

CREATE TABLE COURSE(
	ID VARCHAR(7) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	CONSTRAINT PK_COURSE PRIMARY KEY(ID)
);

CREATE TABLE SCHEDULE(
	ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
	COURSE_ID VARCHAR(7) NOT NULL,
	TEACHER_ID INT NOT NULL,
	DATE_START DATE,
	DATE_END DATE,
	DAY_OF_WEEK VARCHAR(10),
	SHIFT_START VARCHAR(10),
	SHIFT_END VARCHAR(10),
	ROOM VARCHAR(20),
	TERM VARCHAR(10),
	YEAR VARCHAR(20) ,
	CONSTRAINT PK_SCHEDULE PRIMARY KEY(ID)

);

CREATE TABLE ENROLL_COURSE(
	
	STUDENT_ID INT NOT NULL,
	SCHEDULE_ID INT NOT NULL,
	
	CONSTRAINT PK_ENROLL_COURSE PRIMARY KEY(STUDENT_ID,SCHEDULE_ID)
);

CREATE TABLE ATTENDANCE(
	ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
	SCHEDULE_ID INT NOT NULL,
	STUDENT_ID INT NOT NULL,
	DAY DATE,
	CONSTRAINT PK_ATTENDACE PRIMARY KEY (ID)
);


ALTER TABLE ENROLL_COURSE ADD CONSTRAINT FK_ENROLL_COURSE_STUDENT FOREIGN KEY (STUDENT_ID)
REFERENCES STUDENT(ID);
ALTER TABLE ENROLL_COURSE ADD CONSTRAINT FK_ENROLL_COURSE_SCHEDULE FOREIGN KEY (SCHEDULE_ID)
REFERENCES SCHEDULE(ID);


ALTER TABLE SCHEDULE ADD CONSTRAINT FK_SCHEDULE_COURSE FOREIGN KEY(COURSE_ID)
REFERENCES COURSE(ID);
ALTER TABLE SCHEDULE ADD CONSTRAINT FK_SCHEDULE_TEACHER FOREIGN KEY(TEACHER_ID)
REFERENCES TEACHER(ID);


ALTER TABLE ATTENDANCE ADD CONSTRAINT FK_ATTENDANCE_SCHEDULE FOREIGN KEY(SCHEDULE_ID)
REFERENCES SCHEDULE(ID);
ALTER TABLE ATTENDANCE ADD CONSTRAINT FK_ATTENDANCE_STUDENT FOREIGN KEY(STUDENT_ID)
REFERENCES STUDENT(ID);

INSERT INTO STUDENT VALUES(19120617,'M???ch Vi Phong','213235412512',TO_DATE('01/11/2001', 'DD/MM/YYYY'),'0123456789','phvia@gmail.com','1','19120617','$2a$12$njbcRHym9nOxv.UH5/F4HOkzq9Pghw0/bCEncckHoEwURESgwpWeu');
INSERT INTO STUDENT VALUES(19120644,'L?? ?????c T??m','216593456783',TO_DATE('31/01/2001', 'DD/MM/YYYY'),'0123456789','ltam@gmail.com','0','19120644','$2a$12$7TDzwlLO1DNZ5tHFI9lL5.hIhpQcgqjUT9rC3AQ2vVO029avebqxy');
INSERT INTO STUDENT VALUES(19120622,'Nguy???n Minh Ph???ng','216529567292',TO_DATE('25/05/2001', 'DD/MM/YYYY'),'0123456789','mp269@gmail.com','1','19120622','$2a$12$ZIw9GYNqpKb9Wq0MXdZ/Xek7ZyMOouijOzTuq4hTnZLpQipo/9seG');
INSERT INTO STUDENT VALUES(19120699,'L?? H?? Nam','116923567292',TO_DATE('11/09/2001', 'DD/MM/YYYY'),'01234532189','lenam@gmail.com','1','19120699','$2a$12$zOR/nSI8eNDxQwet3EK8.eALWzR3QSxpVJswL7OkMkiS5LjMsud1.');
INSERT INTO STUDENT VALUES(19120634,'Tr???n Minh','215529897122',TO_DATE('03/12/2001', 'DD/MM/YYYY'),'0199956789','tranminh@gmail.com','1','19120634','$2a$12$w0Kgoc7S.mhg8gzPNX/a4OG5Q/jY/HEvmIAxJ69joZQ6atqzN5WE6');


INSERT INTO TEACHER VALUES(1237898,'Ph???m Tu???n','213125412512',0123456789,'phvia@gmail.com',3000000,'1237898','$2a$12$lAvbd5r7BUwWt3N2S7ldxentoaHPbCu8z8.wwUgPMsQi6/2F8nLiS');
INSERT INTO TEACHER VALUES(1231231,'L?? H???ng Mai','216999456783',0123456789,'ltam@gmail.com',3500000,'1231231','$2a$12$opxipKUfD.fEOlTL8ve3eOiv/kmKp3T5t.9tsx.8Mndp8t2mPmMcK');
INSERT INTO TEACHER VALUES(1239993,'Nguy???n Th??? Di???m My','216529564862',0123456789,'mp269@gmail.com',4000000,'1239993','$2a$12$gEfnIuwSpMRoEeCBPlYdaeNtwDjuxTQ.fEMtA.R/sNYqVBiJQtfdS');


INSERT INTO COURSE VALUES('CSC0001','L???p tr??nh h?????ng ?????i t?????ng');
INSERT INTO COURSE VALUES('CSC0034','C?? s??? tr?? tu??? nh??n t???o');
INSERT INTO COURSE VALUES ('CSC0053','Nh???p m??n h???c m??y');
INSERT INTO COURSE VALUES('CSC0099','Thi???t k??? ph???n m???m');
INSERT INTO COURSE VALUES('CSC0044','L???p tr??nh blockchain');
INSERT INTO COURSE VALUES('CSC0027','M???ng m??y t??nh');
INSERT INTO COURSE VALUES('CSC0003','An ninh m??y t??nh');
INSERT INTO COURSE VALUES('CSC0013','B???o m???t c?? s??? d??? li???u');


INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0001',1237898,TO_DATE('02/03/2022', 'DD/MM/YYYY'),TO_DATE('13/06/2022', 'DD/MM/YYYY'),'2','1','4','E203','2','2021-2022');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0001',1237898,TO_DATE('02/03/2022', 'DD/MM/YYYY'),TO_DATE('13/06/2022', 'DD/MM/YYYY'),'2','6','9','E203','2','2021-2022');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0034',1231231,TO_DATE('02/03/2022', 'DD/MM/YYYY'),TO_DATE('09/06/2022', 'DD/MM/YYYY'),'5','1','4','G302','2','2021-2022');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0053',1239993,TO_DATE('02/03/2022', 'DD/MM/YYYY'),TO_DATE('10/06/2022', 'DD/MM/YYYY'),'6','1','4','E102','2','2021-2022');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0099',1237898,TO_DATE('02/03/2022', 'DD/MM/YYYY'),TO_DATE('09/06/2022', 'DD/MM/YYYY'),'5','2','4','E302','2','2021-2022');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0044',1231231,TO_DATE('01/09/2022', 'DD/MM/YYYY'),TO_DATE('13/12/2022', 'DD/MM/YYYY'),'3','2','4','E102','1','2022-2023');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0027',1231231,TO_DATE('16/02/2021', 'DD/MM/YYYY'),TO_DATE('26/05/2021', 'DD/MM/YYYY'),'4','6','9','G202','2','2020-2021');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0003',1237898,TO_DATE('01/09/2021', 'DD/MM/YYYY'),TO_DATE('29/12/2021', 'DD/MM/YYYY'),'3','2','4','E102','1','2021-2022');
INSERT INTO SCHEDULE(COURSE_ID,TEACHER_ID,DATE_START,DATE_END,DAY_OF_WEEK,SHIFT_START,SHIFT_END,ROOM,TERM,YEAR) VALUES ('CSC0013',1237898,TO_DATE('01/09/2021', 'DD/MM/YYYY'),TO_DATE('29/12/2021', 'DD/MM/YYYY'),'4','6','9','G202','1','2021-2022');

INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(1,19120617,TO_DATE('14/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(1,19120644,TO_DATE('25/04/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(2,19120644,TO_DATE('21/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(2,19120622,TO_DATE('04/04/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(3,19120617,TO_DATE('03/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(3,19120617,TO_DATE('24/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(3,19120644,TO_DATE('03/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(3,19120644,TO_DATE('07/04/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(4,19120622,TO_DATE('04/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(4,19120622,TO_DATE('11/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(5,19120644,TO_DATE('03/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(5,19120644,TO_DATE('24/03/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(6,19120622,TO_DATE('06/09/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(6,19120617,TO_DATE('06/09/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(6,19120617,TO_DATE('04/10/2022', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(7,19120622,TO_DATE('14/04/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(7,19120622,TO_DATE('17/02/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(7,19120622,TO_DATE('24/02/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(7,19120622,TO_DATE('10/03/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(7,19120622,TO_DATE('17/03/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(7,19120622,TO_DATE('31/03/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(8,19120617,TO_DATE('07/09/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(8,19120617,TO_DATE('28/09/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(8,19120617,TO_DATE('26/10/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(9,19120617,TO_DATE('01/09/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(9,19120617,TO_DATE('08/09/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(9,19120617,TO_DATE('10/09/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(9,19120617,TO_DATE('22/09/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(9,19120617,TO_DATE('29/09/2021', 'DD/MM/YYYY'));
INSERT INTO ATTENDANCE (SCHEDULE_ID,STUDENT_ID,DAY) VALUES(9,19120617,TO_DATE('06/10/2021', 'DD/MM/YYYY'));

INSERT INTO ENROLL_COURSE VALUES(19120644,6);
INSERT INTO ENROLL_COURSE VALUES(19120622,6);
INSERT INTO ENROLL_COURSE VALUES(19120617,6);
INSERT INTO ENROLL_COURSE VALUES(19120617,1);
INSERT INTO ENROLL_COURSE VALUES(19120617,3);
INSERT INTO ENROLL_COURSE VALUES(19120644,1);
INSERT INTO ENROLL_COURSE VALUES(19120644,2);
INSERT INTO ENROLL_COURSE VALUES(19120644,3);
INSERT INTO ENROLL_COURSE VALUES(19120644,5);
INSERT INTO ENROLL_COURSE VALUES(19120622,4);
INSERT INTO ENROLL_COURSE VALUES(19120622,7);
INSERT INTO ENROLL_COURSE VALUES(19120622,8);
INSERT INTO ENROLL_COURSE VALUES(19120617,9);

SELECT * FROM SCHEDULE
INSERT INTO ATTENDANCE SELECT * FROM ATTENDANCE;
select * from COURSE
SELECT * FROM STUDENT;
select * from enroll_course;
SELECT * FROM TEACHER;
SELECT * from attendance;

