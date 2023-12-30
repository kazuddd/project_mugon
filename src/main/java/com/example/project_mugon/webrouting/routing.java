package com.example.project_mugon.webrouting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class routing {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/loginadmin")
    public String loginadmin() {
        return "loginadmin";
    }

    @GetMapping("/logininspektor")
    public String logininspektor() {
        return "logininspektor";
    }

    @GetMapping("/daftar")
    public String daftar() {
        return "daftar";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/menuadm")
    public String menuadm() {
        return "menuadm";
    }

    @GetMapping("/menuadmunv")
    public String menuadmunv() {
        return "menuadmunv";
    }

    @GetMapping("/menuins")
    public String menuins() {
        return "menuins";
    }


    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/profileadm")
    public String profileadm() {
        return "profileadm";
    }

    @GetMapping("/profileins")
    public String profileins() {
        return "profileins";
    }

    @GetMapping("/topup")
    public String topup() {
        return "topup";
    }

    @GetMapping("/keranjang")
    public String keranjang() {
        return "keranjang";
    }

    @GetMapping("/jual")
    public String jual() {
        return "jual";
    }

    @GetMapping("/histori")
    public String histori() {
        return "histori";
    }

    @GetMapping("/notifLogin")
    public String notifLogin() {
        return "notifLogin";
    }

    @GetMapping("/notifLoginins")
    public String  notifLoginins() {
        return "notifLoginins";
    }

    @GetMapping("/notifLoginadm")
    public String  notifLoginadm() {
        return "notifLoginadm";
    }

    @GetMapping("/notifTrans")
    public String  notifTrans() {
        return "notifTrans";
    }

    @GetMapping("/notifJual")
    public String  notifJual() {
        return "notifJual";
    }

    @GetMapping("/notifTopup")
    public String  notifTopup() {
        return "notifTopup";
    }

    @GetMapping("/gagalLogin")
    public String  gagalLogin() {
        return "gagalLogin";
    }

    @GetMapping("/gagalCekot")
    public String  gagalCekot() {
        return "gagalCekot";
    }

    @GetMapping("/adminEdit")
    public String  adminEdit() {
        return "adminEdit";
    }


}
