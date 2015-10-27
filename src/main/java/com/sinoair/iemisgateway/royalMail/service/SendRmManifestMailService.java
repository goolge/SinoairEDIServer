package com.sinoair.iemisgateway.royalMail.service;

import com.sinoair.iemisgateway.royalMail.domain.RoyalMailManifest;
import com.sinoair.iemisgateway.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-7-30
 * Time: 上午9:43
 * To change this template use File | Settings | File Templates.
 */
public class SendRmManifestMailService {
    /**
     * 获取需要发送预报的数据集合
     *
     * @param conn
     * @return
     * @throws Exception
     */
    public Map<String, String> getSendMialInfo(Connection conn) throws Exception {
        String aheadDateStr=DateUtil.getDateStrAheadDays(-1,"yyyy-MM-dd")+" 00:00:00";

        String sql = "select nvl(count(esa.pkg_printcode),0) pkgNum," + //1
                " nvl(sum(esa.pkg_actualweight),0) pkgWeight" +//2
                " from expresssortpackagedetail esa," +
                " (select ed.pkg_parentcode" +
                " from expresssortpackagedetail ed, express_manifest em" +
                " where ed.pkg_printcode = em.eawb_printcode" +
                " and em.em_partner = '"+RoyalMailManifest.PARTNER_CODE+"' and em.em_handletime >to_date('"+aheadDateStr+"','yyyy-mm-dd hh24:mi:ss') " +
                " group by ed.pkg_parentcode) data" +
                " where esa.pkg_printcode = data.pkg_parentcode " ;
        PreparedStatement selectPstm = conn.prepareStatement(sql);
         //LogUtil.log(" 英邮标准小包发邮件："+sql);
            ResultSet rs = selectPstm.executeQuery();
           Map<String, String> map=new HashMap<String,String>();
            map.put("pkgNum","0");
            map.put("pkgWeight","0");
            if (rs.next()) {
                map.put("pkgNum",rs.getString(1)+"");
                map.put("pkgWeight",rs.getString(2)+"");
                 //LogUtil.log(" 英邮标准小包邮件数据--大包数量："+rs.getString(1)+" 大包重量："+rs.getString(2));
            }
         ConnectionFactory.closeConnection(conn);
        return map;
    }
    public  void sendEmail(Connection conn) throws Exception{
        Map<String,String> map=getSendMialInfo(conn);
        int pkgNum=Integer.parseInt(map.get("pkgNum"));
        double pkgWeight=Double.parseDouble(map.get("pkgWeight"));
        //LogUtil.log(" 22英邮标准小包邮件数据--大包数量："+pkgNum+" 大包重量："+pkgWeight);
        if(pkgNum>0){
            MailUtil.postMail(
                    PropertiesUtil.readProperty("royalMail", "rmMailTo"),
                    PropertiesUtil.readProperty("royalMail", "rmMailCc"),
                   PropertiesUtil.readProperty("royalMail", "rmMailBcc"),
                    "the coming mail number to LON",
                    "Packets number: "+pkgNum+" ,Packets weight: "+pkgWeight+" kg, Expected arriving time: 24 hours later",
                    "iemis@sinoair.com",
                    null,
                    "iemis",
                    "SinoAiriemis");
            LogUtil.log(" 英邮标准小包邮件发送成功--大包数量："+pkgNum+" 大包重量："+pkgWeight);
        }
    }
    public static void main(String[] args) throws Exception {
        SendRmManifestMailService generateInfo = new SendRmManifestMailService();
        Connection conn = ConnectionFactory.get194Connection();
        generateInfo.sendEmail(conn);
    }
}
