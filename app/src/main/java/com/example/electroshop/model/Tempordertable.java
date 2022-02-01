package com.example.electroshop.model;

import org.w3c.dom.Text;

public class Tempordertable {
    ////
    ////
    ////
    ///todo 16 column

    int serialno;
    int prodid;
    String prodname;
    String prodtype;
    double prodprice;
    String prodbrand;
    String proddescription;
    int prodquantity;
    String prodimage;
    double totalprice;
    String status;

    public Tempordertable(int serialno, int prodid, String prodname, String prodtype, double prodprice, String prodbrand, String proddescription, int prodquantity, String prodimage, double totalprice, String status) {
        this.serialno = serialno;
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.prodprice = prodprice;
        this.prodbrand = prodbrand;
        this.proddescription = proddescription;
        this.prodquantity = prodquantity;
        this.prodimage = prodimage;
        this.totalprice = totalprice;
        this.status = status;
    }

    public Tempordertable(int prodid, String prodname, String prodtype, double prodprice, String prodbrand, String proddescription, int prodquantity, String prodimage, double totalprice, String status) {
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.prodprice = prodprice;
        this.prodbrand = prodbrand;
        this.proddescription = proddescription;
        this.prodquantity = prodquantity;
        this.prodimage = prodimage;
        this.totalprice = totalprice;
        this.status = status;
    }

    public Tempordertable(int prodid, int prodquantity, double totalprice) {
        this.prodid = prodid;
        this.prodquantity = prodquantity;
        this.totalprice = totalprice;
    }

    public int getSerialno() {
        return serialno;
    }

    public void setSerialno(int serialno) {
        this.serialno = serialno;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }

    public double getProdprice() {
        return prodprice;
    }

    public void setProdprice(double prodprice) {
        this.prodprice = prodprice;
    }

    public String getProdbrand() {
        return prodbrand;
    }

    public void setProdbrand(String prodbrand) {
        this.prodbrand = prodbrand;
    }

    public String getProddescription() {
        return proddescription;
    }

    public void setProddescription(String proddescription) {
        this.proddescription = proddescription;
    }

    public int getProdquantity() {
        return prodquantity;
    }

    public void setProdquantity(int prodquantity) {
        this.prodquantity = prodquantity;
    }

    public String getProdimage() {
        return prodimage;
    }

    public void setProdimage(String prodimage) {
        this.prodimage = prodimage;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Tempordertable{" +
                "serialno=" + serialno +
                ", prodid=" + prodid +
                ", prodname='" + prodname + '\'' +
                ", prodtype='" + prodtype + '\'' +
                ", prodprice=" + prodprice +
                ", prodbrand='" + prodbrand + '\'' +
                ", proddescription='" + proddescription + '\'' +
                ", prodquantity=" + prodquantity +
                ", prodimage='" + prodimage + '\'' +
                ", totalprice=" + totalprice +
                ", status='" + status + '\'' +
                '}';
    }
}
