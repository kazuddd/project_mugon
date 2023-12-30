package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Repository.BarangRepository;
import com.example.project_mugon.Repository.InspectorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InspectorService {

    private final BarangRepository barangRepository;
    private final InspectorRepository inspectorRepository;

    @Autowired
    public InspectorService(BarangRepository barangRepository, InspectorRepository inspectorRepository) {
        this.barangRepository = barangRepository;
        this.inspectorRepository = inspectorRepository;
    }

    public Inspector login(String username, String password) {
        Optional<Inspector> inspectorOptional = inspectorRepository.findByUsername(username);
        if (inspectorOptional.isPresent()) {
            Inspector inspector = inspectorOptional.get();
            if (password.equals(inspector.getPassword())) {
                return inspector;
            }
        }
        return null;
    }

    public void verifyBarang(ObjectId barangId, double ratingKondisi) {
        Optional<Barang> barangOptional = barangRepository.findBy_id(barangId);

        if (barangOptional.isPresent()) {
            // Mengambil barang dan set rating dari inspector
            Barang selectedBarang = barangOptional.get();
            selectedBarang.setKondisi(ratingKondisi);

            selectedBarang.setVerified(true);

            // Pindahkan barang ke MarketPlace
            barangRepository.save(selectedBarang);
        } else {
            throw new IllegalArgumentException("Barang tidak ditemukan dalam WaitingList.");
        }
    }

    public List<Barang> searchBarang(List<Barang> MarketPlace, String search) {
        List<Barang> foundItems = new ArrayList<>();

        for (Barang barang : MarketPlace) {
            // Menggunakan equalsIgnoreCase untuk membandingkan nama barang tanpa memperhatikan case
            if (barang.getNamaBarang().equalsIgnoreCase(search)) {
                foundItems.add(barang);
            }
        }

        return foundItems;
    }

    public List<Barang> getAllItemInWaitingList(){
        return barangRepository.findAll();
    }

    public List<Barang> getAllItemsInWaitingList() {
        return barangRepository.findByIsVerifiedFalse();
    }
}
