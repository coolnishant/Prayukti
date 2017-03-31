package mytwistedidea.wordpress.com.prayukti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class EventCoordinator extends AppCompatActivity {

    View view;
    String name[] = new String[10];
    String post[] = new String[10];
    String phone[] = new String[10];
    String email[] = new String[10];

    String eventname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_coordinator);

        Bundle bundle = getIntent().getExtras();
        eventname = bundle.getString("event");
        setTitle(eventname+" Co-Ordinators");
        choosingData();
        populateListViewEventCoordinators();
    }

    private void choosingData() {

        email = getResources().getStringArray(R.array
                .nulll);
        if(eventname.contains("NFS")){
            name = getResources().getStringArray(R.array
                    .nfs_name);
            phone = getResources().getStringArray(R.array
                    .nfs_phone);
            post = getResources().getStringArray(R.array
                    .nfs_post);
        }
        else if(eventname.contains("Counter")){
            name = getResources().getStringArray(R.array
                    .cs_name);
            phone = getResources().getStringArray(R.array
                    .cs_phone);
            post = getResources().getStringArray(R.array
                    .cs_post);
        }
        else if(eventname.contains("Mini")){
            name = getResources().getStringArray(R.array
                    .mini_name);
            phone = getResources().getStringArray(R.array
                    .mini_phone);
            post = getResources().getStringArray(R.array
                    .mini_post);
        }
        else if(eventname.contains("Plan")){
            name = getResources().getStringArray(R.array
                    .bplan_name);
            phone = getResources().getStringArray(R.array
                    .bplan_phone);
            post = getResources().getStringArray(R.array
                    .bplan_post);
        }

        else if(eventname.contains("Constructeur")){
            name = getResources().getStringArray(R.array
                    .dconstructeur_name);
            phone = getResources().getStringArray(R.array
                    .dconstructeur_phone);
            post = getResources().getStringArray(R.array
                    .dconstructeur_post);
        }

        else if(eventname.contains("Vinashak")){
            name = getResources().getStringArray(R.array
                    .vinashak_name);
            phone = getResources().getStringArray(R.array
                    .vinashak_phone);
            post = getResources().getStringArray(R.array
                    .vinashak_post);
        }

        else if(eventname.contains("Aqua")){
            name = getResources().getStringArray(R.array
                    .aquasoccer_name);
            phone = getResources().getStringArray(R.array
                    .aquasoccer_phone);
            post = getResources().getStringArray(R.array
                    .aquasoccer_post);
        }

        else if(eventname.contains("JunkBot")){
            name = getResources().getStringArray(R.array
                    .junkbotwar_name);
            phone = getResources().getStringArray(R.array
                    .junkbotwar_phone);
            post = getResources().getStringArray(R.array
                    .junkbotwar_post);
        }

        else if(eventname.contains("Track")){
            name = getResources().getStringArray(R.array
                    .trackobot_name);
            phone = getResources().getStringArray(R.array
                    .trackobot_phone);
            post = getResources().getStringArray(R.array
                    .trackobot_post);
        }

        else if(eventname.contains("Paper")){
            name = getResources().getStringArray(R.array
                    .paper_name);
            phone = getResources().getStringArray(R.array
                    .paper_phone);
            post = getResources().getStringArray(R.array
                    .paper_post);
        }

        else if(eventname.contains("Poster")){
            name = getResources().getStringArray(R.array
                    .poster_name);
            phone = getResources().getStringArray(R.array
                    .poster_phone);
            post = getResources().getStringArray(R.array
                    .poster_post);
        }
        else if(eventname.contains("Movier")){
            name = getResources().getStringArray(R.array
                    .poster_name);
            phone = getResources().getStringArray(R.array
                    .poster_phone);
            post = getResources().getStringArray(R.array
                    .poster_post);
        }
        else if(eventname.contains("Photographer")){
            name = getResources().getStringArray(R.array
                    .laphotographe_name);
            phone = getResources().getStringArray(R.array
                    .laphotographe_phone);
            post = getResources().getStringArray(R.array
                    .laphotographe_post);
        }
        else if(eventname.contains("Poster")) {
            name = getResources().getStringArray(R.array
                    .poster_name);
            phone = getResources().getStringArray(R.array
                    .poster_phone);
            post = getResources().getStringArray(R.array
                    .poster_post);
        }
        else if(eventname.contains("Sherlocked")){
            name = getResources().getStringArray(R.array
                    .rusherlocked_name);
            phone = getResources().getStringArray(R.array
                    .rusherlocked_phone);
            post = getResources().getStringArray(R.array
                    .rusherlocked_post);
        }
        else if(eventname.contains("Mania")){
            name = getResources().getStringArray(R.array
                    .maniac_name);
            phone = getResources().getStringArray(R.array
                    .maniac_phone);
            post = getResources().getStringArray(R.array
                    .maniac_post);
        }
        else if(eventname.contains("Requizzit")){
            name = getResources().getStringArray(R.array
                    .requizzit_name);
            phone = getResources().getStringArray(R.array
                    .requizzit_phone);
            post = getResources().getStringArray(R.array
                    .requizzit_post);
        }
        else if(eventname.contains("Technology")){
            name = getResources().getStringArray(R.array
                    .jugaadtechnology_name);
            phone = getResources().getStringArray(R.array
                    .jugaadtechnology_phone);
            post = getResources().getStringArray(R.array
                    .jugaadtechnology_post);
        }
        else if(eventname.contains("Circuitrix")){
            name = getResources().getStringArray(R.array
                    .circuitrix_name);
            phone = getResources().getStringArray(R.array
                    .circuitrix_phone);
            post = getResources().getStringArray(R.array
                    .circuitrix_post);
        }
        else if(eventname.contains("Pradarshan")){
            name = getResources().getStringArray(R.array
                    .pradarshan_name);
            phone = getResources().getStringArray(R.array
                    .pradarshan_phone);
            post = getResources().getStringArray(R.array
                    .pradarshan_post);
        }
        else{
            name = getResources().getStringArray(R.array
                    .circuitrix_name);
            phone = getResources().getStringArray(R.array
                    .circuitrix_phone);
            post = getResources().getStringArray(R.array
                    .circuitrix_post);
        }
    }

    private void populateListViewEventCoordinators() {
        CustomListContact adapter = new
                CustomListContact(this, name, post,phone,email,"none");
//        final LinearLayout lly = (LinearLayout) findViewById(R.id.llevman);
        ListView list = (ListView) findViewById(R.id.lv_eventcoordinators);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });
    }

}
