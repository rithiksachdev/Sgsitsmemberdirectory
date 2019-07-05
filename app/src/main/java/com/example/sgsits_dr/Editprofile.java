package com.example.sgsits_dr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import database.DatabaseHelper;
import database.ImageTable;
import database.UserTable;
import picker.GalleryPickerUtil;

public class Editprofile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText fname;
    private EditText lname;
    private EditText mail;
    private RadioButton ml;
    private RadioButton fml;
    private String path;
    private String pt;
    private EditText contact;
    private EditText pincode;
    private String emmail;
    private String gendersex;
    private RadioButton radioButton1;
    private DatabaseHelper databaseHelper;
    private RadioGroup radiogen;
    private AutoCompleteTextView citydetail;
    private TextView date;
    private boolean flag =false,flag1=false;
    private ImageView imageView;
    private String realPath;
    private ImageView imageViewset;
    private ImageView imageViewsetcam;
    SharePreferenceData sharePreferenceData;
    SharePreferenceData sharePreferenceData1;
    private File mImageFile;
    private byte[] img;
    private String gen;
    private AutoCompleteTextView autoCompleteTextView;
    private Uri mImageUri;
    int PERMISSIONS_MULTIPLE_REQUEST=100;
    String[] stringsPermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
};
    private String[] branch= new String[]{
            "Computer Science Engineering","Electrical Engineering","Biomedical Engineering","Information Technology Engineering"
            ,"Electronics and Instrumentation Engineering","Mechanical Engineering","Industrial Production Engineering"
    };
    private String[] city=new String[]{
            "Indore","Bhopal","Mumbai","Delhi","Jaipur","Banglore"
    };

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dat=year+"-"+(month+1)+"-"+dayOfMonth;
        date.setText(dat);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        mail=findViewById(R.id.email);
        contact=findViewById(R.id.mobileno);
        pincode=findViewById(R.id.pinc);
        ml=findViewById(R.id.male);
        fml=findViewById(R.id.female);
        imageView=findViewById(R.id.imgedt);
        imageViewsetcam=findViewById(R.id.imgcam);
        imageViewset=findViewById(R.id.imggal);
        autoCompleteTextView=findViewById(R.id.branch);
        citydetail=findViewById(R.id.city);
        date=findViewById(R.id.dateofbirth);
        radiogen=findViewById(R.id.radiogen);
        radioButton1=findViewById(radiogen.getCheckedRadioButtonId());
        databaseHelper=DatabaseHelper.getInstance(this);
        sharePreferenceData1=new SharePreferenceData();
        findViewById(R.id.calenderic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogbox();
            }
        });
        sharePreferenceData=new SharePreferenceData();
        emmail=sharePreferenceData.getSharedPrefString(this,"regmail","");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,branch);
        autoCompleteTextView.setAdapter(adapter);
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,city);
        citydetail.setAdapter(adapter1);
        imageViewset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(checkPermission())
                    {
                        galleryImageCall();
                    }
                    else
                    {
                        galleryImageCall();
                    }
                }
            }
        });
        imageViewsetcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkPermission())
                    {
                        cameraImageCall();
                    }
                    else
                    {
                        cameraImageCall();
                    }
                }
            }
        });

        try{
                Cursor cursor = databaseHelper.getselectImageFromUser(emmail);
                cursor.moveToFirst();
                do {
                    pt = cursor.getString(0);
                } while (cursor.moveToNext());
                Bitmap myBitmap = BitmapFactory.decodeFile(pt);
                imageView.setImageBitmap(myBitmap);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try {
                Cursor cursor = databaseHelper.getselectDataFromUser(emmail);
                cursor.moveToFirst();
                do {
                    fname.setText(cursor.getString(0));
                    lname.setText(cursor.getString(1));
                    date.setText(cursor.getString(9));
                    mail.setText(cursor.getString(3));
                    contact.setText(cursor.getString(6));
                    autoCompleteTextView.setText(cursor.getString(2));
                    citydetail.setText(cursor.getString(7));
                    pincode.setText(cursor.getString(8));
                    gendersex=cursor.getString(10);
                } while (cursor.moveToNext());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            if ("male".equalsIgnoreCase(gendersex)) {
                radiogen.check(ml.getId());
            }
            else {
                radiogen.check(fml.getId());
            }
        findViewById(R.id.nxtimg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().contentEquals("")) {
                    fname.setError("Please enter First Name");
                    fname.requestFocus();
                }
                else if(lname.getText().toString().contentEquals(""))
                {
                    lname.setError("Please enter Last Name");
                    lname.requestFocus();
                }
                else if(date.getText().toString().contentEquals(""))
                {
                    date.setError("Please enter Date");
                    date.requestFocus();
                }
                else if(mail.getText().toString().contentEquals(""))
                {
                    mail.setError("Please enter E-mail Address");
                    mail.requestFocus();
                }
                else if(contact.getText().toString().contentEquals(""))
                {
                    contact.setError("Please enter Contact Number");
                    contact.requestFocus();
                }
                else if(autoCompleteTextView.getText().toString().contentEquals(""))
                {
                    autoCompleteTextView.setError("Please enter Branch");
                    autoCompleteTextView.requestFocus();
                }
                else if(citydetail.getText().toString().contentEquals(""))
                {
                    citydetail.setError("Please enter City");
                    citydetail.requestFocus();
                }
                else if(pincode.getText().toString().contentEquals(""))
                {
                    pincode.setError("Please enter Pin-Code");
                    pincode.requestFocus();
                }
                else if(radiogen.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(Editprofile.this,"Please Select a Gender",Toast.LENGTH_LONG).show();
                }
                else
                {
                    flag=true;
                    flag1=true;
                }
                if (v.getId() == R.id.nxtimg && flag && flag1) {
                    try {
                        databaseHelper.updateselectDataFromUser(fname.getText().toString(), lname.getText().toString(), date.getText().toString(), mail.getText().toString(), contact.getText().toString(), autoCompleteTextView.getText().toString(), citydetail.getText().toString(), pincode.getText().toString(), gendersex, emmail);
                        Cursor cursor1 = databaseHelper.getselectDataFromUser(emmail);
                        cursor1.moveToFirst();
                        do {
                            fname.setText(cursor1.getString(0));
                            lname.setText(cursor1.getString(1));
                            String ab=cursor1.getString(9);
                            date.setText(cursor1.getString(9));
                            mail.setText(cursor1.getString(3));
                            contact.setText(cursor1.getString(6));
                            autoCompleteTextView.setText(cursor1.getString(2));
                            citydetail.setText(cursor1.getString(7));
                            pincode.setText(cursor1.getString(8));
                            gen=cursor1.getString(10);
                        } while (cursor1.moveToNext());
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Editprofile.this, EditEducationDetail.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void galleryImageCall()
    {
        GalleryPickerUtil.launchGallery(Editprofile.this);
    }

    private void cameraImageCall()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Build.VERSION.SDK_INT>23) {
            mImageFile = GalleryPickerUtil.createTeampFile();
            mImageUri = FileProvider.getUriForFile(
                    Editprofile.this,
                    getApplicationContext().getPackageName() + "my.package.name.provider",
                    mImageFile
            );
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(
                    android.provider.MediaStore.EXTRA_OUTPUT,
                    mImageUri
            );
            startActivityForResult(intent, GalleryPickerUtil.CAPTURE_PHOTO);
        }
        else
        {
            startActivityForResult(intent,GalleryPickerUtil.CAPTURE_PHOTO);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) throws NullPointerException {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GalleryPickerUtil.GALLERY_PHOTO){
            if(resultCode== Activity.RESULT_OK){
                Uri uri=data.getData();
                realPath = ImageFilePath.getPath(this, data.getData());
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                    imageView.setVisibility(View.VISIBLE);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                path=realPath;
            }
        }else if(requestCode==GalleryPickerUtil.CAPTURE_PHOTO){
            if(resultCode==Activity.RESULT_OK){
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(mImageFile.getAbsolutePath(),bmOptions);
                bitmap = Bitmap.createScaledBitmap(bitmap,100,100,true);
                imageView.setImageBitmap(bitmap);
                path=mImageFile.getAbsolutePath().toString();
            }
        }
        ImageTable input=new ImageTable(path,emmail);
        if(databaseHelper.addimg(input)) {
            try {
                Cursor cursor = databaseHelper.getselectImageFromUser(emmail);
                cursor.moveToFirst();
                do {
                    pt = cursor.getString(0);
                } while (cursor.moveToNext());
                Bitmap myBitmap = BitmapFactory.decodeFile(pt);
                imageView.setImageBitmap(myBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Data Stored Image", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Data Not Stored",Toast.LENGTH_LONG).show();
        }
    }



    @RequiresApi(api=Build.VERSION_CODES.M)
    private  boolean checkPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)+
                ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)+
                ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
         != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(
                                    this,
                                    Manifest.permission.CAMERA
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                    this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
            )
            {
                Snackbar.make(this.findViewById(android.R.id.content),"Please Grant Permissions to Application",
                        Snackbar.LENGTH_INDEFINITE).setAction(
                        "ENABLE", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestPermissions(
                                        stringsPermission,PERMISSIONS_MULTIPLE_REQUEST
                                );
                            }
                        }
                ).show();
            }
            else
            {
                requestPermissions(
                        stringsPermission,PERMISSIONS_MULTIPLE_REQUEST
                );
            }
        }
        else
        {
            return true;
        }

        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
            boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            boolean writeExternalFile = grantResults[2] == PackageManager.PERMISSION_GRANTED;

            if (cameraPermission && readExternalFile && writeExternalFile) {
               /* if (isFromCamera) {
                    cameraImageCall(activity!!)
                } else if (isFromGallery) {
                    galleryImageCall(activity!!)
                }*/
            } else {
                Snackbar.make(
                        getWindow().getDecorView(),
                        "Please Grant Permissions to work with camera and gallery.",
                        Snackbar.LENGTH_INDEFINITE
                ).setAction("ENABLE",
                        new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(View view) {
                                requestPermissions(
                                        stringsPermission
                                        ,
                                        PERMISSIONS_MULTIPLE_REQUEST
                                );
                            }

                        }).show();
            }
        }
    }

    public void showDatePickerDialogbox()
    {
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
