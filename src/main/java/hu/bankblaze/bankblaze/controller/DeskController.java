package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.repo.DeskRepository;
import hu.bankblaze.bankblaze.repo.EmployeeRepository;
import hu.bankblaze.bankblaze.repo.PermissionRepository;
import hu.bankblaze.bankblaze.repo.QueueNumberRepository;
import hu.bankblaze.bankblaze.service.AdminService;
import hu.bankblaze.bankblaze.service.DeskService;
import hu.bankblaze.bankblaze.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/desk")
public class DeskController {
    public QueueNumberRepository queueNumberRepository;
    public DeskRepository deskRepository;
    public DeskService deskService;
    public EmployeeRepository employeeRepository;
    public AdminService adminService;
    public PermissionService permissionService;
    public PermissionRepository permissionRepository;

    @GetMapping("/next")
    public String getNextClient(Model model) {
        Employee employee = adminService.getEmployeeByName(adminService.getLoggedInUsername());
        model.addAttribute("desk", deskService.getDeskByEmployeeId(employee.getId()));
        adminService.setQueueCounts(model);
        adminService.setActualPermission(model, employee);
        adminService.setActualCount(model, employee);
        adminService.setEmployeeCount(model, employee);
        return "next";
    }

    @PostMapping("next")
    public String getNextClient(){
        return "redirect:/next";
    }

}

