package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Nishant on 28-03-2017.
 */

public class EventsMultiple extends AppCompatActivity {
    String subevents[] = new String[10];
    Integer imageevent[] = new Integer[10];

    String from;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_events_multiple);
        String events[] = new String[15];
        Integer imageevent[] = new Integer[15];
        Bundle bundle = getIntent().getExtras();
        from = bundle.getString("from");
        Log.e("a",from);
        if(from.contains("Ranbhoomi"))
            subevents = getResources().getStringArray(R.array
                .ranbhoomi);
        else if(from.contains("Saudagar"))
            subevents = getResources().getStringArray(R.array
                    .saudagar);

        else if(from.contains("Yantriki"))
            subevents = getResources().getStringArray(R.array
                    .yantriki);

        else if(from.contains("Innovacion"))
            subevents = getResources().getStringArray(R.array
                    .innovacion);

        else if(from.contains("Fra")) {
            subevents = getResources().getStringArray(R.array
                    .fra);
            from = "Frame To Frame";
        }

        setTitle(from);
        getIvIDs();
        populateEventsView();

    }

    private void populateEventsView() {
        CustomList adapter = new
                CustomList(this, subevents, imageevent);
        ListView list=(ListView) findViewById(R.id.lv_eventsview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO intent to other
                Toast.makeText(EventsMultiple.this, "You Clicked at " +subevents[+ position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EventsMultiple.this,EventData.class);
                intent.putExtra("eventclicked",subevents[+position]);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    private void getIvIDs() {
        if (from.equals("Ranbhoomi")) {
            imageevent[0] = R.drawable.nfs_most_wanted_icon;
            imageevent[1] = R.drawable.counter_strike_icon;
            imageevent[2] = R.drawable.fifa_11_icon;
            imageevent[3] = R.drawable.mili_militia_icon;
        }
        else if (from.equals("Saudagar")) {
            imageevent[0] = R.drawable.nfs_most_wanted_icon;
            imageevent[1] = R.drawable.counter_strike_icon;
            imageevent[2] = R.drawable.fifa_11_icon;
            imageevent[3] = R.drawable.mili_militia_icon;
        }
        else if (from.equals("Yantriki")) {
            imageevent[0] = R.drawable.nfs_most_wanted_icon;
            imageevent[1] = R.drawable.counter_strike_icon;
            imageevent[2] = R.drawable.fifa_11_icon;
            imageevent[3] = R.drawable.mili_militia_icon;
        }
        else if (from.equals("Innovacion")) {
            imageevent[0] = R.drawable.nfs_most_wanted_icon;
            imageevent[1] = R.drawable.counter_strike_icon;
            imageevent[2] = R.drawable.fifa_11_icon;
            imageevent[3] = R.drawable.mili_militia_icon;
        }
        else if (from.equals("Frame To Frame")) {
            imageevent[0] = R.drawable.nfs_most_wanted_icon;
            imageevent[1] = R.drawable.counter_strike_icon;
            imageevent[2] = R.drawable.fifa_11_icon;
            imageevent[3] = R.drawable.mili_militia_icon;
        }
    }


}
