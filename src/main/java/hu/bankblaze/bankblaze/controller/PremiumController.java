package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.model.Premium;
import hu.bankblaze.bankblaze.service.PremiumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/premium")
public class PremiumController {

    private PremiumService premiumService;

    @GetMapping
    public String getPremium (Model model) {
        model.addAttribute("premium",premiumService.getAllPremium());
        return "premium";
    }


}
