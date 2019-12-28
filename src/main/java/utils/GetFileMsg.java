package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;

public class GetFileMsg {

    Logger log = Logger.getLogger(GetFileMsg.class);

    public String getFilePath(String dir,String filename){

        URL resource = this.getClass().getClassLoader().getResource(dir+"/"+filename);
        String filePath = null;
        try {
            // TODO: 2019/12/11  通过 String.valueOf(filePath)返回，会报错
            filePath = resource.toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        log.info("filePath:"+filePath);

        return filePath;

    }
    public String getValue(String key,String propertiesFilename) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream( propertiesFilename);

        Properties pro = new Properties();
        pro.load(inputStream);
        String value = pro.getProperty(key);
        return value;
    }
    public String getResponseValue(String key,String reponseJson){

        JsonPath jp = new JsonPath(reponseJson);
        String value = jp.get(key);
        return value;
    }

    public static void main(String[] args) throws IOException {
        GetFileMsg gf = new GetFileMsg();
        String value = gf.getValue("key","env.properties");
        System.out.println(value);
    }

}
