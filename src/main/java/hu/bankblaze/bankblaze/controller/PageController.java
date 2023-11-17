package hu.bankblaze.bankblaze.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class PageController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private AdminService adminService;


    @GetMapping("/home")
    public String goHome() {
        return "home";
    }


    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public RedirectView checkLogin(HttpServletRequest request, @RequestParam("username") String userName,
                                   @RequestParam String password, RedirectAttributes redirectAttributes) {
        if (adminService.checkLogin(userName, password)) {
            if (SecurityContextHolder.getContext().getAuthentication() == null ||
                    SecurityContextHolder.getContext()
                            .getAuthentication().getClass().equals(AnonymousAuthenticationToken.class)) {
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(userName, password,new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(token);
            }
            redirectAttributes.addFlashAttribute("message", "Login Successful");
            return new RedirectView("admin");

        }
        redirectAttributes.addFlashAttribute("message", "Invalid Username or Password");
        return new RedirectView("login");
    }
}
