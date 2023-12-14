package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Barang;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketPlaceRepository extends MongoRepository<Barang, String> {
    // Add any specific methods needed for the MarketPlace collection
}
