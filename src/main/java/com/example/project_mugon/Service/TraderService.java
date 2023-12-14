package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Transaksi;
import com.example.project_mugon.Model.Trader;
import com.example.project_mugon.Repository.BarangRepository;
import com.example.project_mugon.Repository.TransaksiRepository;
import com.example.project_mugon.Repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    private final TraderRepository traderRepository;
    private final BarangRepository barangRepository;
    private final TransaksiRepository transaksiRepository;

    @Autowired
    public TraderService(TraderRepository traderRepository, BarangRepository barangRepository, TransaksiRepository transaksiRepository) {
        this.traderRepository = traderRepository;
        this.barangRepository = barangRepository;
        this.transaksiRepository = transaksiRepository;
    }

    // Register a new trader
    public Trader registerTrader(Trader trader) {
        // Check if the trader already exists
        Optional<Trader> existingTrader = traderRepository.findByEmail(trader.getEmail());
        if (existingTrader.isPresent()) {
            throw new IllegalArgumentException("Trader with this email already exists.");
        }
        return traderRepository.save(trader);
    }

    // Login a trader
    public Trader login(String email, String password) {
        Optional<Trader> traderOptional = traderRepository.findByEmail(email);
        if (traderOptional.isPresent()) {
            Trader trader = traderOptional.get();
            if (password.equals(trader.getPassword())) {
                return trader;
            }
        }
        return null;
    }

    // Sell an item
    public void jualBarang(Barang barangToSell, Trader seller) {
        // Implementation logic
    }

    // Check transactions
    public List<Transaksi> cekTransaksi(Trader trader) {
        return transaksiRepository.findByUsernamePembeli(trader.getEmail());
    }

    // Buy an item
    public void beliBarang(Barang barangToBuy, Trader buyer) {
        // Implementation logic
    }

    // Add funds to the trader's account
    public void tambahSaldo(Trader trader, double amount) {
        // Implementation logic
    }
}
