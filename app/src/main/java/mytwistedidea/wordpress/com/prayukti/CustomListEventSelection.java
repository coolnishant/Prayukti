package mytwistedidea.wordpress.com.prayukti;

/**
 * Created by Nishant on 24-03-2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;

public class CustomListEventSelection extends ArrayAdapter<String>{

    private final Activity context;
    private final String eventname ;
    private final int position;
    public CustomListEventSelection(Activity context,
                                    String eventname, int position) {
        super(context, R.layout.content_each_event_selection, Integer.parseInt(eventname));
        this.context = context;
        this.eventname = eventname;
        this.position = position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;
        rowView= inflater.inflate(R.layout.content_each_contact_coordinator, null, true);

//        CheckedTextView txtEventName = (CheckedTextView) rowView.findViewById(R.id.ctvEvent);
//        if(txtEventName.isChecked()){
//
//
//
//        }

        return rowView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
