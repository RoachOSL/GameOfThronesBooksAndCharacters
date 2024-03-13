package com.gameofthronesbooksandcharacters.controllers;

import com.gameofthronesbooksandcharacters.databaseutils.CustomUserDao;
import com.gameofthronesbooksandcharacters.datamodel.CustomUser;
import com.gameofthronesbooksandcharacters.exceptions.EmailExistsException;
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
    public String registerUser(CustomUser user, Model model) {
        CustomUserDao dao = new CustomUserDao();
        try {
            dao.saveUser(user);
        } catch (EmailExistsException e) {
            model.addAttribute("emailExists", "An account with this email already exists.");
            model.addAttribute("customUser", user);
            return "security/register";
        }
        return "security/login";
    }
}
