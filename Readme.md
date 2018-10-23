# Permission

### 동작 시현
![](/screenshot/permission.gif)


<br>

---
#### Manifest에 사용할 권한 추가

* WebView 기능 (INTERNET / Normal Permission)
* Call Phone 기능 (CALL_PHONE / Runtime Permission)

````java
<manifest .../>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.CALL_PHONE" />
    ...
</manifest>
````

<br>
---

#### 권한 확인 및 권한 요청

* INTERNET 권한은 manifest 외에 코드에서 추가되는 부분은 없다.
* M(마시멜로/API23) 버젼부터는 런타임시에 권한을 요청하며 L(롤리팝/API22)까지는 설치시에 권한을 요청하였다.
* M버젼 이상인 경우에 checkSelfPermission 메소드로 해당 권한이 승인되었는지를 확인하고, requestPermissions 메소드로 미승인된 권한을 요청한다.
* M버젼 미만인 경우는 요청을 하지않고 프로그램을 실행한다.

````java
String perms[] = {Manifest.permission.CALL_PHONE};
int i = 0;

if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
  for(i = 0; i < perms.length; ++i) {
    if(checkSelfPermission(perms[i]) != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(perms, REQ_PERM_CALL);
    }
  }
}
````

<br>

----
#### 요청 결과 확인하기

* 요청결과는 onRequestPermissionsResult 메소드는 콜백 호출이며, 요청 결과가 매개변수 grantResults로 넘어오게 된다.

````java
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if(requestCode == REQ_PERM_CALL) {
      for(int i = 0; i < permissions.length; ++i) {
        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(getBaseContext(), "권한이 없으면 앱을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show();
          finish();
        }
      }
      // 프로그램을 정상적으로 실행한다.
    }
  }
````

<br>

---
#### WebView 기능 사용하기

* URL 문자열을 가져와 WebView에 넘겨주면 URL로 이동한다.

````java
webView = findViewById(R.id.webView);
webView.setWebViewClient(new WebViewClient());
webView.setWebChromeClient(new WebChromeClient());

webView.getSettings().setJavaScriptEnabled(true);
webView.getSettings().setSupportZoom(true);
webView.getSettings().setBuiltInZoomControls(true);

webView.loadUrl("http://naver.com");
````

<br>

---
#### Call Phone 기능 사용하기

* 전화번호 문자열을 갖고와 Uri 객체로 만들어 Intent의 인자로 넘겨준다.

````java
Intent intent = new Intent(Intent.ACTION_CALL);
String callNum = "tel:" + editPhone.getText().toString();
intent.setData(Uri.parse(callNum));
startActivity(intent);
````