package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Repository.WaitingListRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    @NonNull
    Optional<Admin> findById(@NonNull String id);

    default Admin saveAdmin(Admin admin) {
        if (admin != null) {
            return save(admin);
        }
        throw new IllegalArgumentException("Cannot save null Admin");
    }

    default Admin updateAdmin(Admin admin) {
        if (admin != null) {
            ObjectId adminId = admin.get_id(); // Assuming get_id() returns ObjectId

            if (adminId != null && existsById(String.valueOf(adminId))) {
                return save(admin);
            }
            throw new IllegalArgumentException("Cannot update non-existing or null Admin");
        }
        throw new IllegalArgumentException("Cannot update null Admin");
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
