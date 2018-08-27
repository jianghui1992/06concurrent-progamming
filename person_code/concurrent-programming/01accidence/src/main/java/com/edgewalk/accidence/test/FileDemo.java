package com.edgewalk.accidence.test;

import java.io.File;

/**
 * Created by: edgewalk
 * 2018-08-19 19:47
 */
public class FileDemo {
    public static void main(String[] args) throws InterruptedException {
        //method1();
     //  File userDir = new File(System.getProperty("user.dir"));
        File userDir = new File("E:\\test");
        System.out.println(userDir.getAbsolutePath());
        while (true) {
            System.out.println(userDir.lastModified());
            Thread.sleep(3000);
//            if (lastModifiedTime == userDir.lastModified()) {
//                continue;
//            }
//            //修改为新的时间
//            lastModifiedTime = userDir.lastModified();
//            String[] list = userDir.list();
//            Stream.of(list).forEach(e -> System.out.println(e));
        }
    }

    private static void method1() {
        System.out.println("用户目录:" + System.getProperty("user.home"));
        System.out.println("用户工作目录:" + System.getProperty("user.dir"));
        /**
         * System.getProperty("user.dir")的原理如下
         */
        File file = new File("");
        System.out.println("用户工作目录:" + file.getAbsolutePath());
    }
}
