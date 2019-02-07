package com.example.admin.zxingapi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.net.HttpURLConnection;

import static java.lang.System.load;

public class MainActivity extends AppCompatActivity {
    //ZXingScannerView zin;
    Button b;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button);
        webView=(WebView)this.findViewById(R.id.webview);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setPrompt("scanner");
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (intentResult!=null){
            if (intentResult.getContents()==null){
                Toast.makeText(this, "Scan Cancelled!", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
                String url=intentResult.getContents();


                webView.loadUrl(url);


            }
        }
        else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

}

