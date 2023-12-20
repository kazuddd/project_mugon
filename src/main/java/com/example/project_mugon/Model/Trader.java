package com.example.project_mugon.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Trader")
public class Trader extends User {
    private String email;
    private List<ObjectId> keranjang;
    private double saldo;
    private List<Transaksi> listTransaksi;
}