package com.example.electroshop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.electroshop.model.Customer;
import com.example.electroshop.model.Ordertable;
import com.example.electroshop.model.Product;
import com.example.electroshop.model.Tempordertable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    List<Product> allLAP, allDESK, allACCEO, allproduct, singlePROD, searchprod;

    List<Tempordertable> tempordert1, tempordert2, tempordert3, checkproducttempcart, quantityfind;


    List<Ordertable> ordertab1, checkoutprodlist, ordertab3;


    List<Customer> loginCustomer;
    List<Customer> allcustomer;
    List<Customer> logincustomerdetails;


    public DatabaseHelper(@Nullable Context context) {
        super(context, "stdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE product(prodid INTEGER PRIMARY KEY AUTOINCREMENT,prodname TEXT NOT NULL UNIQUE,prodtype TEXT, prodprice DOUBLE, prodbrand TEXT,proddescription TEXT,prodquantity INT,prodimage TEXT)");
        //////todo temptable 12 column
        sqLiteDatabase.execSQL("CREATE TABLE tempordertable(serialno INTEGER PRIMARY KEY AUTOINCREMENT,prodid INTEGER, prodname TEXT NOT NULL,prodtype TEXT, prodprice DOUBLE, prodbrand TEXT,proddescription TEXT,prodquantity INTEGER,prodimage TEXT,totalprice DOUBLE,status TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE ordertable(serialno INTEGER PRIMARY KEY AUTOINCREMENT,prodid INTEGER, prodname TEXT NOT NULL,prodtype TEXT, prodprice DOUBLE, prodbrand TEXT,proddescription TEXT," +
                "prodquantity INTEGER,prodimage TEXT,paymenttype TEXT,cardno TEXT, bkashno Text,bkashtransaction Text,delivery Text,totalprice DOUBLE,status TEXT,cusid INTEGER, cusemail TEXT,cusname TEXT,cusnumber TEXT, cusaddress TEXT)");


        sqLiteDatabase.execSQL("CREATE TABLE customer(cusid INTEGER PRIMARY KEY AUTOINCREMENT,cusname TEXT NOT NULL,cusemail TEXT NOT NULL UNIQUE,cuspassword TEXT NOT NULL, cusnumber TEXT NOT NULL UNIQUE , cusaddress TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS product");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tempordertable");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cutomer");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ordertable");
        onCreate(sqLiteDatabase);

    }


    public boolean createcustomerAccount(Customer customer) {
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cusname", customer.getCusname());
        cv.put("cusemail", customer.getCusemail());

        cv.put("cuspassword", customer.getCuspassword());
        cv.put("cusnumber", customer.getCusnumber());
        cv.put("cusaddress", customer.getCusaddress());

        long result = sd.insert("customer", null, cv);
        sd.close();
        if (result == -1) {
            return false;
        } else {
            Log.d("TAG", "createcustomerAccount: ");
            //  Log.e(TAG,"value inserted");
            return true;
        }
    }

    public Customer customerlogin(String email, String password) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM customer where cusemail='" + email + "' and cuspassword='" + password + "'", null);
        loginCustomer = new ArrayList<>();
        while (c.moveToNext()) {
            loginCustomer.add(new Customer(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));
        }
        return loginCustomer.get(0);
    }

    public List<Customer> loginuserdetails(String email) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM customer where cusemail='" + email + "'", null);
        logincustomerdetails = new ArrayList<>();
        while (c.moveToNext()) {
            logincustomerdetails.add(new Customer(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4), c.getString(5)));
            Log.d("alltempordertable: ", c.getString(1));
        }
        return logincustomerdetails;
    }

    public int customeridGET(String email) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * from customer where cusemail='" + email + "'", null);
        while (c.moveToNext()) {
            return c.getInt(0);
        }
        return 0;

    }


    public List<Customer> allcustomer() {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM customer", null);
        allcustomer = new ArrayList<>();
        while (c.moveToNext()) {
            allcustomer.add(new Customer(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4), c.getString(5)));
            Log.d("alltempordertable: ", c.getString(1));
        }
        return allcustomer;
    }


    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    /////////////////////////////TODO PRODUCT///////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////


    public boolean insetProduct(Product product) {
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("prodname", product.getProdname());
        cv.put("prodtype", product.getProdtype());
        cv.put("prodprice", product.getProdprice());
        cv.put("prodbrand", product.getProdbrand());
        cv.put("proddescription", product.getProddescription());
        cv.put("prodquantity", product.getProdquantity());
        cv.put("prodimage", product.getProdimage());
        long result = sd.insert("product", null, cv);
        sd.close();
        if (result == -1) {
            return false;
        } else {
            Log.e("TAG", "value inserted");
            return true;
        }
    }


    public List<Product> allProduct() {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from product", null);

        allproduct = new ArrayList<>();
        while (c.moveToNext()) {
            allproduct.add(new Product(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3),
                    c.getString(4), c.getString(5), c.getInt(6), c.getString(7)));
        }
        return allproduct;
    }

    public List<Product> allLaptop() {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from product where prodtype = " + "'Laptop'", null);

        allLAP = new ArrayList<>();
        while (c.moveToNext()) {
            allLAP.add(new Product(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3),
                    c.getString(4), c.getString(5), c.getInt(6), c.getString(7)));
        }
        return allLAP;
    }

    public List<Product> allDesktop() {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from product where prodtype = " + "'Desktop'", null);

        allDESK = new ArrayList<>();
        while (c.moveToNext()) {
            allDESK.add(new Product(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3),
                    c.getString(4), c.getString(5), c.getInt(6), c.getString(7)));
        }
        return allDESK;
    }

    public List<Product> allAccessories() {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from product where prodtype = " + "'Accessories'", null);

        allACCEO = new ArrayList<>();
        while (c.moveToNext()) {
            allACCEO.add(new Product(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3),
                    c.getString(4), c.getString(5), c.getInt(6), c.getString(7)));
        }
        return allACCEO;
    }


    /////////////////todo single product

    public List<Product> singleProdInfo(String prodid) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from product  WHERE prodid='" + prodid + "'", null);

        singlePROD = new ArrayList<>();
        while (c.moveToNext()) {
            singlePROD.add(new Product(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3),
                    c.getString(4), c.getString(5), c.getInt(6), c.getString(7)));
        }
        return singlePROD;
    }


    public List<Product> searchProduct(String text) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from product where prodbrand LIKE '%" + text + "%' or prodname LIKE '%" + text + "%'  or prodtype LIKE '%" + text + "%' or proddescription LIKE '%" + text + "%' ", null);


        searchprod = new ArrayList<>();
        while (c.moveToNext()) {
            searchprod.add(new Product(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3),
                    c.getString(4), c.getString(5), c.getInt(6), c.getString(7)));
        }
        return searchprod;
    }


    // //////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////TEMPTABLE////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////


    public boolean insertTempordertable(Tempordertable tempordertable) {
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("prodid", tempordertable.getProdid());
        cv.put("prodname", tempordertable.getProdname());
        cv.put("prodtype", tempordertable.getProdtype());
        cv.put("prodprice", tempordertable.getProdprice());
        cv.put("prodbrand", tempordertable.getProdbrand());
        cv.put("proddescription", tempordertable.getProddescription());
        cv.put("prodquantity", tempordertable.getProdquantity());
        cv.put("prodimage", tempordertable.getProdimage());
        cv.put("totalprice", tempordertable.getTotalprice());
        cv.put("status", "cart");
        long result = sd.insert("tempordertable", null, cv);
        sd.close();
        if (result == -1) {
            return false;
        } else {
            //  Log.e(TAG,"value inserted");
            return true;
        }

    }

    public List<Tempordertable> alldataTempordertable() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM Tempordertable", null);
        tempordert1 = new ArrayList<>();
        while (c.moveToNext()) {
            tempordert1.add(new Tempordertable(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getDouble(4), c.getString(5), c.getString(6),
                    c.getInt(7), c.getString(8), c.getDouble(9), c.getString(10)));

        }
        Log.d("alltemporarycart: ", tempordert1.toString());
        return tempordert1;
    }

    ///todo get quantity
    public int gettempproductquntity(int prodid) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from Tempordertable  WHERE prodid='" + prodid + "'", null);

        quantityfind = new ArrayList<>();
        while (c.moveToNext()) {
            return c.getInt(7);
        }
        return 0;
    }

    ///todo total cartquantiy

    public int totalcartTemptablequantity(String status) {
        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor c = sd.rawQuery("SELECT SUM (prodquantity) FROM Tempordertable where status='" + status + "'", null);
        while (c.moveToNext()) {
            return c.getInt(0);
        }
        return 0;

    }


    public boolean updateTempordertable(Tempordertable tempordertable) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update Tempordertable set prodquantity='" + tempordertable.getProdquantity() + "',totalprice='" + tempordertable.getTotalprice() + "'   WHERE prodid='" + tempordertable.getProdid() + "'");
        db.close();
        Log.d("update", "ID " + tempordertable.getProdid());
        return true;
    }

    public void deleteTempcartProduct(String prodname) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Tempordertable WHERE prodname='" + prodname + "'");
        db.close();
    }

    public void deleteaftercreateaccount() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Tempordertable");
        db.close();
    }


    public int checkproducttempcart(int prodid) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from Tempordertable  WHERE prodid='" + prodid + "'", null);

        checkproducttempcart = new ArrayList<>();
        while (c.moveToNext()) {
            return 1;
        }
        return 0;

    }


    public List<Tempordertable> checktemporarycart(String status) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM Tempordertable where status='" + status + "'", null);
        tempordert2 = new ArrayList<>();
        while (c.moveToNext()) {
            tempordert2.add(new Tempordertable(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getDouble(4), c.getString(5), c.getString(6),
                    c.getInt(7), c.getString(8), c.getDouble(9), c.getString(10)));
        }
        return tempordert2;
    }

    public boolean checkouttemptabe(String status, int prodid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update Tempordertable set status='" + status + "'   WHERE prodid='" + prodid + "'");
        db.close();
        Log.d("update", "Product Status " + prodid + status);
        return true;
    }


    public int temptabledatacheck() {
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT count(*) FROM Tempordertable";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return 1;
        } else {
            return 0;

        }
    }


    // ///////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    // ///////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////


    // ///////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////ordertable//////////////////////
    ///////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////


    public boolean insertordertable(Ordertable ordertable) {
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("prodid", ordertable.getProdid());
        cv.put("prodname", ordertable.getProdname());
        cv.put("prodtype", ordertable.getProdtype());
        cv.put("prodprice", ordertable.getProdprice());
        cv.put("prodbrand", ordertable.getProdbrand());
        cv.put("proddescription", ordertable.getProddescription());
        cv.put("prodquantity", ordertable.getProdquantity());
        cv.put("prodimage", ordertable.getProdimage());
        cv.put("totalprice", ordertable.getTotalprice());
        cv.put("status", ordertable.getStatus());
        cv.put("cusid", ordertable.getCusid());
        cv.put("cusemail", ordertable.getCusemail());
        cv.put("cusname", ordertable.getCusname());
        cv.put("cusnumber", ordertable.getCusnumber());
        cv.put("cusaddress", ordertable.getCusaddress());


        long result = sd.insert("ordertable", null, cv);
        sd.close();
        if (result == -1) {
            return false;
        } else {
            //  Log.e(TAG,"value inserted");
            return true;
        }

    }


    public int totalcartordertablequantity(String status, String cusemail) {
        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor c = sd.rawQuery("SELECT SUM (prodquantity) FROM ordertable where status='" + status + "' AND cusemail='" + cusemail + "'", null);
        while (c.moveToNext()) {
            return c.getInt(0);
        }
        return 0;

    }

    public double totalOrdertablecheckoutprice(String status, String cusemail) {
        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor c = sd.rawQuery("SELECT SUM (totalprice) FROM ordertable where status='" + status + "' AND cusemail='" + cusemail + "'", null);
        while (c.moveToNext()) {
            return c.getInt(0);
        }
        return 0;

    }


    public void deleteOrdercartProduct(int prodid, String cusemail, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM ordertable  WHERE prodid='" + prodid + "'  AND cusemail='" + cusemail + "'AND status='" + status + "'");
        db.close();
    }


    public List<Ordertable> alldataordertable(String email, String status) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM Ordertable WHERE cusemail='" + email + "'  AND status='" + status + "'", null);
        ordertab1 = new ArrayList<>();
        while (c.moveToNext()) {
            ordertab1.add(new Ordertable(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getDouble(4), c.getString(5), c.getString(6),
                    c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), c.getString(13), c.getDouble(14), c.getString(15),
                    c.getInt(16), c.getString(17), c.getString(18), c.getString(19), c.getString(20)));

        }
        Log.d("ordertable: ", ordertab1.toString());
        return ordertab1;
    }

    ///todo check
    public int checkproductordercart(int prodid, String cusemail, String status) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from Ordertable WHERE prodid='" + prodid + "'  AND cusemail='" + cusemail + "'AND status='" + status + "'", null);

        checkproducttempcart = new ArrayList<>();
        while (c.moveToNext()) {
            return 1;
        }
        return 0;

    }


    public boolean updateodertable(int quantity, double totalprice, int prodid, String cusemail, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update Ordertable set prodquantity='" + quantity + "',totalprice='" + totalprice + "'   WHERE prodid='" + prodid + "'  AND cusemail='" + cusemail + "'AND status='" + status + "'");
        db.close();
        return true;
    }


    public int getorderproductquntity(int prodid, String cusemail, String status) {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from Ordertable  WHERE prodid='" + prodid + "'  AND cusemail='" + cusemail + "'AND status='" + status + "'", null);

        quantityfind = new ArrayList<>();
        while (c.moveToNext()) {
            return c.getInt(7);
        }
        return 0;
    }


    public boolean updateordertablequantity(Ordertable ordertable) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update Ordertable set prodquantity='" + ordertable.getProdquantity() + "',totalprice='" + ordertable.getTotalprice() + "'  WHERE prodid='" + ordertable.getProdid() + "'  AND cusemail='" + ordertable.getCusemail() + "'AND status='" + ordertable.getStatus() + "'");
        db.close();
        return true;
    }


    public boolean checkoutOrdercart(String status, int prodid, String cusemail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update Ordertable set status='" + status + "' WHERE prodid='" + prodid + "'  AND cusemail='" + cusemail + "'");
        db.close();
        return true;
    }

    //todo order code
    public boolean orderConform(Ordertable ordertable) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("update Ordertable set status='order',cusname='" + ordertable.getCusname() + "',cusnumber='" + ordertable.getCusnumber() + "',cusaddress='" + ordertable.getCusaddress() + "',paymenttype='" + ordertable.getPaymenttype() + "',cardno='" + ordertable.getCardno() + "',bkashno='" + ordertable.getBkashno() + "',bkashtransaction='" + ordertable.getBkashtransaction() + "',delivery='" + ordertable.getDelivery() + "' WHERE  status='" + ordertable.getStatus() + "' AND cusemail='" + ordertable.getCusemail() + "'");
            db.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }



    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////


}
