package com.nd.me.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtManager {
    private static String ID = "JWT";
    private static String SECRET = "pay_service";

    public static final int MINUTE_TTL = 60 * 1000;  //millisecond
    public static final int HOURS_TTL = 60 * 60 * 1000;  //millisecond
    public static final int DAY_TTL = 12 * 60 * 60 * 1000;  //millisecond

    public static String token(String appId, String mchId) {
        //验证appid和secret
        String subject = getSubject(appId, mchId);
        Map<String, Object> payload = getPayload(appId, mchId);
        //生成JWT签名数据ToKen
        String token = createToken(subject, payload, MINUTE_TTL);
        return token;
    }

    /**
     * 创建token
     *
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createToken(String subject, Map<String, Object> payload, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(ID)
                .setIssuedAt(now)
                .setClaims(payload)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims validateToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        return claims;
    }

    /**
     * 生成秘钥
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = ID + SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 获取主题
     *
     * @param appId
     * @param mchId
     * @return
     */
    private static String getSubject(String appId, String mchId) {
        JSONObject object = new JSONObject();
        object.put("appid", appId);
        object.put("mch_id", mchId);
        return object.toJSONString();
    }

    /**
     * 获取payload
     *
     * @param appId
     * @param mchId
     * @return
     */
    private static Map<String, Object> getPayload(String appId, String mchId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("appid", appId);
        payload.put("mch_id", mchId);
        return payload;
    }
}
