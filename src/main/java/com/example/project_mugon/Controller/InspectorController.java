package com.example.project_mugon.Controller;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Model.Trader;
import com.example.project_mugon.Repository.BarangRepository;
import com.example.project_mugon.Repository.MarketPlaceRepository;
import com.example.project_mugon.Service.BarangService;
import com.example.project_mugon.Service.InspectorService;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/logininspektor")
public class InspectorController {
    private final InspectorService inspectorService;
    private final BarangService barangService;
    private final BarangRepository barangRepository;
    private final MarketPlaceRepository marketPlaceRepository;
    public InspectorController(InspectorService inspectorService, BarangService barangService, BarangRepository barangRepository, MarketPlaceRepository marketPlaceRepository) {
        this.inspectorService = inspectorService;
        this.barangService = barangService;
        this.barangRepository = barangRepository;
        this.marketPlaceRepository = marketPlaceRepository;
    }

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

    @PostMapping("RateBarang")
    public String rateBarang(@RequestParam("barangId") ObjectId ID,
                             @RequestParam("kondisi") double kondisi) {

        inspectorService.verifyBarang(ID, kondisi);
        Barang cekBarang = barangService.findBy_idMarketPlace(ID);
        if ((cekBarang != null) && (cekBarang.getKondisi() == kondisi)) {
            return "redirect:/menuinspektor";
        } else {
            return "redirect:/";
        }
    }
}
