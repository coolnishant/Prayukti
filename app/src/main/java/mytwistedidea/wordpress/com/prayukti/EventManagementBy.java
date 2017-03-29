package mytwistedidea.wordpress.com.prayukti;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class EventManagementBy extends Fragment {

    View view;
    String name[] = new String[10];
    String post[] = new String[10];
    String phone[] = new String[10];
    String email[] = new String[10];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_event_management_by, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Event Management");
        this.view = view;
        name = getActivity().getResources().getStringArray(R.array
                .main_name);
        phone = getActivity().getResources().getStringArray(R.array
                .main_phone);
        email = getActivity().getResources().getStringArray(R.array
                .main_email);
        post = getActivity().getResources().getStringArray(R.array
                .main_post);

        populateListViewEventManagement();
    }

    private void populateListViewEventManagement() {
        CustomListContact adapter = new
                CustomListContact(getActivity(), name, post,phone,email,"About Prayukti '17");
        final LinearLayout lly = (LinearLayout) view.findViewById(R.id.llevman);
        ListView list = (ListView) view.findViewById(R.id.lv_eventmanagement);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });
    }

    private static final int REQUEST_CALL = 200;
    private static final int REQUEST_AUDIO = 100;
    //FOR PERMISSIONS
    private final Context context = getActivity();
    public boolean checkPermission()
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED )
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CALL_PHONE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Permission is necessary to do event!!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        //                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

}
