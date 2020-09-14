package com.example.gohospidoctor.ModalClass;

public class DocLocation {

    String doctor_location_id;
    String doctorid;
    String short_address;
    String detail_address;
    String status;

    public String getStatus() {
        return status;
    }

    public String getShort_address() {
        return short_address;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public String getDoctor_location_id() {
        return doctor_location_id;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public void setShort_address(String short_address) {
        this.short_address = short_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public void setDoctor_location_id(String doctor_location_id) {
        this.doctor_location_id = doctor_location_id;
    }
}
