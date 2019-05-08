package com.sxkj.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.sxkj.util.RequestInfo
 *
 * @author zwd
 * @Description RequestInfo
 * @Date Create in 2018-06-20 0020 10:24
 * @Modified
 */
public class RequestInfo {
    /**
     * 获取客户端浏览器、操作系统、真实ip
     * @param request
     * @return
     */
    public static Map<String, String> getRequestInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(16);
        map.put("system", getRequestSystemInfo(request));
        map.put("browser", getRequestBrowserInfo(request));
        String ip = getIpAddr(request);
        map.put("ip", ip);

        return map;
    }

    /**
     * 获取浏览器信息（品牌）
     * @param request
     * @return
     */
    public static String getRequestBrowserInfo(HttpServletRequest request) {
        String brand = null;
        String header = request.getHeader("user-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        if (header.indexOf("MSIE") > 0) {
            brand = "IE";
        } else if (header.indexOf("Firefox") > 0) {
            brand = "Firefox";
        } else if (header.indexOf("Chrome") > 0) {
            brand = "Chrome";
        } else if (header.indexOf("Safari") > 0) {
            brand = "Safari";
        } else if (header.indexOf("Camino") > 0) {
            brand = "Camino";
        } else if (header.indexOf("Konqueror") > 0) {
            brand = "Konqueror";
        }
        return brand;
    }

    /**
     * 获取系统版本信息
     *
     * @param request
     * @return
     */
    public static String getRequestSystemInfo(HttpServletRequest request) {
        String systemInfo = null;
        String header = request.getHeader("user-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        //得到用户的操作系统
        if (header.indexOf("NT 6.0") > 0) {
            systemInfo = "Windows Vista/Server 2008";
        } else if (header.indexOf("NT 5.2") > 0) {
            systemInfo = "Windows Server 2003";
        } else if (header.indexOf("NT 5.1") > 0) {
            systemInfo = "Windows XP";
        } else if (header.indexOf("NT 6.0") > 0) {
            systemInfo = "Windows Vista";
        } else if (header.indexOf("NT 6.1") > 0) {
            systemInfo = "Windows 7";
        } else if (header.indexOf("NT 6.2") > 0) {
            systemInfo = "Windows Slate";
        } else if (header.indexOf("NT 6.3") > 0) {
            systemInfo = "Windows 9";
        } else if (header.indexOf("NT 10.0") > 0) {
            systemInfo = "Windows 10";
        } else if (header.indexOf("NT 5") > 0) {
            systemInfo = "Windows 2000";
        } else if (header.indexOf("NT 4") > 0) {
            systemInfo = "Windows NT4";
        } else if (header.indexOf("Me") > 0) {
            systemInfo = "Windows Me";
        } else if (header.indexOf("98") > 0) {
            systemInfo = "Windows 98";
        } else if (header.indexOf("95") > 0) {
            systemInfo = "Windows 95";
        } else if (header.indexOf("Mac") > 0) {
            systemInfo = "Mac";
        } else if (header.indexOf("Unix") > 0) {
            systemInfo = "UNIX";
        } else if (header.indexOf("Linux") > 0) {
            systemInfo = "Linux";
        } else if (header.indexOf("SunOS") > 0) {
            systemInfo = "SunOS";
        }
        return systemInfo;
    }

    /**
     * 客户端真实ip
     * @param httpservletrequest
     * @return
     */
    public static String getIpAddr(HttpServletRequest httpservletrequest) {
        if (httpservletrequest == null) {
            return null;
        }
        String s = httpservletrequest.getHeader("X-Forwarded-For");
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpservletrequest.getHeader("Proxy-Client-IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpservletrequest.getHeader("WL-Proxy-Client-IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpservletrequest.getHeader("HTTP_CLIENT_IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpservletrequest.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpservletrequest.getRemoteAddr();
        }
        if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s)) {
            try {
                s = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException unknownhostexception) {
            }
        }
        return s;
    }

    /**
     * 获取MAC地址
     *
     * @return 返回MAC地址
     */
    public static String getMacAddress(String ip) {
        String macAddress;
        macAddress = getMacInWindows(ip).trim();
        if (macAddress == null || "".equals(macAddress)) {
            macAddress = getMacInLinux(ip).trim();
        }
        return macAddress;
    }
    /**
     * 调用命令
     *
     * @param cmd
     * @return
     */
    private static String callCmd(String[] cmd) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param cmd     第一个命令
     * @param another 第二个命令
     * @return 第二个命令的执行结果
     */
    private static String callCmd(String[] cmd, String[] another) {
        String result = "";
        String line = "";
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            proc.waitFor(); // 已经执行完第一个命令，准备执行第二个命令
            proc = rt.exec(another);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param ip           目标ip,一般在局域网内
     * @param sourceString 命令处理的结果字符串
     * @param macSeparator mac分隔符号
     * @return mac地址，用上面的分隔符号表示
     */
    private static String filterMacAddress(final String ip, String sourceString, final String macSeparator) {
        String result = "";
        int index = sourceString.indexOf(ip);
        if (index == -1) {
            index = 0;
        }
        sourceString = sourceString.substring(index, sourceString.length() - 1);
        String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(sourceString);
        while (matcher.find()) {
            result = matcher.group(1);
            if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
                break; // 如果有多个IP,只匹配本IP对应的Mac.
            }
        }
        return result;
    }

    /**
     * @param ip 目标ip
     * @return Mac Address
     */
    private static String getMacInWindows(final String ip) {
        String result = "";
        String[] cmd = {"cmd", "/c", "ping " + ip};
        String[] another = {"cmd", "/c", "arp -a"};
        String cmdResult = callCmd(cmd, another);
        result = filterMacAddress(ip, cmdResult, "-");
        return result;
    }

    /**
     * @param ip 目标ip
     * @return Mac Address
     */
    private static String getMacInLinux(final String ip) {
        String result = "";
        String[] cmd = {"/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a"};
        String cmdResult = callCmd(cmd);
        result = filterMacAddress(ip, cmdResult, ":");

        return result;
    }
}
