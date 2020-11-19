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
     * ���öԷ��ӿڷ���
     *
     * @param path �Է���������ṩ��·��
     * @param data ��Է�����������͵����ݣ����������¸��Է�����JSON�����öԷ�����
     */
    public static void interfaceUtil(String path, String data) {
        try {
            URL url = new URL(path);
            //�򿪺�url֮�������
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;

            /**����URLConnection�Ĳ�������ͨ����������****start***/

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            /**����URLConnection�Ĳ�������ͨ����������****end***/

            //�����Ƿ���httpUrlConnection����������Ƿ��httpUrlConnection���룬���ⷢ��post�����������������
            //��õ�Http�����޷���get��post��get������Ի�ȡ��̬ҳ�棬Ҳ���԰Ѳ�������URL�ִ����棬���ݸ�servlet��
            //post��get�� ��֮ͬ������post�Ĳ������Ƿ���URL�ִ����棬���Ƿ���http����������ڡ�
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("GET");//GET��POST����ȫ��д
            /**GET��������*****start*/
            /**
             * ���ֻ�Ƿ���GET��ʽ����ʹ��connet����������Զ����Դ֮���ʵ�����Ӽ��ɣ�
             * �������POST��ʽ��������Ҫ��ȡURLConnectionʵ����Ӧ����������������������
             * */
            conn.connect();

            /**GET��������*****end*/

            /***POST��������****start*/

            /*out = new PrintWriter(conn.getOutputStream());//��ȡURLConnection�����Ӧ�������

            out.print(data);//�����������������

            out.flush();//��������
            */
            /***POST��������****end*/

            //��ȡURLConnection�����Ӧ��������
            InputStream is = conn.getInputStream();
            //����һ���ַ�������
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                str = new String(str.getBytes(), "UTF-8");//���������������
                System.out.println(str);
            }
            //�ر���
            is.close();
            //�Ͽ����ӣ����д�ϣ�disconnect���ڵײ�tcp socket���ӿ���ʱ���жϡ�������ڱ������߳�ʹ�þͲ��жϡ�
            //�̶����̵߳Ļ��������disconnect�����ӻ����ֱ࣬���շ�������Ϣ��д��disconnect������һЩ��
            conn.disconnect();
            System.out.println("����");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void Phonedecrypt() {
//        JSONObject json = new JSONObject();
//
////        String str ="mbmvfcGM/B3SgfoDOb106A==";
////        interfaceUtil("http://134.175.220.13:8001/api/sys/decrypt?str=��+str+","");
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
//        //�ر�writer���ͷ��ڴ�
//        writer.close();
//
//
//    }


    @Test
    public void Phonedecrypt() {
        JSONObject json = new JSONObject();

//        String str ="mbmvfcGM/B3SgfoDOb106A==";
//        interfaceUtil("http://134.175.220.13:8001/api/sys/decrypt?str=��+str+","");

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
        //�ر�writer���ͷ��ڴ�
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
            //�ļ�·����ַ����������
            InputStream inputStream=new FileInputStream(new File("E:\\tools\\1111.xlsx"));
            String[] excelHead={"LOGIN_NAME","USER_NAME","PHONE_NO"};
            String[] excelHeadAlias={"LOGIN_NAME","USER_NAME","PHONE_NO"};
            //��ȡexcel����е����ݱ�����
            List<Map<String,Object>> result= ImportExcel.importExcel(inputStream,excelHead,excelHeadAlias);

            //תjson��ʽ
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
            //���excel��ַ
            ExcelWriter writer = ExcelUtil.getWriter("E:/tools/1112.xlsx");
            writer.write(objects, true);
            //�ر�writer���ͷ��ڴ�

            writer.close();
            System.out.println("������");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
