package mytwistedidea.wordpress.com.prayukti;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Nishant on 23-03-2017.
 */

public class Permissions extends Activity{

    private static final int REQUEST_CALL = 200;
    private static final int REQUEST_AUDIO = 100;
    Context context = null;
    Permissions(Context context){
        this.context = context;
    }

    //FOR PERMISSIONS
    public boolean checkPermission()
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED )
//                    ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CALL_PHONE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Permission is necessary to do event!!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        //                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CALL);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//                    ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context,"Permission granted!!!!",Toast.LENGTH_SHORT).show();
                    Log.e("a","granted");
                } else {
                    Toast.makeText(context,"Permission Required Call!!!!",Toast.LENGTH_SHORT).show();
                }
                break;
//            case REQUEST_AUDIO:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(context,"Permission granted!!!!",Toast.LENGTH_SHORT).show();
//                    Log.e("a","granted");
//                } else {
////code for deny
//                    Toast.makeText(context,"Permission Required Audio!!!!",Toast.LENGTH_SHORT).show();
//
//                }
//                break;
        }
    }
}
