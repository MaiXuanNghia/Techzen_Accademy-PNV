package vn.techzen.academy_pnv_25.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.entity.Employee;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.service.IEmployeeService;
import vn.techzen.academy_pnv_25.utils.JsonResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    IEmployeeService employeeService;

    @GetMapping
//    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees() {
//        return JsonResponse.ok(employeeService.findAll());
//    }
    public ResponseEntity<ApiResponse<Page<Employee>>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sort)));
        return JsonResponse.ok(employeeService.findAll(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(required = false) Employee.Gender gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer departmentId
    ) {
        EmployeeSearchRequest request = new EmployeeSearchRequest(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);
        return JsonResponse.ok(employeeService.findByAttributes(request));
    };

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Employee>>> getEmployees(@PathVariable UUID id) {
        return JsonResponse.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> addEmployees(@RequestBody Employee NewEmployees) {
        return JsonResponse.created(employeeService.save(NewEmployees));
    };

    @PutMapping
    public ResponseEntity<ApiResponse<Optional<Employee>>> updateEmployees(@RequestBody Employee NewEmployees) {
        return JsonResponse.ok(employeeService.update(NewEmployees));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Optional<Employee>>> deleteEmployees(@RequestBody Employee NewEmployees) {
        return JsonResponse.ok(employeeService.delete(NewEmployees));
    }
}
