package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewing extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewing);
        Bundle extras = getIntent().getExtras();
        url = extras.getString("url");
        WebView browser = (WebView) findViewById(R.id.wVfbyt);
        browser.loadUrl(url);
        finish();
    }
}
