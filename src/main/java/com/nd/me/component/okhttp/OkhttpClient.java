package com.nd.me.component.okhttp;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

/**
 * @Author
 * @Description
 * @Date Create in 下午 1:23 2018/5/8 0008
 */
public class OkhttpClient {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        //使用JSONObject封装参数
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mchOrderNo", "1525767102362");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //创建RequestBody对象，将参数按照指定的MediaType封装
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());
        Request request = new Request
                .Builder()
                .addHeader("backToken", "25abeb29724c3fcf3f80067b7740d9ed")
                .post(requestBody)//Post请求的参数传递
                .url("http://payserv.test.66buy.com.cn/pay_service/privates/status/trade")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();

            System.out.println(result);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加证书
     * @param certificates
     * @return
     */
    public SSLSocketFactory setCertificates(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(
                    null,
                    trustManagerFactory.getTrustManagers(),
                    new SecureRandom()
            );
            return sslContext.getSocketFactory();
            //mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
