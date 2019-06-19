package com.kp.pembekalan.ally.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public class ImageResponse {
    private ImageModel imageModel;
    private String message;
    private String path;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

}

