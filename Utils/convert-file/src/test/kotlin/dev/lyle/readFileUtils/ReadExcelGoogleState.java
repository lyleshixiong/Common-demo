package dev.lyle.readFileUtils;

import dev.lyle.dto.GameCommonBean;
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
public class ReadExcelGoogleState {

    private static Map<String,String> beanMap = new HashMap<>();


    public static List<GameCommonBean> readExcel(InputStream is,Map<String,String> temMap) throws Exception {
        Workbook workbook = WorkbookFactory.create(is);
        beanMap = temMap;
        LinkedList<GameCommonBean> list = new LinkedList<>();
        Sheet sheet = workbook.getSheetAt(Integer.valueOf(temMap.get("sheetIndex")));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sheetNameIs = [" +  sheet.getSheetName() + "]");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sheet.getLastRowNum() = [" +  (sheet.getLastRowNum()) + "]");

        for (int rowNum = (Integer.parseInt(beanMap.get("startIndex"))), rs = sheet.getLastRowNum(); rowNum <= rs; rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if(xssfRow == null ||xssfRow.getPhysicalNumberOfCells()==0){
                continue;
            }
//            if (null != xssfRow && !StringUtils.isEmpty(xssfRow.getCell(xssfRow.getFirstCellNum()).getStringCellValue()) ) {
                list.add(judgeRule(xssfRow, sheet.getSheetName()));
//            }
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

    private static GameCommonBean judgeRule(Row xssfRow, String sheetName) {
        GameCommonBean bean = new GameCommonBean();
        bean.setGpId(sheetName);
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
