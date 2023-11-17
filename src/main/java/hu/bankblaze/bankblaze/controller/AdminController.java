package hu.bankblaze.bankblaze.controller;
import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.service.AdminService;
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


    private PermissionService permissionService;


    @GetMapping

    public String getAllClerks(Model model) {
        model.addAttribute("employees", adminService.getAllClerks());
        return "admin";
    }
    @PostMapping("/update")
    public String getAllClerks ( @RequestParam("employeeId") Long id,
                                 @RequestParam("retailCheckbox")Boolean forRetail,
                                 @RequestParam("corporateCheckbox")Boolean forCorporate,
                                 @RequestParam("tellerCheckbox")Boolean forTeller,
                                 @RequestParam("premiumCheckbox")Boolean forPremium)
    {
        permissionService.modifyForRetail(id,forRetail);
        permissionService.modifyForCorporate(id,forCorporate);
        permissionService.modifyForTellers(id,forTeller);
        permissionService.modifyForPremium(id,forPremium);
        return "redirect:/admin";
    }
    @GetMapping("/employee/{id}")
    public String getEmployeeById(Model model, @PathVariable Long id) {
        model.addAttribute("employeeById", adminService.getAllClerks());
        return "admin";

    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdmin(){
        return "admin";
    }


    @GetMapping("/{id}")
    public String getAdminById(Model model, @PathVariable Long id) {
        Employee employee = adminService.getAdminById(id);
        model.addAttribute("admin", employee);
        return "admin";

    }

    @GetMapping("/new")
    public String createEmployee(Model model) {
        model.addAttribute("admin", new Employee());
        return "newEmployee";
    }

    @PostMapping("/add")
    public String createEmployee(@ModelAttribute("admin") Employee employee) {
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