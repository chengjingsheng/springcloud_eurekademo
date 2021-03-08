package com.msc.springcloud_eurekademo.test;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.commons.lang.ArrayUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Misc
 * @create 2020-11-11 9:53
 * @PACKAGE_NAME com.msc.springcloud_eurekademo.test
 * @PROJECT_NAME springcloud_eurekademo
 * 读取excel中的数据进行扫描处理
 */

public class ImportExcel {
    /***
    * @methodName:
    * @Param:
    * @return: 调用手机号码解密接口地址
    * @Author: Misc
    * @Date: 2020/11/11
    */
    private static final String url = "http://134.175.220.13:8001/api/sys/decrypt";

    /**
     * 读取excel表格内容返回List<Map>
     * @param inputStream  excel文件流
     * @param head         表头数组
     * @param headerAlias  表头别名数组
     * @return
     */
    public static List<Map<String,Object>> importExcel(InputStream inputStream, String[] head, String[] headerAlias){
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Object> header=reader.readRow(1);
        //替换表头关键字
        if(ArrayUtils.isEmpty(head)||ArrayUtils.isEmpty(headerAlias)||head.length!=headerAlias.length){
            return null;
        }else{
            for(int i=0;i<head.length;i++){
                if(head[i].equals(header.get(i))){
                    reader.addHeaderAlias(head[i],headerAlias[i]);
                }else{
                    return null;
                }

            }
        }
        //读取指点行开始的表数据（以下介绍的三个参数也可以使用动态传入，根据个人业务情况修改）
        //1：表头所在行数  2：数据开始读取位置   Integer.MAX_VALUE:数据读取结束行位置
        List<Map<String,Object>> read = reader.read(1,2,Integer.MAX_VALUE);
        return read;
    }
    /**
     * 读取excel表格内容返回List<Bean>
     * @param inputStream  excel文件流
     * @param head         表头数组
     * @param headerAlias  表头别名数组
     * @return
     */
    public static <T> List<T> importExcel(InputStream inputStream, String[] head, String[] headerAlias, Class<T> bean){
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Object> header=reader.readRow(1);
        //替换表头关键字
        if(ArrayUtils.isEmpty(head)||ArrayUtils.isEmpty(headerAlias)||head.length!=headerAlias.length){
            return null;
        }else{
            for(int i=0;i<head.length;i++){
                if(head[i].equals(header.get(i))){
                    reader.addHeaderAlias(head[i],headerAlias[i]);
                }else{
                    return null;
                }

            }
        }
        //读取指点行开始的表数据（从0开始）
        List<T> read = reader.read(1,2,bean);
        return read;
    }




}
