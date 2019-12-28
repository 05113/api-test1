package data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import utils.AssembleJson;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataProviders {
    @DataProvider(name = "dataprovider")
    public  static Object[][] dataProvider(Method method) throws IOException, BiffException {

        String classname = method.getDeclaringClass().getSimpleName();
        String classfilename = classname+".xls";
        List<Map<String,String>> caselist = new ArrayList<Map<String, String>>();
        caselist = AssembleJson.assembleMess(classfilename);

        Object[][] objects = new Object[caselist.size()][3];
        int rowindex = 0;
        for(Map<String,String> map:caselist){
            int colindex = 0;
            for(Map.Entry<String,String> entry:map.entrySet()){
                objects[rowindex][colindex] = entry.getValue();
                colindex++;
            }
            rowindex++;
        }
        return objects;
    }

}
