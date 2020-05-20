package dev.lyle.readFileUtils;

import dev.lyle.dto.GameCommonBean;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author_by: Lyle
 */
public class ReadExcelToMap {

    private static Map<String, String> beanMap = new HashMap<>();

    public static void readExcel(InputStream is, Map<String,String> map,Set<Long> uidSet) throws Exception {
        beanMap = map;
        Workbook workbook = WorkbookFactory.create(is);
        int sheetSize = workbook.getNumberOfSheets();
        for (int i=0;i<sheetSize;i++) {
            Sheet sheet = workbook.getSheetAt(i);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sheetNameIs "+i+"= [" +  sheet.getSheetName() + "]");

            for(int rowNum = (Integer.parseInt(beanMap.get("startIndex"))); rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row xssfRow = sheet.getRow(rowNum);
                if (!StringUtils.isEmpty(xssfRow)) {
                    Long uid = judgeRule(xssfRow);
                    if (!StringUtils.isEmpty(uid)) {
                        uidSet.add(uid);
                    }
                }
            }
        }
    }

    public static Map<Long,String> readExcelToMap(InputStream is, Map<String,String> map) throws Exception {
        beanMap = map;
        Workbook workbook = WorkbookFactory.create(is);
        int sheetSize = workbook.getNumberOfSheets();
        Map<Long,String> uidMoneyMap = new HashMap<>();
        for (int i=0;i<sheetSize;i++) {
            Sheet sheet = workbook.getSheetAt(i);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sheetNameIs "+i+"= [" +  sheet.getSheetName() + "]");

            for(int rowNum = (Integer.parseInt(beanMap.get("startIndex"))); rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row xssfRow = sheet.getRow(rowNum);
                if (!StringUtils.isEmpty(xssfRow)) {
                    GameCommonBean bean = judgeRuleToBean(xssfRow);
                    if (bean!= null) {
                        uidMoneyMap.put(bean.getUid(),bean.getAmount());
                    }
                }
            }
        }
        return uidMoneyMap;
    }

    public static Map<String,String> readExcelToMapStr(InputStream is, Map<String,String> map) throws Exception {
        beanMap = map;
        Workbook workbook = WorkbookFactory.create(is);
        int sheetSize = workbook.getNumberOfSheets();
        Map<String,String> uidMoneyMap = new HashMap<>();
        for (int i=0;i<sheetSize;i++) {
            Sheet sheet = workbook.getSheetAt(i);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sheetNameIs "+i+"= [" +  sheet.getSheetName() + "]");

            for(int rowNum = (Integer.parseInt(beanMap.get("startIndex"))); rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row xssfRow = sheet.getRow(rowNum);
                if (!StringUtils.isEmpty(xssfRow)) {
                    GameCommonBean bean = judgeRuleToBean(xssfRow);
                    if (bean!= null) {
                        uidMoneyMap.put(bean.getUid()+"="+bean.getAmount(),bean.getAmount());
                    }
                }
            }
        }
        return uidMoneyMap;
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
                    DecimalFormat df = new DecimalFormat("0");
                    return df.format(cell.getNumericCellValue());
                case ERROR:
                    return cell.getErrorCellValue() + "";
                default:
                    return "";
            }
        }
        return "";
    }
    /**
     * 获取单元格的值
     */
    private static String getCellValueNum(Cell cell) {
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

    private static GameCommonBean judgeRuleToBean(Row xssfRow) {
        GameCommonBean bean = new GameCommonBean();
        for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
            Cell ic_no = xssfRow.getCell(i);
            if (!StringUtils.isEmpty(ic_no)) {

                if (beanMap.containsKey("uid") && i == lowToNum(beanMap.get("uid"))) {
                    bean.setUid(Long.valueOf(getCellValue(ic_no).trim()));
                }else if (beanMap.containsKey("serverCode") && i == lowToNum(beanMap.get("serverCode"))) {
                    bean.setAmount(getCellValue(ic_no).trim());
                }
//                else if (beanMap.containsKey("total") && i == lowToNum(beanMap.get("total"))) {
//                    bean.setTw(getCellValueNum(ic_no).trim());
//                }
            }

        }
        return bean ;
    }

    private static Long judgeRule(Row xssfRow) {
        for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
            Cell ic_no = xssfRow.getCell(i);
            if (!StringUtils.isEmpty(ic_no)) {
                if (beanMap.containsKey("uid") && i == lowToNum(beanMap.get("uid"))) {
                    return Long.valueOf(getCellValue(ic_no).trim());
                }else if (beanMap.containsKey("serverCode") && i == lowToNum(beanMap.get("serverCode"))) {
                    return Long.valueOf(getCellValue(ic_no).trim());
                }
            }

        }
        return null ;
    }

    private static Integer lowToNum(String input) {
        StringBuilder strBuf = new StringBuilder();
        for (byte bt : input.toLowerCase().getBytes()) { strBuf.append (bt - 96); }
        return Integer.valueOf(strBuf.toString())-1;
    }

}
