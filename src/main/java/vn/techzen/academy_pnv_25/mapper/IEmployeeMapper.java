package vn.techzen.academy_pnv_25.mapper;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeRequest;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeResponse;
import vn.techzen.academy_pnv_25.entity.Employee;

@Mapper(componentModel = "spring")
@Primary
public interface IEmployeeMapper {
    Employee employeeRequestToEmployee (EmployeeRequest employee);
    EmployeeResponse employeeToEmployeeResponse (Employee employee);
}
