package utils;

import com.google.gson.Gson;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.util.*;


public class AssembleJson {

    static List<Map<String, String>> caseList = new ArrayList<Map<String, String>>();
    static Logger log = Logger.getLogger(AssembleJson.class);

    public static List<Map<String,String>> assembleMess(String filename) throws IOException, BiffException {

        String filepath = new GetFileMsg().getFilePath("testCase",filename);

        File xlsFile = new File(filepath);

        Workbook wb =Workbook.getWorkbook(xlsFile);//获取工作簿对象

        Sheet sheet = wb.getSheet(0);//获取第一页

        int rows = sheet.getRows();//获取总行数

        int cols = sheet.getColumns();//获取总列数

        int YorNcellCount = AssembleJson.returnKeyCellCount(sheet,cols,"YorN");

        log.info("rows:"+rows+"cols"+cols);

        for(int row=1;row<rows;row++){
            String yorn = sheet.getCell(YorNcellCount,row).getContents();
            if(yorn.equals("Y")){
            getMap(sheet,row,cols);
            }
        }
        return caseList;
    }
    public static void getMap(Sheet sheet, int row, int cols){
        Map<String,String> methodMap = new HashMap<String,String>();
        Map<String,String> caseMap = new HashMap<String, String>();
        Map<String,String> respMap = new HashMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        int YorNCount = returnKeyCellCount(sheet,cols,"YorN");
        int methodCount = returnKeyCellCount(sheet,cols,"method");
        for(int col=0;col<cols;col++){

            String cellKey = sheet.getCell(col,0).getContents();
            String cellValue = sheet.getCell(col,row).getContents();
            if(col<YorNCount){
                respMap.put(cellKey,FunctionUtil.useFunction(cellValue));
            }if(col>=YorNCount && col <= methodCount){
                methodMap.put(cellKey,FunctionUtil.useFunction(cellValue));
            }if(col>methodCount){
                caseMap.put(cellKey,FunctionUtil.useFunction(cellValue));
            }
            map.put("responseMap",new Gson().toJson(respMap));
            map.put("methodMap",new Gson().toJson(methodMap));
            map.put("caseMap",new Gson().toJson(caseMap));
        }
/*        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getValue());
        }*/
        caseList.add(map);

    }
    //返回关键字的列数
    public static Integer returnKeyCellCount(Sheet sheet,int cols,String key){
        int keyCellCount = 0;
        for(int i = 0;i<cols;i++){
            String cellKey = sheet.getCell(i,0).getContents();
            if(cellKey.equals(key)){
                keyCellCount = i;
            }
        }
        return keyCellCount;

    }



}
