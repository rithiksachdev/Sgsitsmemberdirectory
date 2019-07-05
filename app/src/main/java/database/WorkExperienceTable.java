package database;

import android.database.sqlite.SQLiteDatabase;

public class WorkExperienceTable {
    private String tablename="WorkExperienceTable";
    private String companyname="companyname";
    private String joindate="joindate";
    private String position="position";
    private String enddate="enddate";
    private String email="email";

    public WorkExperienceTable()
    {}

    public WorkExperienceTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+tablename+"("+companyname+" VARCHAR(40),"+joindate+" VARCHAR(20),"+position+" VARCHAR(80),"+enddate+" VARCHAR(20),"+email+" VARCHAR(30));");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WorkExperienceTable(String companyname, String joindate, String position, String enddate, String email) {
        this.companyname = companyname;
        this.joindate = joindate;
        this.position = position;
        this.enddate = enddate;
        this.email = email;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
