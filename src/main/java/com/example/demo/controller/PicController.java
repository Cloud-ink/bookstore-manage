package com.example.demo.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class PicController {
    @ResponseBody//返回json数据
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public JSONObject uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws JSONException {

        String contentType = file.getContentType();
        //获取当前系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmSS");
        String timeString = df.format(new Date());

        String fileName = timeString + file.getOriginalFilename();
        String filePath = "D:/img";
        JSONObject jo = new JSONObject();//实例化json数据

        if (file.isEmpty()) {
            jo.put("success", 0);
            jo.put("file", "");
        }
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            jo.put("success", 1);
            jo.put("fileName", fileName);
            jo.put("xfileName", "/imctemp-rainy/" + fileName);
        } catch (Exception e) {
        }
        return jo;

    }

    //quxiao
    @ResponseBody//返回json数据
    @RequestMapping(value = "deleteFile", method = RequestMethod.POST)
    public String deleteFile(String path) {

        String resultInfo = null;
        if (path.equals("no_img.png")) {
            resultInfo = "请选择图片";
        } else {
            String sb = "D:/img/" + path;
            File file = new File(sb);
            if (file.exists()) {
                if (file.delete()) {
                    resultInfo = "删除";
                } else {
                    resultInfo = "shiba";
                }
            } else {
                resultInfo = "不存在";
            }
        }
        return resultInfo;
    }

    //文件上传
    public static void uploadFile(byte[] file,String filePath,String fileName) throws Exception{
        //设置目标文件夹
        File targetFile = new File(filePath);
        if(!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //输出流写入文件并命名
        FileOutputStream out = new FileOutputStream(fileName+"/"+fileName);
        //写入文件，刷新，关闭流
        out.write(file);
        out.flush();
        out.close();
    }
}
