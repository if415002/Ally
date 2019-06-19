package com.kp.pembekalan.ally.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public class FavoriteModel {
    @SerializedName( "nama" )
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
