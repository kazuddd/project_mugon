package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Barang;
import com.mongodb.lang.NonNullApi;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaitingListRepository extends MongoRepository<Barang, String> {

    Optional<Barang> findById(String barangId);

    default void delete(Barang selectedBarang) {
        deleteById(selectedBarang.getID());
    }
}
