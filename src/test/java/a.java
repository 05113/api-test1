import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.nio.file.Files.readAllBytes;
import static org.testng.Assert.fail;


@Epic("Allure Epic")
@Feature("Allure Feature")
public class a {
    @BeforeClass(description = "测试环境参数")
    public void setUp() {
        RestAssured.baseURI = "http://10.232.138.107";
        RestAssured.basePath = "v1/gateway.do";
        RestAssured.port = 8187;
    }
    @Test
    @Story("failedTest")
    @Description("failedTest Description")
    public void failedTest(){
        Assert.assertEquals(2,3);
    }
    @Test(dependsOnMethods = {"failedTest"})
    @Story("skipTest")
    @Description("skipTest Description")
    public void skipTest(){
        Assert.assertEquals(2,2);
    }

    @Story("短信发送Story")
    @Description("描述发送短信接口")
    @Issue("123")
    @TmsLink("test-123")
    @Title("Tomandydddddd")
    @Severity(SeverityLevel.BLOCKER)
    @Test()
    public void runCases(String caseNo, String testPoit, String preResult, String YorN, String tableCheck, String appId, String merchantId, String api, String version,
                         String phone, String bizTransaction, String acctType) throws IOException, URISyntaxException {

        String bodyString = "{\n" +
                "\t\"appId\":\"" + appId + "\",\n" +
                "\t\"api\":\"" + api + "\",\n" +
                "\t\"data\":{\n" +
                "\t\t\"merchantId\":\"" + merchantId + "\",\n" +
                "\t\t\"bizTransaction\":\"" + bizTransaction + "\",\n" +
                "\t\t\"phone\":\"" + phone + "\",\n" +
                "\t\t\"acctType\":\"" + acctType + "\"\n" +
                "\t\t},\n" +
                "\t\"version\":\"" + version + "\"\n" +
                "}\n";

        //测试报告展现 请求报文
        requestBody(RestAssured.baseURI+":"+RestAssured.port+"/"+RestAssured.basePath,bodyString);

        Response response = given()
                .contentType("application/json;charset=UTF-8")
                .request()
                .body(bodyString)
                .post();

        response.prettyPrint();//格式化参数

        //断言
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

        //测试报告展现 请求报文
        respondBody(json);

        //添加附件
        addAttachment();

        if (response.statusCode() == 200) { //请求成功
            Assert.assertEquals(jp.get("message").toString(), preResult);
        } else {
            Assert.assertEquals(jp.get("data.errMsg").toString(), preResult);
        }
    }

    @Attachment(value = "附件",type = "properties")
    public byte[] addAttachment() throws IOException, URISyntaxException {
        return getSampleFile("allure.properties");
    }

    private byte[] getSampleFile(String fileName) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if(resource == null){
            fail(format("Couldn't find resource '%s'", fileName));
        }

        return readAllBytes(Paths.get(resource.toURI()));
    }

    @Step
    public void requestBody(String URL,String Body){
        //报告展现请求报文
    }

    @Step
    public void respondBody(String Respond){
        //报告展现响应报文
    }

}
