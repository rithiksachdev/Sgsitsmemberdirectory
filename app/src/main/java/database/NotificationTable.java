package database;

import android.database.sqlite.SQLiteDatabase;

public class NotificationTable {
    private String tableName = "NotificationTable";
    private String title="title";
    private String domain="domain";
    private String summary="summary";
    private String startdate="startdate";
    private String enddate="enddate";
    private String description="description";
    private String mail="mail";

    public NotificationTable()
    {

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public NotificationTable(String title, String domain, String summary, String startdate, String enddate, String description, String mail) {
        this.title = title;
        this.domain = domain;
        this.summary = summary;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.mail = mail;
    }

    public NotificationTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+tableName+"("+title+" VARCHAR(20),"+domain+" VARCHAR(30),"+summary+" VARCHAR(80),"+startdate+" VARCHAR(20),"+enddate+" VARCHAR(20),"+description+" VARCHAR(500),"+mail+" VARCHAR(30));");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
