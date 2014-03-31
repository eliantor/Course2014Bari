package me.aktor.corsobari.app;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by eto on 24/03/14.
 */
public class ActivityEdit extends FragmentActivity {


    public static final String RESULT_KEY = "RES";

    ConfirmFragment mConfirm;
    FragmentEdit mEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        FragmentManager manager = getSupportFragmentManager();
        mEdit =(FragmentEdit) manager.findFragmentById(R.id.EditFragment);
        mEdit.setOnEditCompleteListener(new FragmentEdit.OnEditCompletedListener() {
            @Override
            public void onCancel() {
                setResult(RESULT_CANCELED);
                finish();
            }

            @Override
            public void onResult(String text) {
                setResult(RESULT_OK,new Intent().putExtra(RESULT_KEY,text));
                finish();
            }
        });
    }


}
