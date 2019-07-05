package database;

import android.database.sqlite.SQLiteDatabase;

public class ImageTable {
    private String tablename="ImageTable";
    private String path="path";
    private String email="email";

    public ImageTable(String path, String email) {
        this.path = path;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ImageTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+tablename+"("+path+" VARCHAR(100),"+email+" VARCHAR(30));");
    }
    public ImageTable()
    {

    }
    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
