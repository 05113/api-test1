package common;


import io.restassured.response.Response;
import utils.GetJsonValue;

import static io.restassured.RestAssured.given;

public class RestAssuredJson {
    public static Response runCase(String methodJson,String caseJson){
        String method = GetJsonValue.getJsonValue(methodJson,"method");
        String url = GetJsonValue.getJsonValue(methodJson,"url");
        Response response = null;
        if(method.equals("get"))
            response = given().contentType("application/json;charset=UTF-8")
                .request()
                .body(caseJson)
                .get(url);
        else
            response = given().contentType("application/json;charset=UTF-8")
                    .request()
                    .body(caseJson)
                    .post(url);
        return response;

    }
}
