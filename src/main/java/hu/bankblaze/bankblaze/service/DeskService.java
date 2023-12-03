package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Desk;
import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.model.Permission;
import hu.bankblaze.bankblaze.model.QueueNumber;
import hu.bankblaze.bankblaze.repo.DeskRepository;
import hu.bankblaze.bankblaze.repo.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DeskService {
    private DeskRepository deskRepository;
    private EmployeeRepository employeeRepository;
    private QueueNumberService queueNumberService;
    private PermissionService permissionService;

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

    public Long getDeskIdByLoggedInUser(Long loggedInUserId) {
        Desk desk = deskRepository.findByEmployeeId(loggedInUserId);

        return desk.getId();
    }

    public Desk getDeskByEmployeeId(Long employeeId) {
        return deskRepository.findByEmployeeId(employeeId);
    }

    public void saveDesk(Desk desk) {
        deskRepository.save(desk);
    }

    public boolean nextQueueNumber(Employee employee) {
        Desk desk = getDeskByEmployeeId(employee.getId());
        Permission permission = permissionService.getPermissionByEmployee(employee);
        List<QueueNumber> queueNumberList = new ArrayList<>();
        try {
            if (permission.getForRetail() && queueNumberService.countRetail() > 0) {
                queueNumberList.add(queueNumberService.getNextRetail());
            }
            if (permission.getForCorporate() && queueNumberService.countCorporate() > 0) {
                queueNumberList.add(queueNumberService.getNextCorporate());
            }
            if (permission.getForTeller() && queueNumberService.countTeller() > 0) {
                queueNumberList.add(queueNumberService.getNextTeller());
            }
            if (permission.getForPremium() && queueNumberService.countPremium() > 0) {
                queueNumberList.add(queueNumberService.getNextPremium());
            }
            QueueNumber queueNumber = queueNumberService.getSmallestNumber(queueNumberList);
            desk.setQueueNumber(queueNumber);
            deskRepository.save(desk);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected Desk findDeskByQueueNumber(QueueNumber queueNumber) {
        return deskRepository.findByQueueNumber(queueNumber);
    }


}
