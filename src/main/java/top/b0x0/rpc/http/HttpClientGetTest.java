package top.b0x0.rpc.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientGetTest {

    public static void main(String[] args)
            throws ClientProtocolException, IOException {
        // url请求前加上HTTP协议头，标明该请求为HTTP请求
        String url = "http://www.baidu.com";
        // 构建http客户端
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 构建请求
        HttpUriRequest httpGet = new HttpGet(url);

        // 发送并接收响应
        HttpResponse response = httpClient.execute(httpGet);
        // 获取http响应报文实体
        HttpEntity entity = response.getEntity();
        // 解析成字节数组
        byte[] bytes = EntityUtils.toByteArray(entity);
        // 解析成功字符串
        String result = new String(bytes, "utf8");
        System.out.println(result);
    }

}