package mytwistedidea.wordpress.com.prayukti;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;

public class LoginId extends Fragment implements View.OnClickListener {

    EditText etStudentId, etStudentPassword;
    TextView tvForgottenPassword;
    Button bStudentLogin, bNewReg;
    private JSONArray users = null;
    View view;
    boolean status = false;
    String json;
    Context context;
    final String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/loginuserpasswordverificationFinalAll.php";
    String ID, name, email, phone, gender, tsize, password, rollno, event1, event2, event3, event4, fees,locks,events;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_login_id,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        context = getActivity();
        getActivity().setTitle("Login Id");
        initialize();
        bStudentLogin.setOnClickListener(this);
        bNewReg.setOnClickListener(this);
        tvForgottenPassword.setOnClickListener(this);
    }

    private void initialize() {
        etStudentId = (EditText) view.findViewById(R.id.etStudentEmailId);
        etStudentPassword = (EditText) view.findViewById(R.id.etStudentPassword);
        tvForgottenPassword =(TextView) view.findViewById(R.id.tvForgottenPassword);
        bStudentLogin = (Button) view.findViewById(R.id.bStudentLogin);
        bNewReg = (Button) view.findViewById(R.id.bnewReg);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bStudentLogin:
                //Done Login
                Log.e("Here","login press");
                email = etStudentId.getText().toString();
                password = etStudentPassword.getText().toString();
                if (email.contains("@") && email.contains(".com") && password.length() >= 5){
                    loginStudent(email, password);
                    Log.e("Status",""+status);
                }
                else{
                    Log.e("Here","else login");
                    Toast.makeText(getActivity(),"Wrong Data Entered! :(",Toast.LENGTH_SHORT).show();
                    etStudentPassword.setText("");
                }
                break;
            case R.id.bnewReg:
                //Done NewReg
                Fragment fragment = new Registration();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_mainis,fragment);
                ft.commit();
                break;
            case R.id.tvForgottenPassword:
                //Done Forgotten Password
                Intent intent = new Intent(getActivity(),ForgottenPassword.class);
                getActivity().startActivity(intent);
                break;
        }
    }


    private void loginStudent(String email, String password) {
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

                if (s.contains("@")) {
                    status = true;
                } else if(s.contains("nodata")){
                    status = false;
                }
                else {
                    Toast.makeText(getActivity(), "Please Connect To INTERNET :)",Toast.LENGTH_LONG).show();
                    status = false;
                }
                if (status) {
                    parseJSON();
                    startEventSelection();
//                    getActivity().finish();
                    Fragment fragment = new Home();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_mainis,fragment);
                    ft.commit();
                } else if(s.contains("nodata")){
                    Toast.makeText(getActivity(),"Wrong Credential! :(",Toast.LENGTH_SHORT).show();
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
        ru.execute(email, password);

    }

    private JSONObject jsonBuilderIsHere() {
        JSONObject jsonObject = null;
        jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("email", etStudentId.getText().toString());
            jsonObject.accumulate("password", etStudentPassword.getText().toString());
            return jsonObject;
        } catch (JSONException e) {
            Log.e("Nish", "Can't format JSON!");
        }
        return null;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
                jsonObject = new JSONObject(json);
                ID = jsonObject.getString("ID");
                name = jsonObject.getString("name");
                email = jsonObject.getString("email");
                phone = jsonObject.getString("phone");
                gender = jsonObject.getString("gender");
                tsize = jsonObject.getString("tsize");
//                password = jsonObject.getString("password");
            rollno = jsonObject.getString("rollno");
            events = jsonObject.getString("event");
//            event2 = jsonObject.getString("event2");
//            event3 = jsonObject.getString("event3");
//            event4 = jsonObject.getString("event4");
            fees = jsonObject.getString("fees");
            locks = jsonObject.getString("locks");

                Log.e("Roll no: ",rollno);
//                 = jo.getString("");

    } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void startEventSelection(){
        if(status){
            //Done on LoginEvent Selection
            Intent intent = new Intent(getActivity(),EventSelection.class);
            intent.putExtra("ID",ID);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            intent.putExtra("rollno",rollno);
            intent.putExtra("phone",phone);
            intent.putExtra("gender",gender);
            intent.putExtra("tsize",tsize);
//            intent.putExtra("password",password);
            intent.putExtra("events",events);
            intent.putExtra("fees",fees);
            intent.putExtra("locks",locks);
            context.startActivity(intent);
        }
    }
}