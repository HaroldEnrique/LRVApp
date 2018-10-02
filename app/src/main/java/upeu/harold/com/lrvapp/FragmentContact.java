package upeu.harold.com.lrvapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import upeu.harold.com.lrvapp.db.DBHelper;
import upeu.harold.com.lrvapp.dto.Student;

public class FragmentContact extends Fragment{

    View v;
    private RecyclerView myreRecyclerView;
    private List<Student> lststudent;

    DBHelper db;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment,container, false);


        myreRecyclerView = (RecyclerView) v.findViewById(R.id.contact_recycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lststudent);
        myreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myreRecyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(getActivity().getBaseContext());

        lststudent = new ArrayList<>();
        lststudent = db.getAllStudents();
        //lstcontact.add(new Contact("Aaron Josias","(222) 265123512", R.drawable.round_img));
       // lstcontact.add(new Contact("Juan Gonzales","(222) 265123512", R.drawable.round_img));
       // lstcontact.add(new Contact("Julio Granados","(222) 265123512", R.drawable.round_img));
       // lstcontact.add(new Contact("Enrique Camarena","(222) 265123512", R.drawable.round_img));
       // lstcontact.add(new Contact("Flor Tapia","(222) 265123512", R.drawable.round_img));
        //lstcontact.add(new Contact("Pedro Ugarte","(222) 265123512", R.drawable.round_img));
        //lstcontact.add(new Contact("Caleb Num","(222) 265123512", R.drawable.round_img));
       // lstcontact.add(new Contact("Javier Tito","(222) 265123512", R.drawable.round_img));
       // lstcontact.add(new Contact("Kevin Bengoilea","(222) 265123512", R.drawable.round_img));
       // lstcontact.add(new Contact("Gerson Santana","(222) 265123512", R.drawable.round_img));

    }
}
