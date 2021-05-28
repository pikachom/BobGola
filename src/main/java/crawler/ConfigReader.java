package crawler;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private String resource = "src/main/resources/config.properties";
    private Properties prop;

    public ConfigReader() throws Exception{
        prop = new Properties();
        try{
//            InputStream reader = getClass().getResourceAsStream(resource);
            FileInputStream fis = new FileInputStream(resource);
            prop.load(fis);
            System.out.println(prop.getProperty("ACCESS_TOKEN"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ConfigReader cr = new ConfigReader();
    }

    public String getPropertyValue(String key){
        return this.prop.getProperty(key);
    }
}
