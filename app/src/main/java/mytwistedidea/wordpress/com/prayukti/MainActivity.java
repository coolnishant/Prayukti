package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    final int TIME_BACK = 2;
    int k = TIME_BACK;
    int presentfrag = R.layout.activity_frag_home;
//    Button bper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = MainActivity.this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Permissions permissions = new Permissions(MainActivity.this);
        permissions.checkPermission();

        displaySelectedScreenFragment(R.id.nav_home);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            Toast.makeText(getApplicationContext(), "Press again to Exit!!", Toast.LENGTH_SHORT).show();
            k--;
        }else {
            super.onBackPressed();
        }
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
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

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
            case R.id.nav_contact:
                fragment = new ContactUs();
                presentfrag = R.layout.activity_frag_contact_us;
                break;
            case R.id.nav_about:
                fragment = new About();
                presentfrag = R.layout.activity_frag_about;
                break;
            case R.id.nav_registered:
                fragment = new IamRegistered();
                presentfrag = R.layout.activity_frag_iam_registered;
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
}
