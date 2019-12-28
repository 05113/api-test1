package common;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.GetFileMsg;

import java.io.IOException;

public class SetUpTearDown {

    @BeforeSuite
    public void dataSetup(){
        // TODO: 2019/12/10 数据配置
    }
    @BeforeClass
    public void envSetup()  {
        // TODO: 2019/12/10 环境配置

        String profilename = "env.properties";
        try {
            RestAssured.baseURI = new GetFileMsg().getValue("bathuri",profilename);
            RestAssured.basePath = new GetFileMsg().getValue("bathpath",profilename);
            RestAssured.port = Integer.parseInt(new GetFileMsg().getValue("port",profilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @AfterSuite
    public void createReport(){
        // TODO: 2019/12/10 测试后的报告展示 
    }
    @AfterSuite
    public void dataTearDown(){
        // TODO: 2019/12/10 数据清理
    }
    

}
