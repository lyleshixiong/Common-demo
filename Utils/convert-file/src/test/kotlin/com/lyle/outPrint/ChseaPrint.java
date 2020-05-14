package com.lyle.outPrint;

import com.lyle.commonutils.FilePathUtils;
import com.lyle.dto.GameCommonBean;
import com.lyle.readFileUtils.ReadExcelCommon;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChseaPrint {
    private final static Map<String,String> beanMap = new HashMap<>();


    static{ //需操作下excle 中的productId.   copy all ProductId-> 右键 (选择性粘贴) ->数值
        beanMap.put("appId","a");
        beanMap.put("productId","b");
        beanMap.put("usd","c");
//        beanMap.put("gpId","n");
//        beanMap.put("oneStoreId","m");
        beanMap.put("amount","d");
//        beanMap.put("ybNum","k");

        //start 0
        beanMap.put("startIndex","1");
        beanMap.put("endIndex","16");
        //第几个sheet ^start 0
        beanMap.put("sheetIndex","0");

    }
    public static void main(String[] args){
        print();
    }

    public static void print() {
        System.out.println(new File(".").getAbsolutePath());

        try {
            List<GameCommonBean> list
                    = ReadExcelCommon.readExcel(new FileInputStream(FilePathUtils.getFilePath("chsea.xlsx")),beanMap);
            if (null != list) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>size: [" + list.size() + "]\n\n");
                fmtMap(list);
            }
        } catch (Exception e) { e.printStackTrace(); }

    }

    public static void fmtMap(List<GameCommonBean> list) {

        int i0=0,i1=0,i2=0,i3=0,ii = 0 ;
        StringBuilder ss = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        ss.append("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
//        for (GameCommonBean e : list) {
//            ++ii;
//            if (ii > 6) {
//                continue;
//            }
//            String stone = Integer.parseInt(Math.round(e.getUsd()) * 20 + "")+"";
//            ss.append("{\n\t\"amount\":").append(e.getKrw()).append(",")
//                    .append("\n\t\"stone\":").append(stone).append(",")
//                    .append("\n\t\"content\":\"").append(e.getKrw()).append("元 <span style='color:white;'>兌換</span>  ").append(stone).append("水晶\",")
//                    .append("\n\t\"remark\":\"").append(e.getAppId()).append("\"\n},");
//
//        }

        ss.append("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
        for (GameCommonBean iosBean : list) {

            ++i1;
            ss.append("googlePlayPrice.put(\"").append(iosBean.getProductId())
                    .append("\", ")
                    .append("new Object[] {\"").append(iosBean.getUsd())
                    .append("\",")
                    .append(iosBean.getAmount())
                    .append("});\n");

        }


//        for (GameCommonBean iosBean : list) {
//            sb.append("iapIOS.put(\"").append(iosBean.getProductId()).append("\", ")
//                    .append("new Object[] {\"")
//                    .append(iosBean.getAppId()).append("\",\"")
//                    .append(iosBean.getUsd()).append("\",")
//                    .append(0).append("});").append("\n");
//
//        }


        System.out.println(ss.toString());
        ss.append("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
        System.out.println(sb.toString());
    }

}
