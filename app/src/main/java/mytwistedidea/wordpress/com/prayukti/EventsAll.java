package mytwistedidea.wordpress.com.prayukti;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Nishant on 28-03-2017.
 */

public class EventsAll extends Activity {
    String events[] = new String[15];
    Integer imageevent[] = new Integer[15];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_events);
        setTitle("Events");
        events = getResources().getStringArray(R.array
                .eventnames);
        getIvIDs();
        populateEventsView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    private void getIvIDs() {
        imageevent[0] = R.mipmap.pic_tete_a_tete;
        imageevent[1] = R.mipmap.pic_ranbhomi;
        imageevent[2] = R.mipmap.pic_r_u_sherlocked;
        imageevent[3] = R.mipmap.pic_saudagar;
        imageevent[4] = R.mipmap.pic_yantriki;
        imageevent[5] = R.mipmap.pic_innvacion;
        imageevent[6] = R.mipmap.pic_frametoframe;
        imageevent[7] = R.mipmap.pic_requizzit;
        imageevent[8] = R.mipmap.pic_manic_c;
        imageevent[9] = R.mipmap.pic_jugaadtechnology;
        imageevent[10] = R.mipmap.pic_circuitrix;
        imageevent[11] = R.mipmap.pic_pradarshan;
    }

    private void populateEventsView() {
        CustomList adapter = new
                CustomList(this, events, imageevent);
        ListView list=(ListView) findViewById(R.id.lv_eventsview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO intent to other
//                Toast.makeText(getActivity(), "You Clicked at " +events[+ position], Toast.LENGTH_SHORT).show();

                if(events[+position].trim().contains("Ranbhoomi")){
                    Intent intent = new Intent(EventsAll.this,EventsMultiple.class);
                    intent.putExtra("from",events[+position].trim());
                    startActivity(intent);
                }
//                else {
//                    Intent intent = new Intent(getContext(), EventData.class);
//                    intent.putExtra("eventclicked", events[+position]);
//                    intent.putExtra("position", position);
//                    getActivity().startActivity(intent);
//                }
                else {
                    Log.e("here","a");
                    //TODO POPUP window
//                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    RelativeLayout viewGroup = (RelativeLayout) findViewById(R.id.popup);
//                    View popupView = inflater.inflate(R.layout.events_popup_each, viewGroup);
////                    final PopupWindow popup = new PopupWindow(inflater.inflate(
////                            R.layout.events_popup_each, null, false), 200, 265, true);
//
//                    final PopupWindow popup = new PopupWindow(EventsAll.this);
//                    popup.setContentView(popupView);
//                    popup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
//                    popup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//                    popup.setFocusable(true);


                    LayoutInflater layoutInflater
                            = (LayoutInflater)getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.events_popup_each, null);
                    final PopupWindow popupWindow = new PopupWindow(
                            popupView,
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT);

                    ImageButton imageButton = (ImageButton) popupView.findViewById(R.id.ibbackpopup);

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast tos = Toast.makeText(EventsAll.this, " Welcome Again!!!! ", Toast.LENGTH_LONG);
                            tos.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
                            tos.show();
                            popupWindow.dismiss();
                        }
                    });
                }
            }
        });
    }
}
