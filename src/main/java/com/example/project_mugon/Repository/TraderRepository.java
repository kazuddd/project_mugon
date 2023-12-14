package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Trader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraderRepository extends MongoRepository<Trader, String> {
    Optional<Trader> findByEmail(String email);

    // Save method MongoDB
    default Trader saveTrader(Trader trader) {
        if (trader != null) {
            return save(trader);
        }
        throw new IllegalArgumentException("Cannot save null Trader");
    }

    // Update method MongoDB
    default Trader updateTrader(Trader trader) {
        if (trader != null && existsById(trader.getId())) {
            return save(trader);
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Trader");
    }

    // Delete method MongoDB
    default void deleteTrader(Trader trader) {
        if (trader != null) {
            delete(trader);
        } else {
            throw new IllegalArgumentException("Cannot delete null Trader");
        }
    }

    // Create method MongoDB
    default Trader createTrader(Trader trader) {
        if (trader != null) {
            return save(trader);
        }
        throw new IllegalArgumentException("Cannot create null Trader");
    }
}
