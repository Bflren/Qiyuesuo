package top.ltq9.client;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Upload {

    public static void main(String[] args) throws Exception {
        //上传地址，服务器开启
        URL url = new URL("http://127.0.0.1:8080/upload");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        String path = "D://Test/契约锁2021年JAVA校招测试题.pdf";
        String type = path.split("\\.")[1];
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);

        //设置指定的超时值（以毫秒为单位）
        urlConnection.setConnectTimeout(3000);
        //设置请求方法
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);


        //设置请求头
        urlConnection.setRequestProperty("Connection", "Keep-Alive");
        urlConnection.setRequestProperty("Content-Type" ,"application/x-www-form-urlencoded;charset=UTF-8");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
        //文件信息设置在请求头中
        urlConnection.setRequestProperty("fileName", URLEncoder.encode(file.getName(), "UTF-8"));
        urlConnection.setRequestProperty("type",type);
        urlConnection.setRequestProperty("size", String.valueOf(file.length()));

        //获得向请求写入数据的流
        OutputStream outputStream = urlConnection.getOutputStream();
        
        int data;
        while((data=fis.read())!=-1){
            outputStream.write(data);
        }
        fis.close();
        outputStream.flush();
        outputStream.close();
        System.out.println("上传成功");

        // 读取返回数据
        StringBuilder strBuf = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                urlConnection.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            strBuf.append(line).append("\n");
        }
        String res = strBuf.toString();
        System.out.println(res);
        reader.close();
        urlConnection.disconnect();
    }
}
