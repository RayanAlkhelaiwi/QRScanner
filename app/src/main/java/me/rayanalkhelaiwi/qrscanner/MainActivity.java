package me.rayanalkhelaiwi.qrscanner;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.google.zxing.Result;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    Context context;
    Style croutonStyle;
    Configuration croutonConfiguration;
    private ZXingScannerView zXingScannerView;
    private String QRtext = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        croutonConfiguration = new Configuration.Builder().setDuration(5000).build();

        croutonStyle = new Style.Builder()
                .setBackgroundColorValue(Color.parseColor("#000000"))
                .setGravity(Gravity.CENTER_HORIZONTAL)
                .setConfiguration(croutonConfiguration)
                .setHeight(125)
                .setTextColorValue(Color.parseColor("#ffffff")).build();

        context = this;

        zXingScannerView = new ZXingScannerView(context);
        setContentView(zXingScannerView);

        zXingScannerView.setResultHandler((ZXingScannerView.ResultHandler) context);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {

        zXingScannerView.stopCamera();
        QRtext = result.getText();
        Crouton.showText(this, QRtext, croutonStyle);

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getString(R.string.success_copy), QRtext);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, R.string.success_copy, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}