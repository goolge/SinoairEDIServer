package com.sinoair.iemisgateway.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-20
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    /**
     * 复制文件
     * @param s 原文件
     * @param targetPath 目标路径  例如：D:\tmp\zipUtil1
     */
    public static void copyFile(File s,String targetPath){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        File t=newFile(targetPath,s.getName());
        try{
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道

        }catch(Exception e){
             e.printStackTrace();
        }finally {
              try{
                    fi.close();
                    in.close();
                    fo.close();
                    out.close();
              }catch (IOException ioe){
                ioe.printStackTrace();
              }
        }
    }

    /**
     * 在路径下新建空白文件，如果文件存在，则是获取文件
     * @param targetPath  D:\tmp\zipUtil1
     * @param fileName  aa.txt
     * @return
     */
    public static File newFile(String targetPath,String fileName){
        String filePath=targetPath+fileName;
        File t=new File(filePath);
        return t;
    }
      /**
     * 生成预报文件
     * strData:预报数据;
     * strFilePath:文件路径和文件名称;
     */
    public static boolean generateFile(String strData, String strFilePath) throws Exception {
        //BaseLogger.info(strData);
        File file = new File(strFilePath);
       /* if (file.exists()) {
            file.delete();
        }*/
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bt = strData.getBytes();
            fos.write(bt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    /**
     * 删除文件
     * @param s
     */
    public static void deleteFile(File s){
        if(s.isFile() && s.exists()){
            boolean a=s.delete();
            //BaseLogger.info("文件删除："+a);
        }
    }

    /**
     * 取得此路径下的所有文件
     * @param path
     * @return
     */
    public static File[] getFiles(String path){
        File fileUpload = new File(path);
        File[] tempList = fileUpload.listFiles();
        return tempList;
    }
    /**
     * 读取文件，把读到的内容填写到List里
     * @param s
     * @return
     */
    public static List<String> realFileTxt(File s){
        if(s.isFile() && s.exists()){
            ArrayList<String> arrayList=new ArrayList<String>();
            try{
                BufferedReader br = new BufferedReader(new FileReader(s));
                String str = null;
                while((str = br.readLine())!=null){//使用readLine方法，一次读一行
                    arrayList.add(str);
                }
                br.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return arrayList;
        }
        return null;
    }

    public static void main(String[] args) {
       File file=new File("D:\\tmp\\zipUtil1\\FR48K2RC20150515044504.txt");
       if(file.isFile() && file.exists()){
           copyFile(file,"D:\\tmp\\zipCopy");
           List<String> arrayList=realFileTxt(file);
           if(arrayList!=null && arrayList.size()>0){
               for(int i=0;i<arrayList.size();i++){
                   BaseLogger.info(arrayList.get(i));
               }
           }
           deleteFile(file);
       }
    }
}
