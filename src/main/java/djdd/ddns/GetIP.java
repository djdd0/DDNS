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

public class GetIP {


    public static String get() throws IOException, InterruptedException {
        switch (GetProperties.getGetIPMethod()) {
            case "localIPv4":
                return LocalIP.getIPv4();
            case "localIPv6":
                return LocalIP.getIPv6();
            case "TPlinkIPv4":
                return TPlinkIPv4.get();
            default:
                System.out.println("Get IP error!");
                return null;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println(get());
    }
}
