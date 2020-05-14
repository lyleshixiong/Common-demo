package com.lyle.outPrint;

import com.lyle.commonutils.FilePathUtils;
import com.lyle.dto.GameCommonBean;
import com.lyle.readFileUtils.ReadExcelCommon;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictSql {
    private final static Map<String,String> beanMap = new HashMap<>();

    static{ //需操作下excle 中的productId.   copy all ProductId-> 右键 (选择性粘贴) ->数值
        beanMap.put("appId","a");
        beanMap.put("productId","b");
        beanMap.put("krw","d");

        //start 0
        beanMap.put("startIndex","0");
        beanMap.put("endIndex","18");
        //第几个sheet ^start 0
        beanMap.put("sheetIndex","0");

    }

    private static void print() {
        try {
            List<GameCommonBean> list
                    = ReadExcelCommon.readExcel(new FileInputStream(FilePathUtils.getFilePath("dict.xlsx")),beanMap);
            if (null != list) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>size: [" + list.size() + "]");
                fmtMap(list);
            }
        } catch (Exception e) { e.printStackTrace(); }

    }

    public static void main(String[] args){
        print();
    }

    private static void fmtMap(List<GameCommonBean> list) {

        StringBuilder sb = new StringBuilder();
        for (GameCommonBean e : list) {
            if (StringUtils.isEmpty(e.getAppId())) {
                continue;
            }
            sb.append("INSERT INTO dict(`key`, `value`, `type`) VALUES (");
            sb.append("'").append(e.getAppId()).append("','").append(e.getProductId()).append("',").append(e.getKrw());
            sb.append(");\n");
        }
        System.out.println(sb.toString());

        System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

}
