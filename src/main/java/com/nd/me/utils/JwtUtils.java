package com.nd.me.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class JwtUtils {
    private static final String MAC_INSTANCE_NAME = "HMacSHA256";

    public static String Hmacsha256(String secret, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac_sha256 = Mac.getInstance(MAC_INSTANCE_NAME);
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), MAC_INSTANCE_NAME);
        hmac_sha256.init(key);
        byte[] buff = hmac_sha256.doFinal(message.getBytes());
        return Base64.encodeBase64URLSafeString(buff);
    }

    public static String build(String secret, String header, String payload) throws InvalidKeyException, NoSuchAlgorithmException {


        String base64Header = Base64.encodeBase64URLSafeString(header.getBytes());
        String base64Claim = Base64.encodeBase64URLSafeString(payload.getBytes());
        String signature = Hmacsha256(secret, base64Header + "." + base64Claim);

        String jwt = base64Header + "." + base64Claim + "." + signature;
        return jwt;
    }

    public static void main(String[] args) throws Exception{
        String secret = "pay_service";
        String header = "{\"type\":\"JWT\",\"alg\":\"HS256\"}";
        String payload = "{\n" +
                "\t\"appid\":\"111\",\n" +
                "\t\"mch_id\":\"111\",\n" +
                "\t\"sub_mch_id\":\"222\"\n" +
                "}";

        String jwt = build(secret, header, payload);
        System.out.println("token:"+jwt);
    }
}
