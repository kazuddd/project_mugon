package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Transaksi;
import com.example.project_mugon.Model.Trader;
import com.example.project_mugon.Repository.BarangRepository;
import com.example.project_mugon.Repository.MarketPlaceRepository;
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
    private final BarangService barangService;
    private final MarketPlaceRepository marketPlaceRepository;

    @Autowired
    public TraderService(TraderRepository traderRepository, BarangRepository barangRepository, TransaksiRepository transaksiRepository, BarangService barangService, MarketPlaceRepository marketPlaceRepository) {
        this.traderRepository = traderRepository;
        this.barangRepository = barangRepository;
        this.transaksiRepository = transaksiRepository;
        this.barangService = barangService;
        this.marketPlaceRepository = marketPlaceRepository;
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
    public ObjectId jualBarang(String namaBarang, double harga, String tipeBarang, boolean isBaru, String lokasi, Trader seller) {
        Barang newBarang = new Barang();
        newBarang.setNamaBarang(namaBarang);
        newBarang.setHarga(harga);
        newBarang.setTipeBarang(tipeBarang);
        newBarang.setBaru(isBaru);
        newBarang.setKondisi(-1);
        newBarang.setLokasi(lokasi);
        newBarang.setIdPenjual(seller.get_id());

        // Input barang pada repository WaitingList
        barangRepository.save(newBarang);

        return newBarang.get_id();
    }
    public void addToKeranjang(ObjectId barangID, Trader buyer) {
        // Update keranjang pembeli
        List<ObjectId> keranjang = buyer.getKeranjang();
        keranjang.add(barangID); // Menambahkan ID barang baru kedalam list
        buyer.setKeranjang(keranjang); // Memanggil method setter untuk update keranjang pada class Buyer
        traderRepository.updateTrader(buyer); // Update trader dengan keranjang baru ke Database

    }

    public void checkoutKeranjang(Trader pembeli) {
        Transaksi newTransaksi = new Transaksi();

        newTransaksi.setUsernamePembeli(pembeli.get_id());

        // Inisialisasi total harga
        double totalHarga = 0.0;

        // Iterate melalui setiap ObjectId dalam listBeli
        for (ObjectId barangId : pembeli.getKeranjang()) {
            // Dapatkan informasi Barang berdasarkan ObjectId
            Barang barang = barangService.findByID(barangId); // Menggunakan service untuk menemukan Barang berdasarkan ID

            // Jika barang ditemukan, tambahkan harganya ke totalHarga
            if (barang != null) {
                totalHarga += barang.getHarga();
            } else {
                System.out.println("\nBARANG DENGAN ID " + barangId + "TIDAK DITEMUKAN");
            }
        }

        // Set totalHarga ke newTransaksi
        newTransaksi.setTotalHarga(totalHarga);

        // Pengecekan apakah saldo cukup
        if (pembeli.getSaldo() >= totalHarga) {

            List<Barang> semuaBarangKeranjang = marketPlaceRepository.findAllBy_idIn(pembeli.getKeranjang());

            // Penambahan saldo penjual
            semuaBarangKeranjang.forEach(barang -> {
                ObjectId idPenjual = barang.getIdPenjual();
                double hargaBarang = barang.getHarga();

                Trader penjual = traderRepository.findBy_id(idPenjual); // Ganti dengan metode yang sesuai untuk menemukan penjual berdasarkan _id

                if (penjual != null) {
                    double saldoPenjual = penjual.getSaldo();
                    saldoPenjual += hargaBarang;
                    penjual.setSaldo(saldoPenjual);

                    traderRepository.save(penjual); // Simpan kembali penjual setelah saldo diperbarui
                }
            });
            newTransaksi.setListBeli(semuaBarangKeranjang);

            // Tambahkan transaksi ini pada LIST TRANSAKSI pembeli
            List<Transaksi> listT = pembeli.getListTransaksi();
            listT.add(newTransaksi);
            pembeli.setListTransaksi(listT);

            // Kurangi SALDO pembeli dengan total harga pada transaksi
            pembeli.setSaldo(pembeli.getSaldo() - totalHarga);

            // Reset keranjang menjadi kosong lagi
            List<ObjectId> newKeranjang = pembeli.getKeranjang();
            List<ObjectId> toDelete = pembeli.getKeranjang();
            newKeranjang.clear();
            pembeli.setKeranjang(newKeranjang);

            // Update atribut pembeli
            traderRepository.updateTrader(pembeli);

            // Hapus semua barang yang dibeli dari repository MarketPlace
            marketPlaceRepository.deleteBy_idIn(toDelete);
        } else {
            System.out.println("SALDO TIDAK CUKUP");
        }
    }

    public void tambahSaldo(Trader traderToUpdate, double amount) {
        // Update Saldo
        double currentSaldo = traderToUpdate.getSaldo();
        traderToUpdate.setSaldo(currentSaldo + amount);

        // Update trader
        traderRepository.save(traderToUpdate);
        System.out.println("\nSALDO BERHASIL DI UPDATE");
    }
}