package upeu.harold.com.lrvapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import upeu.harold.com.lrvapp.dto.Student;

public class DBHelper extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "students.db";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void queryData(String sql){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }





    public void insertStudent(String name,String escuela, String semester, String address, String phone, byte[] image){


        SQLiteDatabase database = getWritableDatabase();
        //query to insert record in database table
        String sql = "INSERT INTO STUDENT VALUES(NULL, ?, ?, ?, ?, ?, ?)"; //where "RECORD" is table name in database we will create in mainActivity

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, escuela);
        statement.bindString(3, semester);
        statement.bindString(4, address);
        statement.bindString(5, phone);
        statement.bindBlob(6, image);

        statement.executeInsert();
                //    return noteModel;

    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    // Getting All Contacts
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM STUDENT ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String major = cursor.getString(2);
                String phone = cursor.getString(5);
                byte[] image  = cursor.getBlob(6);
                Student student = new Student(id, name, major, phone, image);
                // Adding contact to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return studentList;

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //queryData(tabla);

       // mdbhelper = new DBHelper(this, "students.sqlite",null, 1);
        System.out.println("se creo la base de datos");

        String tabla = "CREATE TABLE STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, ESCUELA TEXT,SEMESTER TEXT, ADDRESS TEXT, PHONE TEXT,  IMAGE BLOB)";
       // mdbhelper.queryData(tabla);
        sqLiteDatabase.execSQL(tabla);
        System.out.println("Creando las tablas");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //queryData("DROP TABLE IF EXITS STUDENT");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT");

        // Create tables again
        onCreate(sqLiteDatabase);
    }







    public// Adding new contact
    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", student.getName());
        values.put("ESCUELA", student.getEscuela());
        values.put("SEMESTER", student.getSemester());
        values.put("ADDRESS", student.getAddress());
        values.put("PHONE", student.getPhone());
        values.put("IMAGE", student.getImage());

        // Inserting Row
        db.insert("STUDENT", null, values);
        db.close(); // Closing database connection
    }



}
