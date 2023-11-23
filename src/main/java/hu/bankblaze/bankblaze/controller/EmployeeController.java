package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.repo.QueueNumberRepository;
import hu.bankblaze.bankblaze.service.AdminService;
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

@Controller
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private AdminService adminService;
    private QueueNumberRepository queueNumberRepository;
    private PermissionService permissionService;


    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public String getLoggedInClerks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        model.addAttribute("loggedInUsername", loggedInUsername);
        model.addAttribute("lakossagCount", queueNumberRepository.countByNumberBetween(1000, 1999));
        model.addAttribute("vallalatCount", queueNumberRepository.countByNumberBetween(2000, 2999));
        model.addAttribute("penztarCount", queueNumberRepository.countByNumberBetween(3000, 3999));
        model.addAttribute("premiumCount", queueNumberRepository.countByNumberBetween(9000, 9999));
        // model.addAttribute("descs", descService.getAllDescs());
        return "employee";
    }
    @PostMapping
    public String getLoggedInClerks() {
        return "redirect:/employee";
    }

}

