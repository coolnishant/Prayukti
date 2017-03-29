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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListContact extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] name ;
    private final String[] post;
    private final String[] phone;
    private final String[] email;
    private final String emailsubject;
    public CustomListContact(Activity context,
                             String[] name, String[] post,String[] phone, String email[],String emailsubject) {
        super(context, R.layout.content_each_contact,name);
        this.context = context;
        this.name = name;
        this.post = post;
        this.phone = phone;
        this.email = email;
        this.emailsubject = emailsubject;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;
        if(emailsubject.contains("none"))
            rowView= inflater.inflate(R.layout.content_each_contact_coordinator, null, true);
        else
            rowView= inflater.inflate(R.layout.content_each_contact, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.tv_contactname);
        TextView txtPost = (TextView) rowView.findViewById(R.id.tv_contactpost);

        ImageButton ibCall , ibEmail;
        ibCall = (ImageButton) rowView.findViewById(R.id.ibCall);

        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Call
                Log.e("Call ",phone[+position]);
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + phone[position]));
                context.startActivity(intent);
            }
        });


        if(!emailsubject.contains("none")) {
            ibEmail = (ImageButton) rowView.findViewById(R.id.ibEmail);
            ibEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO Email
                    Log.e("Email: ", email[+position]);
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email[+position]});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear " + name[position] + ",\n\t");

                    try {
                        context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        Log.i("Finished sending em", "FInd");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        txtName.setText(name[+position]);
        txtPost.setText(post[+position]);

        return rowView;
    }

}
