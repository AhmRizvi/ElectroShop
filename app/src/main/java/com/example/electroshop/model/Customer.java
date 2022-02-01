package com.example.electroshop.model;

public class Customer {

    int cusid;
    String cusname;
    String cusemail;
    String cuspassword;
    String cusnumber;
    String cusaddress;

    public Customer() {
    }


    public Customer(int cusid, String cusname, String cusemail, String cuspassword, String cusnumber, String cusaddress) {
        this.cusid = cusid;
        this.cusname = cusname;
        this.cusemail = cusemail;
        this.cuspassword = cuspassword;
        this.cusnumber = cusnumber;
        this.cusaddress = cusaddress;
    }

    public Customer(String cusname, String cusemail, String cuspassword, String cusnumber, String cusaddress) {
        this.cusname = cusname;
        this.cusemail = cusemail;
        this.cuspassword = cuspassword;
        this.cusnumber = cusnumber;
        this.cusaddress = cusaddress;
    }

    public Customer(int cusid) {
        this.cusid = cusid;
    }

    public Customer(String cusname, String cuspassword) {
        this.cusname = cusname;
        this.cuspassword = cuspassword;
    }

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public Customer(String cusemail) {
        this.cusemail = cusemail;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getCusemail() {
        return cusemail;
    }

    public void setCusemail(String cusemail) {
        this.cusemail = cusemail;
    }

    public String getCuspassword() {
        return cuspassword;
    }

    public void setCuspassword(String cuspassword) {
        this.cuspassword = cuspassword;
    }

    public String getCusnumber() {
        return cusnumber;
    }

    public void setCusnumber(String cusnumber) {
        this.cusnumber = cusnumber;
    }

    public String getCusaddress() {
        return cusaddress;
    }

    public void setCusaddress(String cusaddress) {
        this.cusaddress = cusaddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusid=" + cusid +
                ", cusname='" + cusname + '\'' +
                ", cusemail='" + cusemail + '\'' +
                ", cuspassword='" + cuspassword + '\'' +
                ", cusnumber='" + cusnumber + '\'' +
                ", cusaddress='" + cusaddress + '\'' +
                '}';
    }
}
