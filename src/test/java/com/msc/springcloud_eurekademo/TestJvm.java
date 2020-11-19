package com.msc.springcloud_eurekademo;

/**
 * @author Misc
 * @create 2020-11-12 15:34
 * @PACKAGE_NAME com.msc.springcloud_eurekademo
 * @PROJECT_NAME springcloud_eurekademo
 */

public class TestJvm {

    public Object instance = null;
    private static int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC(){
        TestJvm test1 = new TestJvm();
        TestJvm test2 = new TestJvm();
            test1.instance = test2;
            test2.instance = test1;
            test1 = null;
            test2 = null;
            // 强制JVM进行垃圾回收
            System.gc();

    }

    public static void main(String[] args) {
        testGC();
    }



}
