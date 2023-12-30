package com.example.project_mugon.Controller;

import com.example.project_mugon.Model.Admin;
import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){this.adminService = adminService;}

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session){
        // Memanggil fungsi login dari AdminService dan assign kepada loggedInAdmin
        Admin loggedInAdmin = adminService.login(username, password);

        // Pengecekan apakah login admin berhasil
        if (loggedInAdmin != null) {
            // Set session menjadi atribut inspector yang login
            session.setAttribute("loggedInAdmin", loggedInAdmin);

            // Ambil data marketplace
            List<Barang> MarketPlaceItems = adminService.getAllItemsInMarketPlace();
            session.setAttribute("MarketPlaceItems", MarketPlaceItems);

            // Ambil data unverified
            List<Barang> UnverifiedItems = adminService.getAllItemsInWaitingList();
            session.setAttribute("UnverifiedItems", UnverifiedItems);

            // Redirect ke menu utama jika berhasil
            return "redirect:/menuadm";
        } else {
            // Redirect jika login gagal
            return "redirect:/"; // MASIH BINGUNG
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("barangId") String id,
                       HttpSession session){
        ObjectId ID = new ObjectId(id);

        adminService.deleteFromBarangCollection(ID);

        // Ambil data marketplace
        List<Barang> MarketPlaceItems = adminService.getAllItemsInMarketPlace();
        session.setAttribute("MarketPlaceItems", MarketPlaceItems);

        return "redirect:/menuadm";
    }

    @PostMapping("toEditPage")
    public String toEdit(@RequestParam("barangId") String id, HttpSession session) {
        session.setAttribute("id", id);

        return "redirect:/adminEdit";
    }

    @PostMapping("/AdminEdit")
    public String edit(@RequestParam("namabarang") String namaBarang,
                       @RequestParam("lokasibarang") String lokasi,
                       @RequestParam("tipebarang") String tipe,
                       HttpSession session) {
        String id = (String) session.getAttribute("id");

        adminService.editBarangInMarketPlace(id, namaBarang, tipe, lokasi);

        List<Barang> MarketPlaceItems = adminService.getAllItemsInMarketPlace();
        session.setAttribute("MarketPlaceItems", MarketPlaceItems);

        return "redirect:/menuadm";
    }
}
