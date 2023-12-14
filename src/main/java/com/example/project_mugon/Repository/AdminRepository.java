package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Repository.WaitingListRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByUsername(String username);

    default Admin saveAdmin(Admin admin) {
        if (admin != null) {
            return save(admin);
        }
        throw new IllegalArgumentException("Cannot save null Admin");
    }

    default Admin updateAdmin(Admin admin) {
        if (admin != null && existsById(admin.getId())) {
            return save(admin);
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Admin");
    }

    default void deleteAdmin(Admin admin) {
        if (admin != null) {
            delete(admin);
        } else {
            throw new IllegalArgumentException("Cannot delete null Admin");
        }
    }

    default Admin createAdmin(Admin admin) {
        if (admin != null) {
            return save(admin);
        }
        throw new IllegalArgumentException("Cannot create null Admin");
    }

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
