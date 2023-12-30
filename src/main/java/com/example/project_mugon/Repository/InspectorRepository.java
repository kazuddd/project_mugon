package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Inspector;
import com.example.project_mugon.Model.Trader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InspectorRepository extends MongoRepository<Inspector, String> {

    Optional<Inspector> findByUsername(String username);
}
