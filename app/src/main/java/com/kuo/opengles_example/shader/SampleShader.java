package com.kuo.opengles_example.shader;

import android.opengl.GLES20;

/**
 * Created by User on 2016/5/22.
 */
public class SampleShader {

    public static final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    public static final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    public static int loadShader(int type, String shaderCode){

        //兩種不同的Type
        //GLES20.GL_VERTEX_SHADER or GLES20.GL_FRAGMENT_SHADER
        int shader = GLES20.glCreateShader(type);

        // 加入所訂製的Shader並且編譯
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
