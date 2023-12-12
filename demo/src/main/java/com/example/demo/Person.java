package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty PIP = new SimpleStringProperty("");
    private SimpleStringProperty PHONE = new SimpleStringProperty("");

    public Person(String PIP, String PHONE) {
        this.PIP = new SimpleStringProperty(PIP);
        this.PHONE = new SimpleStringProperty(PHONE);
    }

    public String getPIP() {
        return PIP.get();
    }

    public SimpleStringProperty pipProperty(){
        return PIP;
    }

    public void setPIP(String PIP) {
        this.PIP.set(PIP);
    }

    public String getPHONE() {
        return PHONE.get();
    }

    public SimpleStringProperty phoneProperty(){
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE.set(PHONE);
    }

    @Override
    public String toString(){
        return "Person{"+
                "PIP="+ PIP +
                ", PHONE="+ PHONE +
                '}';
    }
}
