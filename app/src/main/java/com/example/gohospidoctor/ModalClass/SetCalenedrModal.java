package com.example.gohospidoctor.ModalClass;

public class SetCalenedrModal {
    private String doctor_calendar_id;
    private    String doctorid;
    private   String from_time;
    private   String to_time;
    private   String date;
    private  String location;
    private  String no_patient;

    public SetCalenedrModal() {
        this.doctor_calendar_id = doctor_calendar_id;
        this.doctorid = doctorid;
        this.from_time = from_time;
        this.to_time = to_time;
        this.date = date;
        this.location = location;
        this.no_patient = no_patient;
    }

    public String getLocation() {
        return location;
    }

    public String getNo_patient() {
        return no_patient;
    }

    public String getTo_time() {
        return to_time;
    }

    public String getFrom_time() {
        return from_time;
    }

    public String getDate() {
        return date;
    }

    public String getDoctor_calendar_id() {
        return doctor_calendar_id;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setNo_patient(String no_patient) {
        this.no_patient = no_patient;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public void setDoctor_calendar_id(String doctor_calendar_id) {
        this.doctor_calendar_id = doctor_calendar_id;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
