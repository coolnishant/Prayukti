package mytwistedidea.wordpress.com.prayukti;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.audiofx.LoudnessEnhancer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SendJasonRegister extends AppCompatActivity implements View.OnClickListener {
    TextView tvData;
    JSONObject jsonObject;
    boolean status = false;
    String response;
    String name, email, phone, gender, rollno, finalData, tsize, uniqid, password;
    Button bSend, bCancel;
    final String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/testingJSONAgain.php";
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
        name = gotBox.getString("name");
        email = gotBox.getString("email");
        phone = gotBox.getString("phone");
        gender = gotBox.getString("gender");
        tsize = gotBox.getString("tsize");
        password = gotBox.getString("password");
        rollno = gotBox.getString("batch") + "-" + gotBox.getString("dept") + "-" + gotBox.getString("roll");
        finalData = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone +
                "\nGender: " + gender + "\nRoll No.: " + rollno + "\nT-Shirt Size: " + tsize + "\n";
        tvData.setText(finalData);

        bSend.setOnClickListener(this);
        bCancel.setOnClickListener(this);

//        requestQueue = Volley.newRequestQueue(getApplicationContext());

    }


    private String reversePhone() {
        String str = "";
        for (int i = phone.length() - 1; i >= 0; i--) {
            str += phone.charAt(i);
        }
        return str;
    }


    private String getServerResponse(String json) {
        //// TODO: 25-03-2017
//        HttpPost post = new HttpPost();
        return null;
    }


    private String jsonBuilderIsHere() {
        jsonObject = new JSONObject();
        try {

            uniqid = reversePhone();
            jsonObject.accumulate("ID", uniqid);
            jsonObject.accumulate("name", name);
            jsonObject.accumulate("email", email);
            jsonObject.accumulate("phone", phone);
            jsonObject.accumulate("gender", gender);
            jsonObject.accumulate("tsize", tsize);
            jsonObject.accumulate("password", password);
            jsonObject.accumulate("rollno", rollno);
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
                tos.setGravity(Gravity.CENTER, 0, 0);
                tos.show();
                break;

            case R.id.bSendFinal:
                //Todo Send JSON

                    register(uniqid, name, email, phone, gender, tsize, password, rollno);
                    checkForNext();

                break;
        }
        checkForNext();
    }

    private void checkForNext() {

    }

    private void register(String uniqid, final String name, String email, String phone, String gender, String tsize,
                          String password, String rollno) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading = null;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SendJasonRegister.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                loading.dismiss();
//                if(loading != null && loading.isShowing()){ loading.dismiss();}
                response = new String(s);
                s.trim();
                Log.e("response", response);

                if (s.equals("Successfully Registered. :)")) {
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
                    tos.setText(name + "\n" + response + "\nID Saved Drawer/Registration");
                    toastTV.setGravity(Gravity.CENTER);
                    tos.setView(v1);
                    tos.show();
                    finish();
//                    savingData();
                    Intent i = new Intent(SendJasonRegister.this, MainActivity.class);
                    // set the new task and clear flags
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                } else if (response.contains("already")){
                    v1.setBackgroundColor(Color.RED);
                    toastTV.setGravity(Gravity.CENTER);
                    tos.setText(response);
                    toastTV.setTextSize(20);
                    tos.setView(v1);
                    tos.show();
                    finish();
                }
                else{
                    v1.setBackgroundColor(Color.MAGENTA);
                    toastTV.setTextSize(20);
                    tos.setText(name +"!\nPlease Connect to INTERNET!");
                    toastTV.setGravity(Gravity.CENTER);
                    tos.setView(v1);
                    tos.show();
                    finish();
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
        ru.execute(uniqid, name, email, phone, gender, tsize, password, rollno);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (status == true) {
            savingData();
        }
//        else if(response == null){
//            if (isNetworkConnected() && isInternetAvailable()){
//                Toast tos = Toast.makeText(getApplicationContext(), "No InternetConnection\nPlease Connect TO ACTIVE Internet!", Toast.LENGTH_LONG);
//                tos.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
//                View v1 = tos.getView();
//                LinearLayout toastLayout = (LinearLayout) tos.getView();
//                TextView toastTV = (TextView) toastLayout.getChildAt(0);
//                v1.setBackgroundColor(Color.MAGENTA);
//                toastTV.setGravity(Gravity.CENTER);
//                toastTV.setTextSize(20);
//                toastTV.setTextColor(Color.GRAY);
//                tos.setView(v1);
//                tos.show();
//            }
//        }
    }

    private void savingData() {
        helpers = new DatabaseHelper(this);
        helpers.insertRegStudent(uniqid, name, phone, email, tsize, rollno);
    }

    @Override
    public void onBackPressed() {
        Toast tos = Toast.makeText(getApplicationContext(), "Correct it!", Toast.LENGTH_LONG);
        tos.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
        View v1 = tos.getView();
        LinearLayout toastLayout = (LinearLayout) tos.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        v1.setBackgroundColor(Color.RED);
        toastTV.setGravity(Gravity.CENTER);
        toastTV.setTextSize(20);
        tos.setView(v1);
        tos.show();
        finish();
        super.onBackPressed();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}