package mytwistedidea.wordpress.com.prayukti;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;

public class SendJasonEventsSelection extends AppCompatActivity implements View.OnClickListener {
    TextView tvData;
    JSONObject jsonObject;
    boolean status = false;
    String response;
    int feeCal = 0;
    String name, uniqid,finalData,event1=null,event2=null,event3=null,event4=null,fees,locks,event;
    String eventsArray[];
    int count;
    Button bSend, bCancel;
    String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/addingEventSelectionFinalAll.php";
    RequestQueue requestQueue;
    DatabaseHelper helpers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_jason_register);
        ProgressDialog loading;
        tvData = (TextView) findViewById(R.id.tvLastCheck);
        bCancel = (Button) findViewById(R.id.bCancel);
        bSend = (Button) findViewById(R.id.bSendFinal);
        Bundle gotBox = getIntent().getExtras();
        uniqid = gotBox.getString("ID");
        name = gotBox.getString("name");
        eventsArray = gotBox.getStringArray("eventselected");
        count = (int) gotBox.get("count");
        locks = gotBox.getString("locks");
        toCommaSeperated();
        feeCal = 400+50*count;
        fees = Integer.toString(feeCal);
        finalData = "ID: "+uniqid+"\nName: " + name+"\n\nEVENT SELECTED ARE:\n";
        for(int i=0;i<eventsArray.length;i++){
            finalData += "\n "+eventsArray[i];
        }
        tvData.setText(finalData+"\nTotal Fees: "+feeCal);
        bSend.setOnClickListener(this);
        bCancel.setOnClickListener(this);
    }

    private void toCommaSeperated() {
        event = "";
        for(int i = 0 ;i<eventsArray.length;i++){

            if(i>0){
                event+=",";
            }
            if(eventsArray[i].contains("Sherlocked")){
                event += "r_u_sherlocked";
            }
            else if(eventsArray[i].contains("FIFA")){
                event += "fifa";
            }
            else if(eventsArray[i].contains("NFS")){
                event += "nfsmw";
            }
            else if(eventsArray[i].contains("Counter")){
                event += "cs";
            }
            else if(eventsArray[i].contains("Mini")){
                event += "militia";
            }
            else if(eventsArray[i].contains("Plan")){
                event += "b_plan";
            }
            else if(eventsArray[i].contains("Construct")){
                event += "d_plan";
            }
            else if(eventsArray[i].contains("Vinashak")){
                event += "vinashak";
            }
            else if(eventsArray[i].contains("Aqua")){
                event += "aqua_soccer";
            }
            else if(eventsArray[i].contains("JunkBot")){
                event += "junkbot_war";
            }
            else if(eventsArray[i].contains("Track")){
                event += "track_bot";
            }
            else if(eventsArray[i].contains("Paper")){
                event += "paper_prep";
            }
            else if(eventsArray[i].contains("Poster")){
                event += "poster_prep";
            }
            else if(eventsArray[i].contains("Movie")){
                event += "de_movier";
            }
            else if(eventsArray[i].contains("Photo")){
                event += "la_photography";
            }
            else if(eventsArray[i].contains("quizz")){
                event += "requizzit";
            }
            else if(eventsArray[i].contains("Mania")){
                event += "maniac";
            }
            else if(eventsArray[i].contains("Jugaad")){
                event += "jugaad_tech";
            }
            else if(eventsArray[i].contains("Circuit")){
                event += "circuitrix";
            }
            else if(eventsArray[i].contains("darshan")){
                event += "model_prep";
            }
        }
    }

    private String getServerResponse(String json) {
        //// TODO: 25-03-2017
//        HttpPost post = new HttpPost();
        return null;
    }


    private String jsonBuilderIsHere() {
        jsonObject = new JSONObject();
        try {

            jsonObject.accumulate("ID", uniqid);
            jsonObject.accumulate("name", name);
            jsonObject.accumulate("event", event);
            Log.e("ID",uniqid);
            Log.e("event",event);
//            jsonObject.accumulate("event2", event2);
//            jsonObject.accumulate("event3", event3);
//            jsonObject.accumulate("event4", event4);
            jsonObject.accumulate("fees", fees);
//            jsonObject.accumulate("locks", locks);
            return jsonObject.toString();
        } catch (JSONException e) {
            Log.e("Nish", "Can't format JSON!");
        }
        Log.e("a", jsonObject.toString());
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Toast tos;
        switch (id) {
            case R.id.bCancel:
                finish();
                tos = Toast.makeText(this, "Correct it!!!!", Toast.LENGTH_SHORT);
                tos.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL, 0, 0);
                tos.show();
                break;
            case R.id.bSendFinal:
                //done Send JSON
                    eventSelectionDataBase(uniqid, name, event1, event2, event3, event4, fees, locks);
                break;
        }
    }

    private void eventSelectionDataBase(String uniqid, final String name, String event1, String event2, String event3,
                                        String event4, String fees, String locks) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading = null;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SendJasonEventsSelection.this, "Please Wait :)", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
//                if(loading != null && loading.isShowing()){ loading.dismiss();}
                response = new String(s);
                s.trim();
                Log.e("response", response);

                if (s.contains(":)")) {
                    status = true;
                } else {
                    status = false;
                }
                Toast tos = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG);
                tos.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
                View v1 = tos.getView();
                LinearLayout toastLayout = (LinearLayout) tos.getView();
                TextView toastTV = (TextView) toastLayout.getChildAt(0);
                if (status) {
                    v1.setBackgroundColor(Color.BLUE);
                    toastTV.setTextSize(30);
                    tos.setText(response);
                    toastTV.setGravity(Gravity.CENTER);
                    tos.setView(v1);
                    tos.show();
                    finish();
                    Intent i = new Intent(SendJasonEventsSelection.this, MainActivity.class);
                    finish();
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                } else if (response.contains("already")){
                    v1.setBackgroundColor(Color.RED);
                    toastTV.setGravity(Gravity.CENTER);
                    tos.setText(response);
                    toastTV.setTextSize(20);
                    tos.setView(v1);
                    tos.show();
                }
                else{
                    v1.setBackgroundColor(Color.MAGENTA);
                    toastTV.setTextSize(20);
                    tos.setText(name +"!\nPlease Connect to INTERNET!");
                    toastTV.setGravity(Gravity.CENTER);
                    tos.setView(v1);
                    tos.show();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                jsonBuilderIsHere();
                String result = ruc.sendPostRequest(REGISTER_URL, jsonObject);
                return result;
            }

        }

        RegisterUser ru = new RegisterUser();
        ru.execute(uniqid, name, event1, event2, event3, event4, fees,locks);
    }

    @Override
    public void onBackPressed() {
        Toast tos = Toast.makeText(getApplicationContext(), "Correct it!", Toast.LENGTH_LONG);
        tos.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
        View v1 = tos.getView();
        LinearLayout toastLayout = (LinearLayout) tos.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        v1.setBackgroundColor(Color.RED);
        toastTV.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL);
        toastTV.setTextSize(20);
        tos.setView(v1);
        tos.show();
        finish();
        super.onBackPressed();
    }
}