package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.Employee;
import hu.bankblaze.bankblaze.model.Premium;
import hu.bankblaze.bankblaze.model.QueueNumber;
import hu.bankblaze.bankblaze.service.PremiumService;
import hu.bankblaze.bankblaze.service.QueueNumberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/premium")
public class PremiumController {

    private PremiumService premiumService;
    private QueueNumberService queueNumberService;

    @GetMapping
    public String getPremium (Model model) {
        model.addAttribute("header", "Prémium");
        model.addAttribute("newQueueNumber", new QueueNumber());
        return "queueNumber";
    }
    @PostMapping("/processPremium")
    public String processPremium(@ModelAttribute("Prémium") Premium premium, Model model, @ModelAttribute("newQueueNumber") QueueNumber newQueueNumber) {
        model.addAttribute("header", "Prémium");
        queueNumberService.generateQueueNumber(newQueueNumber);
        return "redirect:/queue/queueNumber";
    }


}
