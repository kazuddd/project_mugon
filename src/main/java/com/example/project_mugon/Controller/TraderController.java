package com.example.project_mugon.Controller;

import com.example.project_mugon.Service.TraderService;
import com.example.project_mugon.Model.Trader;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("")
public class TraderController {
    private final TraderService traderService;

    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @PostMapping("/login") // Make sure the correct mapping is specified ("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session) {
        // Memanggil fungsi login dari TraderService dan assign kepada loggedInUser
        Trader loggedInUser = traderService.login(email, password);

        // Pengecekan apakah login berhasil
        if (loggedInUser != null) {
            // Set the user object in the session for further use
            session.setAttribute("loggedInUser", loggedInUser);

            // Redirect to a success page if login is successful
            return "redirect:/menu";
        } else {
            // Redirect jika login gagal
            return "redirect:/daftar"; // MASIH BINGUNG
        }
    }

    @PostMapping("/Register")
    public String register(@RequestParam("nama") String nama,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           HttpSession session) {
        try {
            // Call the registerTrader method from TraderService
            Trader newTrader = traderService.registerTrader(nama, email, password);

            Trader loggedInUser = traderService.login(email, password);
            // Optionally, you can set the logged-in user in the session
            session.setAttribute("loggedInUser", newTrader);
            if (loggedInUser != null) {
                // Redirect kedalam menu jika sukses
                return "redirect:/menu";
            } else {
                return "redirect:/index";
            }
            // Redirect to a success page or URL after successful registration

        } catch (IllegalArgumentException e) {
            // Redirect jika register gagal
            return "redirect:/register?error=EmailAlreadyUsed"; // MASIH BINGUNG
        }
    }
}
