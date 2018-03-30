package me.rayanalkhelaiwi.qrscanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButtonActivity extends AppCompatActivity {

    @BindView(R.id.scan_button)
    Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        ButterKnife.bind(this);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ButtonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
