package com.example.qiyuesuo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest
class QiyuesuoApplicationTests {

    @Test
    void contextLoads() throws IOException {
        LocalDate date = LocalDate.now();
        String dateStr = date.toString().replace("-", "");
        File file = new File("D://upload/"+dateStr+"/"+"1.txt");
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        boolean newFile = file.createNewFile();
        System.out.println(newFile);
    }

}
