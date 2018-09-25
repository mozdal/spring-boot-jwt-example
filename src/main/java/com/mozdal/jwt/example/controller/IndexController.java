package com.mozdal.jwt.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class IndexController {

    @RequestMapping(value = "/")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
