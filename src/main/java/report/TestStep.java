package report;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import io.qameta.allure.Attachment;

public class TestStep {
    public static void requestAndRequestBody(String url,String responseJson,String responseToString){
        requestBody(url,responseJson);
        responseBody(responseToString);
    }
    @Attachment("响应报文")
    public static String responseBody(String responseToString){
        JSONObject jsonpObject =JSONObject.parseObject(responseToString);
        String str = JSONObject.toJSONString(jsonpObject,true);
        return str;
    }
    @Attachment("预期报文")
    public static String  requestBody(String url,String responseJson){
        return "预期报文";
    }

}
