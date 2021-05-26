package com.example.nader.e_commerceonlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class ECommerceDB extends SQLiteOpenHelper {

    private static String DBName = "Ecommerce";
    SQLiteDatabase ecommerces;

    public ECommerceDB(Context context) {
        super(context, DBName, null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table customers(CustID integer primary key autoincrement, CustName text, UserName text, Password text, Gender text, Birthdate text, Job text);");
        db.execSQL("create table rememberme(RemID integer primary key autoincrement, User_Name text, FOREIGN KEY(User_Name) REFERENCES customers(UserName));");
        db.execSQL("create table orders(OrderID integer primary key autoincrement, OrdDate text , Address text, Cust_id integer, FOREIGN KEY(Cust_id) REFERENCES customers(CustID));");
        db.execSQL("create table categories(CatID integer primary key autoincrement, CatName text);");
        db.execSQL("create table products(ProID integer primary key autoincrement, ProName text , Price integer, Quantity integer, Cat_id integer, FOREIGN KEY(Cat_id) REFERENCES categories(CatID));");
        db.execSQL("create table orderdetails(Quantity integer, Ord_id integer NOT NULL, Pro_id integer NOT NULL, value, primary key (Ord_id, Pro_id), FOREIGN KEY(Ord_id) REFERENCES orders(OrdID), FOREIGN KEY(Pro_id) REFERENCES products(ProID));");

        ContentValues row;

        row = new ContentValues();
        row.put("CustName", "Nader");
        row.put("UserName", "ndr");
        row.put("Password", "123");
        row.put("Gender", "M");
        row.put("Birthdate", "16/12/2018");
        row.put("Job", "Prog");
        db.insert("customers", null, row);
        row = new ContentValues();
        row.put("CustName", "Memmo");
        row.put("UserName", "mi");
        row.put("Password", "987");
        row.put("Gender", "F");
        row.put("Birthdate", "29/2/2018");
        row.put("Job", "Eng");
        db.insert("customers", null, row);


        row = new ContentValues();
        row.put("User_Name", "ndr");
        db.insert("rememberme", null, row);


        row = new ContentValues();
        row.put("CatName", "Electronics");
        db.insert("categories", null, row);
        row = new ContentValues();
        row.put("CatName", "Gaming");
        db.insert("categories", null, row);
        row = new ContentValues();
        row.put("CatName", "Garden & Outdoor");
        db.insert("categories", null, row);
        row = new ContentValues();
        row.put("CatName", "Sports & Fitness");
        db.insert("categories", null, row);


        row = new ContentValues();
        row.put("ProName", "Electronic1");
        row.put("Price", "15000");
        row.put("Quantity", "5");
        row.put("Cat_id", "1");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "Electronic2");
        row.put("Price", "5000");
        row.put("Quantity", "10");
        row.put("Cat_id", "1");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "Electronic3");
        row.put("Price", "10000");
        row.put("Quantity", "3");
        row.put("Cat_id", "1");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "Electronic4");
        row.put("Price", "9500");
        row.put("Quantity", "7");
        row.put("Cat_id", "1");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "Game1");
        row.put("Price", "500");
        row.put("Quantity", "20");
        row.put("Cat_id", "2");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "Game2");
        row.put("Price", "1000");
        row.put("Quantity", "9");
        row.put("Cat_id", "2");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "GO1");
        row.put("Price", "4500");
        row.put("Quantity", "12");
        row.put("Cat_id", "3");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "GO2");
        row.put("Price", "6800");
        row.put("Quantity", "19");
        row.put("Cat_id", "3");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "GO3");
        row.put("Price", "800");
        row.put("Quantity", "13");
        row.put("Cat_id", "3");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "SF1");
        row.put("Price", "7500");
        row.put("Quantity", "6");
        row.put("Cat_id", "4");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "SF2");
        row.put("Price", "6800");
        row.put("Quantity", "7");
        row.put("Cat_id", "4");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "SF3");
        row.put("Price", "800");
        row.put("Quantity", "10");
        row.put("Cat_id", "4");
        db.insert("products", null, row);
        row = new ContentValues();
        row.put("ProName", "SF3");
        row.put("Price", "3500");
        row.put("Quantity", "8");
        row.put("Cat_id", "4");
        db.insert("products", null, row);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists customers");
        db.execSQL("drop table if exists rememberme");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists categories");
        db.execSQL("drop table if exists products");
        db.execSQL("drop table if exists orderdetails");
        onCreate(db);
    }

    public void addCust(String naaame, String usernaaame, String passsword, String joob, String gendeer, String biiirthdate) {
        ContentValues newRow = new ContentValues();
        newRow.put("CustName", naaame);
        newRow.put("UserName", usernaaame);
        newRow.put("Password", passsword);
        newRow.put("Gender", gendeer);
        newRow.put("Birthdate", biiirthdate);
        newRow.put("Job", joob);
        ecommerces = getWritableDatabase();
        ecommerces.insert("customers", null, newRow);
        ecommerces.close();
    }

    public boolean ExistCustUserName(String useername) {   // username repeatnece check
        ecommerces = getReadableDatabase();
        Cursor cursor = ecommerces.query("customers", null, "UserName like ?", new String[]{useername}, null, null, null);
        ecommerces.close();
        return (cursor.getCount() >= 1);
    }

    public boolean ExistCustUsernameandPw(String usernaaame, String passsword) { //  login check
        ecommerces = getReadableDatabase();
        Cursor cursor = ecommerces.query("customers", null, "UserName like ? AND Password like ?", new String[]{usernaaame, passsword}, null, null, null);

        return (cursor.getCount() == 1);
    }


    public Cursor fetchAllCust() {  // login view customers
        ecommerces = getReadableDatabase();
        String[] rowDetails = {"CustName", "UserName", "Password", "Gender", "Birthdate", "Job"};
        Cursor curs = ecommerces.query("customers", rowDetails, null, null, null, null, null);
        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }

    public Cursor fetchACustbyUserName(String useername) {
        ecommerces = getReadableDatabase();
        String[] rowDetails = {"CustName", "UserName", "Password", "Gender", "Birthdate", "Job", "CustID"};
        Cursor curs = ecommerces.query("customers", rowDetails, "UserName like ?", new String[]{useername}, null, null, null);
        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }

    public void InsertInRememberMe(String useername) {
        ecommerces = getReadableDatabase();
        Cursor cursor = ecommerces.query("rememberme", null, "User_Name like ?", new String[]{useername}, null, null, null);

        if (!(cursor.getCount() >= 1)) {
            ContentValues newRow = new ContentValues();
            newRow.put("User_Name", useername);
            ecommerces = getWritableDatabase();
            ecommerces.insert("rememberme", null, newRow);
        }
        ecommerces.close();
    }

    public Cursor fetchAllRememberMe() {
        ecommerces = getReadableDatabase();
        String[] rowDetails = {"CustName", "UserName", "Password", "Gender", "Birthdate", "Job"};
        //  Cursor curs = ecommerces.query("customers", rowDetails, null, null, null, null, null);
        Cursor curs = ecommerces.rawQuery("SELECT customers.UserName FROM customers, rememberme WHERE customers.UserName = rememberme.User_Name", null);

        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }

    public boolean UpdateCustPassword(String useername, String birthdaate, String newpassword) {
        Cursor cursor = fetchACustbyUserName(useername);
        if (cursor.getCount() < 1) {
            return false;
        } else {
            if (birthdaate.equals(cursor.getString(4))) {
                ContentValues newRow = new ContentValues();
                newRow.put("Password", newpassword);
                ecommerces = getWritableDatabase();
                ecommerces.update("customers", newRow, "UserName like ?", new String[]{useername});
                ecommerces.close();
                return true;
            }
        }
        return false;

    }

    public Cursor fetchAllCateg() {  // view categories
        ecommerces = getReadableDatabase();
        String[] rowDetails = {"CatName"};
        Cursor curs = ecommerces.query("categories", rowDetails, null, null, null, null, null);
        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }

    public Cursor fetchAProdbyCateg(String cateeg) {  // view products
        ecommerces = getReadableDatabase();
        Cursor curs = ecommerces.rawQuery("SELECT products.ProName, products.Price, products.Quantity FROM products, categories WHERE products.Cat_id = categories.CatID AND categories.CatName = ?", new String[]{cateeg});
        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }

    public Cursor fetchAProdbyName(String productname) {
        ecommerces = getReadableDatabase();
        String[] rowDetails = {"ProName", "Price", "Quantity", "Cat_id", "ProID"};
        Cursor curs = ecommerces.query("products", rowDetails, "ProName like ?", new String[]{productname}, null, null, null);
        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }

    public void AddAnOrder(String orddate, String customerid, String address) {
        ContentValues newRow = new ContentValues();
        newRow.put("OrdDate", orddate);
        newRow.put("Cust_id", customerid);
        newRow.put("Address", address);
        ecommerces = getWritableDatabase();
        ecommerces.insert("orders", null, newRow);
        ecommerces.close();
    }

    public Cursor fetchAnOrderbyDate(String orderdate) {
        ecommerces = getReadableDatabase();
        Cursor curs = ecommerces.query("orders", new String[]{"OrderID"}, "OrdDate like ?", new String[]{orderdate}, null, null, null);
        curs.moveToFirst();
        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }


    public void AddAnOrderDetails(String orddate, String pro_id, String quantity) {
        ContentValues newRow = new ContentValues();
        newRow.put("Quantity", quantity);
        newRow.put("Pro_id", pro_id);
        String ordeer_id = fetchAnOrderbyDate(orddate).getString(0);
        newRow.put("Ord_id", ordeer_id);
        ecommerces = getWritableDatabase();
        ecommerces.insert("orderdetails", null, newRow);
        ecommerces.close();
    }

    public Cursor fetchCartbyUserName(String customeeer_id) {
        ecommerces = getReadableDatabase();
        Cursor curs = ecommerces.rawQuery("SELECT products.ProName, products.Price, orderdetails.Quantity, orders.OrdDate, orders.Address "
                + "FROM orders, products, orderdetails "
                + "WHERE orders.OrderID = orderdetails.Ord_id AND products.ProID = orderdetails.Pro_id AND orders.Cust_id = ?", new String[]{customeeer_id});
        if (curs != null) curs.moveToFirst();
        ecommerces.close();
        return curs;
    }

    public void DeleteFromCart(String orderdate, String prodname) {
        String prodid = fetchAProdbyName(prodname).getString(4);
        String orderid = fetchAnOrderbyDate(orderdate).getString(0);

        ecommerces = getWritableDatabase();
        ecommerces.delete("orderdetails", "Ord_id like ? AND Pro_id like ?", new String[]{orderid, prodid});
        ecommerces.delete("orders", "OrderID like ?", new String[]{orderid});
        ecommerces.close();

    }

    public void CompeteAllOrdersForCustomer(String custID, List<String> productnames, List<String> productquan) {
        ecommerces = getWritableDatabase();
        for (int i = 0; i < productnames.size(); ++i) {
            ContentValues newRow = new ContentValues();
            String old_quan = fetchAProdbyName(productnames.get(i)).getString(2);
            String new_quan = String.valueOf(Integer.parseInt(old_quan) - Integer.parseInt(productquan.get(i)));
            newRow.put("Quantity", new_quan);
            ecommerces = getWritableDatabase();
            ecommerces.update("products", newRow, "ProName like ?", new String[]{productnames.get(i)});
        }
        ecommerces.delete("orders", "Cust_id like ?", new String[]{custID});
        ecommerces.close();


    }

}//Quantity integer, Ord_id integer NOT NULL, Pro_id
