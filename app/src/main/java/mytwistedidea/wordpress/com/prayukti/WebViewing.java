package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewing extends AppCompatActivity {

    String url,website;
    WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_viewing);
        Bundle extras = getIntent().getExtras();
        url = extras.getString("url");
        website = extras.getString("website");
        setTitle(website);
        browser = (WebView) findViewById(R.id.wVurl);
        browser.loadUrl(url);
        Log.e("url",url);
        if(website.contains("@"))
            finish();
        else{
            browser.getSettings().setJavaScriptEnabled(true);
            browser.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            browser.loadUrl(browser.getUrl());
            return true;
        }
        else if(id == R.id.action_home){
            browser.loadUrl(url);
        }

        return super.onOptionsItemSelected(item);
    }
}
