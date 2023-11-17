package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.QueueNumber;
import hu.bankblaze.bankblaze.service.QueueNumberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/home")
public class PageController {

    private QueueNumberService queueNumberService;

    @GetMapping
    public String goHome (Model model) {
        model.addAttribute("newQueueNumber", new QueueNumber());
        return "home";
    }

    @PostMapping
    public String goHome(@ModelAttribute("newQueueNumber") QueueNumber queueNumber,
                         @RequestParam("whereTo") int id) {
        queueNumberService.addQueueNumber(queueNumber);
        switch (id) {
            case 1 -> {
                return "redirect:/retail";
            }
            case 2 -> {
                return "redirect:/corporate";
            }
            case 3 -> {
                return "redirect:/teller";
            }
            case 4 -> {
                queueNumberService.addQueueNumber(queueNumber);
                return "redirect:/premium";
            }
        }
        return "home";
    }

    @GetMapping("/login")
    public String showLogin () {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin (@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/admin";
        } else {
            return "redirect:/login?error=true";
        }
    }


}
