package com.example.admin.w6d3project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.Result;
import com.squareup.leakcanary.LeakCanary;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    Button btn;
    TextView tvScanResults;
    private ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        zXingScannerView = new ZXingScannerView(getApplicationContext());
        zXingScannerView.setResultHandler(this);

        Timber.d("onCreate: ");

        btn = findViewById(R.id.btnScan);
        tvScanResults = findViewById(R.id.tvScanResults);

        ((TheApplication)getApplicationContext()).getRefWatcher().watch(btn);
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
        Timber.d("paused: ");
    }

    public void btnScan_Clicked(View view)
    {
        setContentView(zXingScannerView);
        zXingScannerView.startCamera();
        Timber.d("scan Started: ");
    }

    @Override
    public void handleResult(Result result) {
        zXingScannerView.stopCamera();
        tvScanResults.setText(result.getText());
        Timber.d("scan Results: " + result.getText());
    }
}
