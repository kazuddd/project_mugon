package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Transaksi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.Nullable; // Import @Nullable annotation

import java.util.List;
import java.util.Optional;

public interface TransaksiRepository extends MongoRepository<Transaksi, String> {

    // Save method adjusted to handle potential null
    default Transaksi saveTransaksi(Transaksi transaksi) {
        if (transaksi != null) {
            return save(transaksi);
        }
        throw new IllegalArgumentException("Cannot save null Transaksi");
    }

    // Update method
    default Transaksi updateTransaksi(Transaksi transaksi) {
        if (transaksi != null && transaksi.getID() != null && existsById(transaksi.getID().toString())) {
            return save(transaksi); // Save performs update if the entity already exists
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Transaksi");
    }

    // Delete method adjusted to handle potential null
    default void deleteTransaksi(Transaksi transaksi) {
        if (transaksi != null) {
            delete(transaksi);
        } else {
            throw new IllegalArgumentException("Cannot delete null Transaksi");
        }
    }

    // Create method to insert a new Transaksi
    default Transaksi createTransaksi(Transaksi transaksi) {
        if (transaksi != null) {
            return save(transaksi); // Save method inserts a new Transaksi
        }
        throw new IllegalArgumentException("Cannot create null Transaksi");
    }

    List<Transaksi> findByUsernamePembeliOrUsernamePenjual(String email, String email1);

    // Other custom methods for specific operations can be added here as needed.
}
