package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.repo.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private EmployeeRepository employeeRepository;

    public Employee getAdminById (Long id){
        return employeeRepository.findById(id).orElse(null);
    }
    public void saveAdmin (Employee employee) {
        employeeRepository.save(employee);
    }
    public void deleteAdminById( Long id) {
        employeeRepository.deleteById(id);
    }



}

