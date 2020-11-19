package com.msc.springcloud_eurekademo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.msc.springcloud_eurekademo.test.ImportExcel;
import com.msc.springcloud_eurekademo.test.Phone;
import com.msc.springcloud_eurekademo.test.ReadTxtFile;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Misc
 * @create 2020-11-10 9:21
 * @PACKAGE_NAME com.msc.springcloud_eurekademo
 * @PROJECT_NAME springcloud_eurekademo
 */

@SpringBootTest
public class TestDemo {

    private static final String url = "http://134.175.220.13:8001/api/sys/decrypt";


    /**
     * 调用对方接口方法
     *
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static void interfaceUtil(String path, String data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;

            /**设置URLConnection的参数和普通的请求属性****start***/

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            /**设置URLConnection的参数和普通的请求属性****end***/

            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("GET");//GET和POST必须全大写
            /**GET方法请求*****start*/
            /**
             * 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
             * 如果发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
             * */
            conn.connect();

            /**GET方法请求*****end*/

            /***POST方法请求****start*/

            /*out = new PrintWriter(conn.getOutputStream());//获取URLConnection对象对应的输出流

            out.print(data);//发送请求参数即数据

            out.flush();//缓冲数据
            */
            /***POST方法请求****end*/

            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                str = new String(str.getBytes(), "UTF-8");//解决中文乱码问题
                System.out.println(str);
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void Phonedecrypt() {
//        JSONObject json = new JSONObject();
//
////        String str ="mbmvfcGM/B3SgfoDOb106A==";
////        interfaceUtil("http://134.175.220.13:8001/api/sys/decrypt?str=“+str+","");
//
//        Phone strs=new Phone();
//        List<Object> objects = new ArrayList<>();
//        for (String str : strs.getStrs()) {
//            String result = HttpUtil.get(url, json.putOpt("str", str));
//            JSONObject jsonObject = JSONUtil.parseObj(result);
//            System.out.println(jsonObject.get("resultData"));
//            String resultdate =(String) jsonObject.get("resultData");
//            System.out.println(resultdate);
//            objects.add(CollUtil.newArrayList(str,resultdate));
//
//        }
//        ExcelWriter writer = ExcelUtil.getWriter("E:/tools/writeTest.xlsx");
//        writer.write(objects, true);
//        //关闭writer，释放内存
//        writer.close();
//
//
//    }


    @Test
    public void Phonedecrypt() {
        JSONObject json = new JSONObject();

//        String str ="mbmvfcGM/B3SgfoDOb106A==";
//        interfaceUtil("http://134.175.220.13:8001/api/sys/decrypt?str=“+str+","");

        Phone strs=new Phone();
        List<Object> objects = new ArrayList<>();
        for (String str : strs.getStrs()) {
            String result = HttpUtil.get(url, json.putOpt("str", str));
            JSONObject jsonObject = JSONUtil.parseObj(result);
            System.out.println(jsonObject.get("resultData"));
            String resultdate =(String) jsonObject.get("resultData");
            System.out.println(resultdate);
            objects.add(CollUtil.newArrayList(str,resultdate));

        }
        ExcelWriter writer = ExcelUtil.getWriter("E:/tools/writeTest.xlsx");
        writer.write(objects, true);
        //关闭writer，释放内存
        writer.close();


    }

    @Test
    public void readTxtFileTest(){
        String filePath = "E:\\ContentBody.txt";
        ReadTxtFile.readTxtFile(filePath);
    }

    @Test
    public void ImportExcelTest(){
        try {
            //文件路径地址不能有中文
            InputStream inputStream=new FileInputStream(new File("E:\\tools\\1111.xlsx"));
            String[] excelHead={"LOGIN_NAME","USER_NAME","PHONE_NO"};
            String[] excelHeadAlias={"LOGIN_NAME","USER_NAME","PHONE_NO"};
            //读取excel表格中的数据别名化
            List<Map<String,Object>> result= ImportExcel.importExcel(inputStream,excelHead,excelHeadAlias);

            //转json格式
            JSONObject json = new JSONObject();

            List<Object> objects = new ArrayList<>();
            for (Map<String,Object> results : result){
                String r = HttpUtil.get(url, json.putOpt("str", results.get("PHONE_NO")));
                JSONObject jsonObject = JSONUtil.parseObj(r);
//                System.out.println(jsonObject.get("resultData"));
                String resultdate =(String) jsonObject.get("resultData");
//                System.out.println(resultdate);
                objects.add(CollUtil.newArrayList(results.get("PHONE_NO"),resultdate,results.get("USER_NAME")));

            }
            //存放excel地址
            ExcelWriter writer = ExcelUtil.getWriter("E:/tools/1112.xlsx");
            writer.write(objects, true);
            //关闭writer，释放内存

            writer.close();
            System.out.println("。。。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
