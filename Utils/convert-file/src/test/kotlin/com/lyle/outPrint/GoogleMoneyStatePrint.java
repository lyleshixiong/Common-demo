package com.lyle.outPrint;

import com.lyle.commonutils.FilePathUtils;
import com.lyle.dto.GameCommonBean;
import com.lyle.readFileUtils.ReadExcelGoogleState;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleMoneyStatePrint {
    private final static Map<String,String> beanMap = new HashMap<>();

    static{ //需操作下excle 中的productId.   copy all ProductId-> 右键 (选择性粘贴) ->数值
//        beanMap.put("appId","a");
        beanMap.put("productId","a");
//        beanMap.put("gpId","b");
//        beanMap.put("stone","e");
//        beanMap.put("amount","d");
//        beanMap.put("krw","d");
//        beanMap.put("gpId","n");
//        beanMap.put("ybNum","k");

        //start 0
        beanMap.put("startIndex","1");
        beanMap.put("endIndex","100");
        //第几个sheet ^start 0
        beanMap.put("sheetIndex","1");

    }
    public static void main(String[] args){
        print();
    }

    private static void print() {
        try {
            List<GameCommonBean> list = ReadExcelGoogleState.readExcel(new FileInputStream(FilePathUtils.getFilePath("googletw.xlsx")), beanMap);
            if (null != list) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>size: [" + list.size() + "]\n\n");
                fmtMap(list);
            }
        } catch (Exception e) { e.printStackTrace(); }

    }

    private static void fmtMap(List<GameCommonBean> list) {
        StringBuilder sb = new StringBuilder("UPDATE googlePlay set account_status = 5 ,country ='tem1' WHERE googleOrderId IN(");
        int ii = 0;
        Map<String, String> map = new HashMap<>();
        for (GameCommonBean g : list) {
            map.put(g.getProductId(), g.getProductId());
        }

        for (GameCommonBean e : list) {
//            if (!map.containsKey(e.getProductId())) {
//                ++ii;
//                sb.append("'").append(e.getProductId()).append(">>>>").append(e.getGpId()).append("',");
//            }

            sb.append("'").append(e.getProductId()).append("'");
            ++ii;
            if(ii!=list.size()){
                sb.append(",");
            }

        }
        sb.append(");");
        sb.append("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n"+ii);
        System.out.println(sb.toString()+">>>>>>>>>>>>>"+ii);
    }

}
