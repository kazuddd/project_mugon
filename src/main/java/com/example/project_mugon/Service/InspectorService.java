package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Repository.BarangRepository;
import com.example.project_mugon.Repository.InspectorRepository;
import com.example.project_mugon.Repository.MarketPlaceRepository;
import com.example.project_mugon.Repository.WaitingListRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectorService {

    private final BarangRepository barangRepository;
    private final MarketPlaceRepository marketPlaceRepository;
    private final InspectorRepository inspectorRepository;

    @Autowired
    public InspectorService(WaitingListRepository waitingListRepository, BarangRepository barangRepository, MarketPlaceRepository marketPlaceRepository, InspectorRepository inspectorRepository) {
        this.barangRepository = barangRepository;
        this.marketPlaceRepository = marketPlaceRepository;
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

            // Pindahkan barang ke MarketPlace
            marketPlaceRepository.save(selectedBarang);

            // Delete dari WaitingList
            barangRepository.delete(selectedBarang);
        } else {
            throw new IllegalArgumentException("Barang tidak ditemukan dalam WaitingList.");
        }
    }
}
