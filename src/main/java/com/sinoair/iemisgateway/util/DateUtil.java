package com.sinoair.iemisgateway.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-8
 * Time: 下午11:12
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {
    public static void main(String[] args) {
        BaseLogger.info(getDateWith());
    }
    public static String getDateWith() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss-SSS");
        return simpleDateFormat.format(new Date());
    }
    public static String getSysdate() {
        Calendar export_date = Calendar.getInstance();
        long currtimemillis = System.currentTimeMillis();
        Timestamp currtime = new Timestamp(currtimemillis);
        export_date.setTime(currtime);


        int year = export_date.get(Calendar.YEAR);
        int month = export_date.get(Calendar.MONTH) + 1;
        int day = export_date.get(Calendar.DAY_OF_MONTH);
        int hour = export_date.get(Calendar.HOUR_OF_DAY);
        int minute = export_date.get(Calendar.MINUTE);
        int second = export_date.get(Calendar.SECOND);
        int millisecond = (int) (currtimemillis % 1000);

        return "" + year + month + day + hour + minute + second + millisecond;
    }
    public static String getSysdateYMD() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
              return simpleDateFormat.format(new Date());
        }
    /**
     * 获得指定带时分秒的当前时间
     *
     */

    public static Date getDateWithHMS(){
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        Date d = null;
        try {
            d = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date getDateWithoutHMS(){
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        Date d = null;
        try {
            d = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date getStrToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d1 = null;
		if (!"".equals(strDate) && strDate != null) {
            try {
                d1 = formatter.parse(strDate + " 00:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
		return d1;
	}
    public static Date getStrToDate(String strDate,String formatString) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		Date d1 = null;
		if (!"".equals(strDate) && strDate != null) {
            try {
                d1 = formatter.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
		return d1;
	}
    public static Date getStringToDate(String strDate,String dateFormatter) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatter);
		Date d1 = null;
            try {
                d1 = formatter.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

		return d1;
	}
    public static String getCurrentDateStrGB(String format) {
        return getDateStrAheadHours(-7, format);
    }
    public static String getDateStrAheadHours(int hours,String format){
      Calendar calendar=Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(Calendar.HOUR_OF_DAY,hours);
      SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
      return simpleDateFormat.format(calendar.getTime());
    }
}
