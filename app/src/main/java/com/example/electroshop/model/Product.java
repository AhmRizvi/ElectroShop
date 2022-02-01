package com.example.electroshop.model;

public class Product {

    int prodid;
    String prodname;
    String prodtype;
    double prodprice;
    String prodbrand;
    String proddescription;
    int prodquantity;
    String prodimage;

    public Product(int prodid, String prodname, String prodtype, double prodprice, String prodbrand, String proddescription, int prodquantity, String prodimage) {
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.prodprice = prodprice;
        this.prodbrand = prodbrand;
        this.proddescription = proddescription;
        this.prodquantity = prodquantity;
        this.prodimage = prodimage;
    }

    public Product(String prodname, String prodtype, double prodprice, String prodbrand, String proddescription, int prodquantity, String prodimage) {
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.prodprice = prodprice;
        this.prodbrand = prodbrand;
        this.proddescription = proddescription;
        this.prodquantity = prodquantity;
        this.prodimage = prodimage;
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

    @Override
    public String toString() {
        return "Product{" +
                "prodid=" + prodid +
                ", prodname='" + prodname + '\'' +
                ", prodtype='" + prodtype + '\'' +
                ", prodprice=" + prodprice +
                ", prodbrand='" + prodbrand + '\'' +
                ", proddescription='" + proddescription + '\'' +
                ", prodquantity=" + prodquantity +
                ", prodimage='" + prodimage + '\'' +
                '}';
    }
}
