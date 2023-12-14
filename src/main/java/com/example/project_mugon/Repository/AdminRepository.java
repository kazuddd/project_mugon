package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByUsername(String username);

    // method SAVE MongoDB
    default Admin saveAdmin(Admin admin) {
        if (admin != null) {
            return save(admin);
        }
        throw new IllegalArgumentException("Cannot save null Admin");
    }

    // Update method MongoDB
    default Admin updateAdmin(Admin admin) {
        if (admin != null && findById(admin.getId()).isPresent()) {
            return save(admin); //
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Admin");
    }

    // Delete method MongoDB
    default void deleteAdmin(Admin admin) {
        if (admin != null) {
            delete(admin);
        } else {
            throw new IllegalArgumentException("Cannot delete null Admin");
        }
    }

    // Create method MongoDB
    default Admin createAdmin(Admin admin) {
        if (admin != null) {
            return save(admin);
        }
        throw new IllegalArgumentException("Cannot create null Admin");
    }
}
