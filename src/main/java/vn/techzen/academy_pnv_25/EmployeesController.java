package vn.techzen.academy_pnv_25;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.dto.exception.AppException;
import vn.techzen.academy_pnv_25.dto.exception.ErrorCode;
import vn.techzen.academy_pnv_25.utils.JsonResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:5173")
public class EmployeesController {

    private List<Employees> employees = new ArrayList<>(
            Arrays.asList(
                    new Employees(UUID.randomUUID(), "Nguyen Van A", LocalDate.of(1990, 5, 15), Employees.Gender.MALE, 1500.0, "0901234567", 1),
                    new Employees(UUID.randomUUID(), "Tran Thi B", LocalDate.of(1995, 8, 20), Employees.Gender.FEMALE, 2000.0, "0902234567", 2),
                    new Employees(UUID.randomUUID(), "Le Van C", LocalDate.of(1992, 12, 10), Employees.Gender.MALE, 1800.0, "0903234567", 3),
                    new Employees(UUID.randomUUID(), "Pham Thi D", LocalDate.of(1997, 3, 25), Employees.Gender.FEMALE, 2200.0, "0904234567", 4),
                    new Employees(UUID.randomUUID(), "Nguyen Van E", LocalDate.of(1998, 7, 5), Employees.Gender.OTHER, 1600.0, "0905234567", 1)
            )
    );
    @GetMapping
    public ResponseEntity<ApiResponse<List<Employees>>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(required = false) Employees.Gender gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer departmentId
    ) {
        List<Employees> filteredEmployees = employees.stream()
                .filter(e -> name == null || e.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(e -> dobFrom == null || !e.getDob().isBefore(dobFrom))
                .filter(e -> dobTo == null || !e.getDob().isAfter(dobTo))
                .filter(e -> gender == null || e.getGender() == gender)
                .filter(e -> phone == null || e.getPhone().contains(phone))
                .filter(e -> departmentId == null || e.getDepartmentId().equals(departmentId))
                .filter(e -> {
                    if (salaryRange == null) return true;
                    return switch (salaryRange) {
                        case "1-5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "gt20" -> e.getSalary() > 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
        return JsonResponse.ok(filteredEmployees);
    };

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employees>> getEmployees(@PathVariable UUID id) {
        for (Employees emp : employees) {
            if (emp.getId().equals(id)) {
                return JsonResponse.ok(emp);
            }
        }
        throw new AppException(ErrorCode.EMPLOYEES_NOT_EXIST);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employees>> addEmployees(@RequestBody Employees NewEmployees) {
        employees.add(NewEmployees);
        return JsonResponse.created(NewEmployees);
    };

    @PutMapping
    public ResponseEntity<ApiResponse<Employees>> updateEmployees(@RequestBody Employees NewEmployees) {
        for (Employees emp : employees) {
            if (emp.getId().equals(NewEmployees.getId())) {
                emp.setName(NewEmployees.getName());
                emp.setGender(NewEmployees.getGender());
                emp.setSalary(NewEmployees.getSalary());
                emp.setPhone(NewEmployees.getPhone());
                emp.setDob(NewEmployees.getDob());

                return JsonResponse.ok(emp);
            }
        }
        throw new AppException(ErrorCode.EMPLOYEES_NOT_EXIST);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Employees>> deleteEmployees(@RequestBody Employees NewEmployees) {
        for (Employees emp : employees) {
            if (emp.getId().equals(NewEmployees.getId())) {
                employees.remove(emp);

                return JsonResponse.ok(emp);
            }
        }
        throw new AppException(ErrorCode.EMPLOYEES_NOT_EXIST);
    }
}
