package es.ubu.sd.p2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Maincontroller {
    @GetMapping("/")
    public String vistaHome( ModelMap interfazConPantalla){
        return "index";
    }
}
