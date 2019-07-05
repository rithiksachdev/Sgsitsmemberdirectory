package database;

import android.database.sqlite.SQLiteDatabase;

import static java.sql.Types.INTEGER;

public class UserTable {
    private String tableName="UserTable";
    private String fname="fname";
    private String lname="lname";
    private String branch="branch";
    private String email="email";
    private String password="password";
    private String rollno="rollno";
    private String contactno="contactno";
    private String city="city";
    private String pincode="pincode";
    private String dateofbirth="dateofbirth";
    private String gender="gender";


    public UserTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+tableName+"("+fname+" VARCHAR(30), "+lname+" VARCHAR(30), "+branch+" VARCHAR(40), "+email+" VARCHAR(40) PRIMARY KEY, "+password+" VARCHAR(30), "+rollno+" VARCHAR(15), "+contactno+" INTEGER, "+city+" VARCHAR(30), "+pincode+" INTEGER, "+dateofbirth+" VARCHAR(30), "+gender+" VARCHAR(10));");
    }

    public UserTable(String fname, String lname, String branch, String email, String password, String rollno, String contactno, String city, String pincode, String dateofbirth, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.branch = branch;
        this.email = email;
        this.password = password;
        this.rollno = rollno;
        this.contactno = contactno;
        this.city = city;
        this.pincode = pincode;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTable(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserTable()
    {

    }
}
