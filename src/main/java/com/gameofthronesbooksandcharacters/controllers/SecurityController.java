package com.gameofthronesbooksandcharacters.controllers;

import com.gameofthronesbooksandcharacters.databaseutils.CustomUserDao;
import com.gameofthronesbooksandcharacters.datamodel.CustomUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "security/login";
    }
    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("customUser", new CustomUser());
        return "security/register";
    }
    @PostMapping("/register")
    public String registerUser(CustomUser user) {
        CustomUserDao dao = new CustomUserDao();
        dao.saveUser(user);
        return "books";
    }
}
