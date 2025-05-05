package ru.yaroglek.naujava.practice3.extern.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yaroglek.naujava.practice3.app.service.UserService;
import ru.yaroglek.naujava.practice3.domain.User;

@Controller
@RequiredArgsConstructor
public class RegistrationController
{
    private final UserService userService;

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String adduser(User user, Model model)
    {
        try
        {
            userService.saveUser(user);
            return "redirect:/login";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}

