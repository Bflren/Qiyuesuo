package top.ltq9.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetMetaData {

    public static void main(String[] args) throws Exception {
        //请求地址
        URL url = new URL("http://127.0.0.1:8080/getMetaData?fileName=e83f2f36-0240-4096-8366-97f59eb085c2.pdf");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);

        InputStream inputStream = connection.getInputStream();
        OutputStream outputStream = System.out;

        int data;
        while ((data=inputStream.read())!=-1){
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
    }
}
