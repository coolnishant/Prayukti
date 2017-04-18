package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Schedule extends Fragment {

    String events[] = new String[15];
    Integer imageevent[] = new Integer[15];
    Context context;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_events,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Schedule");
        events = getActivity().getResources().getStringArray(R.array
                .eventnames);
        this.view = view;
        context = getActivity();
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

            }
        });
    }

}
