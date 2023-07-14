package com.rest.assured.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utlility {

    public Properties prop;



    public void readApplicationPropertyData() throws IOException {

        FileReader reader = new FileReader("src/test/resources/application.properties");
        //reading the application Properties file from the path
        Properties prop = new Properties();
        prop.load(reader);

        System.out.println(prop.getProperty("url"));
//        return prop.getProperty("url");

    }
}
