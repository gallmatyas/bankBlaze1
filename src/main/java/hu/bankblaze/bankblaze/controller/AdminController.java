package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.model.Permission;
import hu.bankblaze.bankblaze.repo.QueueNumberRepository;
import hu.bankblaze.bankblaze.service.AdminService;
import hu.bankblaze.bankblaze.service.DeskService;
import hu.bankblaze.bankblaze.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    private QueueNumberRepository queueNumberRepository;
    private PermissionService permissionService;
    private DeskService deskService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllClerks(Model model) {
        model.addAttribute("employees", adminService.getAllClerks());
        model.addAttribute("admins", adminService.getAllAdmins());
        return "admin";
    }

    @PostMapping("/update")
    public String getAllClerks(@RequestParam("employeeId") Long id,
                               @RequestParam("retailCheckbox") Boolean forRetail,
                               @RequestParam("corporateCheckbox") Boolean forCorporate,
                               @RequestParam("tellerCheckbox") Boolean forTeller,
                               @RequestParam("premiumCheckbox") Boolean forPremium) {
        permissionService.modifyForRetail(id, forRetail);
        permissionService.modifyForCorporate(id, forCorporate);
        permissionService.modifyForTellers(id, forTeller);
        permissionService.modifyForPremium(id, forPremium);
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
        model.addAttribute("employees", adminService.getAllClerks());
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

    @PostMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdminById(id);
        return "redirect:/admin";
    }

    @PostMapping("update/{id}")
    public String updatePermission(@PathVariable("id") Integer id, @ModelAttribute("permission") Permission update) {
        permissionService.savePermisson(update);
        return "redirect:/admin/" + id;
    }


}