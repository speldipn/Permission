package com.example.jyo05.permission;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallActivity extends AppCompatActivity {

  Button btnCall;
  EditText editPhone;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_call);

    init();
  }

  private void init() {
    btnCall = findViewById(R.id.btnCall);
    editPhone = findViewById(R.id.editPhone);

    btnCall.setOnClickListener(new View.OnClickListener() {
      @SuppressLint("MissingPermission")
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String callNum = "tel:" + editPhone.getText().toString();
        intent.setData(Uri.parse(callNum));
        startActivity(intent);
      }
    });
  }
}
