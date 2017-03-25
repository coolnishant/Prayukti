package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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
        ListView list=(ListView) view.findViewById(R.id.lv_eventsview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO intent to other
                Toast.makeText(getActivity(), "You Clicked at " +events[+ position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),EventData.class);
                intent.putExtra("eventclicked",events[+position]);
                intent.putExtra("position",position);
                getActivity().startActivity(intent);
            }
        });

    }

}
