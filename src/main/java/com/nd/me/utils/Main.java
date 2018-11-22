package com.nd.me.utils;

import com.nd.me.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import java.io.StringReader;
import java.util.HashMap;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        /*JwtManager manager = new JwtManager();
        long t1 = System.currentTimeMillis();
        String token = manager.token("12345", "12345");
        long t2 = System.currentTimeMillis();
        System.out.println("time:" + (t2 - t1));
        System.out.println("token = " + token);*/
        /*System.out.println(JAXBContext.newInstance(User.class).createUnmarshaller().unmarshal(new StringReader("111")));*/

    }
}
