package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FunctionUtil {
    public static String useFunction(String cellValue)  {
        if(cellValue.equals("_timestamp()"))
        {
            String methodname = "timestamp";
            String classname = "functions.XlxFunctions";
            Class clz = null;
            try {
                clz = Class.forName(classname);
                Object obj = clz.newInstance();
                Method method = obj.getClass().getDeclaredMethod(methodname);
                String returnValue = (String) method.invoke(obj);
                return returnValue;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        return cellValue;
    }

}
