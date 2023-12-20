package com.example.project_mugon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaksi {
    @Id
    private ObjectId _id;
    public Barang[] ListBeli;
    public double TotalHarga;
    private String Lokasi;
    private String usernamePembeli;

    public String getID() {
        return String.valueOf(this._id);
    }
}
