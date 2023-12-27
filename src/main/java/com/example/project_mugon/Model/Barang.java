package com.example.project_mugon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "WaitingList")
public class Barang {
    @Id
    private ObjectId _id;
    private String namaBarang;
    private double harga;
    private String tipeBarang;
    private boolean isBaru;
    private double kondisi;
    private String lokasi;
    private ObjectId idPenjual;
    private boolean isVerified;

    public String getID(){
        return String.valueOf(this._id);
    }
}
