package mytwistedidea.wordpress.com.prayukti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IamRegistered extends Fragment {


    DatabaseHelper MyHelpers;

    View view;
    TextView tvRegStudentHeader;
    ArrayList<String> regstudentallAL;

    String uniqid[] = new String[50];
    String name[] = new String[50];
    String phone[] = new String[50];
    String email[] = new String[50];
    String tsize[] = new String[50];
    String rollno[] = new String[50];
//    String [] = new String[50];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_iam_registered,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Registrated Students");
        this.view = view;
        tvRegStudentHeader = (TextView) view.findViewById(R.id.tv_regstudentheader);
        com.github.clans.fab.FloatingActionButton fab1 = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fab_addregister);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment registration = new Registration();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_mainis,registration);
                ft.commit();
            }
        });

        populateRegStudent();
    }

    private void populateRegStudent() {
        MyHelpers = new DatabaseHelper(getActivity().getApplicationContext());
        regstudentallAL = MyHelpers.getAllRegStudent();

        int l =regstudentallAL.size();
        int j;
        int i=0;
        Log.e("aa",l+"");
        if(l == 5) {
            tvRegStudentHeader.setText("No one is Registered From Your Phone!");
            return;
        }
        tvRegStudentHeader.setText("I Registered From this Phone");
        for(i=0,j=0;i< l;){

            uniqid[j] = regstudentallAL.get(i++);
            if(i<l) {
                name[j] = regstudentallAL.get(i++);
                Log.e("data",name[j]);
            }if(i<l)
                phone[j] = regstudentallAL.get(i++);
            if(i<l)
                email[j] = regstudentallAL.get(i++);
            if(i<l)
                tsize[j] = regstudentallAL.get(i++);
            if(i<l) {
                rollno[j] = regstudentallAL.get(i++);
                Log.e("roll",rollno[j]);
            }
            j++;
        }
        Log.e("j=",""+j);
        final List<HashMap<String,String>> aList = new ArrayList<>();
        for(i = 0;i<j;i++) {
            Log.e("all",uniqid[i]+name[i]+rollno[i]);
        }
        for(i = 0;i<j;i++){

            HashMap<String,String> hashMap = new HashMap<>();

            hashMap.put("id","ID: "+uniqid[i]);
            hashMap.put("ne","Name: "+name[i]);
            hashMap.put("ro","Roll no: "+rollno[i]);
            aList.add(hashMap);

        }

        String[] from = {"ne","ro","id"};
        int to[] = {R.id.tv_nameregd,R.id.tv_rollregd,R.id.tv_uniqregd};

        SimpleAdapter simpleAdapterPeriod = new SimpleAdapter(getActivity().getApplicationContext(),aList,R.layout.content_each_reg_student,from,to);
        final ListView listView = (ListView) view.findViewById(R.id.lv_reg_student);
        listView.setAdapter(simpleAdapterPeriod);

        registerForContextMenu(listView);
    }

}
