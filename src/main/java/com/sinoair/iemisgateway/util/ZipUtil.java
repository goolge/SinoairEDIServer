package com.sinoair.iemisgateway.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.*;
import java.util.zip.*;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-20
 * Time: 上午8:58
 * To change this template use File | Settings | File Templates.
 */
public class ZipUtil {
    private final static int FILE_BUFFER_SIZE = 20480;

    public static boolean unzipFile(String zipFilePath, String zipFileName, String targetFileDir) {
        boolean flag = false;
        //1.判断压缩文件是否存在，以及里面的内容是否为空
        File file = null;   //压缩文件(带路径)
        ZipFile zipFile = null;
        file = new File(zipFilePath + "/" + zipFileName);
        BaseLogger.info(">>>>>>解压文件【" + zipFilePath + "/" + zipFileName + "】到【" + targetFileDir + "】目录下<<<<<<");
        if (false == file.exists()) {
            BaseLogger.info(">>>>>>压缩文件【" + zipFilePath + "/" + zipFileName + "】不存在<<<<<<");
            return false;
        } else if (0 == file.length()) {
            BaseLogger.info(">>>>>>压缩文件【" + zipFilePath + "/" + zipFileName + "】大小为0不需要解压<<<<<<");
            return false;
        } else {
            //2.开始解压ZIP压缩文件的处理
            byte[] buf = new byte[FILE_BUFFER_SIZE];
            int readSize = -1;
            ZipInputStream zis = null;
            FileOutputStream fos = null;
            try {
                // 检查是否是zip文件
                zipFile = new ZipFile(file);
                zipFile.close();
                // 判断目标目录是否存在，不存在则创建
                File newdir = new File(targetFileDir);
                if (false == newdir.exists()) {
                    newdir.mkdirs();
                    newdir = null;
                }
                zis = new ZipInputStream(new FileInputStream(file));
                ZipEntry zipEntry = zis.getNextEntry();
                // 开始对压缩包内文件进行处理
                while (null != zipEntry) {
                    String zipEntryName = zipEntry.getName().replace('\\', '/');
                    //判断zipEntry是否为目录，如果是，则创建
                    if (zipEntry.isDirectory()) {
                        int indexNumber = zipEntryName.lastIndexOf('/');
                        File entryDirs = new File(targetFileDir + "/" + zipEntryName.substring(0, indexNumber));
                        entryDirs.mkdirs();
                        entryDirs = null;
                    } else {
                        try {
                            fos = new FileOutputStream(targetFileDir + "/" + zipEntryName);
                            while ((readSize = zis.read(buf, 0, FILE_BUFFER_SIZE)) != -1) {
                                fos.write(buf, 0, readSize);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException(e.getCause());
                        } finally {
                            try {
                                if (null != fos) {
                                    fos.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e.getCause());
                            }
                        }
                    }
                    zipEntry = zis.getNextEntry();
                }
                flag = true;
            } catch (ZipException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
            } finally {
                try {
                    if (null != zis) {
                        zis.close();
                    }
                    if (null != fos) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getCause());
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        unzipFile("D:\\tmp\\zipUtil1", "FR48K2RC20150515044504.zip", "D:\\tmp\\zipUtil1");
    }

}
