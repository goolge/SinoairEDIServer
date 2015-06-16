package com.sinoair.iemisgateway.util.sftp;


import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;
import com.sinoair.iemisgateway.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-20
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class SftpUpload {
    private static final int BUFFERSIZE = 1024;

    /**
     * 把本地目录下的所有文件都上传到远程目录下,同时备份本地文件，删除文件,
     *
     * @param localPath    本地目录 C://ftp//correos//out//
     * @param remotePath   远程目录 /salida/
     * @param sftPv3Client sft客户端
     */


    public static void upload(String localPath, String remotePath, SFTPv3Client sftPv3Client,String localCopyPath) {
        try {

            File[] tempList = FileUtil.getFiles(localPath);
            for (int i = 0; i < tempList.length; i++) {
                File fi = tempList[i];
                SFTPv3FileHandle SFTPFILEHandle = sftPv3Client.createFileTruncate(remotePath + fi.getName());
                FileInputStream fis = new FileInputStream(fi);
                byte[] b = new byte[BUFFERSIZE];
                int n;
                int length = 0;
                while ((n = fis.read(b)) != -1) {
                   // System.out.println("b:"+b.length);
                    sftPv3Client.write(SFTPFILEHandle, length, b, 0, n);
                     length += n;
                }
                sftPv3Client.closeFile(SFTPFILEHandle);
                fis.close();

                if(localCopyPath!=null && !"".equals(localCopyPath)){
                    FileUtil.copyFile(fi,localCopyPath);
                    FileUtil.deleteFile(fi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     public static void uploadHaha(String localPath, String remotePath, SFTPv3Client sftPv3Client,String localCopyPath) {
        try {
            String data = "sample data";
            SFTPv3FileHandle handle = sftPv3Client.createFile(remotePath + "test_file.txt");
            byte []arr = data.getBytes();
            sftPv3Client.write(handle, 0, arr, 0, arr.length);
            sftPv3Client.closeFile(handle);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
