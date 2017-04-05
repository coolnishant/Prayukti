package mytwistedidea.wordpress.com.prayukti;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ShareActionProvider mShareActionProvider;
    final String MY_PREFS_NAME = "contactdatabasecheck";
    final int TIME_BACK = 1;
    int k = TIME_BACK;
    final String myAppLink = "https://drive.google.com/drive/folders/0BwKuFWuV41ZUZktUZzJZS0tzZHc";
    int presentfrag = R.layout.activity_frag_home;
//    Button bper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = MainActivity.this;

//        if(!checkingNoContactDatabaseFull()){
//            fillDatabase();
//            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//            editor.putBoolean("commited", true);
////            editor.putInt("phone1", );
//            editor.commit();
//        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                k = TIME_BACK;
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                k = TIME_BACK;
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        checkPermission();
        displaySelectedScreenFragment(R.id.nav_home);
    }

//    private void fillDatabase() {
//        String name[] = new String[100];
//        String phone[] = new String[100];
//
//    }

    private boolean checkingNoContactDatabaseFull() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            boolean testData = prefs.getBoolean("commited", false);//"No name defined" is the default value.
            if(testData){
               return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(k!=0 && presentfrag == R.layout.activity_frag_registration) {
            Toast tos = Toast.makeText(getApplicationContext(), "On Back will Lose DATA!", Toast.LENGTH_SHORT);
            tos.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
            tos.show();
            k--;
        }
        else if(presentfrag != R.layout.activity_frag_home){
            Fragment fragment = new Home();
            presentfrag = R.layout.activity_frag_home;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_mainis,fragment);
            ft.commit();
            k = TIME_BACK;
        }
        else if(k!=0 && presentfrag == R.layout.activity_frag_home) {
            Toast tos = Toast.makeText(getApplicationContext(), "Press again to Exit!!", Toast.LENGTH_SHORT);
            tos.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
            tos.show();
            k--;
        }
        else {
            super.onBackPressed();
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                k=TIME_BACK;
                Log.e("reset",k+"");
            }
        }, 3100);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_aboutdeveloper) {
            Fragment fragment = new AboutDeveloper();
            presentfrag = R.layout.activity_frag_about_developer;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_mainis, fragment);
            ft.commit();
            return true;
        }
            else if(id == R.id.action_item_share){
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Download Prayukti '17 App");
                i.putExtra(Intent.EXTRA_TEXT, "Please Download\nPrayukti '17 From\n"+myAppLink);
                startActivity(Intent.createChooser(i, "Share URL"));
                return true;
            }
        else if(id == R.id.action_apphelp){
            Fragment fragment = new Help();
            presentfrag = R.layout.activity_frag_help;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_mainis, fragment);
            ft.commit();
        }
        else if(id == R.id.action_rate){
            Intent intent = new Intent(this,WebViewing.class);
            intent.putExtra("url", "https://goo.gl/forms/2RzNGTLvyL6T2VKl2");
            intent.putExtra("website","Prayukti App Survey");
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    public void hideKeybord() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        hideKeybord();
//        if(id == R.id.nav_events){
//            Intent intent = new Intent(MainActivity.this,EventsAll.class);
//            startActivity(intent);
//            return  true;
//        }

//        if(id == R.id.nav_register){
//            Intent intent = new Intent(MainActivity.this, NewRegister.class);
//            startActivity(intent);
//            return true;
//        }
        displaySelectedScreenFragment(id);
        return true;
    }

    private void displaySelectedScreenFragment(int id){
        Fragment fragment = null;
        checkPermission();
        switch(id) {
            case R.id.nav_home:
                fragment = new Home();
                presentfrag = R.layout.activity_frag_home;
                break;
            case R.id.nav_stream:
                fragment = new LiveStream();
                presentfrag = R.layout.activity_frag_live_stream;
                break;
            case R.id.nav_events:
                fragment = new Events();
                presentfrag = R.layout.activity_frag_events;
                break;
            case R.id.nav_schedule:
                fragment = new Schedule();
                presentfrag = R.layout.activity_frag_schedule;
                break;
            case R.id.nav_register:
                fragment = new Registration();
                presentfrag = R.layout.activity_frag_registration;
                break;
//            case R.id.nav_contact:
//                fragment = new AboutDeveloper();
//                presentfrag = R.layout.activity_frag_about_developer;
//                break;
            case R.id.nav_about:
                fragment = new About();
                presentfrag = R.layout.activity_frag_about;
                break;
            case R.id.nav_registered:
                fragment = new IamRegistered();
                presentfrag = R.layout.activity_frag_iam_registered;
                break;
            case R.id.nav_sponsers:
                fragment = new Sponsers();
                presentfrag = R.layout.activity_frag_sponsers;
                break;
            case R.id.nav_eventby:
                fragment = new EventManagementBy();
                presentfrag = R.layout.activity_frag_event_management_by;
                break;
            case R.id.nav_about_developer:
                fragment = new AboutDeveloper();
                presentfrag = R.layout.activity_frag_about_developer;
                break;
            case R.id.nav_login:
                fragment = new LoginId();
                presentfrag = R.layout.activity_frag_login_id;
                break;
        }
        if(presentfrag != R.layout.activity_frag_home){
            k=TIME_BACK;
        }
        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_mainis,fragment);
            ft.commit();
        }

        //Close the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private static final int REQUEST_CALL = 200;
    private static final int REQUEST_AUDIO = 100;
    Context context = MainActivity.this;
    //FOR PERMISSIONS
    public boolean checkPermission()
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED )
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission Necessary :)");
                    alertBuilder.setMessage("Permission is Necessary to Call for Details!!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        //                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission granted!!!!",Toast.LENGTH_SHORT).show();
                    Log.e("a","granted");
                } else {
                    Toast.makeText(this,"Permission Required Call!!!!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
