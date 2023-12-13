package com.example.project_mugon.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trader extends User {
    private String Email;
    private String Nama;
    protected String Password;
    private Barang[] Keranjang;
    private double Saldo;
    private Transaksi[] ListTransaksi;
}
