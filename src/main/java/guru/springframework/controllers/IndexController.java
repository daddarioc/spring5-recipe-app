package com.dadarioc.spring.recipe.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class IndexController {

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {
        return "index";
    }
}
