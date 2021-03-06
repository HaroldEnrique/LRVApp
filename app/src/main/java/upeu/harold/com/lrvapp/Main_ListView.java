package upeu.harold.com.lrvapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

import upeu.harold.com.lrvapp.db.DBHelper;
import upeu.harold.com.lrvapp.dto.Student;

public class Main_ListView extends AppCompatActivity {


    EditText edtName, edtPhone, edtAddress, edtMajor, edtSemester;
    Button btnAdd, btnList;
    ImageView mImageView;


    final int REQUEST_CODE_GALLERY = 999;

    public static DBHelper mdbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_listview);

        edtName =  findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtMajor = findViewById(R.id.edtMajor);
        edtSemester = findViewById(R.id.edtSemester);
        edtPhone = findViewById(R.id.edtPhone);
        btnAdd = findViewById(R.id.btnAdd);
        btnList = findViewById(R.id.btnList);

        mImageView = (ImageView) findViewById(R.id.imageView);


        //mdbhelper = new DBHelper(this, "students.sqlite",null, 1);
        //System.out.println("se creo la base de datos");

        //String tabla = "CREATE TABLE IF NOT EXISTS STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, ESCUELA TEXT,SEMESTER TEXT" +
         //       ", ADDRESS TEXT, PHONE TEXT,  IMAGE BLOB)";
        //mdbhelper.queryData(tabla);
       // System.out.println("Creando las tablas");


        //select image by onclick
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //read permission to select image from gallery
                //runtime permision for devices android  6.0 and above
                //ActivityCompat.requestPermissions(
                  //      Main_ListView.this,
                    //    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                      //  REQUEST_CODE_GALLERY
                //);
                CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(Main_ListView.this);
          }
        });


        //add a record to sqlite
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    String mname = edtName.getText().toString().trim();
                    String mmajor = edtMajor.getText().toString().trim();
                    String msemest =  edtSemester.getText().toString().trim();
                    String maddr = edtAddress.getText().toString().trim();
                    String mphone = edtPhone.getText().toString().trim();


                    DBHelper db = new DBHelper(getApplicationContext());
                    //getActivity().getBaseContext() p---para fragments

                    System.out.println("a punto de insertar " + mname + " , " + mmajor+ " , " +msemest+ " , " +maddr+ " , " + mphone );
                    //mdbhelper.insertStudent(
                      //     mname, mmajor, msemest, maddr, mphone,
                     //       imageViewToByte(mImageView)
                    //);

                    Student student = new Student(mname, mmajor, msemest,maddr, mphone, imageViewToByte(mImageView));
                    db.addStudent(student);

                    Toast.makeText(Main_ListView.this,"Added successfully", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtMajor.setText("");
                    edtSemester.setText("");
                    edtAddress.setText("");
                    edtPhone.setText("");
                    mImageView.setImageResource(R.drawable.addphoto);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        //show records
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_ListView.this, StudentListView.class));

            }
        });


    }

    private static byte[] imageViewToByte(ImageView mImageView) {

        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //GALLEY INTENT
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,REQUEST_CODE_GALLERY);

            }else {
                Toast.makeText(this, "No cuenta con permisos para el acceso de archivos locales", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageView) findViewById(R.id.imageView)).setImageURI(result.getUri());
                Toast.makeText(
                        this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG)
                        .show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }

       // if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
       //     Uri imageUri = data.getData();
        //    CropImage.activity(imageUri)
         //           .setGuidelines(CropImageView.Guidelines.ON) //habilita guia de imagenes
          //          .setAspectRatio(1,1) //imagen sera cuadrada
        //            .start(this);
       // }if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
         //   CropImage.ActivityResult result = CropImage.getActivityResult(data);
           // if(resultCode == RESULT_OK){
            //    Uri resultUri = result.getUri();

                //enviar image escogida de la galeria a la vista de imagen
              //  System.out.println("a punto de setear la imagen");
              //  mImageView.setImageURI(resultUri);
           // }
           // else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
             //   System.out.println("Error ...... error,,,,, ");
               // Exception error = result.getError();

            //}

        //}
        //super.onActivityResult(requestCode, resultCode, data);

    }
}
