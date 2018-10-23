package com.example.jyo05.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  public static final int REQ_PERM_CALL = 99;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // check permission for calling
    queryPerm();
  }

  private void init() {
    setContentView(R.layout.activity_main);
  }

  private void queryPerm() {
    String perms[] = {Manifest.permission.INTERNET, Manifest.permission.CALL_PHONE};
    int i = 0;

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      for(i = 0; i < perms.length; ++i) {
        if(checkSelfPermission(perms[i]) != PackageManager.PERMISSION_GRANTED) {
          requestPermissions(perms, REQ_PERM_CALL);
        }
      }
      if(i == perms.length) {
        init();
      }
    } else {
      // under mashmello
      init();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if(requestCode == REQ_PERM_CALL) {
      for(int i = 0; i < permissions.length; ++i) {
        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(getBaseContext(), "권한이 없으면 앱을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show();
          finish();
        }
      }
      init();
    }
  }

  public void openWeb(View v) {
    Intent intent = new Intent(getBaseContext(), WebActivity.class);
    startActivity(intent);
  }

  public void openCall(View v) {
    Intent intent = new Intent(getBaseContext(), CallActivity.class);
    startActivity(intent);
  }
}
