package com.example.project_mugon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaksi {
    private ObjectId ID;
    public Barang[] ListBeli;
    public double TotalHarga;
    private String Lokasi;
    private String usernamePembeli;
}
