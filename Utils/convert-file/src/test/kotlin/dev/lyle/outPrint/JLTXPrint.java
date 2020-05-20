package dev.lyle.outPrint;

import dev.lyle.commonutils.FilePathUtils;
import dev.lyle.dto.GameIAPSQLBean;
import dev.lyle.readFileUtils.ReadExcelJLTXCommon;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JLTXPrint {
    private final static Map<String,String> beanMap = new HashMap<>();

    static{ //需操作下excle 中的productId.   copy all ProductId-> 右键 (选择性粘贴) ->数值
        beanMap.put("productId","c");
        beanMap.put("appId","d");
        beanMap.put("gamePay","h");
        beanMap.put("stone","g");
        beanMap.put("content","e");
        //start 0
        beanMap.put("startIndex","1");
        beanMap.put("endIndex","7");
        //第几个sheet ^start 0
        beanMap.put("sheetIndex","1");
    }

    public static void main(String[] args){

        printSQL();
        printMap();
    }


    public static void printMap() {
        try {
            List<GameIAPSQLBean> list
                    = ReadExcelJLTXCommon.readExcel(new FileInputStream(FilePathUtils.getFilePath("chsea.xlsx")),beanMap);
            if (null != list) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>size: [" + list.size() + "]\n\n");
                fmtMap(list);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
    public static void printSQL() {
        try {
            List<GameIAPSQLBean> list
                    = ReadExcelJLTXCommon.readExcel(new FileInputStream(FilePathUtils.getFilePath("chsea.xlsx")),beanMap);
            if (null != list) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>size: [" + list.size() + "]\n\n");
                fmtSQLMap(list);
            }
        } catch (Exception e) { e.printStackTrace(); }

    }

    private static void fmtSQLMap(List<GameIAPSQLBean> list) {
        StringBuilder va = new StringBuilder();
        StringBuilder ss = new StringBuilder("INSERT INTO more_items(" +
                "menuId,productId,sequenceId,payFrom," +
                "gamePay,stone,startTime,endTime,createTime," +
                "content,cashType,moneyType,paid,pointArea,flag) VALUES ");
        for (int i = 0; i < list.size(); i++) {
            GameIAPSQLBean e = list.get(i);
            va.append("(")
                    .append("'").append(e.getMenuId()).append("',")
                    .append("'").append(e.getProductId()).append("',")
                    .append(e.getSequenceId()+i).append(",")
                    .append("'").append(e.getPayFrom()).append("',")
                    .append("'").append(e.getGamePay()).append("',")
                    .append("'").append(e.getStone().replace(".00","")).append("',")
                    .append("'").append(e.getStartTime()).append("',")
                    .append("'").append(e.getEndTime()).append("',")
                    .append("now()").append(",")
                    .append("'").append(e.getContent()).append("',")
                    .append("'").append(e.getCashType()).append("',")
                    .append("'").append(e.getMoneyType()).append("',")
                    .append("'").append(e.getPaid()).append("',")
                    .append("'").append(e.getPointArea()).append("',")
                    .append("'").append(e.getFlag()).append("')")
            ;
            if(i<list.size()-1){
                va.append(",");
            }
        }

        ss.append(va.toString());
        ss.append("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");


        System.out.println(ss.toString());
    }

    private static void fmtMap(List<GameIAPSQLBean> list) {
        StringBuilder ss = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            GameIAPSQLBean e = list.get(i);
            ss.append("googlePlayPrice.put(\"").append(e.getProductId())
                    .append("\", ")
                    .append("new Object[] {\"").append(e.getGamePay())
                    .append("\",")
                    .append(e.getStone().replace(".00",""))
                    .append("});\n");
            sb.append("iapIOS.put(\"").append(e.getProductId()).append("\", ")
                    .append("new Object[] {\"")
                    .append(e.getAppId().replace(".00","")).append("\",\"")
                    .append(e.getGamePay()).append("\",")
                    .append(e.getStone().replace(".00","")).append("});").append("\n");
        }




        ss.append("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
        ss.append(sb.toString());

        System.out.println(ss.toString());
    }


}