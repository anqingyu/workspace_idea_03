package com.xf.mavenssm.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xf.mavenssm.utils.SignUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: todo(测试:Java 开放api接口签名验证（MD5模式）)
 * @Author: xiefu
 * @Date: 2019/4/25 16:28
 */
@RestController
@RequestMapping("/webapi/test")
public class SignTestController {
    /**
     * 模拟客户端请求API接口
     * @param request
     * @return
     */
    @RequestMapping("/send")
    public String send(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> param = new HashMap();
        param.put("userId","9527");
        param.put("amount","9.99");
        param.put("productId","9885544154");
        param.put("secretKey","mysecret123456");
        try {
//            String postResult = HttpClient.post("http://localhost:8099/test", SignUtil.sign(param));
//            return postResult;
            String jsonString = JSON.toJSONString(SignUtil.sign(param));
            String body = HttpRequest.post("http://localhost:8084/maven_ssm/webapi/test/checkSign.do").body(jsonString).execute().body();
            System.out.println(body);
//            Map<String, String> map = JSONObject.parseObject(body,Map.class);
            response.getWriter().write("校验成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    /**
     * 模拟服务的API接口
     * @param jsonStr
     * @return
     */
    @RequestMapping("/checkSign")
    public String checkSign(@RequestBody String jsonStr){
//        logger.info("repairTakeOrder入参：" + jsonStr);
//        Object o = JSON.parse(jsonStr);
        Map map1 = JSON.parseObject(jsonStr, Map.class);
        //从request中获取参数列表，转成map
//        Map<String, String> map = SignUtil.toVerifyMap(map1,false);
        Map<String, String> map=map1;
        String secretKey =  map.get("secretKey");
        if (StringUtils.isEmpty(secretKey) || !map.get("secretKey").equals(SignUtil.SECRET_KEY)){
            System.out.println("secretKey is err");
            return "fail";
        }
        if (SignUtil.verify(map)){
            return "success";
        }else {
            return "fail";
        }
    }
}