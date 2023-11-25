package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Desk;
import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.repo.DeskRepository;
import hu.bankblaze.bankblaze.repo.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeskService {
    private DeskRepository deskRepository;
    private EmployeeRepository employeeRepository;

    public void saveDeskLayout(Desk desk) {
        deskRepository.save(desk);
    }

    public Desk getDeskById(Long id) {
        return deskRepository.findById(id).orElse(null);
    }

    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    public void modifyEmployee(Long id, Long newEmployee) {
        Desk desk = getDeskById(id);
        Employee employee = employeeRepository.findById(newEmployee).orElse(null);
        desk.setEmployee(employee);
        deskRepository.save(desk);
    }

}
