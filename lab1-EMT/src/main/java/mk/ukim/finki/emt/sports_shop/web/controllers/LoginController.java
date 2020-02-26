package mk.ukim.finki.emt.sports_shop.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login-page")
public class LoginController {

    @GetMapping
    public String login() {
        return "login";
    }
}
