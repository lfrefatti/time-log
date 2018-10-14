package br.com.lfr.timelog.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static Properties appProps;

    public static String getProperty(String propertyKey) throws IOException {
        String value = getAppProps().getProperty(propertyKey);

        if (value.startsWith("${")) {
            value = value.replace("${", "").replace("}", "");
            return System.getenv(value);
        }

        return value;
    }


    private static Properties getAppProps() throws IOException {
        if (appProps == null){
            InputStream is = Thread.currentThread().getClass().getClassLoader().getResourceAsStream("application.properties");
            appProps = new Properties();
            appProps.load(is);
        }

        return appProps;
    }
}
