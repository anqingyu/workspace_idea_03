package com.example.demo.test;

import org.junit.jupiter.api.Test;
import static com.alibaba.druid.filter.config.ConfigTools.encrypt;
import static com.alibaba.druid.filter.config.ConfigTools.genKeyPair;

/**
 * 使用 druid 提供的 ConfigTools 来对“数据库访问密码”进行加密，该类采用非对称方式加密
 */
public class ConfigToolsTest {
    @Test
    public void testPassword() throws Exception {
        String password = "123456";
        String[] arr = genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + encrypt(arr[0], password));
    }
}
