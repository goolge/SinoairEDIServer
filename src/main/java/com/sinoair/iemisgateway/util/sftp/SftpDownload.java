package com.sinoair.iemisgateway.util.sftp;

import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import ch.ethz.ssh2.SFTPv3FileHandle;

import java.io.*;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-20
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public class SftpDownload {
    private static final int BUFFERSIZE = 1024;

    /**
     * 把远程目录下的所有文件都下载到本地目录下
     *
     * @param localPath    本地目录 C://ftp//correos//out//
     * @param remotePath   远程目录 /salida/
     * @param sftPv3Client sft客户端
     */
    public static void download(String localPath, String remotePath, SFTPv3Client sftPv3Client) {
        try {
            Vector listV = sftPv3Client.ls(remotePath);
            for (int i = 0; i < listV.size(); i++) {
                SFTPv3DirectoryEntry aa = (SFTPv3DirectoryEntry) listV.elementAt(i);
                //BaseLogger.info("aaaa:" + aa.filename);
                if (!aa.filename.endsWith(".zip") && !aa.filename.endsWith(".txt")) {
                    continue;
                }
                SFTPv3FileHandle fileHandle = sftPv3Client.openFileRO(remotePath + aa.filename);
                File myFile = new File(localPath + aa.filename);
                OutputStream out = new FileOutputStream(myFile);
                long numread = 0;
                int length = 0;
                Long len = aa.attributes.size;
                int loadSize = 1024;
                int readSize = len.compareTo(Long.valueOf(loadSize)) > 0 ? loadSize : len.intValue();
                byte[] bytes = new byte[readSize];
                while (readSize > 0 && (numread = sftPv3Client.read(fileHandle, length, bytes, 0, readSize)) != -1) {
                    length += numread;
                    out.write(bytes);
                    readSize = len.compareTo(Long.valueOf(length + loadSize)) > 0 ? loadSize : (int) (len - length);
                    bytes = new byte[readSize];
                }
                out.close();
                sftPv3Client.rm(remotePath+aa.filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
