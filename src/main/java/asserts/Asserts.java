package asserts;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import utils.GetFileMsg;
import org.apache.log4j.Logger;

import static asserts.ResponsedAssertForJson.verifyResult;
import static report.TestStep.requestAndRequestBody;


public class Asserts {

    static Logger log =Logger.getLogger(Asserts.class);

    public static void asserts( String responseJson , String responseToString){
        String url = RestAssured.baseURI+RestAssured.port+RestAssured.basePath;


        requestAndRequestBody(url,responseJson,responseToString);
        String caseNo = new GetFileMsg().getResponseValue("caseNo",responseJson);
        String caseName = new GetFileMsg().getResponseValue("caseName",responseJson);
        String preResult = new GetFileMsg().getResponseValue("preReult",responseJson);

        JSONObject jsonObject = JSONObject.parseObject(responseToString);

        responseToString  = JSONObject.toJSONString(jsonObject,true);

        log.info("用例编号"+caseNo);
        log.info("相应报文"+responseToString);
        databaseAndRespondAsserts(preResult,responseToString);

    }
    public static void databaseAndRespondAsserts(String preResult,String responseToString){
        StringBuffer stringBufferResult = new StringBuffer();
        StringBuffer stringBufferData = new StringBuffer();

        boolean assertFlag = true;

        stringBufferResult = verifyResult(preResult,responseToString);
        System.out.println(stringBufferResult);


    }


}
