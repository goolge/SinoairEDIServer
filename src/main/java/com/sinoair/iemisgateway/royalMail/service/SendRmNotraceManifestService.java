package com.sinoair.iemisgateway.royalMail.service;

import com.sinoair.iemisgateway.royalMail.domain.RoyalMailNotraceItem;
import com.sinoair.iemisgateway.royalMail.domain.RoyalMailNotraceManifest;
import com.sinoair.iemisgateway.util.*;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-7-30
 * Time: 上午9:43
 * To change this template use File | Settings | File Templates.
 */
public class SendRmNotraceManifestService {

    /**
     * 获取需要发送预报的数据集合
     *
     * @param conn
     * @return
     * @throws Exception
     */
    public ArrayList getInfoList(Connection conn) throws Exception {
        String sql = "select distinct eawb.EAWB_PRINTCODE," + //1
                "eawb.EAWB_REFERENCE1," +//2
                "eawb.EAWB_REFERENCE2," + //3
                "eawb.EAWB_CONSIGNEE_ACCOUNTNAME," + //4
                "eawb.EAWB_DELIVER_ADDRESS," +  //5
                "eawb.EAWB_DESTCITY," + //6
                "eawb.EAWB_DELIVER_POSTCODE," +   //7
                "eawb.EAWB_DECLAREGROSSWEIGHT," +  //8
                "eawb.EAWB_DELIVER_PHONE," +
                "eawb.EAWB_DELIVER_EMAIL"+
                " from express_manifest em, expressairwaybill eawb" +
                " ,expressbusinessactivity eba" +
                " where eawb.eawb_printcode=em.eawb_printcode " +
                " and eawb.eawb_printcode = eba.eawb_printcode " +
                " and eba.ead_code = 'INTERNATIONAL'" +
                " and eba.east_code = 'CP'" +
                " and em.em_status='PENDING' and em.EM_PARTNER='" + RoyalMailNotraceManifest.PARTNER_CODE + "' order by eawb.EAWB_PRINTCODE ";
        // System.out.println(sql);
        ExeSQL texesql = new ExeSQL();
        texesql.setConnection(conn);
        ArrayList arrayList = texesql.execSqltoArr(sql);
        BaseLogger.info("aaaa:" + sql);
        return arrayList;
    }

    public ArrayList getInfoListSome(Connection conn) throws Exception {
        String sql = "select distinct eawb.EAWB_PRINTCODE," + //1
                "eawb.EAWB_REFERENCE1," +//2
                "eawb.EAWB_REFERENCE2," + //3
                "eawb.EAWB_CONSIGNEE_ACCOUNTNAME," + //4
                "eawb.EAWB_DELIVER_ADDRESS," +  //5
                "eawb.EAWB_DESTCITY," + //6
                "eawb.EAWB_DELIVER_POSTCODE," +   //7
                "eawb.EAWB_DECLAREGROSSWEIGHT," +  //8
                "eawb.EAWB_DELIVER_PHONE," +
                "eawb.EAWB_DELIVER_EMAIL " +
                " from  expressairwaybill eawb" +
                " where eawb.EAWB_PRINTCODE in('880001022245', '880001022256', '880001022260', '880001022271','880001022282')";
        // System.out.println(sql);
        ExeSQL texesql = new ExeSQL();
        texesql.setConnection(conn);
        ArrayList arrayList = texesql.execSqltoArr(sql);
        //BaseLogger.info("aaaa:" + sql);
        return arrayList;
    }

    /**
     * 根据数据集合，在本地生成报文
     *
     * @param arrData
     * @param app_dir
     * @return
     * @throws Exception
     */
    public void generateFileRoyalMail(ArrayList arrData, String app_dir, Connection conn) throws Exception {
        int maxLine = 24496;//一个文档最多存放24546行数据，0代表没有限制,再加上三行的表头表尾，不能超过24500
        ArrayList<List> arrayListArrayList = null;
        if (arrData != null && arrData.size() > 0) {
            arrayListArrayList = new ArrayList<List>();
            if (maxLine > 0) {
                int yushu = arrData.size() % maxLine;
                int listNum = 0;
                listNum = (int) Math.floor(arrData.size() / maxLine);
                if (yushu > 0) {
                    listNum = listNum + 1;
                }
                //System.out.println("listNum:"+listNum);
                for (int num = 0; num < listNum; num++) {
                    List al = new ArrayList();
                    if (num == listNum - 1) {
                        //System.out.println("最后一个");
                        al = arrData.subList(num * maxLine, arrData.size());
                        //System.out.println("al:" + al.size());
                    } else {
                        al = arrData.subList(num * maxLine, (num + 1) * maxLine);
                    }
                    arrayListArrayList.add(al);
                }

            } else {
                arrayListArrayList.add(arrData);
            }
        }
        if (arrayListArrayList != null && arrayListArrayList.size() > 0) {
            RoyalMailNotraceManifest royalMailNotraceManifest = new RoyalMailNotraceManifest();
            for (int i = 0; i < arrayListArrayList.size(); i++) {
                PreparedStatement selectSeq = conn.prepareStatement("select SEQ_ROYALMAILNOTRACE.nextval from dual ");
                ResultSet rs = selectSeq.executeQuery();
                String oneSeq = "";
                if (rs.next()) {
                    oneSeq = rs.getString(1);
                }
                oneSeq = StringUtil.getIntFormString(9, Integer.parseInt(oneSeq));
                List listi = arrayListArrayList.get(i);
                List<RoyalMailNotraceItem> royalMailNotraceItemList = new ArrayList<RoyalMailNotraceItem>();
                for (int m = 0; m < listi.size(); m++) {
                    HashMap map = (HashMap) listi.get(m);
                    RoyalMailNotraceItem royalMailNotraceItem = new RoyalMailNotraceItem(map);
                    royalMailNotraceItemList.add(royalMailNotraceItem);
                }
                royalMailNotraceManifest.setRoyalMailNotraceItemList(royalMailNotraceItemList);
                //System.out.println("royalMailItemList:" + royalMailItemList.size());
                //System.out.println("royalMailManifest.getMessageContent():" + royalMailManifest.getMessageContent());
                String strFilePath = "";
                strFilePath = app_dir + RoyalMailNotraceManifest.FILENAMEPREFIX + royalMailNotraceManifest.getA12_WireNumber() + RoyalMailNotraceManifest.PREADVICE3 + oneSeq+".csv";
                FileUtil.generateFile(royalMailNotraceManifest.getMessageContent(), strFilePath);
            }
        }
    }

