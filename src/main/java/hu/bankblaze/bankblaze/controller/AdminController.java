package hu.bankblaze.bankblaze.controller;


import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @GetMapping("/{id}")
    public String getAdminById (Model model, @PathVariable Long id) {
        Employee employee = adminService.getAdminById(id);
        model.addAttribute("admin",employee);
        return "admin";

    }

    @GetMapping("/new")
    public String createEmployee (Model model) {
        model.addAttribute("admin", new Employee());
        return "newEmployee";
    }

    @PostMapping("/add")
    public String createEmployee (@ModelAttribute ("admin") Employee employee ){
        adminService.saveAdmin(employee);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteAdmin (@PathVariable Long id){
        adminService.deleteAdminById(id);
        return "redirect:/admin";
    }




}

