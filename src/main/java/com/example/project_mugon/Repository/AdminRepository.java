package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    Optional<Admin> findByUsername(String username);

    default void deleteBarangInWaitingList(String barangId) {
        if (barangId != null) {
            deleteById(barangId);
        } else {
            throw new IllegalArgumentException("Barang ID cannot be null.");
        }
    }

    default void deleteBarangInMarketPlace(String barangId) {
        if (barangId != null) {
            deleteById(barangId);
        } else {
            throw new IllegalArgumentException("Barang ID cannot be null.");
        }
    }
}
