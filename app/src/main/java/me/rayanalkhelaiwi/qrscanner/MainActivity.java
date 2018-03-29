package me.rayanalkhelaiwi.qrscanner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    Context context;
    private ZXingScannerView zXingScannerView;
    private String QRtext = "";
    private Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        scanButton = (Button) findViewById(R.id.scan_button);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zXingScannerView = new ZXingScannerView(context);
                setContentView(zXingScannerView);

                zXingScannerView.setResultHandler((ZXingScannerView.ResultHandler) context);
                zXingScannerView.startCamera();

            }
        });
    }

    @Override
    public void handleResult(Result result) {

        zXingScannerView.stopCamera();
        QRtext = result.getText();
        Toast.makeText(this, QRtext, Toast.LENGTH_LONG).show();
        finish();
    }
}