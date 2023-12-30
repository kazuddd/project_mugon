package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Transaksi;
import com.example.project_mugon.Model.Trader;
import com.example.project_mugon.Repository.BarangRepository;
import com.example.project_mugon.Repository.TraderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    private final TraderRepository traderRepository;
    private final BarangRepository barangRepository;
    private final BarangService barangService;

    @Autowired
    public TraderService(TraderRepository traderRepository, BarangRepository barangRepository, BarangService barangService) {
        this.traderRepository = traderRepository;
        this.barangRepository = barangRepository;
        this.barangService = barangService;
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
        newBarang.setVerified(false);

        // Input barang pada repository WaitingList
        barangRepository.save(newBarang);

        return newBarang.get_id();
    }

    public void checkoutKeranjang(Trader pembeli) {
        Transaksi newTransaksi = new Transaksi();

        newTransaksi.setUsernamePembeli(pembeli.get_id());

        // Inisialisasi total harga
        double totalHarga = 0.0;

        // Iterate melalui setiap ObjectId dalam listBeli
        for (ObjectId barangId : pembeli.getKeranjang()) {
            // Dapatkan informasi Barang berdasarkan ObjectId
            Optional<Barang> barangOptional = barangRepository.findBy_id(barangId);

            if (barangOptional.isPresent()) {
                Barang barang = barangOptional.get();
                totalHarga += barang.getHarga();
            }
        }
        // Set totalHarga ke newTransaksi
        newTransaksi.setTotalHarga(totalHarga);

        // Pengecekan apakah saldo cukup

        List<Barang> barangDiKeranjang = new ArrayList<>();
        if (pembeli.getSaldo() >= totalHarga) {

            for (ObjectId idBarang : pembeli.getKeranjang()) {
                Optional<Barang> barangOptional = barangRepository.findBy_id(idBarang);
                if (barangOptional.isPresent()) {
                    Barang barang = barangOptional.get();
                    barangDiKeranjang.add(barang);
                }
            }

            // Penambahan Saldo Penjual
            for (Barang barang : barangDiKeranjang) {
                ObjectId idPenjual = barang.getIdPenjual();
                double hargaBarang = barang.getHarga();

                Trader penjual = traderRepository.findBy_id(idPenjual);

                // Check if the seller exists and update the balance
                if (penjual != null) {
                    double saldoPenjual = penjual.getSaldo() + hargaBarang;
                    penjual.setSaldo(saldoPenjual);
                    traderRepository.save(penjual); // Save the updated seller info
                }
            }
            // Penambahan List Barang yang dibeli dalam satu transaksi
            newTransaksi.setListBeli(barangDiKeranjang);

            // Tambahkan transaksi ini pada LIST TRANSAKSI pembeli
            List<Transaksi> listT = pembeli.getListTransaksi();
            listT.add(newTransaksi);
            pembeli.setListTransaksi(listT);

            // Kurangi SALDO pembeli dengan total harga pada transaksi
            pembeli.setSaldo(pembeli.getSaldo() - totalHarga);

            // Delete barang dalam keranjang pada collection barang
            for (Barang barang : barangDiKeranjang) {
                barangRepository.delete(barang);
            }

            // Reset keranjang menjadi kosong lagi
            List<ObjectId> newKeranjang = pembeli.getKeranjang();
            List<ObjectId> toDelete = pembeli.getKeranjang();
            newKeranjang.clear();
            pembeli.setKeranjang(newKeranjang);

            // Update atribut pembeli
            traderRepository.save(pembeli);

            // Hapus semua barang yang dibeli dari repository MarketPlace
            barangRepository.deleteBy_idIn(toDelete);
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

    public void addToKeranjang(Trader buyer, String id) {
        ObjectId itemId = new ObjectId(id);
        List<ObjectId> keranjang = buyer.getKeranjang();

        if (!keranjang.contains(itemId)) {
            keranjang.add(itemId);
            buyer.setKeranjang(keranjang);
        }

        traderRepository.save(buyer);
    }

    public List<Barang> getAllItemsInMarketPlace(Trader user) {
        List<Barang> listBarang = barangRepository.findByIsVerifiedTrue();

        // Iterasi melalui listBarang dan hapus item yang memiliki idPenjual yang sama dengan user._id
        Iterator<Barang> iterator = listBarang.iterator();
        while (iterator.hasNext()) {
            Barang barang = iterator.next();
            if (barang.getIdPenjual().equals(user.get_id())) {
                iterator.remove(); // Hapus barang dari listBarang
            }
        }

        return listBarang;
    }

    public List<Barang> getALlInKeranjang(Trader buyer) {
        List<ObjectId> IdDalamKeranjang = buyer.getKeranjang();
        List<Barang> barangDiKeranjang = new ArrayList<>();

        for (ObjectId idBarang : IdDalamKeranjang) {
            Optional<Barang> barangOptional = barangRepository.findBy_id(idBarang);
            if (barangOptional.isPresent()) {
                Barang barang = barangOptional.get();
                barangDiKeranjang.add(barang);
            }
        }

        return barangDiKeranjang;
    }

    public List<Barang> searchBarang(List<Barang> MarketPlace, String search) {
        List<Barang> foundItems = new ArrayList<>();

        for (Barang barang : MarketPlace) {
            // Menggunakan equalsIgnoreCase untuk membandingkan nama barang tanpa memperhatikan case
            if (barang.getNamaBarang().equalsIgnoreCase(search)) {
                foundItems.add(barang);
            }
        }

        return foundItems;
    }
}