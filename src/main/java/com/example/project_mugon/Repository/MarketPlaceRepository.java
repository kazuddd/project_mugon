package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Barang;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketPlaceRepository extends MongoRepository<Barang, String> {
    void deleteBy_idIn(List<ObjectId> objectIds);

    List<Barang> findAllBy_idIn(List<ObjectId> objectIds);

    Optional<Barang> findBy_id(ObjectId id);
}
