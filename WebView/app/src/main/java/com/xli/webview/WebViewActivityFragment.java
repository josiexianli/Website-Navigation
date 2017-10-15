package com.xli.webview;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class WebViewActivityFragment extends Fragment {
    private String[] urls;

    public WebViewActivityFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        urls = getResources().getStringArray(R.array.url_array);

        final RadioGroup radioUrlGroup =
                (RadioGroup)getActivity().findViewById(R.id.web_radioGroup1);
        radioUrlGroup.check(R.id.web_radio0);

        final WebView myWebView =
                (WebView)getActivity().findViewById(R.id.web_webView);
        myWebView.loadUrl(urls[0]);
        myWebView.setWebViewClient(new MyWebViewClient());

        Button showWebSiteBtn = (Button)getActivity().findViewById(R.id.web_showButton);
        showWebSiteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayWebSite(radioUrlGroup, myWebView);
            }
        });
    }

    private void displayWebSite(RadioGroup radioUrlGroup, WebView myWebView){
        int selectedButtonId = radioUrlGroup.getCheckedRadioButtonId();
        View radioUrlButton = radioUrlGroup.findViewById(selectedButtonId);
        int index = radioUrlGroup.indexOfChild(radioUrlButton);
        if (index > urls.length) index = urls.length - 1;
        if (index < 0) index = 0;
        myWebView.loadUrl(urls[index]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }
}

 class MyWebViewClient extends WebViewClient {

    @Override
    @TargetApi(Build.VERSION_CODES.N)
    public boolean shouldOverrideUrlLoading(WebView view,
                                            WebResourceRequest request){
        String url= request.getUrl().toString();
        view.loadUrl(url);
        return true;
    }
}