package com.kuo.opengles_example;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.kuo.opengles_example.object.GLTriangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by User on 2016/5/14.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    private GLTriangle glTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        // ClearColor意思是說每一禎，使用這個顏色去清除畫面在重繪
        // 顏色模式為RGBA
        GLES20.glClearColor(0.5f, 0.5f, 0.8f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        // 設定攝影機的位置與大小
        GLES20.glViewport(0, 0, width, height);

        glTriangle = new GLTriangle(new float[] {
                0.0f,  0.622008459f, 0.0f,
                -0.5f, -0.311004243f, 0.0f,
                0.5f, -0.311004243f, 0.0f
        }, new float[] { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f });
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        // 將剛剛設置的顏色畫出來
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        glTriangle.draw();
    }

}
