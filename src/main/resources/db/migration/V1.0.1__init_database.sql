CREATE TABLE department
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NULL,
    CONSTRAINT pk_department PRIMARY KEY (id)
);

CREATE TABLE clazz
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NULL,
    CONSTRAINT pk_clazz PRIMARY KEY (id)
);

CREATE TABLE employee
(
    id            BINARY(16)   NOT NULL,
    name          VARCHAR(255) NULL,
    dob           date         NULL,
    gender        SMALLINT     NULL,
    salary        DOUBLE       NULL,
    phone         VARCHAR(255) NULL,
    department_id INT          NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES department (id);

CREATE TABLE student
(
    id       INT AUTO_INCREMENT NOT NULL,
    score    DOUBLE             NOT NULL,
    name     VARCHAR(255)       NULL,
    clazz_id INT                NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_CLAZZ FOREIGN KEY (clazz_id) REFERENCES clazz (id);