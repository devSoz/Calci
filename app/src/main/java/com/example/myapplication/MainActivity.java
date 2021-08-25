package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ReportFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Result fragmentResult;
    private Keypad fragmentKeypad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentResult = new Result();
        fragmentKeypad = new Keypad();
        setContentView(R.layout.activity_main);
        loadFragmentInstance();
    }

    private void loadFragmentInstance()
    {
      /*  FragmentManager fm = getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentKeypad, );
        fragmentTransaction.commit(); // save the changes*/

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentKeypad, new Keypad())
                .add(R.id.fragmentResult, new Result())
                .commit();
    }

    public void btnClicked(View v) {
        fragmentKeypad.btnClicked(v);
    }
}