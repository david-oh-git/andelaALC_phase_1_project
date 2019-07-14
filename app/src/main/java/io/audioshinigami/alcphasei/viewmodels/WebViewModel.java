package io.audioshinigami.alcphasei.viewmodels;

import android.app.Application;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.AndroidViewModel;

import io.audioshinigami.alcphasei.R;

public class WebViewModel extends AndroidViewModel {

    public WebView webView = null;

    public WebViewModel(Application application){
        super(application);

    } /*end constructor*/

    public WebView initialiseWebView(){

        if( webView == null ){
            webView = new WebView(getApplication());
            final String ALC_URL = "https://andela.com/alc";
            webView.setWebViewClient( new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }

                @Override
                public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
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
            webView.getSettings().getJavaScriptEnabled();

            webView.loadUrl(ALC_URL);
        } /*end if*/

        return webView;
    }  /*end initialWebView*/
}
