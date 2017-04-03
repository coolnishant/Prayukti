package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Help extends Fragment {

    String helps[] = new String[15];
    String eachhelp[] = new String[15];
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_help,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Help");
        helps = getResources().getStringArray(R.array.help);
        eachhelp = getResources().getStringArray(R.array.eachhelp);
        Log.e("Help",helps[1]);
        this.view= view;
        populateHelpView();

    }

    private void populateHelpView() {
        CustomListHelp adapter = new
                CustomListHelp(getActivity(), helps);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.content_each_help_card,R.id.tvHelpName,helps);

        final LinearLayout lly = (LinearLayout) view.findViewById(R.id.llhelp);
        ListView list=(ListView) view.findViewById(R.id.lv_helpview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    RelativeLayout viewGroup = (RelativeLayout) getActivity().findViewById(R.id.popup);
                View popupView = inflater.inflate(R.layout.popup_each_help, null);
                final PopupWindow popup = new PopupWindow(inflater.inflate(
                        R.layout.popup_each_help, null, false), 200, 265, true);

//                    final PopupWindow popup = new PopupWindow(getActivity());
                popup.setContentView(popupView);
                popup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                popup.setHeight(600);
                popup.setFocusable(true);


                if(Build.VERSION.SDK_INT>=21){
                    popup.setElevation(5.0f);
                }
                TextView tvEventShow = (TextView) popupView.findViewById(R.id.tvEachEventHelp);
                tvEventShow.setText(helps[+position]);
                TextView tvEventInfo = (TextView) popupView.findViewById(R.id.tvEachDataHelp);
                tvEventInfo.setText(eachhelp[+position]);

                popup.showAtLocation(lly, Gravity.CENTER,0,0);
            }
        });

    }
}
