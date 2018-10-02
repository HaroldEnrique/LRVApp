package upeu.harold.com.lrvapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import upeu.harold.com.lrvapp.dto.Student;

public class StudentListAdapter extends BaseAdapter {



    private Context context;
    private int layout;
    private ArrayList<Student> studentList;

    public StudentListAdapter(Context context, int layout, ArrayList<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtname, txtmajor, txtphone;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();


        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtname = row.findViewById(R.id.txtname);
            holder.txtmajor = row.findViewById(R.id.txtmajor);
            holder.txtphone = row.findViewById(R.id.txtphone);
            holder.imageView = row.findViewById(R.id.imgicon);
            row.setTag(holder);
        }else{
            holder = (ViewHolder)row.getTag();
        }


        Student student = studentList.get(i);
        holder.txtname.setText(student.getName());
        holder.txtmajor.setText(student.getEscuela());
        holder.txtphone.setText(student.getPhone());

        byte[] studentImage = student.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(studentImage, 0, studentImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
