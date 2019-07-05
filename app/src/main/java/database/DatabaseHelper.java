package database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sgsits_dr.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "memberdirectorydb";
    /*public static final String TABLE_NAME = "login";
    public static final String COL_1 = "e_mail";
    public static final String COL_2 = "password";
    */
    private SQLiteDatabase mywriteabledb;
    private static DatabaseHelper databaseHelper;
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new WorkExperienceTable(db);
        new EducationDetailsTable(db);
        new ImageTable(db);
        new NotificationTable(db);
        new UserTable(db);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            return databaseHelper = new DatabaseHelper(context);
        } else
            return databaseHelper;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           }

    public SQLiteDatabase getMyWriteabledatabse()
    {
        if(mywriteabledb==null || !mywriteabledb.isOpen())
        {
            mywriteabledb=this.getWritableDatabase();
        }
        return mywriteabledb;
    }
    public boolean addUserData(UserTable userTable) {
        try {
            UserTable keyUserName = new UserTable();
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(keyUserName.getFname(), userTable.getFname());
            contentValues.put(keyUserName.getLname(), userTable.getLname());
            contentValues.put(keyUserName.getBranch(), userTable.getBranch());
            contentValues.put(keyUserName.getEmail(), userTable.getEmail());
            contentValues.put(keyUserName.getPassword(), userTable.getPassword());
            contentValues.put(keyUserName.getRollno(), userTable.getRollno());
            contentValues.put(keyUserName.getContactno(), userTable.getContactno());
            contentValues.put(keyUserName.getCity(), userTable.getCity());
            contentValues.put(keyUserName.getPincode(), userTable.getPincode());
            contentValues.put(keyUserName.getDateofbirth(), userTable.getDateofbirth());
            contentValues.put(keyUserName.getGender(), userTable.getGender());
            sqLiteDatabase.insert(keyUserName.getTableName(), null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addNotificationData(NotificationTable notificationTable) {
        NotificationTable keyUserName = new NotificationTable();
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(keyUserName.getTitle(), notificationTable.getTitle());
            contentValues.put(keyUserName.getDomain(), notificationTable.getDomain());
            contentValues.put(keyUserName.getSummary(), notificationTable.getSummary());
            contentValues.put(keyUserName.getStartdate(), notificationTable.getStartdate());
            contentValues.put(keyUserName.getEnddate(), notificationTable.getEnddate());
            contentValues.put(keyUserName.getDescription(), notificationTable.getDescription());
            contentValues.put(keyUserName.getMail(),notificationTable.getMail());
            sqLiteDatabase.insert(keyUserName.getTableName(), null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addimg(ImageTable img)
    {
        ImageTable kb=new ImageTable();
        try{
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(kb.getPath(),img.getPath());
            contentValues.put(kb.getEmail(),img.getEmail());
            sqLiteDatabase.insert(kb.getTablename(),null,contentValues);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addEducationData(EducationDetailsTable educationDetailsTable)
    {
        EducationDetailsTable keyUser = new EducationDetailsTable();
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(String.valueOf(keyUser.getInstitutename()), educationDetailsTable.getInstitutename());
            contentValues.put(String.valueOf(keyUser.getBoard()), educationDetailsTable.getBoard());
            contentValues.put(String.valueOf(keyUser.getResult()), educationDetailsTable.getResult());
            contentValues.put(String.valueOf(keyUser.getStartdate()), educationDetailsTable.getStartdate());
            contentValues.put(String.valueOf(keyUser.getEnddate()), educationDetailsTable.getEnddate());
            contentValues.put(String.valueOf(keyUser.getDegree()), educationDetailsTable.getDegree());
            contentValues.put(String.valueOf(keyUser.getMail()), educationDetailsTable.getMail());
            sqLiteDatabase.insert(keyUser.getTableName(), null, contentValues);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addWorkData(WorkExperienceTable workExperienceTable)
    {
        WorkExperienceTable keyUser = new WorkExperienceTable();
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(String.valueOf(keyUser.getCompanyname()), workExperienceTable.getCompanyname());
            contentValues.put(String.valueOf(keyUser.getJoindate()), workExperienceTable.getJoindate());
            contentValues.put(String.valueOf(keyUser.getPosition()), workExperienceTable.getPosition());
            contentValues.put(String.valueOf(keyUser.getEnddate()), workExperienceTable.getEnddate());
            contentValues.put(String.valueOf(keyUser.getEmail()), workExperienceTable.getEmail());
            sqLiteDatabase.insert(keyUser.getTablename(), null, contentValues);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Cursor getDataFromUser()
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        UserTable userTable=new UserTable();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+userTable.getTableName(),null);
    }
    public Cursor getNotificationFromUser()
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        NotificationTable notificationTable=new NotificationTable();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+notificationTable.getTableName(),null);
    }

    public Cursor getselectDataFromUser(String mail)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String q = "SELECT * FROM UserTable WHERE email = " + "'" +mail+ "'" ;
        return sqLiteDatabase.rawQuery(q,null);
    }

    public Cursor getselectImageFromUser(String mail)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String q = "SELECT * FROM ImageTable WHERE email = " + "'" +mail+ "'" ;
        return sqLiteDatabase.rawQuery(q,null);
    }

    public Cursor getselectEDUDataFromUser(String mail)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String w = "SELECT * FROM EducationDetailsTable WHERE mail = " + "'" +mail+ "'" ;
        return sqLiteDatabase.rawQuery(w,null);
    }


    public Cursor getselectUserinfo(String mail)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String w = "SELECT email,dateofbirth FROM UserTable WHERE email = " + "'" +mail+ "'" ;
        return sqLiteDatabase.rawQuery(w,null);
    }

    public Cursor updateselectDataFromUser(String fname,String lname,String dateofbirth,String mail,String contactno,String branch,String city,String pin,String gender,String lmail)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE UserTable SET fname = ?, lname = ?, dateofbirth = ?, email = ?, contactno = ?, branch = ?, city = ?, pincode = ?, gender = ? WHERE email = " + "'"+lmail+"'",
                new String[] {
                        fname,lname,dateofbirth,mail,contactno,branch,city,pin,gender
                });
        return sqLiteDatabase.rawQuery("UPDATE UserTable SET fname = ?, lname = ?, dateofbirth = ?, email = ?, contactno = ?, branch = ?, city = ?, pincode = ?, gender = ? WHERE email = " + "'"+lmail+"'",
                new String[] {
                        fname,lname,dateofbirth,mail,contactno,branch,city,pin,gender
                });
    }

    public Cursor getselectCMPDataFromUser(String mail)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String z = "SELECT * FROM WorkExperienceTable WHERE email = " + "'" +mail+ "'" ;
        return sqLiteDatabase.rawQuery(z,null);
    }

    public Cursor updateselectCMPDataFromUser(String cmpname,String joindate,String position,String enddate,String mail)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE WorkExperienceTable SET companyname = ?, joindate = ?, position = ?, enddate = ? WHERE email = " + "'"+mail+"'",
                new String[] {
                        cmpname,joindate,position,enddate,mail
                });
        return sqLiteDatabase.rawQuery("UPDATE UserTable SET companyname = ?, joindate = ?, position = ?, enddate = ? WHERE email = " + "'"+mail+"'",
                new String[] {
                        cmpname,joindate,position,enddate,mail
                });
    }


    public void changepass(String mail,String pass)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE UserTable SET password = ? Where email = "+"'"+mail+"'",new String[]{
                pass
        });
    }

    public Cursor updateselectEduDataFromUser(String insname,String board,String result,String startdate,String enddate,String degree,String email)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE EducationDetailsTable SET institutename = ?, board = ?, result = ?, startdate = ?, enddate = ?, degree = ? WHERE mail = " + "'"+email+"'",
                new String[] {
                        insname,board,result,startdate,enddate,degree
                });
        return sqLiteDatabase.rawQuery("UPDATE EducationDetailsTable SET institutename = ?, board = ?, result = ?, startdate = ?, enddate = ?, degree = ? WHERE mail = " + "'"+email+"'",
                new String[] {
                        insname,board,result,startdate,enddate,degree
                });
    }


}
