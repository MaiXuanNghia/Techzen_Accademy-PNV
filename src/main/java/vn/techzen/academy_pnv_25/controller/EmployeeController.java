package vn.techzen.academy_pnv_25.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeRequest;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeResponse;
import vn.techzen.academy_pnv_25.mapper.IEmployeeMapper;
import vn.techzen.academy_pnv_25.typeCustom.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.dto.page.PageResponse;
import vn.techzen.academy_pnv_25.entity.Employee;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.service.IEmployeeService;
import vn.techzen.academy_pnv_25.typeCustom.Gender;
import vn.techzen.academy_pnv_25.utils.JsonResponse;

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
    IEmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<?> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate dobFrom,
            @RequestParam(required = false) LocalDate dobTo,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer departmentId,
            Pageable pageable) {

        Page<Employee> employeePage = employeeService.findAll(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId, pageable);

        Page<EmployeeResponse> employeeResponsePage = employeePage.map(employeeMapper::employeeToEmployeeResponse);

        PageResponse<EmployeeResponse> pageResponse = new PageResponse<>(employeeResponsePage);

        return ResponseEntity.ok(ApiResponse.<PageResponse<EmployeeResponse>>builder()
                .data(pageResponse)
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Employee>>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer departmentId
    ) {
        EmployeeSearchRequest request = new EmployeeSearchRequest(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);
        return JsonResponse.ok(employeeService.findByAttributes(request));
    };

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<EmployeeResponse>>> getEmployees(@PathVariable UUID id) {
        Optional<EmployeeResponse> employeeResponse = employeeService.findById(id)
                .map(employeeMapper::employeeToEmployeeResponse);

        return JsonResponse.ok(employeeResponse);
    }

    @PostMapping
    public ResponseEntity<?> addEmployees(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
        EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(employeeService.save(employee));
        return JsonResponse.created(employeeResponse);
    };

    @PutMapping
    public ResponseEntity<ApiResponse<Optional<EmployeeResponse>>> updateEmployees(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
        Optional<EmployeeResponse> employeeResponse = employeeService.update(employee)
                .map(employeeMapper::employeeToEmployeeResponse);
        return JsonResponse.ok(employeeResponse);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Optional<EmployeeResponse>>> deleteEmployees(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
        Optional<EmployeeResponse> employeeResponse = employeeService.delete(employee)
                .map(employeeMapper::employeeToEmployeeResponse);
        return JsonResponse.ok(employeeResponse);
    }
}
