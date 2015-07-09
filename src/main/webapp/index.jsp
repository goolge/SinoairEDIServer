<%@ page import="com.sinoair.iemisgateway.util.BaseLogger" %>
<%@ page import="com.sinoair.iemisgateway.util.PropertiesUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: WangXX4
  Date: 14-7-7
  Time: 下午1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String environmentName = PropertiesUtil.readProperty("common", "environment.name");
    String environmentDesc = PropertiesUtil.readProperty("common", "environment.desc");
    String historyRootPath = PropertiesUtil.readProperty("common", "historyRootPath");
    BaseLogger.info("historyRootPath = " + historyRootPath);
%>
<html>
<head><title>sinoair</title></head>
<body>
Welcome to Sinoair.<br/>
environmentName:<%=environmentName%><br/>
environmentDesc:<%=environmentDesc%><br/>
historyRootPath:<%=historyRootPath%><br/>
</body>
</html>