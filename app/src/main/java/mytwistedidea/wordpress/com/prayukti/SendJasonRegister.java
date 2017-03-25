package mytwistedidea.wordpress.com.prayukti;

import android.app.ProgressDialog;
import android.content.Context;
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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SendJasonRegister extends AppCompatActivity implements View.OnClickListener {
    TextView tvData;
    JSONObject jsonObject;
    boolean status = false;
    String name, email,phone,gender,rollno,finalData,tsize,uniqid,password;
    Button bSend, bCancel;
    final String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/testingJSONAgain.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_jason_register);

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
        rollno = gotBox.getString("batch")+"-"+ gotBox.getString("dept")+"-"+ gotBox.getString("roll");
        finalData = "Name: "+name+"\nEmail: "+email+"\nPhone: "+phone+
                "\nGender: "+gender+"\nRoll No.: "+rollno+"\nT-Shirt Size: "+tsize+"\n";
        tvData.setText(finalData);

        bSend.setOnClickListener(this);
        bCancel.setOnClickListener(this);

//        requestQueue = Volley.newRequestQueue(getApplicationContext());

    }


    private String reversePhone() {
        String str = "";
        for(int i=phone.length()-1;i>=0;i--){
            str += phone.charAt(i);
        }
        return str;
    }


    private String getServerResponse(String json) {
        //// TODO: 25-03-2017
//        HttpPost post = new HttpPost();
        return null;
    }


    private String  jsonBuilderIsHere() {
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
            jsonObject.accumulate("rollno",rollno);
            return jsonObject.toString();
        } catch (JSONException e) {
            Log.e("Nish","Can't format JSON!");
        }
        Log.e("a",jsonObject.toString());
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Toast tos;
        switch (id){
            case R.id.bCancel:
                finish();

                tos = Toast.makeText(this,"Correct it!!!!",Toast.LENGTH_SHORT);
                tos.setGravity(Gravity.CENTER,0,0);
                tos.show();
                break;

            case R.id.bSendFinal:
                //Todo Send JSON
//                tos = Toast.makeText(this,"All Set!!",Toast.LENGTH_SHORT);
//                tos.setGravity(Gravity.TOP,0,0);
//                tos.show();
//
//                StringRequest request = new StringRequest(Request.Method.POST,REGISTER_URL, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("nisha",response);
//                    }
//                },new Response.ErrorListener(){
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String ,String > parameters = new HashMap<String ,String>();
//                        parameters.put("ID", uniqid);
//                        parameters.put("name", name);
//                        parameters.put("email", email);
//                        parameters.put("phone", phone);
//                        parameters.put("gender", gender);
//                        parameters.put("tsize", tsize);
//                        parameters.put("password", password);
//                        parameters.put("rollno",rollno);
//
//                        return parameters;
//                    }
//                };
//
//                requestQueue.add(request);
//                jsonBuilderIsHere();
                register(uniqid,name,email,phone,gender,tsize,password,rollno);
                break;
        }
    }
    private void register(String uniqid, String name, String email,String phone,  String gender, String tsize,
                          String password, String rollno) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SendJasonRegister.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Toast tos = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG);
                tos.setGravity(Gravity.CENTER,0,0);
                tos.show();
                if(s.equals("successfully registered"))
                    status = true;
                else status = false;
                Log.e("nish",s);
            }

            @Override
            protected String doInBackground(String... params) {

                jsonBuilderIsHere();
                String result = ruc.sendPostRequest(REGISTER_URL,jsonObject);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(uniqid,name,email,phone,gender,tsize,password,rollno);
    }

}


