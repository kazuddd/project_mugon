package com.example.project_mugon.Controller;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Service.BarangService;
import com.example.project_mugon.Service.InspectorService;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/logininspektor")
public class InspectorController {
    private final InspectorService inspectorService;
    private final BarangService barangService;
    public InspectorController(InspectorService inspectorService, BarangService barangService) {
        this.inspectorService = inspectorService;
        this.barangService = barangService;
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

            List<Barang> waitingListItems = inspectorService.getAllItemsInWaitingList();
            session.setAttribute("items", waitingListItems);

            // Redirect ke menu utama jika berhasil
            return "redirect:/notifLoginins";
        } else {
            // Redirect jika login gagal
            return "redirect:/gagalLogin"; // MASIH BINGUNG
        }
    }

    @PostMapping("submitRating")
    public String rateBarang(@RequestParam("itemId") String itemId,
                             @RequestParam("rating") double rating,
                             HttpSession session) {

        ObjectId id = new ObjectId(itemId);

        Inspector loggedInInspector = (Inspector) session.getAttribute("loggedInInspector");

        inspectorService.verifyBarang(loggedInInspector, id, rating);
        Barang cekBarang = barangService.findBy_idMarketPlace(id);

        session.setAttribute("loggedInInspector", loggedInInspector);

        if ((cekBarang != null) && (cekBarang.getKondisi() == rating)) {
            return "redirect:/notifInspek";
        } else {
            return "redirect:/";
        }
    }
}
