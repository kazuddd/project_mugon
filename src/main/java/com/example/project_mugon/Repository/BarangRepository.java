package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Barang;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarangRepository extends MongoRepository<Barang, String> {
    Optional<Barang> findByNamaBarang(String namaBarang);

    // method SAVE MongoDB
    default Barang saveBarang(Barang barang) {
        if (barang != null) {
            return save(barang);
        }
        throw new IllegalArgumentException("Cannot save null Barang");
    }

    // Update method MongoDB
    default Barang updateBarang(Barang barang) {
        if (barang != null && existsById(String.valueOf(barang.get_id()))) {
            return save(barang);
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Barang");
    }

    // Delete method MongoDB
    default void deleteBarang(Barang barang) {
        if (barang != null) {
            delete(barang);
        } else {
            throw new IllegalArgumentException("Cannot delete null Barang");
        }
    }

    // Create method MongoDB
    default Barang createBarang(Barang barang) {
        if (barang != null) {
            return save(barang);
        }
        throw new IllegalArgumentException("Cannot create null Barang");
    }
}
