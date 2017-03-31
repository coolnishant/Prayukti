package mytwistedidea.wordpress.com.prayukti;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

    EditText etEmailForgotten;
    Button bGetPassword;
    TextView tvPassword;
    boolean status = false;
    String json;
    final String REGISTER_URL = "http://prayuktihith.net/2017/androidnish/forgottenpassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        initiallize();
        bGetPassword.setOnClickListener(this);
    }

    private void initiallize() {
        etEmailForgotten = (EditText) findViewById(R.id.eTemailforgotten);
        tvPassword = (TextView) findViewById(R.id.tvForgottenPassword);
        bGetPassword = (Button) findViewById(R.id.bGetMypassword);
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

                if (!s.contains("for this Email")) {
                    status = true;
                } else {
                    status = false;
                }
                if (status) {
                    Toast.makeText(ForgottenPassword.this,json,Toast.LENGTH_SHORT).show();
//                    textView.setText(json);
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgottenPassword.this);
                    builder.setTitle(json);
//                    builder.setMessage(json);
                    builder.create().show();
                } else {
                    Toast.makeText(ForgottenPassword.this,"Wrong Credential! :(",Toast.LENGTH_SHORT).show();
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
            return jsonObject;
        } catch (JSONException e) {
            Log.e("Nish", "Can't format JSON!");
        }
        return null;
    }
//
//    protected void parseJSON(){
//        JSONObject jsonObject=null;
//        try {
//            jsonObject = new JSONObject(json);
//            ID = jsonObject.getString("ID");
//            name = jsonObject.getString("name");
//            email = jsonObject.getString("email");
//            phone = jsonObject.getString("phone");
//            gender = jsonObject.getString("gender");
//            tsize = jsonObject.getString("tsize");
//            password = jsonObject.getString("password");
//            try {
//                rollno = jsonObject.getString("rollno");
//            } catch (JSONException e1) {
//                e1.printStackTrace();
//            }
////                 = jo.getString("");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

}
