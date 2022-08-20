package com.shf.shiro.client;

import com.shf.shiro.tools.DigestsUtil;
import com.shf.shiro.tools.EncodeUtil;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ClientTest {
    @Test
    public void testHex() {
        String val = "hello";
        String encodeHex = EncodeUtil.encodeHex(val.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeHex);

        String valHandler = new String(EncodeUtil.decodeHex(encodeHex));
        System.out.println(valHandler.equals(val));
    }

    @Test
    public void testBase64() {
        String val = "hello";
        String encodeHex = EncodeUtil.encodeBase64(val.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeHex);

        String valHandler = new String(EncodeUtil.decodeBase64(encodeHex));
        System.out.println(valHandler.equals(val));
    }

    @Test
    public void testDigestsUtil() {
        Map<String, String> map = DigestsUtil.entryptPassword("123");
        System.out.println(map);
    }
}
