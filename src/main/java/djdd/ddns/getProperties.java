package djdd.ddns;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class getProperties {
    private static String password = null;
    private static String accessKeyId = null;
    private static String accessKeySecret = null;
    private static String regionId = null;

    public static String getPassword() throws IOException {
        readProperties();
        return password;
    }

    public static String getAccessKeyId() throws IOException {
        readProperties();
        return accessKeyId;
    }

    public static String getAccessKeySecret() throws IOException {
        readProperties();
        return accessKeySecret;
    }

    public static String getRegionId() throws IOException {
        readProperties();
        return regionId;
    }

    private static void readProperties() throws IOException {
        Properties prop = new Properties();
        Reader in = new InputStreamReader(Files.newInputStream(Paths.get("properties.txt")), StandardCharsets.UTF_8);
        prop.load(in);
        password = prop.getProperty("password");
        accessKeyId = prop.getProperty("accessKeyId");
        accessKeySecret = prop.getProperty("accessKeySecret");
        regionId = prop.getProperty("regionId");
        in.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getPassword());
        System.out.println(getAccessKeyId());
        System.out.println(getAccessKeySecret());
        System.out.println(getRegionId());
    }

}
