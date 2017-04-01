package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class EventSelection extends AppCompatActivity implements View.OnClickListener {

    Context context;
    int count = 0;
    String eventnames[] = new String[25];
    String selItems[];
    Button bSelectedEvents;
    final int TIME_BACK = 3;
    int k = 0;
    ImageButton bUserData;
    ArrayList<String> selectedEvents = new ArrayList<>();
    String ID, name, email, phone, gender, tsize, password, rollno, event1, event2, event3, event4, fees;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_event_selection);
        initialize();
        Bundle bundle= getIntent().getExtras();
        ID = bundle.getString("ID");
        name = bundle.getString("name");
        phone = bundle.getString("phone");
        rollno = bundle.getString("rollno");
        email = bundle.getString("email");
        gender = bundle.getString("gender");
        tsize = bundle.getString("tsize");
        password = bundle.getString("password");
        event1 = bundle.getString("event1");
        event2 = bundle.getString("event2");
        event3 = bundle.getString("event3");
        event4 = bundle.getString("event4");
        fees = bundle.getString("fees");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                popUpUser();
            }
        },100);
        if(!fees.contains("Not")){
            bSelectedEvents.setText("Paid Can't change");
            bSelectedEvents.setEnabled(false);
        }

//         = bundle.getString("");
//         = bundle.getString("");
        setTitle("Events Selection");

        context = getApplicationContext();
        final ListView lvEvents = (ListView) findViewById(R.id.lv_eventselection);
        lvEvents.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        eventnames = getResources().getStringArray(R.array.eventselection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.content_each_event_selection,R.id.ctvEventName,eventnames);
//        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.content_each_event_selection,R.id.ctvEventName,eventnames);
        lvEvents.setAdapter(adapter);
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                Log.e("sel item", selectedItem);
                if(selectedEvents.contains(selectedItem)) {
//                    view.setBackgroundColor(Color.RED);
                    selectedEvents.remove(selectedItem); //remove deselected item from the list of selected items
                }
                else if(selectedEvents.size() == 4){
                    Toast.makeText(EventSelection.this,"Only 4 events Allowed!!",Toast.LENGTH_LONG).show();
                    lvEvents.setItemChecked(position,false);
                }
                else {
//                    view.setBackgroundColor(Color.GREEN);
                    selectedEvents.add(selectedItem); //add selected item to the list of selected items
                }

            }
        });

        bSelectedEvents.setOnClickListener(this);
        bUserData.setOnClickListener(this);

    }

    private void initialize() {
        bSelectedEvents = (Button) findViewById(R.id.btSubmitEvent);
        bUserData = (ImageButton) findViewById(R.id.ibUserEventSelection);
    }

    public void getSelectedEvents(){
        count=0;

        for(String item:selectedEvents) {
            count++;
        }
        selItems = new String[count];
        count=0;
        for(String item:selectedEvents){
                selItems[count++]=item;
        }
        Arrays.sort(selItems);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btSubmitEvent:
                getSelectedEvents();
                if(count == 0){
                    Toast tos = Toast.makeText(context,"No item Selected! :(",Toast.LENGTH_SHORT);
                    tos.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL,0,0);
                    tos.show();
                }
                else{

                    //Done submit Page selItem have Data
                    Toast.makeText(this,count+" Events Selected :)",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EventSelection.this,SendJasonEventsSelection.class);
                    intent.putExtra("ID",ID);
                    intent.putExtra("rollno",rollno);
                    intent.putExtra("name",name);
                    intent.putExtra("count",count);
                    intent.putExtra("eventselected",selItems);
                    startActivity(intent);
                }
                break;
            case R.id.ibUserEventSelection:
                popUpUser();
                break;
        }
    }

    private void popUpUser() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    RelativeLayout viewGroup = (RelativeLayout) getActivity().findViewById(R.id.popup);
        View popupView = inflater.inflate(R.layout.popup_user_data, null);
//        final PopupWindow popup = new PopupWindow(inflater.inflate(
//                R.layout.popup_user_data, null, false), 200, 265, true);

        final PopupWindow popup = new PopupWindow(this);
        popup.setContentView(popupView);
        popup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);


        if(Build.VERSION.SDK_INT>=21){
            popup.setElevation(5.0f);
        }
        TextView tvID, tvName, tvRollno, tvPhone,tvEmail, tvTsize, tvEvent1, tvEvent2, tvEvent3, tvEvent4,tvFees;

        tvID = (TextView) popupView.findViewById(R.id.tvUserId);
        tvName = (TextView) popupView.findViewById(R.id.tvUserName);
        tvRollno = (TextView) popupView.findViewById(R.id.tvUserRollno);
        tvPhone = (TextView) popupView.findViewById(R.id.tvUserPhone);
        tvEmail = (TextView) popupView.findViewById(R.id.tvUserEmail);
        tvTsize = (TextView) popupView.findViewById(R.id.tvUserTSize);
        tvEvent1 = (TextView) popupView.findViewById(R.id.tvUserEvent1);
        tvEvent2 = (TextView) popupView.findViewById(R.id.tvUserEvent2);
        tvEvent3 = (TextView) popupView.findViewById(R.id.tvUserEvent3);
        tvEvent4 = (TextView) popupView.findViewById(R.id.tvUserEvent4);
        tvFees = (TextView) popupView.findViewById(R.id.tvUserFees);

        tvID.setText("ID: "+ID);
        tvName.setText("Name: "+name);
        tvRollno.setText("Roll No: "+rollno);
        Log.e("Roll: ",rollno);
        tvPhone.setText("Phone No.: "+phone);
        tvEmail.setText("Email: "+email);
        tvTsize.setText("T-Size: "+tsize);
        String canDo = "Can ADD Event!";
        if(!event1.equals("null")) {
            tvEvent1.setText(event1);
            if (fees.contains("Not")){
                tvFees.setText("Pay: " + fees.charAt(0)+fees.charAt(1)+fees.charAt(2));
//                tvEvent1.setText(canDo);
                tvEvent2.setText(canDo);
                tvEvent3.setText(canDo);
                tvEvent4.setText(canDo);
            }
        else {
                tvFees.setText("Already Paid: "+fees.charAt(0)+fees.charAt(1)+fees.charAt(2));
            }
            if(!event2.equals("null")){
                tvEvent2.setText(event2);
                if(!event3.equals("null")){
                    tvEvent3.setText(event3);
                    if(!event4.equals("null")){
                        tvEvent4.setText(event4);
                    }
                }
            }
        }


        final LinearLayout lly = (LinearLayout) findViewById(R.id.llEventSelection);
        popup.showAtLocation(lly, Gravity.CENTER,0,0);
    }

    @Override
    public void onBackPressed() {
        if(k==0) {
            super.onBackPressed();
        } else if(k == TIME_BACK-1){
            Toast.makeText(this,"Select Event First!!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Relogin Required!!",Toast.LENGTH_LONG).show();
            k--;
        }
    }
}
