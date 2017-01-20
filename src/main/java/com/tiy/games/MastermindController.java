package com.tiy.games;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MastermindController {

    @RequestMapping(path = "/mastermind", method = RequestMethod.GET)
    public String todos(Model model, HttpSession session) {
        return "mastermind";
    }
}