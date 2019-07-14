package io.audioshinigami.alcphasei.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.audioshinigami.alcphasei.R;
import io.audioshinigami.alcphasei.activities.AboutALCActivity;
import io.audioshinigami.alcphasei.activities.MyProfileActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button aboutAlCBtn;
    private Button myProfileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if( getSupportActionBar() != null ){
            getSupportActionBar().setTitle("ALC 4 Phase 1");
        } /*end if*/

        aboutAlCBtn = findViewById(R.id.id_btn_about_alc);
        myProfileBtn = findViewById(R.id.id_btn_my_profile);

        aboutAlCBtn.setOnClickListener(this);
        myProfileBtn.setOnClickListener(this);
    } /*end onCreate*/

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_btn_about_alc:
                clickAction(AboutALCActivity.class);
                break;
            case R.id.id_btn_my_profile:
                clickAction(MyProfileActivity.class);
                break;
        } /*end switch*/

    } /*end onClick*/

    private <T extends AppCompatActivity> void clickAction(Class<T> inputClass){
        Intent intent = new Intent(this, inputClass);
        startActivity(intent);
    }
}
