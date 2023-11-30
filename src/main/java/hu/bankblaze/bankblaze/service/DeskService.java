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

    public void nextQueueNumber(Employee employee) {
        Desk desk = getDeskByEmployeeId(employee.getId());
        Permission permission = permissionService.getPermissionByEmployee(employee);
        List<QueueNumber> queueNumberList = new ArrayList<>();
        if (permission.getForRetail()){
            queueNumberList.add(queueNumberService.getNextRetail());
        } else if (permission.getForCorporate()){
            queueNumberList.add(queueNumberService.getNextCorporate());
        } else if (permission.getForTeller()){
            queueNumberList.add(queueNumberService.getNextTeller());
        } else if (permission.getForPremium()) {
            queueNumberList.add(queueNumberService.getNextPremium());
        }
        QueueNumber queueNumber = queueNumberService.getSmallestNumber(queueNumberList);
        desk.setQueueNumber(queueNumber);
        System.out.println(desk.getQueueNumber());
        deskRepository.save(desk);
    }
    protected Desk findDeskByQueueNumber(QueueNumber queueNumber) {
        return deskRepository.findByQueueNumber(queueNumber);
    }


}
