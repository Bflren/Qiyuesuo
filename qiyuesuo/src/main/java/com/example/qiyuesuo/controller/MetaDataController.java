package com.example.qiyuesuo.controller;

import com.example.qiyuesuo.entity.MetaData;
import com.example.qiyuesuo.service.MetaDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@Slf4j
public class MetaDataController {

    @Autowired
    MetaDataService metaDataService;

    final static Logger logger = LoggerFactory.getLogger(MetaDataController.class);

    @PostMapping("/upload")
    @ResponseBody
    public void upload( HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();

        //生成新文件名UUID
        String fileName = UUID.randomUUID().toString();

        //原始文件名
        String originalName = URLDecoder.decode(request.getHeader("fileName"),"UTF-8");

        //文件类型
        String type = request.getHeader("type");;

        //获得文件大小
        long size = Long.parseLong(request.getHeader("size"));

        //文件夹创建时间
        LocalDate date = LocalDate.now();
        //每个文件文件夹名称
        String dateStr = date.toString().replace("-", "");

        //文件路径
        String path = "D://upload/" + dateStr +"/"+ fileName + "." + type;

        //写入数据库
        MetaData metaData = new MetaData(size, type, fileName + "." + type, originalName, path);
        metaDataService.addRecord(metaData);

        File file1 = new File(path);
        File fileParent = file1.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file1.createNewFile();

        FileOutputStream fos = new FileOutputStream(file1);

        int data;
        while ((data = inputStream.read()) != -1) {
            fos.write(data);
        }
        inputStream.close();
        fos.close();
        PrintWriter writer = response.getWriter();
        writer.write(fileName);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(String fileName,HttpServletResponse response){

        //获取文件路径
        String path = metaDataService.findPathByFileName(fileName);

        //创建字节输入流
        InputStream in;
        byte[] body = null;
        try {
            in = new FileInputStream(path);
            //available():获取输入流所读取的文件的最大字节数
            body = new byte[in.available()];
            //把字节读取到数组中
            in.read(body);
        } catch (Exception e) {
            //异常抛出 410
            try {
                response.sendError(410,"error");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" +fileName);
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<>(body, headers, statusCode);
    }


    @GetMapping("/getMetaData")
    @ResponseBody
    public MetaData getMetaData(String fileName){
        MetaData metaData = metaDataService.findMetaByFileName(fileName);
        return metaData;
    }



}


