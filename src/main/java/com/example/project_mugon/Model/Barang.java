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
    private ObjectId ID;
    private String namaBarang;
    private double Harga;
    private String TipeBarang;
    private boolean IsBaru;
    private double Kondisi;
    private String Lokasi;
    private String IdPenjual;

    public String getID(){
        return String.valueOf(this.ID);
    }
}
