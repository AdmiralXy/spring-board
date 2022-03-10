package com.admiralxy.springboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RouterController {
    @RequestMapping("/p-**")
    public ModelAndView redirect() {
        return new ModelAndView("forward:/");
    }
}