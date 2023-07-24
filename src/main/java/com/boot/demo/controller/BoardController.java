package com.boot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @RequestMapping("/list")
    public String list(Model model){
        return "board/list";
    }
}
