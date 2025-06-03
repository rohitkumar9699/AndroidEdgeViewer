
package com.example.edgeviewer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;

public class Renderer implements GLSurfaceView.Renderer {
    private int textureId;
    private int program;
    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;

    private final float[] vertices = {
            -1.0f,  1.0f,  // Top-left
            -1.0f, -1.0f,  // Bottom-left
             1.0f, -1.0f,  // Bottom-right
             1.0f,  1.0f   // Top-right
    };

    private final float[] textureCoords = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };

    private final short[] drawOrder = {0, 1, 2, 0, 2, 3};

    private final String vertexShaderCode =
        "attribute vec4 vPosition;" +
        "attribute vec2 inputTextureCoordinate;" +
        "varying vec2 textureCoordinate;" +
        "void main() {" +
        "  gl_Position = vPosition;" +
        "  textureCoordinate = inputTextureCoordinate;" +
        "}";

    private final String fragmentShaderCode =
        "precision mediump float;" +
        "uniform sampler2D texture;" +
        "varying vec2 textureCoordinate;" +
        "void main() {" +
        "  gl_FragColor = texture2D(texture, textureCoordinate);" +
        "}";

    public Renderer() {
        vertexBuffer = ByteBuffer.allocateDirect(vertices.length * 4)
                        .order(ByteOrder.nativeOrder())
                        .asFloatBuffer()
                        .put(vertices);
        vertexBuffer.position(0);

        textureBuffer = ByteBuffer.allocateDirect(textureCoords.length * 4)
                         .order(ByteOrder.nativeOrder())
                         .asFloatBuffer()
                         .put(textureCoords);
        textureBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        program = createProgram(vertexShaderCode, fragmentShaderCode);
        textureId = generateTexture();
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUseProgram(program);

        int positionHandle = GLES20.glGetAttribLocation(program, "vPosition");
        int textureCoordHandle = GLES20.glGetAttribLocation(program, "inputTextureCoordinate");

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 2, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        GLES20.glEnableVertexAttribArray(textureCoordHandle);
        GLES20.glVertexAttribPointer(textureCoordHandle, 2, GLES20.GL_FLOAT, false, 0, textureBuffer);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 4);

        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(textureCoordHandle);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    private int createProgram(String vertexSource, String fragmentSource) {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);

        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
        return program;
    }

    private int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    private int generateTexture() {
        int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        return texture[0];
    }
}
