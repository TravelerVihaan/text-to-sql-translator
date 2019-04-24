package com.github.travelervihaan.sqltranslator;

import com.github.travelervihaan.sqltranslator.service.TranslatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqltranslatorApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void splitTest(){
        TranslatorService ts = new TranslatorService();
        ts.setNaturalLanguageStatement("ala ma kota");
        System.out.println(ts.getFirstWord());
        Assert.assertTrue("ala ma kota".equals(ts.getFirstWord()));
    }

}
