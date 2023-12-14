package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Barang;
import com.example.project_mugon.Repository.AdminRepository;
import com.example.project_mugon.Repository.MarketPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void deleteBarangInWaitingList(String barangId) {
        adminRepository.deleteBarangInWaitingList(barangId);
    }

    public void deleteBarangInMarketPlace(String barangId) {
        adminRepository.deleteBarangInMarketPlace(barangId);
    }
}