    /**
     * 批量更新报文数据状态为、Success
     *
     * @param arrData
     * @param conn
     * @throws Exception
     */
    public void updateSuccess(ArrayList arrData, Connection conn) throws Exception {

        PreparedStatement updateStatus = conn.prepareStatement(" update express_manifest em set em.em_status='SUCCESS',em.EM_HANDLETIME=sysdate  where em.eawb_printcode=? ");
        if (arrData != null) {
            for (int i = 0; i < arrData.size(); i++) {
                HashMap map = (HashMap) arrData.get(i);
                String eawb_printcode = map.get("EAWB_PRINTCODE").toString();
                updateStatus.setString(1, eawb_printcode);
                updateStatus.addBatch();
            }
            conn.setAutoCommit(false);
            updateStatus.executeBatch();
        }
    }

    /**
     * @param conn
     * @param historyRootPath
     * @throws Exception
     * @throws SQLException
     */
    public void sendManifestNotrace(Connection conn, String historyRootPath) throws Exception, SQLException {
        //组织西邮报文数据，在本地存一份备份，然后放到西邮服务器，同时更新运单状态为发送成功
        ArrayList arrayList = null;
        String localpfileDir = historyRootPath + "/royalMail/out/manifestNotrace/";
        try {
            //1.获取需要发送预报的运单数据；
            arrayList = getInfoList(conn);
            //2.更新数据状态为已发送
            updateSuccess(arrayList, conn);
            //3.生成报文
            generateFileRoyalMail(arrayList, localpfileDir, conn);

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }
        ConnectionFactory.closeConnection(conn);
        //3.在本地生成西邮报文
        int sendNum = arrayList == null ? 0 : arrayList.size();
        LogUtil.log(" " + RoyalMailNotraceManifest.PARTNER_CODE + "发送报文-获取发预报的数据条数：" + sendNum);
        //4.如果发送运单数量不为0，则给一英国相关人员发送信息
        sendEmail(historyRootPath, sendNum);


    }


    public void sendEmail(String historyRootPath, int sendNum) throws Exception {
        String localpfileDir = historyRootPath + "/royalMail/out/manifestNotrace/";
        String localpfileDirCopy = historyRootPath + "/royalMail/bak/out/manifestNotrace/";
        File[] files = FileUtil.getFiles(localpfileDir);
        ArrayList<File> fileArrayList = null;
        if (files != null && files.length > 0) {
            fileArrayList = new ArrayList<File>();
            for (int i = 0; i < files.length; i++) {
                fileArrayList.add(files[i]);
            }
        }
        if (sendNum > 0 && fileArrayList != null) {
            MailUtil.postMail(
                    "zhangmj@sinoair.com",
                    "WangXX4@sinoair.com",
                    "WangXF@sinoair.com",
                    "pre-advice of RM24 has been sent successfully today.",
                    "The total number of parcels:" + sendNum + "    " + DateUtil.getCurrentDateStrGB("yyyy-MM-dd HH:mm:ss"),
                    "iemis@sinoair.com",
                    fileArrayList,
                    "iemis",
                    "SinoAiriemis");

            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    FileUtil.copyFile(files[i],localpfileDirCopy);
                    FileUtil.deleteFile(files[i]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String historyRootPath = "D:/express/SinoairEDIServerHistory";
        SendRmNotraceManifestService generateInfo = new SendRmNotraceManifestService();
        Connection conn = ConnectionFactory.get200Connection();

        generateInfo.sendManifestNotrace(conn, historyRootPath);


        /* generateInfo.sendRmManifestAndDeleteLocateFiles(historyRootPath);*/

    }
}
