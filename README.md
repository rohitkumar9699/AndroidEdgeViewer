
# 📱 Android + OpenCV-C++ + OpenGL Edge Detection Viewer

## 🧪 Project Overview

This project is a minimal Android application that captures live camera frames, sends them to C++ code via JNI for image processing using OpenCV (Canny Edge Detection), and displays the processed frames using OpenGL ES 2.0 for real-time rendering.

It is designed to demonstrate integration between Android SDK, OpenCV (C++), OpenGL ES, and JNI.

---

## 🧰 Tech Stack

- **Android SDK (Java/Kotlin)**: For camera handling and app lifecycle
- **NDK (Native Development Kit)**: For writing native C++ code
- **OpenCV (C++)**: For real-time image processing (Canny Edge Detection)
- **OpenGL ES 2.0**: For rendering frames as textures
- **JNI (Java Native Interface)**: To call native C++ functions from Java
- **CMake**: To manage native build system

---

## 📸 Features Implemented

- Live camera feed using `TextureView` and `Camera2` API
- JNI bridge for passing camera frames to native layer
- C++ implementation of image processing using OpenCV
- Canny edge detection applied to each frame
- OpenGL ES 2.0 renderer displays processed frames as textures
- Basic project structure: separation of Java, JNI, and OpenGL layers

---

## 📁 Project Structure

```
AndroidEdgeViewer/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/edgeviewer/
│           │   ├── MainActivity.java         # Camera and JNI integration
│           │   └── Renderer.java             # OpenGL rendering logic
│           └── cpp/
│               ├── native-lib.cpp            # C++ OpenCV logic
│               └── CMakeLists.txt            # C++ build configuration
│
├── gl/
│   └── Renderer.java                         # Placeholder OpenGL renderer
├── README.md
└── .gitignore
```

---

## 🧠 Architecture Overview

### 🔄 Frame Flow

```
Camera2 (Java)
   ↓
TextureView Frame Callback
   ↓
ByteBuffer → JNI call
   ↓
C++ with OpenCV
   ↓
Canny Edge Detection
   ↓
Processed Image → GL Texture
   ↓
OpenGL ES Rendering (Java or Native)
```

### 📟 JNI Bridge

- Native method in `MainActivity`:
  ```java
  public native void processFrame(byte[] frameData, int width, int height);
  ```

- Corresponding C++ implementation in `native-lib.cpp`:
  ```cpp
  JNIEXPORT void JNICALL Java_com_example_edgeviewer_MainActivity_processFrame
  ```

---

## 🧪 Setup Instructions

### ✅ Prerequisites

- Android Studio (Arctic Fox+)
- Android NDK (side-by-side)
- OpenCV for Android (C++ SDK)
- CMake installed via Android Studio SDK manager

### 🔧 How to Build & Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/AndroidEdgeViewer.git
   cd AndroidEdgeViewer
   ```

2. **Configure OpenCV**:
   - Download OpenCV Android SDK from [OpenCV.org](https://opencv.org/releases/)
   - Extract and set the correct paths in `CMakeLists.txt`:
     ```cmake
     set(OpenCV_DIR /path/to/OpenCV-android-sdk/sdk/native/jni)
     ```

3. **Import Project in Android Studio**:
   - Open `AndroidEdgeViewer` directory.
   - Make sure `CMake`, `NDK`, and `LLDB` are installed via SDK Manager.
   - Sync Gradle and build the project.

4. **Run the app on a physical Android device**:
   - Ensure camera permissions are granted.
   - Allow camera access and observe edge-detected frames in real-time.

---

## 🎁 Bonus Feature Suggestions

- Toggle between raw camera and edge-processed output using a button
- Add FPS counter to monitor real-time performance
- Use OpenCV for other filters like Gaussian Blur or Threshold
- Use GLSL shaders for grayscale or inverted effects

---

## 📸 Screenshots / GIFs

*(Add screenshots of the app running on an Android device here)*

---

## 🧩 Troubleshooting

- **App crashes on camera open**: Ensure runtime camera permissions are granted.
- **No frames shown**: Validate TextureView readiness and camera session configuration.
- **JNI errors**: Check method signatures between Java and C++.
- **OpenCV not found**: Ensure correct `OpenCV_DIR` is set in `CMakeLists.txt`.

---

## 🏁 Conclusion

This app provides a powerful starting point for real-time vision systems using Android, C++, OpenCV, and OpenGL. It’s modular, easily extendable, and highly optimized for native performance.

---

## 🧑‍💻 Author

**Rohit Kumar**    
Email: rk94523386@gmail.com

Feel free to reach out for questions or contributions.
