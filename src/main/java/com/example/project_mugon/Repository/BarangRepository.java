package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Barang;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarangRepository extends MongoRepository<Barang, String> {
    Optional<Barang> findByNamaBarang(String namaBarang);

    Optional<Barang> findBy_id(ObjectId _id);

    List<Barang> findAll();

    List<Barang> findByIsVerifiedFalse();

    List<Barang> findByIsVerifiedTrue();

    List<Barang> findAllBy_idIn(List<ObjectId> objectIds);

    void deleteBy_idIn(List<ObjectId> objectIds);

    void deleteBy_id(ObjectId _id);
}
