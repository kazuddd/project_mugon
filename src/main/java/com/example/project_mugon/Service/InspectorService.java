package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Repository.InspectorRepository;
import com.example.project_mugon.Repository.MarketPlaceRepository;
import com.example.project_mugon.Repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectorService {

    private final WaitingListRepository waitingListRepository;
    private final MarketPlaceRepository marketPlaceRepository;
    private final InspectorRepository inspectorRepository;

    @Autowired
    public InspectorService(WaitingListRepository waitingListRepository, MarketPlaceRepository marketPlaceRepository, InspectorRepository inspectorRepository) {
        this.waitingListRepository = waitingListRepository;
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

    public void verifyBarang(String barangId) {
        Optional<Barang> barangOptional = waitingListRepository.findById(barangId);

        if (barangOptional.isPresent()) {
            Barang selectedBarang = barangOptional.get();

            // Pindahkan barang ke MarketPlace
            marketPlaceRepository.save(selectedBarang);

            // Delete dari WaitingList
            waitingListRepository.delete(selectedBarang);
        } else {
            throw new IllegalArgumentException("Barang tidak ditemukan dalam WaitingList.");
        }
    }
}
