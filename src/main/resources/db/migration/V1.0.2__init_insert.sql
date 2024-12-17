INSERT INTO department (name) VALUES
                                  ('Human Resources'),
                                  ('Finance'),
                                  ('Engineering'),
                                  ('Marketing'),
                                  ('IT Support');
INSERT INTO clazz (name) VALUES
                             ('Class A'),
                             ('Class B'),
                             ('Class C'),
                             ('Class D'),
                             ('Class E');
INSERT INTO employee (id, name, dob, gender, salary, phone, department_id) VALUES
                                                                               (UNHEX(REPLACE(UUID(), '-', '')), 'John Doe', '1990-01-01', 1, 50000.0, '123456789', 1),
                                                                               (UNHEX(REPLACE(UUID(), '-', '')), 'Jane Smith', '1992-05-15', 2, 60000.0, '987654321', 2),
                                                                               (UNHEX(REPLACE(UUID(), '-', '')), 'Alice Johnson', '1988-03-20', 1, 75000.0, '123123123', 3),
                                                                               (UNHEX(REPLACE(UUID(), '-', '')), 'Bob Brown', '1995-10-10', 1, 40000.0, '456456456', 4),
                                                                               (UNHEX(REPLACE(UUID(), '-', '')), 'Charlie White', '2000-07-25', 2, 30000.0, '789789789', 5);
INSERT INTO student (score, name, clazz_id) VALUES
                                                (85.5, 'Tom Williams', 1),
                                                (90.0, 'Lucy Brown', 2),
                                                (78.0, 'Oliver Smith', 3),
                                                (88.5, 'Emma Johnson', 4),
                                                (95.0, 'Sophia Wilson', 5);
