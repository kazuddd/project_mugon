package com.example.project_mugon.Controller;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Service.BarangService;
import com.example.project_mugon.Service.TraderService;
import com.example.project_mugon.Model.Trader;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class TraderController {
    private final TraderService traderService;
    private final BarangService barangService;

    public TraderController(TraderService traderService, BarangService barangService) {
        this.traderService = traderService;
        this.barangService = barangService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session) {
        // Memanggil fungsi login dari TraderService dan assign kepada loggedInUser
        Trader loggedInUser = traderService.login(email, password);

        // Pengecekan apakah login berhasil
        if (loggedInUser != null) {
            // Set session menjadi atribut user yang login
            session.setAttribute("loggedInUser", loggedInUser);

            // Redirect ke menu utama jika berhasil
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

    @PostMapping("/Jual")
    public String jualBarang(@RequestParam("namabarang") String namabarang,
                             @RequestParam("hargabarang") double hargabarang,
                             @RequestParam("lokasibarang") String lokasibarang,
                             @RequestParam("tipebarang") String tipebarang,
                             @RequestParam("barubekas") String barubekas,
                             HttpSession session) {

        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");
        boolean isBaru = false;
        if (barubekas == "Baru") {
            isBaru = true;
        }
        ObjectId ID = traderService.jualBarang(namabarang, hargabarang, tipebarang, isBaru, lokasibarang, loggedInUser);
        Barang barang = barangService.findByID(ID);

        if (barang != null){
            return "redirect:/menu";
        } else {
            return "redirect:/jual";
        }

    }

    @PostMapping("/Add")
    public String addToKeranjang(@RequestParam("barangId") ObjectId ID,
                                 HttpSession session
                                 ) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");
        Barang barang = barangService.findByID(ID);

        if (barang != null) {
            traderService.addToKeranjang(ID, loggedInUser);
        } else {
            return "GAGAL COK";
        }

        List<ObjectId> keranjang = loggedInUser.getKeranjang();
        if (keranjang.contains(ID)) {
            return "BERHASIL";
        } else {
            return "GAGAL";
        }
    }

    @PostMapping("/UpdateSaldo")
    public String updateSaldo(@RequestParam("topupAmount") double amount,
                              HttpSession session
                              ) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");
        double cek = loggedInUser.getSaldo();

        traderService.tambahSaldo(loggedInUser, amount);

        if (loggedInUser.getSaldo() != cek) {
            return "Berhasil";
        } else {
            return "Gagal";
        }
    }
}
