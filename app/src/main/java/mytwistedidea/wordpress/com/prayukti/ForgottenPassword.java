package mytwistedidea.wordpress.com.prayukti;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgottenPassword extends AppCompatActivity implements View.OnClickListener {

    EditText etEmailForgotten, etIdForgotten;
    Button bGetPassword;
//    TextView tvPassword;
    boolean status = false;
    String json;
    final String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/forgottenpasswordFinal.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        initiallize();
        setTitle("Verification!");
        bGetPassword.setOnClickListener(this);
    }

    private void initiallize() {
        etEmailForgotten = (EditText) findViewById(R.id.eTemailforgotten);
//        tvPassword = (TextView) findViewById(R.id.tvForgottenPassword);
        bGetPassword = (Button) findViewById(R.id.bGetMypassword);
        etIdForgotten = (EditText) findViewById(R.id.eTIdforgotten);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bGetMypassword:
                getMyPasswordEmail(etEmailForgotten.getText().toString());
                break;
        }
    }

    private void getMyPasswordEmail(String email) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading = null;
            RegisterUserClass ruc = new RegisterUserClass();
            TextView textView = null;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                textView = (TextView) findViewById(R.id.tvForgottenPassword);
                loading = ProgressDialog.show(ForgottenPassword.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
//                if(loading != null && loading.isShowing()){ loading.dismiss();}
                json = new String(s);
                s.trim();
                Log.e("response", s);

                if (s.contains("Password is")) {
                    status = true;
                } else if(s.contains("Wrong Combination")) {
                    status = false;
                }
                else{
                    status = false;
                }
                if (status) {
//                    Toast.makeText(ForgottenPassword.this,json,Toast.LENGTH_SHORT).show();
////                    textView.setText(json);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgottenPassword.this);
//                    builder.setTitle(json);
////                    builder.setMessage(json);
//                    builder.create().show();
                    Intent intent = new Intent(ForgottenPassword.this,ResetPassword.class);
                    intent.putExtra("email",etEmailForgotten.getText().toString());
                    startActivity(intent);
                    finish();
                } else if(s.contains("Wrong Combination")){
                    Toast.makeText(ForgottenPassword.this,"Wrong Combination of Email and ID :(",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ForgottenPassword.this,"Please Enable INTERNET!",Toast.LENGTH_SHORT).show();
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
            jsonObject.accumulate("email", etEmailForgotten.getText().toString());
            jsonObject.accumulate("ID", etIdForgotten.getText().toString());
            return jsonObject;
        } catch (JSONException e) {
            Log.e("Nish", "Can't format JSON!");
        }
        return null;
    }
}
