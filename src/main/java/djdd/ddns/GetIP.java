package djdd.ddns;

import java.io.IOException;
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
