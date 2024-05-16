package com.kamran.new_library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kamran.mylibrary.ToasterMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ToasterMessage.s(this, "hello");
    }
}