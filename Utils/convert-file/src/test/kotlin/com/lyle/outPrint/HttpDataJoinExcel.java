package com.lyle.outPrint;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyle.commonutils.ExcelUtil;
import com.lyle.commonutils.FilePathUtils;
import com.lyle.commonutils.HttpOKUtils;
import com.lyle.dto.GameParameters;
import com.lyle.readFileUtils.ReadExcelToMap;
import jxl.write.WriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HttpDataJoinExcel {
    private final static Logger logger = LoggerFactory.getLogger(HttpDataJoinExcel.class);
    private final static Map<String,String> beanMap = new HashMap<>();
    private final static Map<String,ServerRole> serverRoleMap = new HashMap<>();
    private final static Map<String,String> failUserServerCodeMap = new HashMap<>();
    private static Map<String,String> uidServerCodeMapExcel = null;
    private static Set<Long> uidSet = new HashSet<>() ;
    private static Set<Long> serverCodeSet = new HashSet<>() ;

    private static final String GAMECODE = "xx";
    private static final String SESSION_SECRET = "xxxxxx";
//    private static final String excelUidPath = FilePathUtils.getFilePath("loginUID.csv");
    private static final String excelUidPath = FilePathUtils.getFilePath("loginUIDyj.xls");
    private static final String excelUidPath2 = FilePathUtils.getFilePath("loginUIDyj2.xls");


    //Your InputStream was neither an OLE2 stream, nor an OOXML stream導出選擇 excel file *xls
    private static final String excelMoneyPath = FilePathUtils.getFilePath("loginSumPayMoneys22_20191202.xls");
    private static final String excelServerCodePath = FilePathUtils.getFilePath("serverCodes.csv");
    private static final String excelServerCodeFailPath = FilePathUtils.getFileOutPath("failUidLoginRole.csv");
    static{ //需操作下excle 中的productId.   copy all ProductId-> 右键 (选择性粘贴) ->数值
        beanMap.put("uid","a");
//        beanMap.put("total","b");
        beanMap.put("serverCode","b");

        //start 0
        beanMap.put("startIndex","1");
    }


    private static String toMd5(String inputValue){
        if(inputValue==null)return "";
        try {
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(inputValue.getBytes("UTF8"));
            byte s[ ]=m.digest( );
            String result="";
            for(int i=0;i<s.length;i++){
                result+=Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            }
            return result.toUpperCase();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            logger.info(inputValue+ "toMd5 error ,error message:"+e.getMessage());
            return "";
        }
    }

    private static JSONArray httpOKGetGameRole(GameParameters params) {
        String sign = toMd5(SESSION_SECRET + params.getUid() + params.getServerCode())
                .toUpperCase();

        String url = "xxx";

        JSONObject value = HttpOKUtils.get(url,params,sign);
        logger.info("result:" + value);
        JSONArray resultArray = new JSONArray();
        if ("xxx".equals(value.get("code"))) {
            resultArray = value.getJSONArray("list");
//            if (failUserServerCodeMap.containsKey(params.getUid()+"="+params.getServerCode())) {
//                failUserServerCodeMap.remove(params.getUid()+"="+params.getServerCode());
//            }
        } else {
            if ("xxxxx".equals(value.get("code"))) {
                failUserServerCodeMap.put(value.get("userId").toString()+"="+value.get("serverCode").toString(), value.get("serverCode").toString());
            }
        }

        return resultArray;
    }


    public static void httpOKUtils() {
        try {
            long start = System.currentTimeMillis();
           ReadExcelToMap.readExcel(new FileInputStream(excelUidPath),beanMap,uidSet);
           ReadExcelToMap.readExcel(new FileInputStream(excelUidPath2),beanMap,uidSet);
//           ReadExcelToMap.readExcel(new FileInputStream(excelServerCodePath),beanMap,serverCodeSet);
//           logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>serverCodeSet "+ serverCodeSet.size() +" * uidSet: [" + uidSet.size() + "]");
//            if (uidSet.size() > 0 && serverCodeSet.size() > 0) {
//                httpBeforeThenCall();
//            }
//            uidServerCodeMapExcel = ReadExcelToMap.readExcelToMapStr(new FileInputStream(excelServerCodeFailPath),beanMap);
//            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>uidServerCodeMapExcel "+ uidServerCodeMapExcel.size() );
//            if (uidServerCodeMapExcel.size() > 0) {
//                httpFailBeforeThenCall();
//            }

            printWhereInUid(uidSet);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n{}",uidSet.size());
//
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}>>>>>>>>>>>>>>毫秒：{}",excelUidPath,(System.currentTimeMillis()-start));
            Map<Long, String> uidSumMoneyMap = ReadExcelToMap.readExcelToMap(new FileInputStream(new File(excelMoneyPath)), beanMap);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}>>>>>>>>>>>>>>毫秒：{}",excelMoneyPath,(System.currentTimeMillis()-start));
            operateUidInnerSumMoneys(uidSet, uidSumMoneyMap);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{operateUidInnerSumMoneys}>>>>>>>>>>>>>>end<<<<<<<<分鐘：{}",(System.currentTimeMillis()-start)/1000/60);
        } catch (Exception e) { e.printStackTrace(); }

    }

    private static void printWhereInUid(Set<Long> uidSet) {
        StringBuilder sb = new StringBuilder("(");
        uidSet.forEach(uid-> sb.append(uid).append(","));
        sb.append(",)");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n{}",sb.toString());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n{}",uidSet.size());
    }

    private static void operateUidInnerSumMoneys(Set<Long> uidSet, Map<Long, String> uidSumMoneyMap) {
        String[] title = new String[]{"UID","sumTW"};
        List<Object[]> res = new ArrayList<>();
        uidSet.forEach(uid->{
            Object[] obj = new Object[2];
            obj[0] = uid.toString();
            obj[1] = uidSumMoneyMap.get(uid);
            res.add(obj);
        });


        try {
            ExcelUtil.reportExcel("UIDofSumMoneys.csv","page",title,res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    private static void httpBeforeThenCall() {
        List<Integer> reqNum = new ArrayList<>();
        uidSet.parallelStream().forEach(uid-> serverCodeSet.parallelStream().forEach(serverCode -> {
            GameParameters parameters = new GameParameters();
            parameters.setGameCode(GAMECODE);
            parameters.setServerCode(serverCode.toString());
            parameters.setUid(uid.toString());
            logger.info(parameters.toString());
            if(reqNum.size()%20 == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>uid={},serverCode={}",parameters.getUid(),parameters.getServerCode());
            JSONArray result = httpOKGetGameRole(parameters);
            reqNum.add(1);

            if (result.size() > 0) {

                for (int i = 0; i < result.size(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    ServerRole role = new ServerRole();
                    role.setUid(parameters.getUid());
                    role.setRoleid(jo.getString("roleid"));
                    role.setRolename(jo.getString("name"));
                    role.setServerCode(parameters.getServerCode());
                    serverRoleMap.put(role.getUid()+"="+role.getRoleid(), role);
                }
//                logger.info("args >>>>>>>>>>>>>>>>>>>>>>[" + serverRoleMap.get(parameters.getUid() + parameters.getServerCode()).toString() + "]");
            }else {
                logger.info("args -------------------------");
            }
        }));

//        if (failUserServerCodeMap.size() > 0) {
//            logger.info("failUserServerCodeMap>>>>>>>>>>>>>>>>>>>>>>size>>>>>"+failUserServerCodeMap.size());
//            failUserServerCodeMap.entrySet().stream().forEach(mmf -> {
//                GameParameters parameters = new GameParameters();
//                parameters.setGameCode(GAMECODE);
//                parameters.setUid(mmf.getKey().split("=")[0]);
//                parameters.setServerCode(mmf.getValue());
//                logger.info(parameters.toString());
//                if(reqNum.size()/20 >= 1){
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                logger.info("failUserServerCodeMap>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>uid={},serverCode={}",parameters.getUid(),parameters.getServerCode());
//
//                JSONArray result = httpOKGetGameRole(parameters);
//                reqNum.add(1);
//
//                if (result.size() > 0) {
//
//                    for (int i = 0; i < result.size(); i++) {
//                        JSONObject jo = result.getJSONObject(i);
//                        ServerRole role = new ServerRole();
//                        role.setUid(parameters.getUid());
//                        role.setRoleid(jo.getString("roleid"));
//                        role.setRolename(jo.getString("name"));
//                        role.setServerCode(parameters.getServerCode());
//                        serverRoleMap.put(role.getUid()+"="+role.getRoleid(), role);
//                    }
////                logger.info("args >>>>>>>>>>>>>>>>>>>>>>[" + serverRoleMap.get(parameters.getUid() + parameters.getServerCode()).toString() + "]");
//                }else {
//                    logger.info("failUserServerCodeMap args-------------------------");
//                }
//            });
//
//        }
    }



    private static void httpFailBeforeThenCall() {
        List<Integer> reqNum = new ArrayList<>();
        uidServerCodeMapExcel.entrySet().forEach(entry -> {
            GameParameters parameters = new GameParameters();
            parameters.setGameCode(GAMECODE);
            parameters.setServerCode(entry.getValue());
            parameters.setUid(entry.getKey().split("=")[0]);
            logger.info(parameters.toString());
            if(reqNum.size()%20 == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>uid={},serverCode={}",parameters.getUid(),parameters.getServerCode());
            JSONArray result = httpOKGetGameRole(parameters);
            reqNum.add(1);

            if (result.size() > 0) {

                for (int i = 0; i < result.size(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    ServerRole role = new ServerRole();
                    role.setUid(parameters.getUid());
                    role.setRoleid(jo.getString("roleid"));
                    role.setRolename(jo.getString("name"));
                    role.setServerCode(parameters.getServerCode());
                    serverRoleMap.put(role.getUid()+"="+role.getRoleid(), role);
                }
//                logger.info("args >>>>>>>>>>>>>>>>>>>>>>[" + serverRoleMap.get(parameters.getUid() + parameters.getServerCode()).toString() + "]");
            }else {
                logger.info("args -------------------------");
            }
        });
    }






    public static void main(String[] args){
//        GameParameters parameters = new GameParameters();
//        parameters.setUid("369045"); //多个role
////        parameters.setUid("514416");//一个role
////        parameters.setUid("5144161222222");//错误
//        parameters.setServerCode("2001");
//        parameters.setGameCode("xx");
//        JSONArray result = httpOKGetGameRole(parameters);
//
//        if (result.size() > 0) {
//
//            for (int i = 0; i < result.size(); i++) {
//                JSONObject jo = result.getJSONObject(i);
//                ServerRole role = new ServerRole();
//                role.setUid(parameters.getUid());
//                role.setRoleid(jo.getString("roleid"));
//                role.setRolename(jo.getString("name"));
//                role.setServerCode(parameters.getServerCode());
//                serverRoleMap.put(role.getUid()+"="+role.getRoleid(), role);
//            }
//        }else {
//            logger.info("args -------------------------");
//        }


        try {

            httpOKUtils();
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<ServerRole> roleList = new ArrayList<>();
        List<ServerRole> failList = new ArrayList<>();

        serverRoleMap.forEach((key, value) -> roleList.add(value));
        failUserServerCodeMap.forEach((key, value) -> {
            ServerRole r = new ServerRole();
            r.setUid(key.split("=")[0]);
            r.setServerCode(value);
            failList.add(r);
        });

        roleList.stream().sorted(Comparator.comparing(ServerRole::getUid)
                .thenComparing(ServerRole::getServerCode))
                .parallel();

        String[] title = new String[]{"UID","serverCode","roleID","roleName"};
        String[] title2 = new String[]{"UID","serverCode"};
        List<Object[]> res = new ArrayList<>();
        roleList.forEach(en->{
            Object[] obj = new Object[4];
            obj[0] = en.getUid();
            obj[1] = en.getServerCode();
            obj[2] = en.getRoleid();
            obj[3] = en.getRolename();
            res.add(obj);
        });
        List<Object[]> res2 = new ArrayList<>();
        failList.forEach(en->{
            Object[] obj = new Object[2];
            obj[0] = en.getUid();
            obj[1] = en.getServerCode();
            res2.add(obj);
        });


        try {
            ExcelUtil.reportExcel("uidLoginRole.csv","page",title,res);
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }


        try {
            ExcelUtil.reportExcel("failUidLoginRole.csv","page",title2,res2);
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }


    }
    static class ServerRole {
        private String uid;
        private String serverCode;
        private String gameCode;
        //角色id
        private String roleid;
        private String rolename;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getServerCode() {
            return serverCode;
        }

        public void setServerCode(String serverCode) {
            this.serverCode = serverCode;
        }

        public String getGameCode() {
            return gameCode;
        }

        public void setGameCode(String gameCode) {
            this.gameCode = gameCode;
        }

        public String getRoleid() {
            return roleid;
        }

        public void setRoleid(String roleid) {
            this.roleid = roleid;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        @Override
        public String toString() {
            return "ServerRole{" +
                    "uid='" + uid + '\'' +
                    ", serverCode='" + serverCode + '\'' +
                    ", gameCode='" + gameCode + '\'' +
                    ", roleid='" + roleid + '\'' +
                    ", rolename='" + rolename + '\'' +
                    '}';
        }
    }

}
