package ru.yaroglek.naujava.practice3.extern.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yaroglek.naujava.practice3.app.repository.UserRepository;
import ru.yaroglek.naujava.practice3.domain.User;

@Controller
@RequestMapping("/custom/users/view")
@RequiredArgsConstructor
public class UserControllerView
{
    private final UserRepository userRepository;

    @GetMapping("/list")
    public String userListView(Model model)
    {
        Iterable<User> products = userRepository.findAll();
        model.addAttribute("users", products);
        return "userList";
    }
}

