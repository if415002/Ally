package com.kp.pembekalan.ally.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public class KatalogModel {
    @SerializedName( "id" )
    private int id;
    @SerializedName( "nama" )
    private String nama;
    @SerializedName( "deskripsi" )
    private String deskripsi;
    @SerializedName( "harga" )
    private float harga;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }
}
