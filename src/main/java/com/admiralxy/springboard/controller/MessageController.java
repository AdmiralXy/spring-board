package com.admiralxy.springboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class MessageController {
    private final List<String> messages = new ArrayList<>() {{
        add("Hello #1");
        add("Hello #2");
        add("Hello #3");
        add("Hello #4");
    }};

    @RequestMapping("message")
    public List<String> list() {
        return messages;
    }
}
