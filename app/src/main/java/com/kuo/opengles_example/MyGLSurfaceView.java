package com.kuo.opengles_example;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by User on 2016/5/14.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private MyGLRenderer myGLRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // 設定GLES版本為2.0
        setEGLContextClientVersion(2);

        // 創建畫布
        createRenderer();
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private void createRenderer() {

        myGLRenderer = new MyGLRenderer();

        setRenderer(myGLRenderer);

        // 圖形數據改變才會重新繪製畫布的模式
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
