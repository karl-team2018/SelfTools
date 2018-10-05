CREATE TABLE test.t_applicant
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(10) NOT NULL,
    age int(11) NOT NULL,
    telphoneno varchar(11) NOT NULL,
    onthejob tinyint(1) NOT NULL,
    sex char(1) NOT NULL,
    job varchar(20) NOT NULL,
    experience int(11) NOT NULL,
    expected_at_work int(11),
    resume_id varchar(32)
);
INSERT INTO test.t_applicant (id, name, age, telphoneno, onthejob, sex, job, experience, expected_at_work, resume_id) VALUES (101, 'zhunn', 28, '17717374258', 1, '1', 'c软件工程师', 6, 3, 'zhunn-123192389127312738');