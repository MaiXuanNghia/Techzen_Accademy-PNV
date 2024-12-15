package vn.techzen.academy_pnv_25.controllor;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.model.Department;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.service.IDepartmentService;
import vn.techzen.academy_pnv_25.utils.JsonResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
@CrossOrigin("http://localhost:5173")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody Department newDepartment) {
        return JsonResponse.created(departmentService.saveDepartment(newDepartment));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> getDepartments() {
        return JsonResponse.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> getDepartmentById(@PathVariable Integer id) {
        return JsonResponse.ok(departmentService.getDepartment(id));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Department>> updateDepartment(@RequestBody Department updatedDepartment) {
        return JsonResponse.ok(departmentService.updateDepartment(updatedDepartment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> deleteDepartment(@PathVariable int id) {
        return JsonResponse.ok(departmentService.deleteDepartment(id));
    }
}
