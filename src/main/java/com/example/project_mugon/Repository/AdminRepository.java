package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByUsername(String username);

    // Save method adjusted to handle potential null
    default Admin saveAdmin(Admin admin) {
        if (admin != null) {
            return save(admin);
        }
        throw new IllegalArgumentException("Cannot save null Admin");
    }

    // Update method
    default Admin updateAdmin(Admin admin) {
        if (admin != null && findById(admin.getId()).isPresent()) {
            return save(admin); // Save performs update if the entity already exists
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Admin");
    }

    // Delete method adjusted to handle potential null
    default void deleteAdmin(Admin admin) {
        if (admin != null) {
            delete(admin);
        } else {
            throw new IllegalArgumentException("Cannot delete null Admin");
        }
    }

    // Create method to insert a new Admin
    default Admin createAdmin(Admin admin) {
        if (admin != null) {
            return save(admin); // Save method inserts a new Admin
        }
        throw new IllegalArgumentException("Cannot create null Admin");
    }

    // Other custom methods for specific operations can be added here as needed.
}
