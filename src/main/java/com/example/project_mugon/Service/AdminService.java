package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Admin;
import com.example.project_mugon.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

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
