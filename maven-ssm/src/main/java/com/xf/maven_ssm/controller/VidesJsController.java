package com.xf.maven_ssm.controller;

import cn.hutool.core.io.IoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Description: todo(描述)
 * @Author: xiefu
 * @Date: 2019/7/23 10:35
 */
@Controller
@RequestMapping("videojs")
public class VidesJsController {
    @RequestMapping("/videoDisplay")
    public String showDetail(){
        return "testVideoJs";
    }

    @RequestMapping("vedioDownload")
    public void dedioDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("C:\\Users\\xiefu\\Documents\\WeChat Files\\qiantingfengyin_x\\FileStorage\\Video\\2019-03\\85e7ac6ca2f0fe50fbc66ab0cf93c1fe.mp4");
        response.setContentType("application/octest-stream");
        response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");
        response.addHeader("Accept-Ranges", "bytes");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            IoUtil.copy(inputStream, response.getOutputStream());
        }finally{
            if(inputStream != null){
                inputStream.close();
            }
        }
    }
}
