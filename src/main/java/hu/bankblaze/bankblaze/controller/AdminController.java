package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.model.Permission;
import hu.bankblaze.bankblaze.repo.QueueNumberRepository;
import hu.bankblaze.bankblaze.service.AdminService;
import hu.bankblaze.bankblaze.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    private QueueNumberRepository queueNumberRepository;
    private PermissionService permissionService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllClerks(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("clerks", adminService.getAllClerks());
        model.addAttribute("permissions", permissionService.getAllPermissions());
        return "admin";
    }

    @PostMapping("/update")
    public String updatePermissions(@RequestParam Map<String, String> permissions) {
        permissionService.updatePermissions(permissions);
        return "redirect:/admin";
    }



    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("lakossagCount", queueNumberRepository.countByNumberBetween(1000, 1999));
        model.addAttribute("vallalatCount", queueNumberRepository.countByNumberBetween(2000, 2999));
        model.addAttribute("penztarCount", queueNumberRepository.countByNumberBetween(3000, 3999));
        model.addAttribute("premiumCount", queueNumberRepository.countByNumberBetween(9000, 9999));
        return "statistics";
    }

    @GetMapping("/desk")
    public String setDesks(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "desk";
    }

    @PostMapping("/desk")
    public String setDesks(@RequestParam("employeeId") Long id) {
        return "redirect:/admin/desk";
    }

    @GetMapping("/registration")
    public String createEmployee(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("newEmployee", new Employee());
        return "registration";
    }

    @PostMapping("/registration")
    public String createEmployee(@ModelAttribute("newEmployee") Employee employee,
                                 @RequestParam("defaultRole") String defaultRole) {
        employee.setRole(String.valueOf(defaultRole));
        adminService.saveAdmin(employee);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "delete";
    }
    @PostMapping("/delete")
    public String deleteAdmin(@RequestParam("action") String action, String name) {
        if (action.equals("delete")) {
            adminService.deleteAdminByName(name);
        }
        return "redirect:/admin";
    }

    @PostMapping("update/{id}")
    public String updatePermission(@PathVariable("id") Integer id, @ModelAttribute("permission") Permission update) {
        permissionService.savePermisson(update);
        return "redirect:/admin/" + id;
    }


}