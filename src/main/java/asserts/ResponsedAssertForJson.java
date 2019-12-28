package asserts;


import com.alibaba.fastjson.JSONPath;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponsedAssertForJson {
    static Logger log = Logger.getLogger(ResponsedAssertForJson.class);
    public static  StringBuffer verifyResult(String verifyData,String responseData){

        if(verifyData.equals("")||verifyData == null)
            return  null;
        log.info("待验证的预期结果为"+verifyData);

        boolean assertFlag = true;

        StringBuffer stringBuffer = new StringBuffer();
        String preResultSplit[] = verifyData.split(";");

        for(String presult : preResultSplit){
            if(presult.toLowerCase().contains("_contain")){
                log.info("contain断言表达式：" + presult);
                String containMsg = presult.substring(10,presult.length()-1);
                assertFlag = ContainAssert.contains(responseData,containMsg);
                if(!assertFlag)
                    stringBuffer.append("【"+presult+String.format("，期待\n'%s'\n包含'%s'，实际不包含！】\n", responseData, containMsg));
                else
                    stringBuffer.append("【"+presult+String.format("，期待\n'%s'\n包含'%s'，实际包含！】\n", responseData, containMsg));
            } else if(presult.toLowerCase().contains("$")){
                log.info("json断言表达式"+presult);
                Pattern pattern = Pattern.compile("([^;]*)=([^;]*)");
                Matcher matcher = pattern.matcher(presult.trim());
                while(matcher.find()){
                    String actualValue =getBuildValue(responseData,matcher.group(1));
                    log.info("matcher.group(1):" + matcher.group(1));
                    String exceptValue = getBuildValue(responseData, matcher.group(2));
                    log.info("matcher.group(2):" + matcher.group(2));
                    log.info(String.format("验证转换后的值%s=%s", actualValue,
                            exceptValue));
                    if (exceptValue.equals(actualValue)) {
                        assertFlag = true;
                        stringBuffer.append("【" + matcher.group() + "断言" + assertFlag + String.format("，期待预期结果为'%s'，实际结果为'%s'！】\n", exceptValue, actualValue));
                    } else {
                        assertFlag = false;
                        stringBuffer.append("【" + matcher.group() + "断言" + assertFlag + String.format("，期待预期结果为'%s'，实际结果为'%s'！】\n", exceptValue, actualValue));
                    }
                }


            }
            else {
                //Assert.assertTrue(false, "【预期结果断言格式有误,目前仅支持Json及contain断言，多个断言使用英文分号隔开，例如：$.status=200;__contain(tomandy)】");
                assertFlag = false;
                stringBuffer.append("【预期结果断言" + assertFlag + "，断言格式有误,目前仅支持Json及contain断言，多个断言使用英文分号隔开，例如：$.status=200;__contain(tomandy)】\n");
            }
        }
        return  stringBuffer;
    }
    public static String getBuildValue(String sourceJson,String key){
        key = key.trim();
        if(key.startsWith("$")){
            key = JSONPath.read(sourceJson,key).toString();
            log.info("key start with $.,value is :"+key);
        }
        return key;
    }


}
