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

    // Register trader
    public Trader registerTrader(Trader trader) {
        // Check if the trader already exists
        Optional<Trader> existingTrader = traderRepository.findByEmail(trader.getEmail());
        if (existingTrader.isPresent()) {
            throw new IllegalArgumentException("Email sudah digunakan.");
        }
        return traderRepository.save(trader);
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
        newBarang.setIsBaru(isBaru);
        newBarang.setKondisi(0);
        newBarang.setKondisi(kondisi);
        newBarang.setLokasi(lokasi);
        newBarang.setIdPenjual(seller.getId());

        // Input barang pada repository WaitingList
        barangRepository.save(newBarang);
    }

    public List<Transaksi> cekTransaksi(Trader trader) {
        return transaksiRepository.findByUsernamePembeli(trader.getEmail());
    }
    public void addToKeranjang(Barang barangToBuy, Trader buyer) {
        Trader traderToUpdate = traderRepository.findById(buyer.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trader tidak ditemukan"));

        // Keranjang yang dimiliki oleh trader sebelum method
        Barang[] currentCart = traderToUpdate.getKeranjang();

        // Pengecekan apakah barang tersebut sudah ada dalam keranjang
        boolean addedToCart = false;
        for (int i = 0; i < currentCart.length; i++) {
            if (currentCart[i] == null) {
                currentCart[i] = barangToBuy;
                addedToCart = true;
                break;
            }
        }

        if (!addedToCart) {
            // Menambahkan panjang array jika array penuh
            Barang[] newCart = new Barang[currentCart.length + 1];
            System.arraycopy(currentCart, 0, newCart, 0, currentCart.length);
            newCart[currentCart.length] = barangToBuy;
            traderToUpdate.setKeranjang(newCart);
        } else {
            // Update keranjang trader
            traderToUpdate.setKeranjang(currentCart);
        }

        // Update data trader pada collection
        traderRepository.save(traderToUpdate);
    }

    public void beliBarang(Trader pembeli) {
        Barang[] keranjangPembeli= pembeli.getKeranjang();
        double totalHarga = 0;
        for (int i = 0; i < keranjangPembeli.length; i++) {
            totalHarga += keranjangPembeli[i].getHarga();
        }
        if (pembeli.getSaldo() < totalHarga) {
            throw new IllegalArgumentException("Saldo tidak cukup");
        } else {
            Transaksi newTransaksi = new Transaksi();

            Transaksi[] currentTransaksi = pembeli.getListTransaksi();

            int currentLength = currentTransaksi != null ? currentTransaksi.length : 0;
            Transaksi[] updatedTransaksi = new Transaksi[currentLength + 1];
            if (currentLength > 0) {
                System.arraycopy(currentTransaksi, 0, updatedTransaksi, 0, currentLength);
            }

            updatedTransaksi[currentLength] = newTransaksi;
            pembeli.setListTransaksi(updatedTransaksi);

            traderRepository.save(pembeli);

            // DELETE Barang dalam transaksi dari collection MarketPlace
            for (Barang barang : keranjangPembeli) {
                if (barang != null) {
                    barangRepository.deleteBarang(barang);
                }
            }
        }
    }

    public void tambahSaldo(Trader currentTrader, double amount) {
        Trader traderToUpdate = traderRepository.findById(currentTrader.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trader tidak ditemukan"));

        // Update Saldo
        double currentSaldo = traderToUpdate.getSaldo();
        traderToUpdate.setSaldo(currentSaldo + amount);

        traderRepository.save(traderToUpdate);
    }
}