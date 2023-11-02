package com.example.recycleview;

public class infoModel {
    int img;
    String name, number, address;

    public infoModel(int img, String name,String number, String address) {
        this.img = img;
        this.name = name;
        this.number = number;
        this.address = address;
    }
    public infoModel(String name, String number, String address){
        this.name = name;
        this.number = number;
        this.address = address;
    }
}
