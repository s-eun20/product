package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "이미 존재하는 이메일입니다.");
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        if (error != null) model.addAttribute("error", "로그인 실패");
        return "login";
    }
}
