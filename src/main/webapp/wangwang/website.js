var DEFAULT_VERSION = "unknown",
EMPTY = "";
function addContact(d, g, b, e, c, f, h) {
    if (d == "") {
        d = b
    }
    var a = "";
    var i = checkIMVersion(d + g, b, h);
    if (i == "-1") {} else {
        if (i == "0") {
            if (b == "wangwang" || b == "cnalimam") {
                if (d = "cnalichn") {
                    showAlitalkErrMsg(1)
                } else {
                    if (d = "cntaobao") {
                        showWangwangErrMsg(1)
                    }
                }
                return
            }
            if (d == "cntaobao") {
                a = "WangWang:AddContact?uid=" + e + "&CntSiteId=" + b + "&SiteLogonedUserId=" + g + "&SelfSiteId=" + d + "&gid=" + c + "&verify=" + f + getMoreProperties(h);
                execTaobaoShell(a)
            } else {
                if (d = "cnalichn") {
                    a = "Alitalk:AddContact?uid=" + e + "&CntSiteId=" + b + "&AliLoginID=" + g + "&AliLoginsiteid=" + d + "&gid=" + c + "&checkcrm=" + getMoreProperties(h);
                    location = a
                }
            }
        } else {
            if (i == "1") {
                a = "aliim:addcontact?uid=" + d + g + "&touid=" + b + e + "&gid=" + c + "&verify=" + f + getMoreProperties(h);
                execAliimShell(a)
            }
        }
    }
}
function sendClientMsg(e, h, d, b, f, g) {
    if (e == "") {
        e = d
    }
    var a = checkIMVersion(e + h, d, g);
    if (a == "-1") {} else {
        if (a == "0") {
            if (d == "wangwang" || d == "cnalimam") {
                if (e == "cnalichn") {
                    sendMsg("cnalichn", h, d, b, f, g)
                } else {
                    if (e == "cntaobao") {
                        sendMsg("cntaobao", h, d, b, f, g)
                    } else {
                        sendMsg("cnalichn", h, d, b, f, g)
                    }
                }
                return
            }
            if (e == "cnkoubei") {
                var c = isSupportKouBei();
                if (c == 1) {
                    sendMsg("cnalichn", h, d, b, f, g)
                } else {
                    if (c == 2) {
                        sendMsg("cntaobao", h, d, b, f, g)
                    } else {
                        downloadWangWang(e)
                    }
                }
            } else {
                if (e != DEFAULT_VERSION) {
                    sendMsg(e, h, d, b, f, g)
                } else {
                    downloadWangWang(e)
                }
            }
        } else {
            if (a == "1") {
                sendMsgV6Web(e, h, d, b, f, g)
            }
        }
    }
}
function sendMsg(c, f, b, a, d, e) {
    if (c == "chnyahoo") {
        newYahooSendMsg(c, f, b, a, d, e)
    } else {
        if (c == "cntaobao") {
            newWangWangSendMsg(c, f, b, a, d, e)
        } else {
            if (c == "cnalichn") {
                newAlitalkSendMsg(c, f, b, a, d, e)
            }
        }
    }
}
function newWangWangSendMsg(d, g, c, b, e, f) {
    var a = WangWangVerSupportedSMS("cntaobao");
    if (a && e == 4) {
        location.href = "wangwang:SendSms?" + b + "&tositeid=" + c + "&suid=" + g + "&status=" + e + getMoreProperties(f)
    } else {
        location.href = "wangwang:SendIM?uid=" + b + "&tositeid=" + c + "&suid=" + g + "&status=" + e + getMoreProperties(f)
    }
}
function newAlitalkSendMsg(d, g, c, b, e, f) {
    var a = alitalkVerSupportedSMS("cnalichn");
    if (a && e == 4) {
        location.href = "Alitalk:SendSms?" + b + "&siteid=" + c + "&tositeid=" + c + "&status=" + e + "&SendLoginedAlitalk=1" + getMoreProperties(f)
    } else {
        location.href = "Alitalk:SendIM?" + b + "&siteid=" + c + "&AliLoginID=" + g + "&status=" + e + "&SendLoginedAlitalk=1" + getMoreProperties(f)
    }
}
function newYahooSendMsg(d, g, c, b, e, f) {
    var a = yahooVerSupportedSMS("chnyahoo");
    if (a && e == 4) {
        location.href = "YahooWW:SendSms?" + b + "&tositeid=" + c + "&status=" + e + getMoreProperties(f)
    } else {
        location.href = "YahooWW:SendIM?" + b + "&tositeid=" + c + "&status=" + e + getMoreProperties(f)
    }
}
function onlinewangWangSendMsg(c, g, a, d, e, h) {
    var b = c;
    if (c == "" || c == "cnanonym") {
        b = a
    }
    var i = checkIMVersionNoMsg(b);
    if (i != "-1") {
        if (c == "cnanonym") {
            g = ""
        }
        onlinesendMsg(c, g, a, d, e, h)
    } else {
        if (c == "cnanonym") {
            var f = "http://onlineww.im.alisoft.com/wangwang/webim.jsp?memberid=" + encodeURI(g) + "&targetid=" + encodeURI(a + d) + getMoreProperties(h);
            window.open(f)
        } else {
            downloadWangWang(c)
        }
    }
}
function startWebwwTaobao() {
    window.open("//webww.taobao.com/wangwang/webww1.htm")
}
function onlinesendMsg(d, g, c, b, e, f) {
    if (d == "" || d == "cnanonym") {
        d = c
    }
    var a = checkIMVersionNoMsg(d);
    if (a == "-1") {} else {
        if (a == "0") {
            if (c == "wangwang" || c == "cnalimam") {
                if (d = "cnalichn") {
                    showAlitalkErrMsg(1)
                } else {
                    if (d = "cntaobao") {
                        showWangwangErrMsg(1)
                    }
                }
                return
            }
            if (d == "chnyahoo") {
                onlinenewYahooSendMsg(d, g, c, b, e, f)
            } else {
                if (d == "cntaobao") {
                    onlinenewWangWangSendMsg(d, g, c, b, e, f)
                } else {
                    if (d == "cnalichn") {
                        onlinenewAlitalkSendMsg(d, g, c, b, e, f)
                    }
                }
            }
        } else {
            if (a == "1") {
                command = "aliim:sendmsg?uid=" + d + g + "&touid=" + c + b + "&siteid=" + c + "&status=" + e + getMoreProperties(f);
                execAliimShell(command)
            }
        }
    }
}
function onlinenewAlitalkSendMsg(d, g, c, b, e, f) {
    var a = alitalkVerSupportedSMS("cnalichn");
    if (a && e == 4) {
        location.href = "Alitalk:SendSms?" + b + "&siteid=" + c + "&status=" + e + "&AliLoginID=" + g + "&AliLoginsiteid=" + d + "&SendLoginedAlitalk=1" + getMoreProperties(f)
    } else {
        location.href = "Alitalk:SendIM?" + b + "&siteid=" + c + "&status=" + e + "&AliLoginID=" + g + "&AliLoginsiteid=" + d + "&SendLoginedAlitalk=1" + getMoreProperties(f)
    }
}
function onlinenewWangWangSendMsg(d, g, c, b, e, f) {
    var a = WangWangVerSupportedSMS("cntaobao");
    if (a && e == 4) {
        location.href = "wangwang:SendSms?" + b + "&tositeid=" + c + "&status=" + e + "&suid=" + g + getMoreProperties(f)
    } else {
        location.href = "wangwang:SendIM?uid=" + b + "&tositeid=" + c + "&status=" + e + "&suid=" + g + getMoreProperties(f)
    }
}
function onlinenewYahooSendMsg(d, g, c, b, e, f) {
    var a = yahooVerSupportedSMS("chnyahoo");
    if (a && e == 4) {
        location.href = "YahooWW:SendSms?" + b + "&tositeid=" + c + "&status=" + e + "&suid=" + g + getMoreProperties(f)
    } else {
        location.href = "YahooWW:SendIM?uid=" + b + "&tositeid=" + c + "&status=" + e + "&suid=" + g + getMoreProperties(f)
    }
}
function onlinewangWangSend(c, g, b, a, e, f) {
    alert(a + "   aaaaa");
    if (a != "" || a != null || a != "undefined") {
        var d = (document.getElementById ? true: false);
        if (window.aliwangwangSendmsgShowFrame) {
            if (d) {
                alert(a + "   isDOM");
                document.getElementById("aliwangwangSendmsgShowFrame").src = "//webjs.ww.taobao.com/webim/common/online_dispatch_v6.html?fromsite=" + c + "&fromid=" + g + "&sendsite=" + b + "&sendid=" + a + "&imstatus=" + e + "&moreProperties=" + escape(f)
            } else {
                alert(a + "   bbbbb");
                window.aliwangwangSendmsgShowFrame.location = "//webjs.ww.taobao.com/webim/common/online_dispatch_v6.html?fromsite=" + c + "&fromid=" + g + "&sendsite=" + b + "&sendid=" + a + "&imstatus=" + e + "&moreProperties=" + escape(f)
            }
        } else {
            alert(a);
            onlinesendMsg(c, g, b, a, e, f + "&fenliu=0")
        }
    }
}
function startWW(e, c, d) {
    var b = e;
    var a = checkIMVersionNoMsg(b);
    if (a == "-1") {
        return
    } else {
        if (a == "0") {
            if (e == "cntaobao") {
                if (c == "") {
                    location.href = "WangWang:"
                } else {
                    location.href = "WangWang:Login?suid=" + c + "&autologin=0"
                }
            }
            if (e == "chnyahoo") {
                location.href = "yahooWW:"
            }
            if (e == "cnalichn") {
                location.href = "Alitalk:"
            }
        } else {
            if (a == "1") {
                var f = "aliim:login?uid=" + e + c + "&autologin=0&weblogin=1";
                execAliimShell(f)
            }
        }
    }
}
function CloseWinWithoutParent() {
    var b = navigator.userAgent;
    var c = navigator.appName == "Microsoft Internet Explorer" ? true: false;
    if (c) {
        var a = parseFloat(b.substring(b.indexOf("MSIE ") + 5, b.indexOf(";", b.indexOf("MSIE "))));
        if (a < 5.5) {
            window.opener = null;
            window.close()
        } else {
            if (a < 7) {
                window.opener = null;
                window.close()
            } else {
                window.opener = null;
                window.close()
            }
        }
    } else {
        window.close()
    }
}
function isInstalledClient(c) {
    try {
        var b = new ActiveXObject("aliimx.wangwangx");
        if (b != null) {
            return 1
        }
    } catch(d) {}
    var a = getSite(c);
    if (a == "cntaobao") {
        if (newCheckWangWangInstalled() != DEFAULT_VERSION) {
            return 1
        }
    } else {
        if (a == "cnalichn") {
            if (newCheckAlitalkInstalled() != DEFAULT_VERSION) {
                return 1
            }
        } else {
            if (a == "chnyahoo") {
                if (newCheckYahooInstalled() != DEFAULT_VERSION) {
                    return 1
                }
            } else {
                return 0
            }
        }
    }
    return 0
}
function checkIMVersionNoMsg(c) {
    var d = -1;
    try {
        var b = new ActiveXObject("aliimx.wangwangx");
        if (b != null) {
            d = 1
        }
    } catch(f) {}
    var a = c;
    if (a == "cntaobao") {
        if (newCheckWangWangInstalled() != DEFAULT_VERSION) {
            return 0
        }
    } else {
        if (a == "cnalichn") {
            if (newCheckAlitalkInstalled() != DEFAULT_VERSION) {
                return 0
            }
        } else {
            if (a == "chnyahoo") {
                if (newCheckYahooInstalled() != DEFAULT_VERSION) {
                    return 0
                }
            } else {
                return d
            }
        }
    }
    return d
}
function newCheckAlitalkInstalled() {
    var a = DEFAULT_VERSION;
    try {
        var c = new ActiveXObject("AlitalkSetup.Install");
        if (c != null) {
            a = "cnalichn"
        }
    } catch(b) {}
    return a
}
function newCheckWangWangInstalled() {
    var a = DEFAULT_VERSION;
    try {
        var c = new ActiveXObject("WangWangX.WangWangObj");
        if (c != null && "1.6.06.0525" != c.GetVersionStr()) {
            a = "cntaobao"
        }
    } catch(b) {} finally {
        c = null
    }
    return a
}
function newCheckYahooInstalled() {
    var a = DEFAULT_VERSION;
    try {
        var c = new ActiveXObject("YahooWangWangX.WangWangObj");
        if (c != null) {
            a = "chnyahoo"
        }
    } catch(b) {}
    return a
}
function checkIMVersion(d, a, f) {
    var g = "-1";
    try {
        var c = new ActiveXObject("aliimx.wangwangx");
        if (c != null) {
            g = 1
        }
        var i = c.IsLogin(d);
        if (i == "true") {
            return "1"
        }
    } catch(h) {}
    var b = getSite(d);
    if (b == "") {
        b = a
    }
    if (b == "cntaobao") {
        g = checkTaobaoVer(g)
    } else {
        if (b == "chnyahoo") {
            g = checkYahooVer(g)
        } else {
            if (b == "wangwang" || b == "cnalimam") {
                if (g == 1) {
                    return "1"
                } else {
                    showWanghaoErrMsg(2)
                }
            } else {
                if (b == "enaliint") {
                    if (g == 1) {
                        return "1"
                    } else {
                        showWanghaoErrMsg(2)
                    }
                } else {
                    g = checkAlitalkVer(g)
                }
            }
        }
    }
    return g
}
function checkTaobaoVer(c) {
    try {
        var f = new ActiveXObject("WangWangX.WangWangObj");
        if (f != null) {
            var a = f.GetVersionStr();
            var b = a.charAt(0);
            if (a != null) {
                return "0"
            } else {
                if (c == 1) {
                    return "1"
                } else {
                    if (c == 1) {
                        return "1"
                    } else {
                        showWangwangErrMsg(0)
                    }
                }
            }
        } else {
            if (c == 1) {
                return "1"
            } else {
                showWangwangErrMsg(1)
            }
        }
    } catch(d) {
        if (c == 1) {
            return "1"
        } else {
            showWangwangErrMsg(2)
        }
    }
}
function checkAlitalkVer(b) {
    try {
        var d = new ActiveXObject("Ali_Check.InfoCheck");
        if (d != null) {
            var a = d.GetValueBykey("AliTalkVersion");
            if (a != null) {
                return "0"
            } else {
                if (b == 1) {
                    return "1"
                } else {
                    showAlitalkErrMsg(0)
                }
            }
        } else {
            if (b == 1) {
                return "1"
            } else {
                showAlitalkErrMsg(1)
            }
        }
    } catch(c) {
        if (b == 1) {
            return "1"
        } else {
            showAlitalkErrMsg(2)
        }
    }
}
function checkYahooVer(a) {
    try {
        var c = new ActiveXObject("YahooWangWangX.WangWangObj");
        if (c != null) {
            return 0
        } else {
            if (a == 1) {
                return "1"
            } else {
                showYahooErrMsg(1)
            }
        }
    } catch(b) {
        if (a == 1) {
            return "1"
        } else {
            showYahooErrMsg(2)
        }
    }
}
function showWanghaoErrMsg(a) {
    if (a == 0) {
        alert("\u4f60\u7684\u7248\u672c\u8fc7\u65e7\uff0c\u6ca1\u6709\u8be5\u529f\u80fd\uff0c\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u672c!");
        window.target = "_blank";
        window.open("http://im.alisoft.com/download/index.html")
    } else {
        if (a == 2) {
            alert("\u4f60\u6ca1\u6709\u5b89\u88c5\u963f\u91cc\u65fa\u65fa,\u8bf7\u4e0b\u8f7d\u963f\u91cc\u65fa\u65fa!");
            window.target = "_blank";
            window.open("http://im.alisoft.com/download/index.html")
        } else {
            if (a == 1) {
                alert("\u4f60\u7684\u963f\u91cc\u65fa\u65fa\u7248\u672c\u6709\u95ee\u9898,\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u963f\u91cc\u65fa\u65fa\uff01");
                window.target = "_blank";
                window.open("http://im.alisoft.com/download/index.html")
            }
        }
    }
}
function showWangwangErrMsg(a) {
    if (a == 0) {
        alert("\u4f60\u7684\u7248\u672c\u8fc7\u65e7\uff0c\u6ca1\u6709\u8be5\u529f\u80fd\uff0c\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u672c!");
        window.target = "_blank";
        window.open("http://im.alisoft.com/download/taobao.html")
    } else {
        if (a == 2) {
            alert("\u4f60\u6ca1\u6709\u5b89\u88c5\u963f\u91cc\u65fa\u65fa,\u8bf7\u4e0b\u8f7d\u963f\u91cc\u65fa\u65fa!");
            window.target = "_blank";
            window.open("http://im.alisoft.com/download/taobao.html")
        } else {
            if (a == 1) {
                alert("\u4f60\u7684\u963f\u91cc\u65fa\u65fa\u7248\u672c\u6709\u95ee\u9898,\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u963f\u91cc\u65fa\u65fa\uff01");
                window.target = "_blank";
                window.open("http://im.alisoft.com/download/taobao.html")
            }
        }
    }
}
function showAlitalkErrMsg(a) {
    if (a == 0) {
        alert("\u4f60\u7684\u7248\u672c\u8fc7\u65e7\uff0c\u6ca1\u6709\u8be5\u529f\u80fd\uff0c\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u672c!");
        window.target = "_blank";
        window.open("http://im.alisoft.com/download/alibaba.html")
    } else {
        if (a == 2) {
            alert("\u4f60\u6ca1\u6709\u5b89\u88c5\u963f\u91cc\u65fa\u65fa,\u8bf7\u4e0b\u8f7d\u963f\u91cc\u65fa\u65fa!");
            window.target = "_blank";
            window.open("http://im.alisoft.com/download/alibaba.html")
        } else {
            if (a == 1) {
                alert("\u4f60\u7684\u963f\u91cc\u65fa\u65fa\u7248\u672c\u6709\u95ee\u9898,\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u963f\u91cc\u65fa\u65fa\uff01");
                window.target = "_blank";
                window.open("http://im.alisoft.com/download/alibaba.html")
            }
        }
    }
}
function showYahooErrMsg(a) {
    if (a == 0) {
        alert("\u4f60\u7684\u7248\u672c\u8fc7\u65e7\uff0c\u6ca1\u6709\u8be5\u529f\u80fd\uff0c\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u672c!");
        window.target = "_blank";
        window.open("http://im.alisoft.com/download/index.html")
    } else {
        if (a == 2) {
            alert("\u4f60\u6ca1\u6709\u5b89\u88c5\u963f\u91cc\u65fa\u65fa,\u8bf7\u4e0b\u8f7d\u963f\u91cc\u65fa\u65fa!");
            window.target = "_blank";
            window.open("http://im.alisoft.com/download/index.html")
        } else {
            if (a == 1) {
                alert("\u4f60\u7684\u963f\u91cc\u65fa\u65fa\u7248\u672c\u6709\u95ee\u9898,\u8bf7\u4e0b\u8f7d\u6700\u65b0\u7248\u963f\u91cc\u65fa\u65fa\uff01");
                window.target = "_blank";
                window.open("http://im.alisoft.com/download/index.html")
            }
        }
    }
}
function openDownloadPage(a) {
    var b = "";
    if (a == "cntaobao") {
        b = "http://im.alisoft.com/download/taobao.html"
    } else {
        if (a == "cnalichn") {
            b = "http://im.alisoft.com/download/alibaba.html"
        } else {
            b = "http://im.alisoft.com/download/index.html"
        }
    }
    window.target = "_blank";
    window.open(b)
}
function getSite(b) {
    var a = "";
    b = b.replace(/^\s+|\s+$/g, "");
    if (b.indexOf("cnalichn") >= 0) {
        a = b.substr(0, 8)
    } else {
        if (b.indexOf("cntaobao") >= 0) {
            a = b.substr(0, 8)
        } else {
            if (b.indexOf("chnyahoo") >= 0) {
                a = b.substr(0, 8)
            } else {
                if (b.indexOf("cnkoubei") >= 0) {
                    a = b.substr(0, 8)
                } else {
                    if (b.indexOf("cnwujing") >= 0) {
                        a = b.substr(0, 8)
                    } else {
                        if (b.indexOf("chnaigou") >= 0) {
                            a = b.substr(0, 8)
                        } else {
                            if (b.indexOf("cntbbtoc") >= 0) {
                                a = b.substr(0, 8)
                            } else {
                                if (b.indexOf("wangwang") >= 0) {
                                    a = b.substr(0, 8)
                                } else {
                                    if (b.indexOf("cnalimam") >= 0) {
                                        a = b.substr(0, 8)
                                    } else {
                                        if (b.indexOf("enaliint") >= 0) {
                                            a = b.substr(0, 8)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return a
}
function getSiteLoginId(a) {
    if (a == null || a == "") {
        return ""
    }
    if ((getSite(a) != "") && (a.length >= 9)) {
        return a.substr(8)
    }
    return ""
}
function execTaobaoShell(a) {
    location = a
}
function execAlitalkShell(a) {
    location = a
}
function isSupportKouBei() {
    var a = 0;
    try {
        var f = new ActiveXObject("Ali_Check.InfoCheck");
        var c = f.GetValueBykey("AliTalkVersion");
        if (c >= "5.50.00") {
            return 1
        } else {
            a = 3
        }
    } catch(d) {
        try {
            var f = new ActiveXObject("WangWangX.WangWangObj");
            var c = f.GetVersionStr();
            var b = c.charAt(0);
            if (b != "R" && c >= "5.50.00W") {
                return 2
            } else {
                a = 3
            }
        } catch(d) {
            return a
        }
    }
    return a
}
function taobaoVerSupportedSMS(d) {
    var c = false;
    try {
        var g = new ActiveXObject("WangWangX.WangWangObj");
        var b = g.GetVersionStr();
        var a = b.charAt(0);
        if (a != "R" && b >= "5.50.00W") {
            c = true
        }
    } catch(f) {}
    return c
}
function alitalkVerSupportedSMS(c) {
    var b = false;
    try {
        var f = new ActiveXObject("Ali_Check.InfoCheck");
        var a = f.GetValueBykey("AliTalkVersion");
        if (a >= "5.50.00") {
            b = true
        }
    } catch(d) {}
    return b
}
function yahooVerSupportedSMS(a) {
    return true
}
function WangWangVerSupportedSMS(b) {
    var a = false;
    if (b == "cntaobao") {
        a = taobaoVerSupportedSMS(b)
    } else {
        if (b == "cnalichn") {
            a = alitalkVerSupportedSMS(b)
        } else {
            if (b == "chnyahoo") {
                a = yahooVerSupportedSMS(b)
            }
        }
    }
    return a
}
function downloadWangWang(a) {
    if (a == "chnyahoo") {
        location.href = "http://www.alisoft.com/portal/yahooww/site/index.html"
    } else {
        if (a == "cntaobao") {
            window.open("http://webwwtb.im.alisoft.com/wangwang/webww1.htm")
        } else {
            if (a == "cnkoubei") {
                location.href = "http://download.im.alisoft.com/cnkoubei.php"
            } else {
                if (confirm(unescape("%u662F%u5426%u4E0B%u8F7D%u963F%u91CC%u65FA%u65FA%3F"))) {
                    location.href = "http://download.im.alisoft.com/download.php"
                }
            }
        }
    }
}
function spaceAppInviteV6(a, g, b, f, d, c) {
    var e = "aliim:sendappcmd?uid=" + a + "&cmdid=" + g + "&appid=" + b + "&param=" + f;
    if (d != null) {
        e += "&param2=" + d
    }
    if (c != null) {
        e += "&param3=" + c
    }
    execAliimShell(e)
} (function() {
    var c = window,
    b = document,
    a = {
        checkVersion: function(d, h) {
            if (!d) {
                return false
            }
            var f = this,
            e = f.getVersion(),
            g = false;
            if (e && f._checkVersion(e, d, h)) {
                g = true
            }
            return g
        },
        getVersion: function() {
            var e = this,
            d = "";
            if (!e._isMac()) {
                if (e._isIE()) {
                    d = e._checkIE()
                } else {
                    d = e._checkOther()
                }
            } else {
                d = e._checkOther(true)
            }
            return d
        },
        isInstall: function(g) {
            var d = this,
            f = false;
            if (d.getVersion()) {
                f = true
            } else {
                if (g) {
                    try {
                        g()
                    } catch(h) {}
                }
            }
            return f
        },
        _checkIE: function() {
            var g, d = 0,
            f = false;
            try {
                g = new ActiveXObject("aliimx.wangwangx");
                d = g.GetWangWangVersion();
                f = d
            } catch(h) {} finally {
                g = null
            }
            return f
        },
        _checkOther: function(h) {
            var ua = navigator.userAgent.toLowerCase();

            if (ua.indexOf('chrome') > -1 && parseInt(ua.match(/chrome\/([\d.]+)/)[1].split('.')[0]) >= 42) {
                return true;
            }
            var f = navigator.mimeTypes["application/ww-plugin"],
            g = false,
            d = 0;
            if (f) {
                var i = b.createElement("embed"),
                d,
                j;
                i.setAttribute("type", "application/ww-plugin");
                i.style.width = i.style.height = "0px";
                i.style.visibility = "hidden";
                b.body.appendChild(i);
                j = !h ? (i.NPWWVersion()) : (i.isMacWWInstalled());
                try {
                    d = i.WWVersion()
                } catch(k) {}
                if (j) {
                    g = j
                }
                i.parentNode.removeChild(i)
            }
            return g
        },
        _checkVersion: function(e, d, h) {
            var g = this,
            f = parseFloat(d);
            if (h) {
                return (e.indexOf(h.toUpperCase()) > -1) && g._numberify(e) >= f
            }
            return f >= g._numberify(e)
        },
        _numberify: function(d) {
            var e = 0;
            return parseFloat(d.replace(/\./g,
            function() {
                return (e++==0) ? ".": ""
            }))
        },
        _isIE: function() {
            var ua = navigator.userAgent.toLowerCase();
            var s; (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? s[1] : (s = ua.match(/msie ([\d.]+)/)) ? s[1] : 0;
            return s;
        },
        _isMac: function() {
            return (navigator.platform.indexOf("Mac") > -1)
        }
    };
    c.wwclient = a
})();
function sendClientMsg(c, g, b, a, d, f) {
    var e = wwclient.isInstall() || false;
    if (e) {
        c = c || b;
        sendMsgV6Web(c, g, b, a, d, f)
    } else {
        alert("\u65e0\u6cd5\u68c0\u6d4b\u5230\u65fa\u65fa\u63d2\u4ef6\uff0c\u53ef\u80fd\u60a8\u8fd8\u672a\u5b89\u88c5\u963f\u91cc\u65fa\u65fa\uff01")
    }
}
function addContact(c, h, b, a, d, g, f) {
    var e = wwclient.isInstall() || false;
    if (e) {
        execAliimShell("aliim:addcontact?uid=" + c + h + "&touid=" + b + a + "&gid=" + d + "&verify=" + g + getMoreProperties(f))
    } else {
        alert("\u65e0\u6cd5\u68c0\u6d4b\u5230\u65fa\u65fa\u63d2\u4ef6\uff0c\u53ef\u80fd\u60a8\u8fd8\u672a\u5b89\u88c5\u963f\u91cc\u65fa\u65fa\uff01")
    }
}
function sendMsgV6Web(c, g, b, a, d, e) {
    var f = "";
    if (d == 4) {
        f = "aliim:smssendmsg?uid=" + c + g + "&touid=" + b + a + "&suid=" + g
    } else {
        f = "aliim:sendmsg?uid=" + c + g + "&touid=" + b + a + "&siteid=" + b + "&status=" + d + getMoreProperties(e)
    }
    execAliimShell(f)
}
function getMoreProperties(a) {
    var b = "";
    if (a != "") {
        if (a.substring(0, 1) != "&") {
            b = "&" + a
        } else {
            b = a
        }
    }
    return b
}
function execAliimShell(a) {
    window.location.href = a
};