package hu.bankblaze.bankblaze.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PageController {
    @GetMapping("/home")
    public String goHome () {
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
