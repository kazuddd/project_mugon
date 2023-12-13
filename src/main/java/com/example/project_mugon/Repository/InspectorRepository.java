package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Inspector;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InspectorRepository extends MongoRepository<Inspector, String> {
    Optional<Inspector> findByUsername(String username);

    // Save method adjusted to handle potential null
    default Inspector saveInspector(Inspector inspector) {
        if (inspector != null) {
            return save(inspector);
        }
        throw new IllegalArgumentException("Cannot save null Inspector");
    }

    // Update method
    default Inspector updateInspector(Inspector inspector) {
        if (inspector != null && existsById(inspector.getId())) {
            return save(inspector); // Save performs update if the entity already exists
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Inspector");
    }

    // Delete method adjusted to handle potential null
    default void deleteInspector(Inspector inspector) {
        if (inspector != null) {
            delete(inspector);
        } else {
            throw new IllegalArgumentException("Cannot delete null Inspector");
        }
    }

    // Create method to insert a new Inspector
    default Inspector createInspector(Inspector inspector) {
        if (inspector != null) {
            return save(inspector); // Save method inserts a new Inspector
        }
        throw new IllegalArgumentException("Cannot create null Inspector");
    }

    // Other custom methods for specific operations can be added here as needed.
}
