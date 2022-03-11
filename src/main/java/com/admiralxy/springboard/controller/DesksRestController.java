package com.admiralxy.springboard.controller;

import com.admiralxy.springboard.entity.Desk;
import com.admiralxy.springboard.service.interfaces.IDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/desks")
public class DesksRestController {

    private final IDeskService deskService;

    @Autowired
    public DesksRestController(IDeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping
    public List<Desk> index() {
        return deskService.getAll();
    }

    @PostMapping
    public void store(@RequestBody Desk desk) {
        deskService.save(desk);
    }
}
