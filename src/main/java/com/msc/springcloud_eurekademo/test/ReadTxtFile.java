package com.msc.springcloud_eurekademo.test;

import java.io.*;

/**
 * @author Misc
 * @create 2020-11-11 9:35
 * @PACKAGE_NAME com.msc.springcloud_eurekademo.test
 * @PROJECT_NAME springcloud_eurekademo
 */

public class ReadTxtFile {

    public static void readTxtFile(String filePath){
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){
                InputStream in;
                InputStreamReader reader=new InputStreamReader(new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                }
                reader.close();

            }else {
                System.out.println("找不到指定的文件");
            }


        }catch (Exception e ){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

}
