package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Barang;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaitingListRepository {

    Optional<Barang> findById(String barangId);

    void delete(Barang selectedBarang);
}
