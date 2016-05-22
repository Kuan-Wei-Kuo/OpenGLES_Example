package com.kuo.opengles_example.object;

import android.opengl.GLES20;

import com.kuo.opengles_example.shader.SampleShader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by User on 2016/5/22.
 */
public class GLTriangle {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    private int mProgram;

    private int mPositionHandle;
    private int mColorHandle;

    static final int COORDS_PER_VERTEX = 3;

    private final int vertexStride;

    static short[] indices = new short[] {0, 1, 2}; // 繪畫順序

    float triangleCoords[] = {
            0.0f,  0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };

    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    public GLTriangle(float triangleCoords[], float color[]) {

        this.triangleCoords = triangleCoords;
        this.color = color;

        vertexStride = COORDS_PER_VERTEX * 4;

        createBuffer();
        createShader();

    }

    private void createBuffer() {

        // 初始化頂點座標的緩衝區
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        // 初始化繪畫順序的緩衝區
        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(indices);
        drawListBuffer.position(0);
    }

    private void createShader() {

        // 座標
        int vertexShader = SampleShader.loadShader(GLES20.GL_VERTEX_SHADER,
                SampleShader.vertexShaderCode);

        // 顏色
        int fragmentShader = SampleShader.loadShader(GLES20.GL_FRAGMENT_SHADER,
                SampleShader.fragmentShaderCode);

        // 創建一個空的Program
        mProgram = GLES20.glCreateProgram();

        // 將渲染加入Program內
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }

    public void draw() {

        // Program加入ES的環境中
        GLES20.glUseProgram(mProgram);

        // 從Program得到我們所需要的頂點處理器
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // 啟用三角形頂點
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // 準備三角形頂點的資料
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // 從Program得到我們的顏色渲染
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // 設定顏色至三角形中
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // 畫出三角形
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
