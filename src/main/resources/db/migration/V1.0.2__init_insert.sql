-- Chèn dữ liệu vào bảng department
INSERT INTO department (name)
VALUES
    ('HR'),
    ('Finance'),
    ('IT'),
    ('Marketing');

-- Chèn dữ liệu vào bảng clazz
INSERT INTO clazz (name)
VALUES
    ('Class A'),
    ('Class B'),
    ('Class C');

-- Chèn dữ liệu vào bảng employee
INSERT INTO employee (id, name, dob, gender, salary, phone, department_id)
VALUES
    (UUID_TO_BIN(UUID()), 'John Doe', '1985-06-15', 1, 50000.00, '123-456-7890', 1),
    (UUID_TO_BIN(UUID()), 'Jane Smith', '1990-09-20', 2, 60000.00, '987-654-3210', 2),
    (UUID_TO_BIN(UUID()), 'Jim Brown', '1987-02-25', 1, 55000.00, '555-555-5555', 3);

-- Chèn dữ liệu vào bảng student
INSERT INTO student (name, score, clazz_id)
VALUES
    ('Alice Johnson', 95.0, 1),
    ('Bob Lee', 88.0, 2),
    ('Charlie Davis', 91.0, 3);