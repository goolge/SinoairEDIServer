package com.sinoair.iemisgateway.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-20
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class LogUtil {
    public static void log(String msg,String strDirectory) {
		System.out.println(msg);
		if(strDirectory==null||"".equals(strDirectory))
		strDirectory="log/";
		File f_dir = new File(strDirectory);
		if(!f_dir.exists())
			f_dir.mkdirs();
		String message = "";
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.text.DateFormat dflog = new java.text.SimpleDateFormat(
					"yyyyMMdd");
			java.util.Date date = new java.util.Date();
			String datestr = df.format(new java.util.Date());
			String datelog = dflog.format(new java.util.Date());

			// 按日期每天生成一个日志文件
			FileWriter fwl = new FileWriter(strDirectory + datelog
					+ ".log", true);
			PrintWriter outl = new PrintWriter(fwl);
			outl.println(datestr + " " + msg);
			outl.close();
			fwl.close();
		} catch (IOException e) {
			message = "写log文件错误!" + e;
			e.printStackTrace();
			log(message,strDirectory);
			System.out.println(message);
		}
	}
    public static void log(String msg)
      {
        log(msg, null);
      }
}
