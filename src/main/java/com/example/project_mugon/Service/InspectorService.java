package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Repository.MarketPlaceRepository;
import com.example.project_mugon.Repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectorService {

    private final WaitingListRepository waitingListRepository; // Adjusted repository name
    private final MarketPlaceRepository marketPlaceRepository;

    @Autowired
    public InspectorService(WaitingListRepository waitingListRepository, MarketPlaceRepository marketPlaceRepository) {
        this.waitingListRepository = waitingListRepository;
        this.marketPlaceRepository = marketPlaceRepository;
    }

    public void verifyBarang(String barangId) {
        Optional<Barang> barangOptional = waitingListRepository.findById(barangId);

        if (barangOptional.isPresent()) {
            Barang selectedBarang = barangOptional.get();

            // Move the selected Barang to MarketPlace
            marketPlaceRepository.save(selectedBarang);

            // Delete from WaitingList
            waitingListRepository.delete(selectedBarang);
        } else {
            throw new IllegalArgumentException("Barang not found in WaitingList.");
        }
    }
}
