package com.application.e_votingtif;

public class Objek {
    String nim="",nama_lengkap="";

    Objek(String nim, String nama_lengkap){
        this.nim=nim;
        this.nama_lengkap=nama_lengkap;
    }

    public String getNim() {
        return nim;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }
}
