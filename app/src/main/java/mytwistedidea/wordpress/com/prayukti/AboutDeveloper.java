package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AboutDeveloper extends Fragment implements View.OnClickListener {

    ImageButton ibCall, ibFacebook;
    TextView tvMailMe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_about_developer,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("About Developer");
        ibCall = (ImageButton) view.findViewById(R.id.ibCallDev);
        ibFacebook = (ImageButton) view.findViewById(R.id.ibFacebookDev);
        tvMailMe = (TextView) view.findViewById(R.id.tvMailMe);

        ibCall.setOnClickListener(this);
        ibFacebook.setOnClickListener(this);
        tvMailMe.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id){
            case R.id.ibCall:
                Log.e("Call ","8972258387");
                intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + "8972258387"));
                getActivity().startActivity(intent);
                break;
            case R.id.ibFacebookDev:
                intent = new Intent(getActivity(),WebViewing.class);
                intent.putExtra("url", "https://www.facebook.com/imnishantraj");
                intent.putExtra("website","Prayukti @fb");
                startActivity(intent);
                break;
            case R.id.tvMailMe:
                Log.e("Email: ", "nishant2raj@gmail.com");
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"nishant2raj@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "I have some Suggestion for You.");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Nishant Raj,\n\t");

                try {
                    getActivity().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    Log.i("Finished sending em", "FInd");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}


