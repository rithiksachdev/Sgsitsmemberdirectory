package database;

import android.database.sqlite.SQLiteDatabase;

import java.security.PrivateKey;

public class EducationDetailsTable {
    private String tableName="EducationDetailsTable";
    private String institutename="institutename";
    private String board="board";
    private String result="result";
    private String startdate="startdate";
    private String enddate="enddate";
    private String degree="degree";
    private String mail="mail";
    public EducationDetailsTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+tableName+"("+institutename+" VARCHAR(40), "+board+" VARCHAR(50) NOT NULL, "+result+" VARCHAR(10) NOT NULL, "+startdate+" VARCHAR(10) NOT NULL, "+enddate+" VARCHAR(10) NOT NULL,"+degree+" VARCHAR(40), "+mail+" VARCHAR(40));");
    }

    public EducationDetailsTable()
    {

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public EducationDetailsTable(String institutename, String board, String result, String startdate, String enddate, String degree, String mail) {
        this.institutename = institutename;
        this.board = board;
        this.result = result;
        this.startdate = startdate;
        this.enddate = enddate;
        this.degree = degree;
        this.mail=mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInstitutename() {
        return institutename;
    }

    public void setInstitutename(String institutename) {
        this.institutename = institutename;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
