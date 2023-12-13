package com.example.project_mugon.Repository;

import com.example.project_mugon.Model.Trader;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TraderRepository extends MongoRepository<Trader, String> {
    Optional<Trader> findByEmail(String email);

    // Save method adjusted to handle potential null
    default Trader saveTrader(Trader trader) {
        if (trader != null) {
            return save(trader);
        }
        throw new IllegalArgumentException("Cannot save null Trader");
    }

    // Update method
    default Trader updateTrader(Trader trader) {
        if (trader != null && existsById(trader.getId())) {
            return save(trader); // Save performs update if the entity already exists
        }
        throw new IllegalArgumentException("Cannot update non-existing or null Trader");
    }

    // Delete method adjusted to handle potential null
    default void deleteTrader(Trader trader) {
        if (trader != null) {
            delete(trader);
        } else {
            throw new IllegalArgumentException("Cannot delete null Trader");
        }
    }

    // Create method to insert a new Trader
    default Trader createTrader(Trader trader) {
        if (trader != null) {
            return save(trader); // Save method inserts a new Trader
        }
        throw new IllegalArgumentException("Cannot create null Trader");
    }

    // Other custom methods for specific operations can be added here as needed.
}
