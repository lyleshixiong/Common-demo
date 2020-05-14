package com.lyle.readFileUtils;

import com.lyle.dto.GameCommonBean;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Author_by: Lyle
 */
public class ReadExcelCommon {

    private static Map<String,String> beanMap = new HashMap<>();

//    static{ //需操作下excle 中的productId.   copy all ProductId-> 右键 (选择性粘贴) ->数值
//        beanMap.put("appId","a");
//        beanMap.put("productId","b");
//        beanMap.put("usd","c");
//        beanMap.put("gpId","n");
//        beanMap.put("oneStoreId","m");
//        beanMap.put("krw","d");
//        beanMap.put("ybNum","k");
//
//        //start 0
//        beanMap.put("startIndex","3");
//        beanMap.put("endIndex","18");
//        //第几个sheet ^start 0
//        beanMap.put("sheetIndex","0");
//
//    }

    public static List<GameCommonBean> readExcel(InputStream is,Map<String,String> temMap) throws Exception {
        Workbook workbook = WorkbookFactory.create(is);
        int sheetNum = workbook.getNumberOfSheets();
        beanMap = temMap;
        LinkedList<GameCommonBean> list = new LinkedList<>();
        for (int i=0;i<sheetNum;i++) {
            Sheet sheet = workbook.getSheetAt(Integer.parseInt(beanMap.get("sheetIndex")));

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sheetNameIs = [" +  sheet.getSheetName() + "]");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sumColumnIs = [" + (Integer.parseInt(beanMap.get("endIndex"))-(Integer.parseInt(beanMap.get("startIndex")))) + "]");

            for(int rowNum = (Integer.parseInt(beanMap.get("startIndex"))); rowNum <= (Integer.parseInt(beanMap.get("endIndex"))); rowNum++) {
                Row xssfRow = sheet.getRow(rowNum);
                if (!StringUtils.isEmpty(xssfRow)) { list.add(judgeRule(xssfRow)); }
            }
        }

        return list;
    }


    /**
     * 获取单元格的值
     */
    private static String getCellValue(Cell cell) {
        if (!StringUtils.isEmpty(cell)) {
            switch (cell.getCellTypeEnum()) {
                case STRING: // 字符串
                    return cell.getStringCellValue();
                case BOOLEAN: // bool型
                    return cell.getBooleanCellValue() + "";
                case NUMERIC: // 数值型
//                    return  cell.getNumericCellValue() + "";
                    DecimalFormat df = new DecimalFormat("0"); //long,double
                    return df.format(cell.getNumericCellValue());
                case ERROR:
                    return cell.getErrorCellValue() + "";
                default:
                    return "";
            }
        }
        return "";
    }

    private static GameCommonBean judgeRule(Row xssfRow) {
        GameCommonBean bean = new GameCommonBean();
        for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
            Cell ic_no = xssfRow.getCell(i);
            if (!StringUtils.isEmpty(ic_no)) {

                if (beanMap.containsKey("appId") && i == lowToNum(beanMap.get("appId"))) {
                    bean.setAppId(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("productId") && i == lowToNum(beanMap.get("productId"))) {
                    bean.setProductId(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("oneStoreId") && i == lowToNum(beanMap.get("oneStoreId"))) {
                    bean.setOneStoreId(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("gpId") && i == lowToNum(beanMap.get("gpId"))) {
                    bean.setGpId(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("krw") && i == lowToNum(beanMap.get("krw"))) {
                    bean.setKrw((int)Double.parseDouble(getCellValue(ic_no).trim()));
                } else if (beanMap.containsKey("usd") && i == lowToNum(beanMap.get("usd"))) {
                    bean.setUsd(Double.parseDouble(getCellValue(ic_no).trim()));
                } else if (beanMap.containsKey("stone") && i == lowToNum(beanMap.get("stone"))) {
                    bean.setStone((int) Double.parseDouble(getCellValue(ic_no).trim()));
                }else if (beanMap.containsKey("amount") && i == lowToNum(beanMap.get("amount"))) {
                    bean.setAmount(getCellValue(ic_no).trim());
                }
            }

        }
        return bean ;
    }

    private static Integer lowToNum(String input) {
        StringBuilder strBuf = new StringBuilder();
        for (byte bt : input.toLowerCase().getBytes()) { strBuf.append (bt - 96); }
        return Integer.valueOf(strBuf.toString())-1;
    }

}
