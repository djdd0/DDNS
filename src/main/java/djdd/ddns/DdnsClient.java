package djdd.ddns;

import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;

public class DdnsClient {

    /**
     * Initialization  初始化公共请求参数
     */
    public static com.aliyun.alidns20150109.Client Initialization() throws Exception {
        Config config = new Config();
        // 您的AccessKey ID
        config.accessKeyId = getProperties.getAccessKeyId();
        // 您的AccessKey Secret
        config.accessKeySecret = getProperties.getAccessKeySecret();
        // 您的可用区ID
        config.regionId = getProperties.getRegionId();
        return new com.aliyun.alidns20150109.Client(config);
    }

    /**
     * 获取主域名的解析记录
     */
    public static DescribeDomainRecordsResponse DescribeDomainRecords(com.aliyun.alidns20150109.Client client, String domainName, String RR, String recordType) {
        DescribeDomainRecordsRequest req = new DescribeDomainRecordsRequest();
        // 主域名
        req.domainName = domainName;
        // 主机记录
        req.RRKeyWord = RR;
        // 解析记录类型
        req.type = recordType;
        try {
            return client.describeDomainRecords(req);
        } catch (TeaException error) {
            System.out.println(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            System.out.println(error.message);
        }
        return null;
    }

    /**
     * 修改解析记录
     */
    public static void UpdateDomainRecord(com.aliyun.alidns20150109.Client client, UpdateDomainRecordRequest req) {
        try {
            client.updateDomainRecord(req);
        } catch (TeaException error) {
            System.out.println(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            System.out.println(error.message);
        }
    }

    public static void update() throws Exception {
        String currentHostIP = getIP.get();//要更新的ip
        String domainName = "djdd.fun";//主域名
        String RR = "in1";//主机记录
        String recordType = "A";//记录类型
        System.out.println("将"+RR+"."+domainName+"记录更新为："+currentHostIP);

        com.aliyun.alidns20150109.Client client = DdnsClient.Initialization();
        DescribeDomainRecordsResponse resp = DdnsClient.DescribeDomainRecords(client, domainName, RR, recordType);
        if (resp==null || com.aliyun.teautil.Common.isUnset(TeaModel.buildMap(resp)) || com.aliyun.teautil.Common.isUnset(TeaModel.buildMap(resp.body.domainRecords.record.get(0)))) {
            System.out.println("错误参数！");
            return ;
        }

        DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord record = resp.body.domainRecords.record.get(0);
        // 记录ID
        String recordId = record.recordId;
        // 记录值
        String recordsValue = record.value;
        if (!com.aliyun.teautil.Common.equalString(currentHostIP, recordsValue)) {
            // 修改解析记录
            UpdateDomainRecordRequest req = new UpdateDomainRecordRequest();
            // 主机记录
            req.RR = RR;
            // 记录ID
            req.recordId = recordId;
            // 将主机记录值改为当前主机IP
            req.value = currentHostIP;
            // 解析记录类型
            req.type = recordType;
            DdnsClient.UpdateDomainRecord(client, req);
            System.out.println("更新完成！");
        } else {
            System.out.println("IP无变化，无需更新！");
        }

    }

    public static void main(String[] args_) throws Exception {
        while(true) {
            update();
            Thread.sleep(60000);
        }
    }
}
