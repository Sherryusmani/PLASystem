package com.example.plasystem;

public class OnewayClass {

    String spin;
    String spin2;
    String date;
    String passeng;
    String Eclass;

    public OnewayClass(String spin, String spin2, String date, String passeng, String eclass) {
        this.spin = spin;
        this.spin2 = spin2;
        this.date = date;
        this.passeng = passeng;
        Eclass = eclass;
    }

    public String getSpin() {
        return spin;
    }

    public void setSpin(String spin) {
        this.spin = spin;
    }

    public String getSpin2() {
        return spin2;
    }

    public void setSpin2(String spin2) {
        this.spin2 = spin2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPasseng() {
        return passeng;
    }

    public void setPasseng(String passeng) {
        this.passeng = passeng;
    }

    public String getEclass() {
        return Eclass;
    }

    public void setEclass(String eclass) {
        Eclass = eclass;
    }
}
