package com.serhii.myproject.controller;

import com.serhii.myproject.component.HeaderComponent;
import com.serhii.myproject.dto.UserDto;
import com.serhii.myproject.dto.UserTransformer;
import com.serhii.myproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ManagersHomeController {
    private final UserService userService;
    private final HeaderComponent headerComponent;

    public ManagersHomeController(UserService userService, HeaderComponent headerComponent) {
        this.userService = userService;
        this.headerComponent = headerComponent;
    }

    @GetMapping({"/managers-home"})
    public String home(Model model, Principal principal) {
        headerComponent.addUserToModel(model, principal);


        List<UserDto> userDtos = userService.getAllUsers().stream()
                .map(UserTransformer::convertToDto)
                .collect(Collectors.toList());

        model.addAttribute("users", userDtos);

        return "managers-home";
    }
}
