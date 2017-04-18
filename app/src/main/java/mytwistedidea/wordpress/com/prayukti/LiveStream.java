package mytwistedidea.wordpress.com.prayukti;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LiveStream extends Fragment implements View.OnClickListener {
    DatabaseHelper MyHelpers;
    Button brefreshNotification;
    TextView tvNoNotify;
    public static String filename = "MySharedString";
    String nid[] = new String[500], neventname[] = new String[500],
            neventcontent[] = new String[500], ntime[] = new String[500], json;
    //TODO save nid
    String nidl , oldnidl;
    int addednotification = 0;
    View view;
    Context context;
    boolean status;
    final int REFRESH_TIME = 2000;
    DatabaseHelper helpers;
    ArrayList<String> notificationAL;
    //Done url setting
    final String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/addNotification.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_live_stream,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Live Stream");
        this.view = view;
        status = false;
        context = getActivity();
        brefreshNotification = (Button) view.findViewById(R.id.bRefreshNotification);
        tvNoNotify = (TextView) view.findViewById(R.id.tvNotifyNone);
        brefreshNotification.setOnClickListener(this);
        startPopulatingNotification();
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                brefreshNotification.performClick();
            }
        }, REFRESH_TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        startPopulatingNotification();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bRefreshNotification:
                getNidl();
                oldnidl = nidl;
                eventNotification(nidl);
                break;
        }
    }
    private void eventNotification(final String nidl) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading = null;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
//                if(loading != null && loading.isShowing()){ loading.dismiss();}
                json = new String(s);
                s.trim();
                Log.e("response", s);

                if (s.contains("neventname")) {
                    status = true;
                } else if(s.contains("none")){
                    status = false;
                }
                else {
                    Toast.makeText(getActivity(), "Please Connect To INTERNET :)",Toast.LENGTH_LONG).show();
                    status = false;
                }
//                Log.e("res json",json);
                if (status) {
                    parseJSON();
                    Log.e("ok",status+"here");
                    startPopulatingNotification();
                    addNotificationOnBar();
//                    getActivity().finish();
                } else if(s.contains("none")){
                    Toast.makeText(getActivity(),"No New Notification!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                JSONObject jsonObject = jsonBuilderIsHere();
                String result = ruc.sendPostRequest(REGISTER_URL, jsonObject);
                return result;
            }

        }

        RegisterUser ru = new RegisterUser();
        ru.execute(nidl);

    }

    private void startPopulatingNotification() {
        MyHelpers = new DatabaseHelper(getActivity().getApplicationContext());
        notificationAL = MyHelpers.getAllNOtification();

        int l =notificationAL.size();
        int j;
        int i=0;
        Log.e("aa",l+"");
        if(l == 1) {
//            tvNoNotify.setTextSize();
            tvNoNotify.setText("No Notification Available!");
            return;
        }
        tvNoNotify.setVisibility(View.GONE);
//        tvNoNotify.setTextSize(0);
        for(i=0,j=0;i< l;){
            i++;
            nid[j] = notificationAL.get(i++);
            if(i<l) {
                neventname[j] = notificationAL.get(i++);
                Log.e("data",neventname[j]);
            }if(i<l)
                neventcontent[j] = notificationAL.get(i++);
            if(i<l)
                ntime[j] = notificationAL.get(i++);
            j++;
        }
        Log.e("j= "," "+j);
        final List<HashMap<String,String>> aList = new ArrayList<>();
        for(i = 0;i<j;i++) {
            Log.e("all",nid[i]+neventname[i]+neventcontent[i]+ntime[i]);
        }
        for(i = j-1;i>=0;i--){

            HashMap<String,String> hashMap = new HashMap<>();

            hashMap.put("nid","ID: "+nid[i]);
            hashMap.put("nname","Event: "+neventname[i]);
//            hashMap.put("ncontent","ID: "+neventcontent[i]);
            hashMap.put("ntime","Time: "+ntime[i]);
            aList.add(hashMap);
        }

        String[] from = {"nid","nname",
//                "ncontent",
                "ntime"};
        int to[] = {R.id.tvNotifyEventNid,R.id.tvNotifyEventName,
//                R.id.tvNotifyEventContent,
                R.id.tvNotifyEventDateTime};

        SimpleAdapter simpleAdapterPeriod = new SimpleAdapter(getActivity().getApplicationContext(),aList,R.layout.content_each_event_notification,from,to);
        final ListView listView = (ListView) view.findViewById(R.id.lv_eventsnotification);
        listView.setAdapter(simpleAdapterPeriod);

        registerForContextMenu(listView);
        final LinearLayout lly = (LinearLayout) view.findViewById(R.id.llnotify);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    RelativeLayout viewGroup = (RelativeLayout) getActivity().findViewById(R.id.popup);
                View popupView = inflater.inflate(R.layout.popup_each_notification_content, null);
                final PopupWindow popup = new PopupWindow(inflater.inflate(
                        R.layout.popup_each_notification_content, null, false), 200, 265, true);

//                    final PopupWindow popup = new PopupWindow(getActivity());
                popup.setContentView(popupView);
                popup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                popup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);


                if(Build.VERSION.SDK_INT>=21){
                    popup.setElevation(5.0f);
                }
                TextView tvEventShow = (TextView) popupView.findViewById(R.id.tvEachEventNotify);
                tvEventShow.setText(neventname[+position]);
                TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tvEachEventContentNotify);
                tvEventInfo.setText(neventcontent[+position]);

                popup.showAtLocation(lly, Gravity.CENTER,0,0);
            }
        });
    }

    private void parseJSON() {
        Log.e("res json",json);
        try {
            Log.e("res json",json);
            JSONArray a = new JSONArray(json);
            addednotification = a.length();
            Log.e("res jsonarray",a.toString());
            Log.e("len a:",a.length()+"");
            for (int i = 0; i < a.length(); i++) {
                JSONObject actor = a.getJSONObject(i);
                nid[i] = actor.getString("nid");
                neventname[i] = actor.getString("neventname");
                neventcontent[i] = actor.getString("neventcontent");
                ntime[i] = actor.getString("ntime");
                Log.e("NotificationData: ", actor.getString("nid")+ actor.getString("neventname")+
                        actor.getString("ntime")+nid[i]);
                helpers = new DatabaseHelper(context);
                helpers.insertNotification(nid[i],neventname[i],neventcontent[i],ntime[i]);
            }
            nidl = Integer.toString(addednotification);
            saveNidl();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject jsonBuilderIsHere() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("nid", nidl);
            return jsonObject;
        } catch (JSONException e) {
            Log.e("Nish", "Can't format JSON!");
        }
        return null;
    }

    private void getNidl() {
        SharedPreferences someData = getActivity().getSharedPreferences(filename, 0);
        nidl = someData.getString("nidl", "0");
    }

    private void saveNidl() {
        SharedPreferences someData = getActivity().getSharedPreferences(filename, 0);
        SharedPreferences.Editor editor = someData.edit();
        editor.clear();
        editor.putString("nidl", nidl);
        editor.commit();
    }

    private void addNotificationOnBar() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.logo_p)
                        .setContentTitle("New Notifications")
                        .setContentText("Your Event updates Here!");

        Intent notificationIntent = new Intent(context, context.getClass());
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
