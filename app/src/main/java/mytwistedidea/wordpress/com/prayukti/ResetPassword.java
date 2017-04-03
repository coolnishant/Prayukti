package mytwistedidea.wordpress.com.prayukti;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nishant on 02-04-2017.
 */

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    EditText etPassword, etRetype;
    Button bNewPassword;
    String pass,retypepass,email;
    String json;
    boolean status = false;
    final String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/resetingpasswordFinal.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password );
        initiallize();
        setTitle("Reseting Password");
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
        bNewPassword.setOnClickListener(this);
    }

    private void initiallize() {
        etPassword = (EditText) findViewById(R.id.eTresetnewpassword);
        etRetype = (EditText) findViewById(R.id.eTresetretypepassword);
        bNewPassword = (Button) findViewById(R.id.bGetMynewpassword);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bGetMynewpassword:
                pass = etPassword.getText().toString();
                retypepass = etRetype.getText().toString();
                if(pass.length()<5){
                    Toast tos = Toast.makeText(this,"Atleast 5 characters Long!!",Toast.LENGTH_LONG);
                    tos.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL,0,0);
                    tos.show();
                }
                else{
                    getMyNewPasswordEmail(email);
                }
                break;
        }
    }

    private void getMyNewPasswordEmail(String email) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading = null;
            RegisterUserClass ruc = new RegisterUserClass();
            TextView textView = null;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                textView = (TextView) findViewById(R.id.tvForgottenPassword);
                loading = ProgressDialog.show(ResetPassword.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
//                if(loading != null && loading.isShowing()){ loading.dismiss();}
                json = new String(s);
                s.trim();
                Log.e("response", s);
                //TODO Data Reset
                if (s.contains("successfully")) {
                    status = true;
                } else if(s.contains("try again")) {
                    status = false;
                }
                else{
                    status = false;
                }
                if (status) {
                    Toast.makeText(ResetPassword.this,json,Toast.LENGTH_SHORT).show();
////                    textView.setText(json);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgottenPassword.this);
//                    builder.setTitle(json);
////                    builder.setMessage(json);
//                    builder.create().show();
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    finish();
                } else if(s.contains("try again")){
                    Toast.makeText(ResetPassword.this,"Something went wrong try again!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ResetPassword.this,"Please Enable INTERNET!",Toast.LENGTH_SHORT).show();
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
        ru.execute(email);
    }

    private JSONObject jsonBuilderIsHere() {
        JSONObject jsonObject = null;
        jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("email", email);
            jsonObject.accumulate("password", pass);
            return jsonObject;
        } catch (JSONException e) {
            Log.e("Nish", "Can't format JSON!");
        }
        return null;
    }
}
