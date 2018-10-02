package upeu.harold.com.lrvapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import upeu.harold.com.lrvapp.db.DBHelper;
import upeu.harold.com.lrvapp.dto.Student;

public class StudentListView extends AppCompatActivity {



    ListView mlistView;
    ArrayList<Student> mList;
    StudentListAdapter madapter = null;

    List<Student> students;
    //ArrayList<Student> imageArry = new ArrayList<Student>();
    ImageView imageViewIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_view);


        mlistView = findViewById(R.id.listViewWrap);
        mList = new ArrayList<Student>();
        //madapter = new StudentListAdapter(this, R.layout.row_listview, mList);
       // mlistView.setAdapter(madapter);

        DBHelper db = new DBHelper(getApplicationContext());
        students = db.getAllStudents();
        for (Student cn : students) {
           // String log = "ID:" + cn.getID() + " Name: " + cn.getName()
             //       + " ,Image: " + cn.getImage();

            // Writing Contacts to log
           // Log.d("Result: ", log);
            //add contacts data in arrayList
            mList.add(cn);

        }
        madapter = new StudentListAdapter(this, R.layout.row_listview, mList);
        //ListView dataList = (ListView) findViewById(R.id.list);
        mlistView.setAdapter(madapter);

        if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this, "No student found...", Toast.LENGTH_SHORT).show();
        }






        //get all data from sqlite
        //Cursor cursor = Main_ListView.mdbhelper.getData("SELECT * FROM STUDENT");
       // mList.clear();
        //while (cursor.moveToNext()){
            //int id = cursor.getInt(1);
         //   String name = cursor.getString(1);
         //   String major = cursor.getString(2);
         //   String phone = cursor.getString(5);
         //   byte[] image  = cursor.getBlob(6);
            //add to list
         //   mList.add(new Student(name, major, phone, image));
        //}
        //madapter.notifyDataSetChanged();
        //if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
        //    Toast.makeText(this, "No student found...", Toast.LENGTH_SHORT).show();
  //      }
//
    //    mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      //      @Override
        //    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
          //      return false;
           // }


       // });
    }

}





