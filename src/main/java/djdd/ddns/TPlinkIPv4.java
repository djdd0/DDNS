package djdd.ddns;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TPlinkIPv4 {

    private static String STOK = null;

    private static void login() throws InterruptedException, IOException {

        if (STOK != null) {
            return;
        }
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost hp = new HttpPost("http://192.168.1.1");
        StringEntity LoginEntity = new StringEntity("{\"method\":\"do\",\"login\":{\"password\":\"" +GetProperties.getPassword() +"\"}}");
        hp.setEntity(LoginEntity);
        try {
            CloseableHttpResponse response = httpclient.execute(hp);
            Scanner in = new Scanner(response.getEntity().getContent());
            String s = in.nextLine();
            Matcher m = Pattern.compile("\"stok\":\"(.+?)\"").matcher(s);
            if (m.find()) {
                STOK = m.group(1);
            } else {
                System.out.println("Log in error!");
                Thread.sleep(1000);
                login();
            }
        } catch (IOException e) {
            System.out.println("Log in error!");
            Thread.sleep(1000);
            login();
        }
    }

    public static String get() throws InterruptedException, IOException {
        login();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost hp2 = new HttpPost("http://192.168.1.1/stok="+STOK+"/ds");
        StringEntity GetIPEntity = new StringEntity("{\"network\":{\"name\":\"wan_status\"},\"cloud_config\":{\"name\":[\"new_firmware\",\"device_status\",\"bind\"]},\"wireless\":{\"name\":[\"wlan_wds_2g\",\"wlan_wds_5g\"]},\"method\":\"get\"}");
        hp2.setEntity(GetIPEntity);
        try {
            CloseableHttpResponse response = httpclient.execute(hp2);
            Scanner in = new Scanner(response.getEntity().getContent());
            String s = in.nextLine();
            Matcher ip = Pattern.compile("\"ipaddr\":\"(.+?)\"").matcher(s);
            if (ip.find()) {
                return ip.group(1);
            } else {
                return null;
            }
        } catch (IOException e) {
            System.out.println("get IP error!");
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println(get());
    }
}
