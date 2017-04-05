package mytwistedidea.wordpress.com.prayukti;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class AboutDeveloper extends Fragment implements View.OnClickListener {

    ImageButton ibCall, ibFacebook, ibWhatsapp;
    TextView tvMailMe;
    ImageView ivMe;
    LinearLayout lly;

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
        ivMe = (ImageView) view.findViewById(R.id.riv_mypic);
        lly = (LinearLayout) view.findViewById(R.id.llaboutme);
        ibWhatsapp = (ImageButton) view.findViewById(R.id.ibWhatsappDev);

        ibCall.setOnClickListener(this);
        ibFacebook.setOnClickListener(this);
        tvMailMe.setOnClickListener(this);
        ivMe.setOnClickListener(this);
        ibWhatsapp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id){
            case R.id.ibCallDev:
                Log.e("Call ","8972258387");
                intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.my_phone)));
                this.startActivity(intent);
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
            case R.id.riv_mypic:
                Log.e("me:","here");
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    RelativeLayout viewGroup = (RelativeLayout) getActivity().findViewById(R.id.popup);
                View popupView = inflater.inflate(R.layout.popup_mypic, null);
                final PopupWindow popup = new PopupWindow(inflater.inflate(
                        R.layout.popup_mypic, null, false), 200, 265, true);
                popup.setContentView(popupView);
                popup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                popup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);

                if(Build.VERSION.SDK_INT>=21){
                    popup.setElevation(5.0f);
                }
                ImageView ivMeLarge = (ImageView) popupView.findViewById(R.id.ivMeLarge);

                Animation hyperspaceJump = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_zoom_out);
                ivMeLarge.startAnimation(hyperspaceJump);
                popup.showAtLocation(lly, Gravity.CENTER,0,0);
                break;
            case R.id.ibWhatsappDev:
                openWhatsApp();
                break;
        }

    }

    private void openWatsappChat() {
        Uri mUri = Uri.parse("smsto:+8051499246");
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
        mIntent.setPackage("com.whatsapp");
        mIntent.putExtra("sms_body", "Hey Nishant,");
        mIntent.putExtra("chat",true);
        startActivity(mIntent);
    }
    private void openWhatsApp() {
        String smsNumber = "+918051499246";
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {
            openWatsappChat();
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(getActivity(), "WhatsApp not Installed :(",Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}


