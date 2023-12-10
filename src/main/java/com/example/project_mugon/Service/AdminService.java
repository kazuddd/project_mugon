package com.example.project_mugon.Service;

import com.example.project_mugon.Model.Admin;
import com.example.project_mugon.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    public AdminRepository adminRepository;

    public List<Admin> allAdmin() {
        // Mengambil semua data pada database repository admin
        return (List<Admin>) adminRepository.findAll();
    }
    public Optional<Admin> findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
