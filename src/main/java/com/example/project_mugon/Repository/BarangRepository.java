package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Barang;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BarangRepository extends MongoRepository<Barang, String> {
    Optional<Barang> findByNamaBarang(String namaBarang);

    // method SAVE MongoDB
    default Barang saveBarang(Barang barang) {
        if (barang != null) {
            return save(barang);
        }
        throw new IllegalArgumentException("Cannot save null Barang");
    }

    // Update method UPDATE
    default Barang updateBarang(Barang barang) {
        if (barang != null && existsById(String.valueOf(barang.getID()))) {
            return save(barang); // Save performs update if the entity already exists
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Barang");
    }

    // Update method DELETE
    default void deleteBarang(Barang barang) {
        if (barang != null) {
            delete(barang);
        } else {
            throw new IllegalArgumentException("Cannot delete null Barang");
        }
    }

    // Create method to insert a new Barang
    default Barang createBarang(Barang barang) {
        if (barang != null) {
            return save(barang); // Save method inserts a new Barang
        }
        throw new IllegalArgumentException("Cannot create null Barang");
    }

    // Other custom methods for specific operations can be added here as needed.
}
