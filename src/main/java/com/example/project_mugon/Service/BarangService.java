package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Repository.BarangRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarangService {
    private final BarangRepository barangRepository;
    public Barang findByID(ObjectId ID) {
        Optional<Barang> barangOptional = barangRepository.findBy_id(ID);
        return barangOptional.orElse(null);
    }

    public Barang findBy_idMarketPlace(ObjectId ID) {
        Optional<Barang> barangOptional = barangRepository.findBy_id(ID);
        if (barangOptional.isPresent()) {
            Barang barang = barangOptional.get();
        }
        return null;
    }
}
