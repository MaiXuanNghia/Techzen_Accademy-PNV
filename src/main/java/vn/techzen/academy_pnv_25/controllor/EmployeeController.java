package vn.techzen.academy_pnv_25.controllor;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.model.Employee;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.service.IEmployeeService;
import vn.techzen.academy_pnv_25.utils.JsonResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:5173")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
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
    public ResponseEntity<ApiResponse<Employee>> getEmployees(@PathVariable UUID id) {
        return JsonResponse.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> addEmployees(@RequestBody Employee NewEmployees) {
        return JsonResponse.created(employeeService.save(NewEmployees));
    };

    @PutMapping
    public ResponseEntity<ApiResponse<Employee>> updateEmployees(@RequestBody Employee NewEmployees) {
        return JsonResponse.ok(employeeService.update(NewEmployees));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Employee>> deleteEmployees(@RequestBody Employee NewEmployees) {
        return JsonResponse.ok(employeeService.delete(NewEmployees));
    }
}
