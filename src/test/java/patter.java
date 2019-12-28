import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class patter {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
/*        String className = "functions.XlxFunctions";
        String methodname = "phoneFunction";
        Class clz = Class.forName(className);
        Object obj = clz.newInstance();
        Method m = obj.getClass().getDeclaredMethod(methodname);
        m.invoke(obj);*/
        Pattern pattern = Pattern.compile("([^;]*)=([^;]*)");
        Matcher matcher = pattern.matcher("aa.cc.cc.ss=bbcc");
        int count=0;
        while(matcher.find()){
            count++;
        }
        System.out.println(count);



}







}
