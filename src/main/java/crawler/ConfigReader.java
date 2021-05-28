package crawler;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private final String resource = "src/main/resources/config.properties";
    private Properties prop;

    public ConfigReader() throws Exception{
        prop = new Properties();
        try{
            FileInputStream fis = new FileInputStream(resource);
            prop.load(fis);
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
