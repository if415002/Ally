package com.kp.pembekalan.ally.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public class FavoriteResponse {
    @SerializedName( "favorit" )
    private FavoriteModel favoriteModel;

    public FavoriteModel getFavoriteModel() {
        return favoriteModel;
    }

    public void setFavoriteModel(FavoriteModel favoriteModel) {
        this.favoriteModel = favoriteModel;
    }
}
