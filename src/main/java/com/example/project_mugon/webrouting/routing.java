package com.example.project_mugon.webrouting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class routing {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/loginadmin")
    public String  loginadmin(){
        return "loginadmin";
    }
    @GetMapping("/logininspektor")
    public String  logininspektor(){
        return "logininspektor";
    }

    @GetMapping("/daftar")
    public String  daftar(){
        return "daftar";
    }

    @GetMapping("/menu")
    public String  menu(){
        return "menu";
    }

    @GetMapping("/menuadm")
    public String  menuadm(){
        return "menuadm";
    }

    @GetMapping("/menuins")
    public String  menuins() {
        return "menuins";
    }
    
}
