package com.example.gohospidoctor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("id")
    @Expose

   private String id;
    @SerializedName("status")
    @Expose

   private String status;
    @SerializedName("message")
    @Expose

   private String message;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
