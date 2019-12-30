
import asserts.Asserts;
import common.RestAssuredJson;
import common.SetUpTearDown;
import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import io.restassured.response.Response;


@Epic("EpicTest")
@Feature("FeatureTest")

public class movecase extends SetUpTearDown {
    @Story("Storytest")
    @Description("描述")
    @Test(dataProvider = "dataprovider",dataProviderClass = DataProviders.class)
    public void runCase(String responseJson, String methodJson,String caseJson){
        Response response = RestAssuredJson.runCase(methodJson,caseJson);
        System.out.println(response.asString());

        Asserts.asserts(responseJson,response.asString());










}

}
