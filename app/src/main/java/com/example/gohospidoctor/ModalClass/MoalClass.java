package com.example.gohospidoctor.ModalClass;

public class MoalClass {

    int id;
    String mobile;
    String password;
    String type;

    public MoalClass(int id, String mobile, String password, String type) {
        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }
}
