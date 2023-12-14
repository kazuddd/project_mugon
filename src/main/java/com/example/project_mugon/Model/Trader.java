package com.example.project_mugon.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Trader")
public class Trader extends User {
    private String Email;
    private String Nama;
    protected String Password;
    private Barang[] Keranjang;
    private double Saldo;
    private Transaksi[] ListTransaksi;
}