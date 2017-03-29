package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nishant on 28-03-2017.
 */

public class EventsMultiple extends AppCompatActivity {
    String subevents[] = new String[10];
    Integer imageevent[] = new Integer[10];
    TextView tvsubeventstext;
    String websiteIs = null;
    int fileURL = -1;

    String from;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_events_multiple);
        Bundle bundle = getIntent().getExtras();
        from = bundle.getString("from");
        tvsubeventstext = (TextView) findViewById(R.id.tv_subeventstext);
        Log.e("a", from);
        if (from.contains("Ranbhoomi")) {
            subevents = getResources().getStringArray(R.array
                    .ranbhoomi);
            tvsubeventstext.setText(R.string.ranbhoomi_main);
        } else if (from.contains("Saudagar")) {
            subevents = getResources().getStringArray(R.array
                    .saudagar);
            tvsubeventstext.setText(R.string.saudagar_main);
        } else if (from.contains("Yantriki")){
            subevents = getResources().getStringArray(R.array
                    .yantriki);
            tvsubeventstext.setText(R.string.yantriki_main);
    }
        else if(from.contains("Innovacion")) {
            subevents = getResources().getStringArray(R.array
                    .innovacion);
            tvsubeventstext.setText(R.string.innovacion_main);
        }
        else if(from.contains("Fra")) {
            subevents = getResources().getStringArray(R.array
                    .frame_to_frame);
            from = "Frame To Frame";
            tvsubeventstext.setText(R.string.frame_to_frame_main);
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
                //done intent to other
//                Toast.makeText(EventsMultiple.this, "You Clicked at " +subevents[+ position], Toast.LENGTH_SHORT).show();

                //Done POPUP window
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    RelativeLayout viewGroup = (RelativeLayout) getActivity().findViewById(R.id.popup);
                View popupView = inflater.inflate(R.layout.events_popup_each, null);
                final PopupWindow popup = new PopupWindow(inflater.inflate(
                        R.layout.events_popup_each, null, false), 200, 265, true);

//                    final PopupWindow popup = new PopupWindow(getActivity());
                popup.setContentView(popupView);
                popup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                popup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);


                if(Build.VERSION.SDK_INT>=21){
                    popup.setElevation(5.0f);
                }
                com.github.clans.fab.FloatingActionButton fab = (com.github.clans.fab.FloatingActionButton) popupView.
                        findViewById(R.id.fab_coordinators);

                websiteIs = subevents[+position]+" Info :)";

                if(subevents[+position].trim().contains("NFS")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("NFS-MW");

                    fileURL = R.string.nfs_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","NFS-MW");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_ranbhomi_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.nfs_mw);

                }
                else if(subevents[+position].trim().contains("Counter")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Counter-Strike 1.6");

                    fileURL = R.string.cs_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Counter Strike");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_ranbhomi_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.counter_strike);
                }
                else if(subevents[+position].trim().contains("Mini")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Mini-Militia");

                    fileURL = R.string.mini_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Mini-Militia");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_ranbhomi_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.mini_militia);
                }
                else if(subevents[+position].trim().contains("FIFA")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("FIFA 11");

                    fileURL = R.string.fifa_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","FIFA 11");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_ranbhomi_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.fifa_11);
                }
                //Saudagar
                else if(subevents[+position].trim().contains("Plan")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("B Plan");

                    fileURL = R.string.bplan_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","B Plan");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_saudagar_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.b_plan);
                }
                else if(subevents[+position].trim().contains("Constructeur")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("D-Constructeur");

                    fileURL = R.string.dconstructeur_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","D-Construcueur");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_saudagar_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.d_contructeur);
                }
                //Yantriki
                else if(subevents[+position].trim().contains("Vinashak")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Vinashak");

                    fileURL = R.string.vinashak_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Vinashak");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_yantriki_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.vinashak);
                }
                else if(subevents[+position].trim().contains("Aqua")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Aqua Soccer");

                    fileURL = R.string.aqua_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Aqua Soccer");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_yantriki_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.aqua_soccer);
                }
                else if(subevents[+position].trim().contains("JunkBot")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("JunkBot War");

                    fileURL = R.string.junkbot_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","JunkBot War");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_yantriki_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.junkbot_war);
                }
                else if(subevents[+position].trim().contains("Track")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Track-O-Bot");

                    fileURL = R.string.track_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Track-O-Bot");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_yantriki_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.track_o_bot);
                }
                //Innovacion
                else if(subevents[+position].trim().contains("Paper")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Paper Presentation");

                    fileURL = R.string.paper_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Paper Presentation");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_innvacion_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.paper_presenation);
                }
                else if(subevents[+position].trim().contains("Poster")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Poster Presentation");

                    fileURL = R.string.poster_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Poster Presentation");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_innvacion_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.poster_presentaion);
                }
                //Frame To Frame
                else if(subevents[+position].trim().contains("Movier")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("De-Movier");

                    fileURL = R.string.movier_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","De-Movier");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_frametoframe_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.de_movier);
                }
                else if(subevents[+position].trim().contains("Photographer")) {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("La Photographer");

                    fileURL = R.string.photographe_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","La Photographer");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_frametoframe_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.la_photographe);
                }
                else {
                    TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                    tvEventShow.setText("Paper Presentation");

                    fileURL = R.string.paper_file;

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                            Intent intent = new Intent(EventsMultiple.this,EventCoordinator.class);
                            intent.putExtra("event","Paper Presentation");
//                            intent.putExtra("website", "Prayukti '17");
                            startActivity(intent);
                        }
                    });

                    ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                    iv_event_icon_large.setImageResource(R.drawable.pic_innvacion_large);

                    TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                    tvEventInfo.setText(R.string.paper_presenation);
                }

                ImageButton ibfileshow = (ImageButton) popupView.findViewById(R.id.ibfileShow);

                ibfileshow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (fileURL == -1) {
                            Toast tos = Toast.makeText(EventsMultiple.this, "No File Event!\nContact Coordinators :)", Toast.LENGTH_SHORT);
                            tos.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
                            tos.show();
                        } else {
                            Intent intent = new Intent(EventsMultiple.this, WebViewing.class);
                            intent.putExtra("url", getResources().getString(fileURL));
                            intent.putExtra("website", websiteIs);
                            startActivity(intent);
                        }
                    }
                });

                ImageButton imageButton = (ImageButton) popupView.findViewById(R.id.ibbackpopup);

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast tos = Toast.makeText(EventsMultiple.this, " :) ", Toast.LENGTH_SHORT);
                        tos.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
                        tos.show();
                        popup.dismiss();
                    }
                });
                final LinearLayout lly = (LinearLayout) findViewById(R.id.lly2);
                popup.showAtLocation(lly, Gravity.CENTER,0,0);


