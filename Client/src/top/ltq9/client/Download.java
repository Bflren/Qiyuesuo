package top.ltq9.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {

    public static void main(String[] args) throws Exception {

        //下载地址 get请求 参数是生成的文件名
        URL url = new URL("http://127.0.0.1:8080/download?fileName=e83f2f36-0240-4096-8366-97f59eb085c2.pdf");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);

        //获得输入流，下载到本地
        InputStream inputStream = connection.getInputStream();
        File file = new File("d://upload/download/download.pdf");
        FileOutputStream outputStream = new FileOutputStream(file);

        int data=0;
        while((data=inputStream.read())!=-1){
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
        urlConnection.disconnect();
        System.out.println("下载完成");
    }
}
