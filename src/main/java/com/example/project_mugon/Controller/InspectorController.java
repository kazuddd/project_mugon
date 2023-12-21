package com.example.project_mugon.Controller;

import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Model.Trader;
import com.example.project_mugon.Service.InspectorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/logininspektor")
public class InspectorController {
    private final InspectorService inspectorService;
    public InspectorController(InspectorService inspectorService) {this.inspectorService = inspectorService;}

    @PostMapping("/loginInspector")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        // Memanggil fungsi login dari InspectorService dan assign kepada loggedInUser
        Inspector loggedInInspector = inspectorService.login(username, password);

        // Pengecekan apakah login inspector berhasil
        if (loggedInInspector != null) {
            // Set session menjadi atribut inspector yang login
            session.setAttribute("loggedInInspector", loggedInInspector);

            // Redirect ke menu utama jika berhasil
            return "redirect:/menuinspektor";
        } else {
            // Redirect jika login gagal
            return "redirect:/"; // MASIH BINGUNG
        }
    }
}
