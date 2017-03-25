package mytwistedidea.wordpress.com.prayukti;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDescription extends Fragment {

    int position;
    String eventname;
    TextView tveventname,tveventdescription;
    ImageView iveventicon;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_event_description,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventData activity = (EventData) getActivity();
        position = activity.position;
        eventname = activity.eventname;
        this.view = view;
        initialize();
        assignData();
    }

    private void assignData() {
        switch (position) {
            case 0:
                String des[] = getActivity().getResources().getStringArray(R.array.tete_a_tete);
                iveventicon.setImageResource(R.drawable.pic_tete_a_tete);
                tveventname.setText(eventname);
                tveventname.setTextColor(Color.RED);
                tveventdescription.setText(des[0]);
                break;
           }
    }

    private void initialize() {
        tveventdescription = (TextView) view.findViewById(R.id.tvDescription);
        tveventname = (TextView) view.findViewById(R.id.tvEventNameIs);
        iveventicon = (ImageView) view.findViewById(R.id.ivEventPic);
    }

}
