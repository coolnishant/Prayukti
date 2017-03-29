package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    final int SPLASH_DISPLAY_LENGTH = 1100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imPrayuktiLogo = (ImageView) findViewById(R.id.ivPrayukriLogoSplash);

        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.animation_zoom_out);
        imPrayuktiLogo.startAnimation(hyperspaceJump);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
