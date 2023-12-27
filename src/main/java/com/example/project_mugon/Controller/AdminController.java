package com.example.project_mugon.Controller;

import com.example.project_mugon.Model.Admin;
import com.example.project_mugon.Service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/loginadmin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){this.adminService = adminService;}

    //@PostMapping("/loginAdmin")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session){
        // Memanggil fungsi login dari AdminService dan assign kepada loggedInAdmin
        Admin loggedInAdmin = adminService.login(username, password);

        // Pengecekan apakah login admin berhasil
        if (loggedInAdmin != null) {
            // Set session menjadi atribut inspector yang login
            session.setAttribute("loggedInAdmin", loggedInAdmin);

            // Redirect ke menu utama jika berhasil
            return "redirect:/menuadmin";
        } else {
            // Redirect jika login gagal
            return "redirect:/"; // MASIH BINGUNG
        }
    }
}
