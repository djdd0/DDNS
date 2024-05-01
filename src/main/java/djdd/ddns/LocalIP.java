package djdd.ddns;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class LocalIP {
    public static String getIPv4() throws SocketException {
        InetAddress inetAddress =null;
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        outer:
        while(networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAds = networkInterfaces.nextElement().getInetAddresses();
            while(inetAds.hasMoreElements()) {
                inetAddress = inetAds.nextElement();
                //检查此地址是否是IPv6地址以及是否是保留地址
                if(inetAddress instanceof Inet4Address && isGlobalAddr(inetAddress)) {
                    break outer;
                }
            }
        }
        String ipAddr = null;
        if (inetAddress != null) {
            ipAddr = inetAddress.getHostAddress();
            //过滤网卡
            int index = 0;
            if (ipAddr != null) {
                index = ipAddr.indexOf('%');
            }
            if(index>0) {
                ipAddr = ipAddr.substring(0, index);
            }
        }
        return ipAddr;
    }

    public static String getIPv6() throws SocketException {
        InetAddress inetAddress =null;
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        outer:
        while(networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAds = networkInterfaces.nextElement().getInetAddresses();
            while(inetAds.hasMoreElements()) {
                inetAddress = inetAds.nextElement();
                //检查此地址是否是IPv6地址以及是否是保留地址
                if(inetAddress instanceof Inet6Address && isGlobalAddr(inetAddress)) {
                    break outer;
                }
            }
        }
        String ipAddr = null;
        if (inetAddress != null) {
            ipAddr = inetAddress.getHostAddress();
            //过滤网卡
            int index = 0;
            if (ipAddr != null) {
                index = ipAddr.indexOf('%');
            }
            if(index>0) {
                ipAddr = ipAddr.substring(0, index);
            }
        }
        return ipAddr;
    }

    private static boolean isGlobalAddr(InetAddress inetAddr) {
        return !inetAddr.isAnyLocalAddress() && !inetAddr.isLinkLocalAddress() && !inetAddr.isLoopbackAddress();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getIPv4());
        System.out.println(getIPv6());
    }
}
