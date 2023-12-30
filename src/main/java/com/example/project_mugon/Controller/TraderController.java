package com.example.project_mugon.Controller;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Service.BarangService;
import com.example.project_mugon.Service.TraderService;
import com.example.project_mugon.Model.Trader;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

            List<Barang> MarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);

            List<Barang> barangKeranjang = traderService.getALlInKeranjang(loggedInUser);


            session.setAttribute("MarketPlaceItems", MarketPlaceItems);

            session.setAttribute("barangKeranjang", barangKeranjang);

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
            // Register trader pada collection Trader lalu login
            Trader newTrader = traderService.registerTrader(nama, email, password);
            Trader loggedInUser = traderService.login(email, password);
            session.setAttribute("loggedInUser", newTrader);

            // Pengambilan data pada marketplace yang siap dibeli
            List<Barang> MarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);
            session.setAttribute("MarketPlaceItems", MarketPlaceItems);
            List<Barang> barangKeranjang = traderService.getALlInKeranjang(loggedInUser);
            session.setAttribute("barangKeranjang", barangKeranjang);

            // Pengecekan apakah login sukses
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
                             @RequestParam("baru/bekas") String barubekas,
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
    public String addToKeranjang(@RequestParam("ItemId") String itemId,
                                 HttpSession session) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");

        ObjectId id = new ObjectId(itemId);
        Barang barang = barangService.findBy_idMarketPlace(id);

        traderService.addToKeranjang(loggedInUser, itemId);

        Trader updatedTrader = traderService.login(loggedInUser.getEmail(), loggedInUser.getPassword());

        session.setAttribute("loggedInUser", updatedTrader);

        List<Barang> barangKeranjang = traderService.getALlInKeranjang(loggedInUser);
        session.setAttribute("barangKeranjang", barangKeranjang);

        return "redirect:/menu";
    }

    @PostMapping("/UpdateSaldo")
    public String updateSaldo(@RequestParam("topupAmount") double amount,
                              HttpSession session
                              ) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");

        traderService.tambahSaldo(loggedInUser, amount);

        Trader updatedTrader = traderService.login(loggedInUser.getEmail(), loggedInUser.getPassword());

        session.setAttribute("loggedInUser", updatedTrader);

        return "redirect:/menu";
    }

    @PostMapping("/Checkout")
    public String checkoutKeranjang(HttpSession session){
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");

        traderService.checkoutKeranjang(loggedInUser);

        List<Barang> MarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);
        session.setAttribute("MarketPlaceItems", MarketPlaceItems);

        return "redirect:/menu";
    }
    
    @GetMapping("/Search")
    public String searchBarang(@RequestParam("search") String search,
                               HttpSession session) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");
        List<Barang> OldMarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);

        List<Barang> MarketPlaceItems = traderService.searchBarang(OldMarketPlaceItems, search);

        session.setAttribute("MarketPlaceItems", MarketPlaceItems);

        return "redirect:/menu";
    }
}
