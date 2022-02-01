package com.example.electroshop.model;

import org.w3c.dom.Text;

public class Ordertable {


    int serialno;
    int prodid;
    String prodname;
    String prodtype;
    double prodprice;
    String prodbrand;
    String proddescription;
    int prodquantity;
    String prodimage;
    String paymenttype;
    String cardno;
    String bkashno;
    String bkashtransaction;
    String delivery;
    double totalprice;
    String status;
    int cusid;
    String cusemail;
    String cusname;
    String cusnumber;
    String cusaddress;

    public Ordertable() {
    }

    public Ordertable(String paymenttype, String cardno, String bkashno, String bkashtransaction, String delivery, String status, String cusemail, String cusname, String cusnumber, String cusaddress) {
        this.paymenttype = paymenttype;
        this.cardno = cardno;
        this.bkashno = bkashno;
        this.bkashtransaction = bkashtransaction;
        this.delivery = delivery;
        this.status = status;
        this.cusemail = cusemail;
        this.cusname = cusname;
        this.cusnumber = cusnumber;
        this.cusaddress = cusaddress;
    }

    public Ordertable(int serialno, int prodid, String prodname, String prodtype, double prodprice, String prodbrand, String proddescription, int prodquantity, String prodimage, String paymenttype, String cardno, String bkashno, String bkashtransaction, String delivery, double totalprice, String status, int cusid, String cusemail, String cusname, String cusnumber, String cusaddress) {
        this.serialno = serialno;
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.prodprice = prodprice;
        this.prodbrand = prodbrand;
        this.proddescription = proddescription;
        this.prodquantity = prodquantity;
        this.prodimage = prodimage;
        this.paymenttype = paymenttype;
        this.cardno = cardno;
        this.bkashno = bkashno;
        this.bkashtransaction = bkashtransaction;
        this.delivery = delivery;
        this.totalprice = totalprice;
        this.status = status;
        this.cusid = cusid;
        this.cusemail = cusemail;
        this.cusname = cusname;
        this.cusnumber = cusnumber;
        this.cusaddress = cusaddress;
    }

    public Ordertable(int prodid, String prodname, String prodtype, double prodprice, String prodbrand, String proddescription, int prodquantity, String prodimage, String paymenttype, String cardno, String bkashno, String bkashtransaction, String delivery, double totalprice, String status, int cusid, String cusemail, String cusname, String cusnumber, String cusaddress) {
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.prodprice = prodprice;
        this.prodbrand = prodbrand;
        this.proddescription = proddescription;
        this.prodquantity = prodquantity;
        this.prodimage = prodimage;
        this.paymenttype = paymenttype;
        this.cardno = cardno;
        this.bkashno = bkashno;
        this.bkashtransaction = bkashtransaction;
        this.delivery = delivery;
        this.totalprice = totalprice;
        this.status = status;
        this.cusid = cusid;
        this.cusemail = cusemail;
        this.cusname = cusname;
        this.cusnumber = cusnumber;
        this.cusaddress = cusaddress;
    }

    public Ordertable(int prodid, String prodname, String prodtype, double prodprice, String prodbrand, String proddescription, int prodquantity, String prodimage, double totalprice, String status, int cusid, String cusemail, String cusname, String cusnumber, String cusaddress) {
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
        this.cusid = cusid;
        this.cusemail = cusemail;
        this.cusname = cusname;
        this.cusnumber = cusnumber;
        this.cusaddress = cusaddress;
    }


    public Ordertable(int prodid, int prodquantity, double totalprice, String status, String cusemail) {
        this.prodid = prodid;
        this.prodquantity = prodquantity;
        this.totalprice = totalprice;
        this.status = status;
        this.cusemail = cusemail;
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

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getBkashno() {
        return bkashno;
    }

    public void setBkashno(String bkashno) {
        this.bkashno = bkashno;
    }

    public String getBkashtransaction() {
        return bkashtransaction;
    }

    public void setBkashtransaction(String bkashtransaction) {
        this.bkashtransaction = bkashtransaction;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
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

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public String getCusemail() {
        return cusemail;
    }

    public void setCusemail(String cusemail) {
        this.cusemail = cusemail;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
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
        return "Ordertable{" +
                "serialno=" + serialno +
                ", prodid=" + prodid +
                ", prodname='" + prodname + '\'' +
                ", prodtype='" + prodtype + '\'' +
                ", prodprice=" + prodprice +
                ", prodbrand='" + prodbrand + '\'' +
                ", proddescription='" + proddescription + '\'' +
                ", prodquantity=" + prodquantity +
                ", prodimage='" + prodimage + '\'' +
                ", paymenttype='" + paymenttype + '\'' +
                ", cardno='" + cardno + '\'' +
                ", bkashno='" + bkashno + '\'' +
                ", bkashtransaction='" + bkashtransaction + '\'' +
                ", delivery='" + delivery + '\'' +
                ", totalprice=" + totalprice +
                ", status='" + status + '\'' +
                ", cusid=" + cusid +
                ", cusemail='" + cusemail + '\'' +
                ", cusname='" + cusname + '\'' +
                ", cusnumber='" + cusnumber + '\'' +
                ", cusaddress='" + cusaddress + '\'' +
                '}';
    }
}
