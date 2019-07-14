package io.audioshinigami.alcphasei.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.audioshinigami.alcphasei.R;
import io.audioshinigami.alcphasei.viewmodels.WebViewModel;

public class AboutALCActivity extends AppCompatActivity {

    private WebView alcWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);
        // set up actionbar to custom
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About ALC");
        }

        alcWebView = findViewById(R.id.id_about_alc_webview);
        initialiseWebView();

    } /*end onCreate*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    } /*end onOptionsItem*/

    private void initialiseWebView(){

        final String ALC_URL = "https://andela.com/alc";

        alcWebView.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view,final SslErrorHandler handler, SslError error) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(AboutALCActivity.this);
                builder.setMessage(R.string.ssl_error_cert_str);
                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        handler.cancel();
                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        alcWebView.getSettings().getJavaScriptEnabled();

        alcWebView.loadUrl(ALC_URL);
    }  /*end initialWebView*/
}
