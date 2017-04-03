package mytwistedidea.wordpress.com.prayukti;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.HashMap;

public class EventSelection extends AppCompatActivity implements View.OnClickListener {

    Context context;
    int count = 0;
    String eventnames[] = new String[25];
    String selItems[];
    boolean flag = false;
//    Button bSelectedEvents;
    final int TIME_BACK = 3;
    int k = 0,incount = 0;
    ImageButton bUserData;
    Button bLockEvent;
    String lockedEvents[];
    ArrayList<String> selectedEvents = new ArrayList<>();
    String ID, name, email, phone, gender, tsize, password, rollno, fees,locks = "no",events;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_event_selection);
        initialize();
        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("ID");
        name = bundle.getString("name");
        phone = bundle.getString("phone");
        rollno = bundle.getString("rollno");
        email = bundle.getString("email");
        gender = bundle.getString("gender");
        tsize = bundle.getString("tsize");
//        password = bundle.getString("password");
        events = bundle.getString("events");
//        event2 = bundle.getString("event2");
//        event3 = bundle.getString("event3");
//        event4 = bundle.getString("event4");
        selItems = lockedEvents;
        fees = bundle.getString("fees");
        locks = bundle.getString("locks");
        Log.e("Locks: ",locks);

//        selItems = new String[8];
//        for(int i= 0;i<5;i++){
//            selItems [i] = "none";
//        }

//        if (event1 != "none") {
//            incount++;
//            selItems[0] = event1;
//        } else if (event2 != "none") {
//            incount++;
//            selItems[1] = event2;
//        } else if (event3 != "none") {
//            incount++;
//            selItems[2] = event3;
//        } else if (event4 != "none"){
//            incount++;
//            selItems[3] = event4;
//        }
        count = incount;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                popUpUser();
            }
        },100);
        if(locks.contains("yes")){
            bLockEvent.setText("Locked Can't change");
//            bSelectedEvents.setEnabled(false);
            bLockEvent.setEnabled(false);
        }
        setTitle("Events Selection");

        context = getApplicationContext();
        final ListView lvEvents = (ListView) findViewById(R.id.lv_eventselection);
        lvEvents.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        eventnames = getResources().getStringArray(R.array.eventselection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.content_each_event_selection,R.id.ctvEventName,eventnames);
        lvEvents.setAdapter(adapter);
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
//                allNoneSel();
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

//        bSelectedEvents.setOnClickListener(this);
        bUserData.setOnClickListener(this);
        bLockEvent.setOnClickListener(this);

    }

    private String hashMapping(String id) {
        return id;
    }

    private void initialize() {
//        bSelectedEvents = (Button) findViewById(R.id.btSubmitEvent);
        bUserData = (ImageButton) findViewById(R.id.ibUserEventSelection);
        bLockEvent = (Button) findViewById(R.id.ibLockEventSelection);
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
//            case R.id.btSubmitEvent:
//                getSelectedEvents();
//                if(count == 0){
//                    Toast tos = Toast.makeText(context,"No item Selected! :(",Toast.LENGTH_SHORT);
//                    tos.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL,0,0);
//                    tos.show();
//                }
//                else{
//                    locks = "no";
//                    //Done submit Page selItem have Data
//                    callIntentSubmit();
//                }
//                break;
            case R.id.ibUserEventSelection:
                popUpUser();
                break;
            case R.id.ibLockEventSelection:
//                popUpUser();
                getSelectedEvents();
                if(count == 0){
                    Toast tos = Toast.makeText(context,"No item Selected! :(",Toast.LENGTH_SHORT);
                    tos.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL,0,0);
                    tos.show();
                }
                else{
                    open();
                }
                break;
        }
    }

    private void callIntentSubmit() {
        Toast.makeText(this,count+" Events Selected :) Locked: "+locks,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(EventSelection.this,SendJasonEventsSelection.class);
        intent.putExtra("ID",ID);
        intent.putExtra("rollno",rollno);
        intent.putExtra("name",name);
        intent.putExtra("count",count);
        intent.putExtra("eventselected",selItems);
        intent.putExtra("locks",locks);
        startActivity(intent);
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
        lockedEvents = selItems;

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
        int i=0;

        if(locks.contains("yes"))
        {
            String[] lockedEvents = events.split(",");
            Log.e("Eis",lockedEvents.length+"is");


            HashMap<String, String> map = new HashMap<String, String>();
            map.put("r_u_sherlocked","R U Sherlocked");
            map.put("fifa","FIFA 11");
            map.put("nfsmw","NFS MW");
            map.put("cs","Counter Strike");
            map.put("militia","Mini Militia");
            map.put("b_plan","B Plan");
            map.put("d_plan","D Constructeur");
            map.put("vinashak","Vinashak");
            map.put("aqua_soccer","Aqua Soccer");
            map.put("junkbot_war","JunkBot War");
            map.put("track_bot","Track-O-Bot");
            map.put("paper_prep","Paper Presentation");
            map.put("poster_prep","Poster Presentation");
            map.put("de_movier","De Movier");
            map.put("la_photography","La PhotoGrapher");
            map.put("requizzit","Requizzit");
            map.put("maniac","Mania C");
            map.put("jugaad_tech","Jugaad Technology");
            map.put("circuitrix","Circuitrix");
            map.put("model_prep","Pradarshan");

            if (i < lockedEvents.length) {
                tvEvent1.setText(map.get(lockedEvents[i++]));
            }
            if (i < lockedEvents.length) {
                tvEvent2.setText(map.get(lockedEvents[i++]));
            }
            if (i < lockedEvents.length) {
                tvEvent3.setText(map.get(lockedEvents[i++]));
                Log.e("3:",lockedEvents[i-1]);
            }
            if (i < lockedEvents.length) {
                tvEvent4.setText(map.get(lockedEvents[i++]));
            }

        }
        fees = Integer.toString(450 +i*50);
        if(locks == "yes") {
            tvFees.setText("Paid: " + fees);
        }
        else{
            tvFees.setText("Pay: none");
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
    public void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to Lock Events.\nOnce Locked it cannot be undone!!");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                getSelectedEvents();
                                if(count == 0){
                                    Toast tos = Toast.makeText(context,"No item Selected! :(",Toast.LENGTH_SHORT);
                                    tos.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL,0,0);
                                    tos.show();
                                }
                                else{
                                    locks = "yes";
                                    //Done submit Page selItem have Data
                                    callIntentSubmit();
                                }
                            }
                        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                locks = "no";
//                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
//        allNoneSel();
    }

//    private void allNoneSel() {
//        for(int i =0;i<5;i++){
//            selItems[i] = "none";
//        }
//    }

}
