package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Transaksi;
import com.example.project_mugon.Model.Trader;
import com.example.project_mugon.Repository.BarangRepository;
import com.example.project_mugon.Repository.TransaksiRepository;
import com.example.project_mugon.Repository.TraderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // Register trader
    public Trader registerTrader(String nama, String email, String password) {
        // Check if the trader with the given email already exists
        Optional<Trader> existingTrader = traderRepository.findByEmail(email);
        if (existingTrader.isPresent()) {
            throw new IllegalArgumentException("Email sudah digunakan.");
        }

        // Create a new Trader object with the provided details
        Trader newTrader = new Trader();
        newTrader.setNama(nama);
        newTrader.setEmail(email);
        newTrader.setPassword(password);
        newTrader.setKeranjang(new ArrayList<ObjectId>());
        newTrader.setListTransaksi(new ArrayList<Transaksi>());

        // Save the new Trader
        return traderRepository.save(newTrader);
    }

    // Login trader
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
    public void jualBarang(String namaBarang, double harga, String tipeBarang, boolean isBaru, double kondisi, String lokasi, Trader seller) {
        Barang newBarang = new Barang();
        newBarang.setNamaBarang(namaBarang);
        newBarang.setHarga(harga);
        newBarang.setTipeBarang(tipeBarang);
        newBarang.setBaru(isBaru);
        newBarang.setKondisi(0);
        newBarang.setKondisi(kondisi);
        newBarang.setLokasi(lokasi);
        newBarang.setIdPenjual(seller.get_id());

        // Input barang pada repository WaitingList
        barangRepository.save(newBarang);
    }
    public List<Transaksi> cekTransaksi(Trader trader) {
        return transaksiRepository.findByUsernamePembeli(trader.getEmail());
    }
    public void addToKeranjang(Barang barangToBuy, Trader buyer) {
    }

    public void beliBarang(Trader pembeli) {
    }

    public void tambahSaldo(Trader currentTrader, double amount) {
        Trader traderToUpdate = traderRepository.findById(currentTrader.getID())
                .orElseThrow(() -> new IllegalArgumentException("Trader tidak ditemukan"));

        // Update Saldo
        double currentSaldo = traderToUpdate.getSaldo();
        traderToUpdate.setSaldo(currentSaldo + amount);

        traderRepository.save(traderToUpdate);
    }
}