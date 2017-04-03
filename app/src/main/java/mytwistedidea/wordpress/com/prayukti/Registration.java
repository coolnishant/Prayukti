package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import java.util.jar.Attributes;

import static android.R.attr.data;
import static android.R.attr.defaultValue;
import static android.R.attr.reversible;

public class Registration extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextView tvName, tvEmail, tvPhone,tvPass,tvPasschk,tvRoll;
    String name = null, email = null, phone = null,pass = null,passchk = null,
            roll = null,batch = null, tsize = null, dept=null,uniqid=null,gender=null;
    Button bSubmit;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton;
    Spinner spBatch, spDept, spTsize;
    View viewof = null;
    int selectedid;
    JSONObject jsonObject;

    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_registration, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit) {
            Toast.makeText(context,"Testing1",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.register_menu,menu);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Register");
        viewof = view;
        initialize(view);
        initializeSpinner();
        bSubmit.setOnClickListener(this);
    }
    private void initializeSpinner() {

        spBatch.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Batch");
        categories.add("16");
        categories.add("L16");
        categories.add("15");
        categories.add("L15");
        categories.add("14");
        categories.add("L14");
        categories.add("13");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
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

        dataAdapter = new ArrayAdapter<String>(this.getActivity(),
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

        dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.my_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.my_spinner_item);

        // attaching data adapter to spinner
        spTsize.setAdapter(dataAdapter);
    }

    private void initialize(View view) {
        tvName = (TextView) view.findViewById(R.id.eTname);
        tvEmail = (TextView) view.findViewById(R.id.eTemail);
        tvPhone = (TextView) view.findViewById(R.id.eTphoneno);
        tvPass= (TextView) view.findViewById(R.id.eTpass);
        tvPasschk= (TextView) view.findViewById(R.id.eTpasschk);
        spBatch = (Spinner) view.findViewById(R.id.spinner_batch);
        spDept = (Spinner) view.findViewById(R.id.spinner_department);
        spTsize = (Spinner) view.findViewById(R.id.spinner_tsize);
        bSubmit = (Button) view.findViewById(R.id.btSubmit);
        tvRoll = (TextView) view.findViewById(R.id.eTroll);
        radioSexGroup = (RadioGroup) view.findViewById(R.id.radioSex);
        context = this.getActivity();
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
                //Done send data function
                boolean flag = validateInputedData();
                if(flag){
                    int selectedId=radioSexGroup.getCheckedRadioButtonId();
                    radioSexButton= (RadioButton)viewof.findViewById(selectedId);
                    gender=radioSexButton.getText().toString();
                    Intent intent = new Intent(getActivity(),SendJasonRegister.class);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("phone",phone);
                    intent.putExtra("gender",gender);
                    intent.putExtra("password",pass);
                    intent.putExtra("roll",roll.toLowerCase());
                    intent.putExtra("batch",batch);
                    intent.putExtra("tsize",tsize);
                    intent.putExtra("dept",dept);
//                    intent.putExtra("",);
                    getActivity().startActivity(intent);
                }
                break;
        }
    }

    private boolean validateInputedData() {
        if(validateName() && validateEmail()  && validatePhoneNo() && validateTSize()  && validatePassword()
                && validateBatch() && validateDept() && validateRoll())
            return true;
        return false;
    }

    private boolean validateTSize() {
        tsize = spTsize.getSelectedItem().toString().trim();
        if(tsize.contains("Size")){
            Toast tos = Toast.makeText(context,"Enter Your T-shirt size!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            tsize = null;
            return false;
        }
        return true;
    }

    private boolean validateDept() {
        dept = spDept.getSelectedItem().toString();
        if(dept.equals("Dept")){
            Toast tos = Toast.makeText(context,"Select Your department!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            dept = null;
            return false;
        }
        return true;
    }

    private boolean validateBatch() {
        batch = spBatch.getSelectedItem().toString();
        if(batch.equals("Batch")){
            Toast tos = Toast.makeText(context,"Select Your batch!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            batch = null;
            return false;
        }
        batch = batch.toLowerCase();
        return true;
    }

    private boolean validateEmail() {
        email = tvEmail.getText().toString();
        email.trim();
        if(email.length()==0){
            Toast tos = Toast.makeText(context,"Email is important!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            email = null;
            return false;
        }
        else if(email.contains("@") && email.contains(".com") && email.length()>9)
            return true;
        Toast tos = Toast.makeText(context,"Email-ID is not valid!",Toast.LENGTH_LONG);
        tos.setGravity(Gravity.TOP,0,0);
        tos.show();
        batch = null;
        return false;
    }


    private boolean validateRoll() {
        roll = tvRoll.getText().toString();
        roll.trim();
        if(roll.length()==0 || roll.equals("0") || roll.equals("00") || roll.equals("000")){
            Toast tos = Toast.makeText(context,"Enter Your Class Roll no!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP,0,0);
            tos.show();
            roll = null;
            return false;
        }
//        if(roll.length()==1){
//            roll = "00"+roll;
//        }
//        if(roll.length()==2){
//            roll = "0"+roll;
//        }
        return true;

    }

    private boolean validateName() {
        name = tvName.getText().toString();
        name.trim();
        if(name.length() == 0){
            Toast tos = Toast.makeText(context,"Enter Your \"Awesome\" Name please!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL,0,0);
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
            tos.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL,0,0);
            tos.show();
            return false;
        }
        else if (!pass.equals(passchk)){
            Toast tos = Toast.makeText(context,"Password didn't Matched, Retype Password!",Toast.LENGTH_LONG);
            tos.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL,0,0);
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
            tos.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL,0,0);
            tos.show();
            phone = null;
            return false;
        }
    }
}
