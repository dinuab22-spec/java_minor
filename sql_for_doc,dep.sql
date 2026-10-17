CREATE TABLE department (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE doctor (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_name VARCHAR(100),
    department_id INT ,
    status VARCHAR(20)default "offline",

    FOREIGN KEY (department_id)
        REFERENCES department(department_id)
   
);
