package com.lyle.readFileUtils;

import com.lyle.dto.GameIAPSQLBean;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Author_by: Lyle
 */
public class ReadExcelJLTXCommon {

    private static Map<String,String> beanMap = new HashMap<>();


    public static List<GameIAPSQLBean> readExcel(InputStream is,Map<String,String> temMap) throws Exception {
        Workbook workbook = WorkbookFactory.create(is);
        beanMap = temMap;
        LinkedList<GameIAPSQLBean> list = new LinkedList<>();
        Sheet sheet = workbook.getSheetAt(Integer.parseInt(beanMap.get("sheetIndex")));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sheetNameIs = [" +  sheet.getSheetName() + "]");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sumColumnIs = [" + (Integer.parseInt(beanMap.get("endIndex"))-(Integer.parseInt(beanMap.get("startIndex")))) + "]");

        for(int rowNum = (Integer.parseInt(beanMap.get("startIndex"))); rowNum <= (Integer.parseInt(beanMap.get("endIndex"))); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (!StringUtils.isEmpty(xssfRow)) { list.add(judgeRule(xssfRow)); }
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
//                    return cell.getNumericCellValue() + "";
                    DecimalFormat df = new DecimalFormat("0.00");
                    return df.format(cell.getNumericCellValue());
                case ERROR:
                    return cell.getErrorCellValue() + "";
                default:
                    return "";
            }
        }
        return "";
    }

    private static GameIAPSQLBean judgeRule(Row xssfRow) {
        GameIAPSQLBean bean = new GameIAPSQLBean();
        for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
            Cell ic_no = xssfRow.getCell(i);
            if (!StringUtils.isEmpty(ic_no)) {

                if (beanMap.containsKey("productId") && i == lowToNum(beanMap.get("productId"))) {
                    bean.setProductId(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("gamePay") && i == lowToNum(beanMap.get("gamePay"))) {
                    bean.setGamePay(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("content") && i == lowToNum(beanMap.get("content"))) {
                    bean.setContent(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("appId") && i == lowToNum(beanMap.get("appId"))) {
                    bean.setAppId(getCellValue(ic_no).trim());
                } else if (beanMap.containsKey("stone") && i == lowToNum(beanMap.get("stone"))) {
                    bean.setStone(getCellValue(ic_no).trim());
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
