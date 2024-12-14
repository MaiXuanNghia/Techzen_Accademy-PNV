package vn.techzen.academy_pnv_25;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.dto.exception.AppException;
import vn.techzen.academy_pnv_25.dto.exception.ErrorCode;
import vn.techzen.academy_pnv_25.utils.JsonResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
@CrossOrigin("http://localhost:5173")
public class DepartmentController {
    private List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "HR"),
                    new Department(2, "IT"),
                    new Department(3, "Finance"),
                    new Department(4, "Marketing")
            )
    );

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody Department newDepartment) {
        departments.add(newDepartment);
        return JsonResponse.created(newDepartment);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> getDepartments() {
        return JsonResponse.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> getDepartmentById(@PathVariable int id) {
        for (Department dept : departments) {
            if (dept.getId() == id) {
                return JsonResponse.ok(dept);
            }
        }
        throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Department>> updateDepartment(@RequestBody Department updatedDepartment) {
        for (Department dept : departments) {
            if (dept.getId().equals(updatedDepartment.getId())) {
                dept.setName(updatedDepartment.getName());
                return JsonResponse.ok(dept);
            }
        }
        throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> deleteDepartment(@PathVariable int id) {
        for (Department dept : departments) {
            if (dept.getId() == id) {
                departments.remove(dept);
                return JsonResponse.ok(dept);
            }
        }
        throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }
}
