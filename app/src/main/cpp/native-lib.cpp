
#include <jni.h>
#include <opencv2/opencv.hpp>

extern "C"
JNIEXPORT void JNICALL
Java_com_example_edgeviewer_MainActivity_processFrame(JNIEnv *env, jobject, jbyteArray frameData, jint width, jint height) {
    // Canny edge detection example
}
