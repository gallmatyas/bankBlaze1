package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.repo.QueueNumberRepository;
import hu.bankblaze.bankblaze.service.AdminService;
import hu.bankblaze.bankblaze.service.DeskService;
import hu.bankblaze.bankblaze.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private AdminService adminService;
    private DeskService deskService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public String getEmployeePage(Model model) {
        Employee employee = adminService.getEmployeeByName(adminService.getLoggedInUsername());
        model.addAttribute("desk", deskService.getDeskByEmployeeId(employee.getId()));

        adminService.getLoggedInClerks();
        adminService.getLoggedInUsername();
        adminService.setQueueCounts(model);
        adminService.setActualPermission(model, employee);
        adminService.setActualCount(model, employee);
        adminService.setEmployeeCount(model, employee);

        return "employee";
    }

    @PostMapping
    public String nextQueueNumber(Model model) {
        Employee employee = adminService.getEmployeeByName(adminService.getLoggedInUsername());
        deskService.nextQueueNumber(employee);
        adminService.setActualPermission(model, employee);
        return "redirect:/desk/next";
    }

    @GetMapping("/closure")
    public String getClosure(Model model){
        adminService.processClosure(model);
        return "redirect:/employee";
    }
    @GetMapping("/redirect")
    public String getRedirect(Model model, @RequestParam("redirectLocation") String redirectLocation) {
        adminService.processRedirect(model, redirectLocation);
        return "redirect:/employee";
    }

    @GetMapping("/deleteNumber")
    public String deleteNumber(Model model){
        adminService.deleteNextQueueNumber(model);
        return "redirect:/employee";
    }


}

