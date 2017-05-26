package cn.com.zfapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.baseutils.IntentUtils;

import cn.com.easypermissions.BasePermissionActivity;

public class MainActivity extends BasePermissionActivity implements View.OnClickListener {

    Button bt;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
        webView = (WebView)findViewById(R.id.webview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
//                webView.loadUrl("file:///sdcard/浅谈VC-编程技术与技巧研究.pdf");
                startActivity(IntentUtils.getPdfFileIntent("/storage/emulated/0/浅谈VC-编程技术与技巧研究.pdf"));
//                requestPermission(100, new String[]{Manifest.permission.CAMERA}, "拍照", new PermissionCallBackM() {
//                    @Override
//                    public void onPermissionGrantedM(int requestCode, String... perms) {
//                        Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
//                        camera();
//                    }
//
//                    @Override
//                    public void onPermissionDeniedM(int requestCode, String... perms) {
//                        Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
                break;
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(MainActivity.this,"获取权限成功",Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(MainActivity.this,"获取权限失败",Toast.LENGTH_SHORT).show();
//        }
//    }

    private void camera() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        startActivity(intent);
    }
}
