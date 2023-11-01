package hu.bankblaze.bankblaze.repo;

import hu.bankblaze.bankblaze.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
