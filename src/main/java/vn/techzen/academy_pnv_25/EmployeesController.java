package vn.techzen.academy_pnv_25;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private List<Employees> employees = new ArrayList<>(
            Arrays.asList(
                    new Employees(UUID.randomUUID(), "Nguyen Van A", LocalDate.of(1990, 5, 15), Employees.Gender.MALE, 1500.0, "0901234567"),
                    new Employees(UUID.randomUUID(), "Tran Thi B", LocalDate.of(1995, 8, 20), Employees.Gender.FEMALE, 2000.0, "0902234567"),
                    new Employees(UUID.randomUUID(), "Le Van C", LocalDate.of(1992, 12, 10), Employees.Gender.MALE, 1800.0, "0903234567"),
                    new Employees(UUID.randomUUID(), "Pham Thi D", LocalDate.of(1997, 3, 25), Employees.Gender.FEMALE, 2200.0, "0904234567"),
                    new Employees(UUID.randomUUID(), "Nguyen Van E", LocalDate.of(1998, 7, 5), Employees.Gender.OTHER, 1600.0, "0905234567")
            )
    );
    @GetMapping
    public ResponseEntity<List<Employees>> getEmployees() {
        return ResponseEntity.ok(employees);
    };

    @PostMapping
    public ResponseEntity<Employees> addEmployees(@RequestBody Employees NewEmployees) {
        employees.add(NewEmployees);
        return ResponseEntity.status(HttpStatus.CREATED).body(NewEmployees);
    };

    @PutMapping
    public ResponseEntity<Employees> updateEmployees(@RequestBody Employees NewEmployees) {
        for (Employees emp : employees) {
            if (emp.getId().equals(NewEmployees.getId())) {
                emp.setName(NewEmployees.getName());
                emp.setGender(NewEmployees.getGender());
                emp.setSalary(NewEmployees.getSalary());
                emp.setPhone(NewEmployees.getPhone());
                emp.setDob(NewEmployees.getDob());

                return ResponseEntity.status(HttpStatus.CREATED).body(emp);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping
    public ResponseEntity<Employees> deleteEmployees(@RequestBody Employees NewEmployees) {
        for (Employees emp : employees) {
            if (emp.getId().equals(NewEmployees.getId())) {
                employees.remove(emp);

                return ResponseEntity.status(HttpStatus.OK).body(emp);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
