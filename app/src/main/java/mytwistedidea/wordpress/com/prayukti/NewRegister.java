package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nishant on 24-03-2017.
 */

public class NewRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    TextView tvName, tvEmail, tvPhone,tvPass,tvPasschk,tvRoll;
    String name = null, email = null, phone = null,pass = null,passchk = null,roll = null;
    Button bSubmit;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton;
    Spinner spBatch, spDept, spTsize;
    View viewof = null;

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_registration);
        setTitle("Register Now");
        initialize();
        initializeSpinner();

        bSubmit.setOnClickListener(this);
    }
    private void initializeSpinner() {

        spBatch.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Batch");
        categories.add("16");
        categories.add("15");
        categories.add("L15");
        categories.add("14");
        categories.add("L14");
        categories.add("13");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                R.layout.my_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.my_spinner_item);

        // attaching data adapter to spinner
        spBatch.setAdapter(dataAdapter);


        spDept.setOnItemSelectedListener(this);
        categories = new ArrayList<String>();
        categories.add("Dept");
        categories.add("AEIE");
        categories.add("BT");
        categories.add("CE");
        categories.add("CSE");
        categories.add("ECE");
        categories.add("EE");
        categories.add("FT");
        categories.add("ICE");
        categories.add("IT");
        categories.add("ME");

        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.my_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.my_spinner_item);

        // attaching data adapter to spinner
        spDept.setAdapter(dataAdapter);

        spTsize.setOnItemSelectedListener(this);
        categories = new ArrayList<String>();
        categories.add("T-Size");
        categories.add("S");
        categories.add("M");
        categories.add("L");
        categories.add("XL");
        categories.add("XXL");

        dataAdapter = new ArrayAdapter<String>(context,
                R.layout.my_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.my_spinner_item);

        // attaching data adapter to spinner
        spTsize.setAdapter(dataAdapter);
    }

    private void jsonBuilderIsHere() {

    }

    private void initialize() {
        tvName = (TextView) findViewById(R.id.eTname);
        tvEmail = (TextView) findViewById(R.id.eTemail);
        tvPhone = (TextView) findViewById(R.id.eTphoneno);
        tvPass= (TextView) findViewById(R.id.eTpass);
        tvPasschk= (TextView) findViewById(R.id.eTpasschk);
        spBatch = (Spinner)  findViewById(R.id.spinner_batch);
        spDept = (Spinner)  findViewById(R.id.spinner_department);
        spTsize = (Spinner) findViewById(R.id.spinner_tsize);
        bSubmit = (Button) findViewById(R.id.btSubmit);
        tvRoll = (TextView) findViewById(R.id.eTroll);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

        int selectedId=radioSexGroup.getCheckedRadioButtonId();
        radioSexButton=(RadioButton)findViewById(selectedId);
        context = this;
//        Toast.makeText(context," id= "+selectedId,Toast.LENGTH_SHORT).show();

        tvName.requestFocus();
    }


    private String formatDataAsJSON(){
        final JSONObject root = new JSONObject();

        try {
            root.put("name",name);

            return root.toString();
        } catch (JSONException e) {
            Log.e("nish",e.toString());
        }
        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = null;
        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.spinner_batch) {
            item = parent.getItemAtPosition(position).toString();
//            Toast.makeText(parent.getContext(), "Selected: Bat " + item, Toast.LENGTH_LONG).show();
        }
        if (spinner.getId() == R.id.spinner_department){
            item = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), "Selected: Dept " + item, Toast.LENGTH_LONG).show();
        }
        if (spinner.getId() == R.id.spinner_tsize){
            item = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), "Selected: Dept " + item, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btSubmit:
                //TODO send data function
                boolean flag = validateInputedData();

                break;
        }
    }

    private boolean validateInputedData() {
        if(validateName() && validateEmail() && validateRoll() && validatePhoneNo() && validatePassword())
            return true;
        return false;
    }

    private boolean validateEmail() {
        email = tvEmail.getText().toString();
        email.trim();
        if(email.contains("@") && email.contains(".com") && email.length()<10)
            return true;
        return false;
    }


    private boolean validateRoll() {
        roll = tvRoll.getText().toString();
        roll.trim();
        if(roll.length()==0 || roll.equals("0") || roll.equals("00") || roll.equals("000")){
            Toast tos = Toast.makeText(context,"",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            roll = null;
            return false;
        }
        if(roll.length()==1){
            roll = "00"+roll;
        }
        if(roll.length()==2){
            roll = "0"+roll;
        }
        return true;

    }

    private boolean validateName() {
        name = tvName.getText().toString();
        name.trim();
        if(name.length() == 0){
            Toast tos = Toast.makeText(context,"Name Cannot be Empty!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.CENTER,0,0);
            tos.show();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        pass = tvPass.getText().toString();
        passchk = tvPasschk.getText().toString();
//        pass.trim();
//        passchk.trim();
        if(pass.length() <5){
            Toast tos = Toast.makeText(context,"Password must Be aleast 5 character long!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            return false;
        }
        else if (!pass.equals(passchk)){
            Toast tos = Toast.makeText(context,"Password didn't Matched, Retype Password!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            return false;
        }
        return true;
    }

    private boolean validatePhoneNo() {
        phone = tvPhone.getText().toString();
        phone.trim();
        if(phone.length() == 10){
            return true;
        }
        else {
            Toast tos = Toast.makeText(context,"Enter a Valid Phone no.!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            phone = null;
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit) {
            //TODO SEND Content
            boolean flag = validateInputedData();
            if(flag){

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
