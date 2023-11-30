package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.model.Permission;
import hu.bankblaze.bankblaze.repo.QueueNumberRepository;
import hu.bankblaze.bankblaze.service.AdminService;
import hu.bankblaze.bankblaze.service.DeskService;
import hu.bankblaze.bankblaze.service.PermissionService;
import hu.bankblaze.bankblaze.service.QueueNumberService;
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
    private QueueNumberService queueNumberService;
    private QueueNumberRepository queueNumberRepository;
    private PermissionService permissionService;
    private DeskService deskService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllClerks(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("clerks", adminService.getAllActiveClerks());
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
        model.addAttribute("retailCount", queueNumberService.countRetail());
        model.addAttribute("corporateCount", queueNumberService.countCorporate());
        model.addAttribute("tellerCount", queueNumberService.countTeller());
        model.addAttribute("privateCount", queueNumberService.countPremium());
        return "statistics";
    }

    @GetMapping("/desk")
    public String setDesks(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("employees", adminService.getAllActiveClerks());
        model.addAttribute("desks", deskService.getAllDesks());
        return "desk";
    }

    @PostMapping("/desk")
    public String setDesks(@RequestParam("desk1Id") Long id1,
                           @RequestParam("desk1") Long desk1,
                           @RequestParam("desk2Id") Long id2,
                           @RequestParam("desk2") Long desk2,
                           @RequestParam("desk3Id") Long id3,
                           @RequestParam("desk3") Long desk3,
                           @RequestParam("desk4Id") Long id4,
                           @RequestParam("desk4") Long desk4,
                           @RequestParam("desk5Id") Long id5,
                           @RequestParam("desk5") Long desk5,
                           @RequestParam("desk6Id") Long id6,
                           @RequestParam("desk6") Long desk6) {
        deskService.modifyEmployee(id1, desk1);
        deskService.modifyEmployee(id2, desk2);
        deskService.modifyEmployee(id3, desk3);
        deskService.modifyEmployee(id4, desk4);
        deskService.modifyEmployee(id5, desk5);
        deskService.modifyEmployee(id6, desk6);
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

    @GetMapping("/management")
    public String getManagement(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("employees", adminService.getAllClerks());
        return "management";
    }

    @PostMapping("/management")
    public String setManagement(@RequestParam("action") String action, String name) {
        switch (action) {
            case "USER", "inactive" -> adminService.modifyEmployeeByName(name, action);
            case "delete" -> adminService.deleteAdminByName(name);
        }

        return "redirect:/admin";
    }

    @PostMapping("update/{id}")
    public String updatePermission(@PathVariable("id") Integer id, @ModelAttribute("permission") Permission update) {
        permissionService.savePermisson(update);
        return "redirect:/admin/" + id;
    }

    @PostMapping("/eod")
    public String endOfDay() {
        queueNumberService.deleteAllQueueNumbers();
        return "redirect:/admin";
    }
    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("admins", adminService.getAllClerks());
        return "delete";
    }
    @PostMapping("/delete")
    public String deleteAdmin(@RequestParam("action") String action, String name) {
        if (action.equals("delete")) {
            adminService.deleteAdminByName(name);
        }
        return "redirect:/admin";
    }
}