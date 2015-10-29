package com.sinoair.iemisgateway.royalMail.service;

import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import com.sinoair.iemisgateway.royalMail.domain.RoyalMailItem;
import com.sinoair.iemisgateway.royalMail.domain.RoyalMailManifest;
import com.sinoair.iemisgateway.util.*;
import com.sinoair.iemisgateway.util.sftp.SftpConnection;
import com.sinoair.iemisgateway.util.sftp.SftpUpload;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-7-30
 * Time: 上午9:43
 * To change this template use File | Settings | File Templates.
 */
public class SendRmManifestService {
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
                "eawb.EAWB_DELIVER_EMAIL," + //9
                "case when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 4)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 4)) " +
                "     when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 3)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 3))" +
                "     when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 2)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 2))" +
                "     when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 1)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 1)) else '___' end SORTCODE " +
                " from express_manifest em, expressairwaybill eawb" +
                " ,expressbusinessactivity eba" +
                " where eawb.eawb_printcode=em.eawb_printcode " +
                " and eawb.eawb_printcode = eba.eawb_printcode " +
                " and eba.ead_code = 'INTERNATIONAL'" +
                " and eba.east_code = 'CP'" +
                " and em.em_status='PENDING' and em.EM_PARTNER='" + RoyalMailManifest.PARTNER_CODE + "' order by eawb.EAWB_PRINTCODE ";
        // System.out.println(sql);
        ExeSQL texesql = new ExeSQL();
        texesql.setConnection(conn);
        ArrayList arrayList = texesql.execSqltoArr(sql);
        BaseLogger.info("aaaa:" + sql);
        return arrayList;
    }
    /* public ArrayList getInfoList(Connection conn) throws Exception {
        String sql = "select distinct eawb.EAWB_PRINTCODE," + //1
                "eawb.EAWB_REFERENCE1," +//2
                "eawb.EAWB_REFERENCE2," + //3
                "eawb.EAWB_CONSIGNEE_ACCOUNTNAME," + //4
                "eawb.EAWB_DELIVER_ADDRESS," +  //5
                "eawb.EAWB_DESTCITY," + //6
                "eawb.EAWB_DELIVER_POSTCODE," +   //7
                "eawb.EAWB_DECLAREGROSSWEIGHT," +  //8
                "eawb.EAWB_DELIVER_PHONE," +
                "eawb.EAWB_DELIVER_EMAIL," + //9
                "case when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 4)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 4)) " +
                "     when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 3)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 3))" +
                "     when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 2)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 2))" +
                "     when (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 1)) is not null then (select ep1.ep_value from express_property ep1 where ep1.ep_group = 'POSTCODE_SORTCODE' and ep1.ep_key = substr(eawb.EAWB_DELIVER_POSTCODE, 0, 1)) else '___' end SORTCODE " +
                " from  expressairwaybill eawb" +
                " where eawb.EAWB_PRINTCODE in('880001022245', '880001022256', '880001022260', '880001022271','880001022282')";
       // System.out.println(sql);
        ExeSQL texesql = new ExeSQL();
        texesql.setConnection(conn);
        ArrayList arrayList = texesql.execSqltoArr(sql);
        //BaseLogger.info("aaaa:" + sql);
        return arrayList;
    }*/

    /**
     * 根据数据集合，在本地生成报文
     *
     * @param arrData
     * @param app_dir
     * @return
     * @throws Exception
     */
    public void generateFileRoyalMail(ArrayList arrData, String app_dir, Connection conn) throws Exception {
        int maxLine = 24496;//一个文档最多存放24500行数据，0代表没有限制
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
            RoyalMailManifest royalMailManifest = new RoyalMailManifest();
            for (int i = 0; i < arrayListArrayList.size(); i++) {
                PreparedStatement selectSeq = conn.prepareStatement("select seq_royalmail.nextval from dual ");
                ResultSet rs = selectSeq.executeQuery();
                String oneSeq = "";
                if (rs.next()) {
                    oneSeq = rs.getString(1);
                }
                oneSeq = StringUtil.getIntFormString(4, Integer.parseInt(oneSeq));
                royalMailManifest.setFileSerialNumber_A4(oneSeq);
                royalMailManifest.setFileSubmissionDate_A5(DateUtil.getCurrentDateStrGB("yyyyMMddHHmmss").substring(0, 8));
                royalMailManifest.setFileSubmissionTime_A6(DateUtil.getCurrentDateStrGB("yyyyMMddHHmmss").substring(8));
                List listi = arrayListArrayList.get(i);
                List<RoyalMailItem> royalMailItemList = new ArrayList<RoyalMailItem>();
                for (int m = 0; m < listi.size(); m++) {
                    HashMap map = (HashMap) listi.get(m);
                    RoyalMailItem royalMailItem = new RoyalMailItem(map);
                    royalMailItemList.add(royalMailItem);
                }
                royalMailManifest.setRoyalMailItemList(royalMailItemList);
                //System.out.println("royalMailItemList:" + royalMailItemList.size());
                //System.out.println("royalMailManifest.getMessageContent():" + royalMailManifest.getMessageContent());
                String strFilePath = "";
                strFilePath = app_dir + royalMailManifest.getWireNumber_A8() + oneSeq;
                FileUtil.generateFile(royalMailManifest.getMessageContent(), strFilePath);
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
            int submitNum = 0;
            for (int i = 0; i < arrData.size(); i++) {
                HashMap map = (HashMap) arrData.get(i);
                String eawb_printcode = map.get("EAWB_PRINTCODE").toString();
                updateStatus.setString(1, eawb_printcode);
                updateStatus.addBatch();
                submitNum = submitNum + 1;
                if (submitNum == 1000) {
                    submitNum = 0;
                    updateStatus.executeBatch();
                }
            }
            conn.setAutoCommit(false);
            updateStatus.executeBatch();
        }
    }

    public void sendManifest(Connection conn, String historyRootPath) throws Exception, SQLException {
        //组织西邮报文数据，在本地存一份备份，然后放到西邮服务器，同时更新运单状态为发送成功
        ArrayList arrayList = null;
        String localpfileDir = historyRootPath + "/royalMail/out/manifest/";
        String localpfileDirCopy = historyRootPath + "/royalMail/bak/out/manifest/";
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
        LogUtil.log(" 英邮发送报文-获取发预报的数据条数：" + sendNum);
        //4.向英邮发送预报,同时备份本地文件
        sendRmManifestAndDeleteLocateFiles(localpfileDir,localpfileDirCopy);
        //5.如果发送运单数量不为0，则给一英国相关人员发送信息
        sendEmail(sendNum);


    }

    public void sendRmManifestAndDeleteLocateFiles(String localpfileDir,String localpfileDirCopy) throws Exception {
        File[] files = FileUtil.getFiles(localpfileDir);
        if (files != null && files.length > 0) {
            ch.ethz.ssh2.Connection connsft = SftpConnection.getSFTPConnectionWithPassword(RoyalMailManifest.RMURL, RoyalMailManifest.USERNAME, RoyalMailManifest.PASSWORD, RoyalMailManifest.PROTNUM);
            if (connsft != null) {
                LogUtil.log(" 英邮发送报文-连接英邮服务器成功");
                SFTPv3Client sftPv3Client = new SFTPv3Client(connsft);
                SftpUpload.upload(localpfileDir, PropertiesUtil.readProperty("royalMail", "rmManifestTmpDir"), sftPv3Client, localpfileDirCopy);
                Vector vector = sftPv3Client.ls(PropertiesUtil.readProperty("royalMail", "rmManifestTmpDir"));
                for (int i = 0; i < vector.size(); i++) {
                    SFTPv3DirectoryEntry aa = (SFTPv3DirectoryEntry) vector.elementAt(i);
                    if (aa.filename.startsWith(RoyalMailManifest.WireNumber_A8)) {
                        sftPv3Client.mv(PropertiesUtil.readProperty("royalMail", "rmManifestTmpDir") + aa.filename, PropertiesUtil.readProperty("royalMail", "rmManifestDir") + aa.filename);
                    }
                }
                sftPv3Client.close();
                connsft.close();
                LogUtil.log(" 英邮发送报文-上传英邮服务器报文成功！");
            }

        }
    }

    public void sendEmail(int sendNum) throws Exception {
        if (sendNum > 0) {
            MailUtil.postMail(
                    PropertiesUtil.readProperty("royalMail", "rmTo"), //PropertiesUtil.readProperty("royalMail", "rmTo")
                    PropertiesUtil.readProperty("royalMail", "rmCc"), // PropertiesUtil.readProperty("royalMail", "rmCc")
                    PropertiesUtil.readProperty("royalMail", "rmBcc"), // PropertiesUtil.readProperty("royalMail", "rmBcc")
                    "pre-advice to RoyalMail has been sent successfully today.",
                    "The total number of parcels:" + sendNum + "    " + DateUtil.getCurrentDateStrGB("yyyy-MM-dd HH:mm:ss"),
                    "iemis@sinoair.com",
                    null,
                    "iemis",
                    "SinoAiriemis");
        }
    }

    public static void main(String[] args) throws Exception {
        String historyRootPath = "D:/express/SinoairEDIServerHistory";
        SendRmManifestService generateInfo = new SendRmManifestService();
        Connection conn = ConnectionFactory.get200Connection();

        generateInfo.sendManifest(conn, historyRootPath);
        generateInfo.sendEmail(6871);


        //generateInfo.sendRmManifestAndDeleteLocateFiles(historyRootPath);


    }
}
