package com.kp.pembekalan.ally.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public class ImageModel {
    @SerializedName( "user_id" )
    private int user_id;
    @SerializedName( "filename" )
    private String filename;
    @SerializedName( "created" )
    private String created;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
