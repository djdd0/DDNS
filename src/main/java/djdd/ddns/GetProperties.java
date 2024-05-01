package djdd.ddns;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class GetProperties {
    private static String domainName = null;
    private static String RR = null;
    private static String recordType = null;
    private static String password = null;
    private static String accessKeyId = null;
    private static String accessKeySecret = null;
    private static String regionId = null;
    private static String getIPMethod = null;

    public static String getDomainName() {
        return domainName;
    }

    public static String getRR() {
        return RR;
    }

    public static String getRecordType() {
        return recordType;
    }

    public static String getPassword() {
        return password;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static String getRegionId() {
        return regionId;
    }

    public static String getGetIPMethod() {
        return getIPMethod;
    }

    public static void readProperties() throws IOException {
        Properties prop = new Properties();
        Reader in = new InputStreamReader(Files.newInputStream(Paths.get(System.getProperty("user.dir")+"/properties.txt")), StandardCharsets.UTF_8);
        prop.load(in);
        domainName = prop.getProperty("domainName");
        RR = prop.getProperty("RR");
        recordType = prop.getProperty("recordType");
        password = prop.getProperty("password");
        accessKeyId = prop.getProperty("accessKeyId");
        accessKeySecret = prop.getProperty("accessKeySecret");
        regionId = prop.getProperty("regionId");
        getIPMethod = prop.getProperty("getIPMethod");
        in.close();
    }

    public static void main(String[] args) throws IOException {
        readProperties();
        System.out.println(getDomainName());
        System.out.println(getRR());
        System.out.println(getRecordType());
        System.out.println(getPassword());
        System.out.println(getAccessKeyId());
        System.out.println(getAccessKeySecret());
        System.out.println(getRegionId());
        System.out.println(getGetIPMethod());
    }
}
