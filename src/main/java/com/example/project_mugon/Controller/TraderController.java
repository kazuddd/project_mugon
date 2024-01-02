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
import java.util.Objects;
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
            return "redirect:/notifLogin";
        } else {
            // Redirect jika login gagal
            return "redirect:/gagalLogin"; // MASIH BINGUNG
        }
    }

    @PostMapping("/Register")
    public String register(@RequestParam("nama") String nama,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           HttpSession session) {
        // Register trader pada collection Trader lalu login
        String cek = traderService.registerTrader(nama, email, password);

        // Pengecekan apakah login sukses
        if (cek.equals("berhasil")) {
            Trader loggedInUser = traderService.login(email, password);
            session.setAttribute("loggedInUser", loggedInUser);

            // Pengambilan data pada marketplace yang siap dibeli
            List<Barang> MarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);
            session.setAttribute("MarketPlaceItems", MarketPlaceItems);
            List<Barang> barangKeranjang = traderService.getALlInKeranjang(loggedInUser);
            session.setAttribute("barangKeranjang", barangKeranjang);

            // Redirect kedalam menu jika sukses
            return "redirect:/notifLogin";
        } else if (cek.equals("sudah_ada")) {
            return "redirect:/gagalRegister";
        } else {
            return "redirect:/gagalRegisterFormat";
        }
    }

    @PostMapping("/Jual")
    public String jualBarang(@RequestParam("namabarang") String namabarang,
                             @RequestParam("hargabarang") String hargabarang,
                             @RequestParam("lokasibarang") String lokasibarang,
                             @RequestParam("tipebarang") String tipebarang,
                             @RequestParam("condition") String condition,
                             HttpSession session) {

        // Check if the hargabarang input is a valid number
        double harga;
        try {
            harga = Double.parseDouble(hargabarang);
        } catch (NumberFormatException e) {
            // If hargabarang is not a valid number, redirect to "/"
            return "redirect:/gagalNgiklan";
        }

        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");
        ObjectId ID;
        if (Objects.equals(condition, "baru")) {
            ID = traderService.jualBarang(namabarang, harga, tipebarang, true, lokasibarang, loggedInUser);
        } else {
            ID = traderService.jualBarang(namabarang, harga, tipebarang, false, lokasibarang, loggedInUser);
        }

        Barang barang = barangService.findByID(ID);

        if (barang != null){
            return "redirect:/notifJual";
        } else {
            return "redirect:/gagalNgiklan";
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

        return "redirect:/notifAddKeranjang";
    }

    @PostMapping("/UpdateSaldo")
    public String updateSaldo(@RequestParam("topupAmount") double amount,
                              HttpSession session
                              ) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");

        traderService.tambahSaldo(loggedInUser, amount);

        Trader updatedTrader = traderService.login(loggedInUser.getEmail(), loggedInUser.getPassword());

        session.setAttribute("loggedInUser", updatedTrader);

        return "redirect:/notifTopup";
    }

    @PostMapping("/Checkout")
    public String checkoutKeranjang(HttpSession session){
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");

        String cek = traderService.checkoutKeranjang(loggedInUser);

        List<Barang> MarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);
        session.setAttribute("MarketPlaceItems", MarketPlaceItems);

        return cek;
    }
    
    @GetMapping("/Search")
    public String searchBarang(@RequestParam("search") String search,
                               HttpSession session) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");
        List<Barang> OldMarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);


        if (!Objects.equals(search, "")) {
            List<Barang> MarketPlaceItems = traderService.searchBarang(OldMarketPlaceItems, search);
            session.setAttribute("MarketPlaceItems", MarketPlaceItems);
        } else {
            List<Barang> MarketPlaceItems = traderService.getAllItemsInMarketPlace(loggedInUser);
            session.setAttribute("MarketPlaceItems", MarketPlaceItems);
        }
        return "redirect:/menu";
    }

    public String deleteFromKeranjang(@RequestParam("ID") String id,
                                      HttpSession session) {
        Trader loggedInUser = (Trader) session.getAttribute("loggedInUser");

        traderService.deleteFromKeranjang(id, loggedInUser);

        return "redirect:/notifDeleteKeranjang";
    }
}
