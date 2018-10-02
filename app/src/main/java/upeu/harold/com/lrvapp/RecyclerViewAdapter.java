package upeu.harold.com.lrvapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import upeu.harold.com.lrvapp.dto.Student;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mcontext;
    List<Student> mData;


    public RecyclerViewAdapter(Context mcontext, List<Student> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(mcontext).inflate(R.layout.contact_item, parent, false);
        MyViewHolder vholder = new MyViewHolder(v);
        return vholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_name.setText(mData.get(i).getName().toString());
        myViewHolder.tv_phone.setText(mData.get(i).getPhone().toString());
        //myViewHolder.image.setImageResource(mData.get(i).getImage());
        byte[] studentImage = mData.get(i).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(studentImage, 0, studentImage.length);
        myViewHolder.image.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_phone;
        private ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.contact_name);
            tv_phone = (TextView) itemView.findViewById(R.id.phone_contact);
            image = (ImageView) itemView.findViewById(R.id.img_contact);

        }
    }

}
