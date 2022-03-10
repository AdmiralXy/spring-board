package com.admiralxy.springboard.controller;

import com.admiralxy.springboard.entity.Desk;
import com.admiralxy.springboard.entity.User;
import com.admiralxy.springboard.security.jwt.JwtUser;
import com.admiralxy.springboard.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/desks")
public class DesksRestController {

    private final IUserService userService;

    @Autowired
    public DesksRestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Desk> index() {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(jwtUser.getUsername());
        return user.getDesks();
    }
}
