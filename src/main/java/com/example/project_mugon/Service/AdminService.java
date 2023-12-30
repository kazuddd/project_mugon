package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Admin;
import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Model.Trader;
import com.example.project_mugon.Repository.AdminRepository;
import com.example.project_mugon.Repository.BarangRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BarangRepository barangRepository;

    public Admin login(String username, String password) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (password.equals(admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }

    @Autowired
    public AdminService(AdminRepository adminRepository, BarangRepository barangRepository) {
        this.adminRepository = adminRepository;
        this.barangRepository = barangRepository;
    }

    public void deleteBarangInMarketPlace(String barangId) {
        adminRepository.deleteBarangInMarketPlace(barangId);
    }

    public List<Barang> getAllItemsInMarketPlace() {
        List<Barang> listBarang = barangRepository.findByIsVerifiedTrue();

        // Iterasi melalui listBarang dan hapus item yang memiliki idPenjual yang sama dengan user._id
        Iterator<Barang> iterator = listBarang.iterator();
        while (iterator.hasNext()) {
            Barang barang = iterator.next();
        }

        return listBarang;
    }

    public List<Barang> getAllItemsInWaitingList() {
        return barangRepository.findByIsVerifiedFalse();
    }

    public void deleteFromBarangCollection(ObjectId IdBarang){
        barangRepository.deleteBy_id(IdBarang);
    }

    public void editBarangInMarketPlace(ObjectId IdBarang, String namaBarang, String tipeBarang, String lokasi) {
        Optional<Barang> barangOptional = barangRepository.findBy_id(IdBarang);

        barangOptional.ifPresent(barang -> {
            barang.setNamaBarang(namaBarang);
            barang.setTipeBarang(tipeBarang);
            barang.setLokasi(lokasi);

            barangRepository.save(barang);
        });
    }
}
