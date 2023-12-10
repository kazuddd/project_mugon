package com.example.project_mugon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barang {
    public String namaBarang;
    public double Harga;
    public String TipeBarang;
    public boolean IsBaru;
    public double Kondisi;
    private String Lokasi;
    public Trader Penjual;

    public void RequestToCheck() {

    }
}
