package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Events extends Fragment {
    String events[] = new String[15];
    Integer imageevent[] = new Integer[15];
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_events,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Events");
        events = getActivity().getResources().getStringArray(R.array
        .eventnames);
        this.view = view;
        getIvIDs();
        populateEventsView();
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
                CustomList(getActivity(), events, imageevent);
        final LinearLayout lly = (LinearLayout) view.findViewById(R.id.ll);
        ListView list=(ListView) view.findViewById(R.id.lv_eventsview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO intent to other
//                Toast.makeText(getActivity(), "You Clicked at " +events[+ position], Toast.LENGTH_SHORT).show();

                if(events[+position].trim().contains("Ranbhoomi")){
                    Intent intent = new Intent(getContext(),EventsMultiple.class);
                    intent.putExtra("from",events[+position].trim());
                    startActivity(intent);
                }
                else if(events[+position].trim().contains("Saudagar")){
                    Intent intent = new Intent(getContext(),EventsMultiple.class);
                    intent.putExtra("from",events[+position].trim());
                    startActivity(intent);
                }
                else if(events[+position].trim().contains("Yantriki")){
                    Intent intent = new Intent(getContext(),EventsMultiple.class);
                    intent.putExtra("from",events[+position].trim());
                    startActivity(intent);
                }
                else if(events[+position].trim().contains("Innovacion")){
                    Intent intent = new Intent(getContext(),EventsMultiple.class);
                    intent.putExtra("from",events[+position].trim());
                    startActivity(intent);
                }
                else if(events[+position].trim().contains("Frame To Frame")){
                    Intent intent = new Intent(getContext(),EventsMultiple.class);
                    intent.putExtra("from",events[+position].trim().substring(0,4));
                    startActivity(intent);
                }
//                else {
//                    Intent intent = new Intent(getContext(), EventData.class);
//                    intent.putExtra("eventclicked", events[+position]);
//                    intent.putExtra("position", position);
//                    getActivity().startActivity(intent);
//                }
                else {
                    //TODO POPUP window
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    RelativeLayout viewGroup = (RelativeLayout) getActivity().findViewById(R.id.popup);
                    View popupView = inflater.inflate(R.layout.events_popup_each, null);
                    final PopupWindow popup = new PopupWindow(inflater.inflate(
                            R.layout.events_popup_each, null, false), 200, 265, true);

//                    final PopupWindow popup = new PopupWindow(getActivity());
                    popup.setContentView(popupView);
                    popup.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                    popup.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                    popup.setFocusable(true);


                    if(Build.VERSION.SDK_INT>=21){
                        popup.setElevation(5.0f);
                    }

                    if(events[+position].trim().contains("Tete-A-Tete")) {
                        TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                        tvEventShow.setText("Tete-A-Tete");

                        ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                        iv_event_icon_large.setImageResource(R.drawable.pic_tete_a_tete_large);

                        TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                        tvEventInfo.setText(R.string.tete_a_tete);
                    }
                    else if(events[+position].trim().contains("Sherlocked")) {
                        TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                        tvEventShow.setText("R U Sherlocked");

                        ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                        iv_event_icon_large.setImageResource(R.drawable.pic_r_u_sherlocked_large);

                        TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                        tvEventInfo.setText(R.string.r_u_sherlocked);
                    }

                    else if(events[+position].trim().contains("Requizzit")) {
                        TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                        tvEventShow.setText("Requizzit");

                        ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                        iv_event_icon_large.setImageResource(R.drawable.pic_requizzit_large);

                        TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                        tvEventInfo.setText(R.string.requizzit);
                    }

                    else if(events[+position].trim().contains("mania")) {
                        TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                        tvEventShow.setText("Mania C");

                        ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                        iv_event_icon_large.setImageResource(R.drawable.pic_manic_c_large);

                        TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                        tvEventInfo.setText(R.string.mania_c);
                    }

                    else if(events[+position].trim().contains("Technology")) {
                        TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                        tvEventShow.setText("Jugaad Technology");

                        ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                        iv_event_icon_large.setImageResource(R.drawable.pic_jugaadtechnology_large);

                        TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                        tvEventInfo.setText(R.string.jugaad_technology);
                    }

                    else if(events[+position].trim().contains("Circuitrix")) {
                        TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                        tvEventShow.setText("Circuitrix");

                        ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                        iv_event_icon_large.setImageResource(R.drawable.pic_circuitrix_large);

                        TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                        tvEventInfo.setText(R.string.circuitrix);
                    }

                    else if(events[+position].trim().contains("Pradarshan")) {
                        TextView tvEventShow = (TextView) popupView.findViewById(R.id.tv_event_name_show);
                        tvEventShow.setText("Pradarshan");

                        ImageView iv_event_icon_large = (ImageView) popupView.findViewById(R.id.iv_event_icon_large);
                        iv_event_icon_large.setImageResource(R.drawable.pic_pradarshan_large);

                        TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tv_event_info);
                        tvEventInfo.setText(R.string.pradarshan);
                    }

                    ImageButton imageButton = (ImageButton) popupView.findViewById(R.id.ibbackpopup);

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast tos = Toast.makeText(getContext(), " :) ", Toast.LENGTH_SHORT);
                            tos.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
                            tos.show();
                            popup.dismiss();
                        }
                    });
                    popup.showAtLocation(lly, Gravity.CENTER,0,0);
                }
            }
        });
        }
    }

