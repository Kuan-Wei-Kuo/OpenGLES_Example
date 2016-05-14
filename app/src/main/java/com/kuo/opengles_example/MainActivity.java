package com.kuo.opengles_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 把Title拿掉，一定要在super之前
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        // 全螢幕設置
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MyGLSurfaceView myGLSurfaceView = new MyGLSurfaceView(this);

        // 我們可以直接設置View
        setContentView(myGLSurfaceView);
    }
}
