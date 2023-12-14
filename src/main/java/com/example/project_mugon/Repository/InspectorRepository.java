package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Inspector;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InspectorRepository extends MongoRepository<Inspector, String> {
    Optional<Inspector> findByUsername(String username);

    // Save method MongoDB
    default Inspector saveInspector(Inspector inspector) {
        if (inspector != null) {
            return save(inspector);
        }
        throw new IllegalArgumentException("Cannot save null Inspector");
    }

    // Update method MongoDB
    default Inspector updateInspector(Inspector inspector) {
        if (inspector != null && existsById(inspector.getId())) {
            return save(inspector);
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Inspector");
    }

    // Delete method MongoDB
    default void deleteInspector(Inspector inspector) {
        if (inspector != null) {
            delete(inspector);
        } else {
            throw new IllegalArgumentException("Cannot delete null Inspector");
        }
    }

    // Create method MongoDB
    default Inspector createInspector(Inspector inspector) {
        if (inspector != null) {
            return save(inspector);
        }
        throw new IllegalArgumentException("Cannot create null Inspector");
    }
}
