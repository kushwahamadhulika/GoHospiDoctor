package com.example.gohospidoctor.ModalClass;

public class ViewBookingDetailsModal {

    String patient_name;
    String patient_age;
    String patient_sex;
    String patient_mobile;

    public ViewBookingDetailsModal(String patient_name, String patient_age, String patient_sex, String patient_mobile) {
        this.patient_name = patient_name;
        this.patient_age = patient_age;
        this.patient_sex = patient_sex;
        this.patient_mobile = patient_mobile;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public String getPatient_mobile() {
        return patient_mobile;
    }

    public String getPatient_sex() {
        return patient_sex;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public void setPatient_mobile(String patient_mobile) {
        this.patient_mobile = patient_mobile;
    }

    public void setPatient_sex(String patient_sex) {
        this.patient_sex = patient_sex;
    }
}
