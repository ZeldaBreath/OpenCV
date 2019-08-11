package src.controller;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.File;

public class MyOpenCv {
    public static void main(String args[]){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.getProperty("user.dir")).append(File.separator).append(Core.NATIVE_LIBRARY_NAME).append(".dll");
        System.load(stringBuilder.toString());
        Mat hello = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println(hello.dump());

    }
}
