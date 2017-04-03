package mytwistedidea.wordpress.com.prayukti;

/**
 * Created by Nishant on 24-03-2017.
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListHelp extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] help;
    public CustomListHelp(Activity context,
                          String[] help) {
        super(context, R.layout.content_each_help_card, help);
        this.context = context;
        this.help = help;
//        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.content_each_help_card, null, true);
        TextView tvHelpName = (TextView) rowView.findViewById(R.id.tvHelpName);
        tvHelpName.setText(help[position]);
        Log.e("Help Cus: " ,help[position]);
        return rowView;
    }
}