//                Intent intent = new Intent(EventsMultiple.this,EventData.class);
//                intent.putExtra("eventclicked",subevents[+position]);
//                intent.putExtra("position",position);
//                startActivity(intent);


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
            imageevent[0] = R.mipmap.pic_saudagar;
            imageevent[1] = R.mipmap.pic_saudagar;
            imageevent[2] = R.mipmap.pic_saudagar;
            imageevent[3] = R.mipmap.pic_saudagar;
        }
        else if (from.equals("Yantriki")) {
            imageevent[0] = R.mipmap.pic_yantriki;
            imageevent[1] = R.mipmap.pic_yantriki;
            imageevent[2] = R.mipmap.pic_yantriki;
            imageevent[3] = R.mipmap.pic_yantriki;
        }
        else if (from.equals("Innovacion")) {
            imageevent[0] = R.mipmap.pic_innvacion;
            imageevent[1] = R.mipmap.pic_innvacion;
            imageevent[2] = R.mipmap.pic_innvacion;
            imageevent[3] = R.mipmap.pic_innvacion;
        }
        else if (from.equals("Frame To Frame")) {
            imageevent[0] = R.mipmap.pic_frametoframe;
            imageevent[1] = R.mipmap.pic_frametoframe;
            imageevent[2] = R.mipmap.pic_frametoframe;
            imageevent[3] = R.mipmap.pic_frametoframe;
        }
    }


}
