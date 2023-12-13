package com.example.project_mugon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barang {
    private ObjectId ID;
    private String namaBarang;
    private double Harga;
    private String TipeBarang;
    private boolean IsBaru;
    private double Kondisi;
    private String Lokasi;
    private Trader Penjual;

    public void RequestToCheck() {

    }
}
