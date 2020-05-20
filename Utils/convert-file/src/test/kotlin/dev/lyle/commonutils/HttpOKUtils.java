package dev.lyle.commonutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dev.lyle.dto.GameParameters;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpOKUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpOKUtils.class);

    public static JSONObject get(String url, GameParameters params, String sign) {
        Response response = null;
        Request request;
        String result = null;
        JSONObject json = new JSONObject();

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("userId", params.getUid())
                .addQueryParameter("gameCode", params.getGameCode())
                .addQueryParameter("serverCode", params.getServerCode())
                .addQueryParameter("sign", sign);
        HttpUrl toUrl = builder.build();
        request = new Request.Builder()
                .url(toUrl)
                .addHeader("Connection", "close")
                .build();
        logger.info("award url: {}", toUrl.url().toString());
        try {
            response = HttpUtil.getInstance().newCall(request).execute();
            if (response.code() != 200) {
                json.put("code", "9999");
                json.put("userId", params.getUid());
                json.put("serverCode", params.getServerCode());
                logger.error("award error: HTTP CODE {}, HTTP CONTENT:{},UID>>>>>>>>>> : {}", response.code(), response.body().string(),params.getUid());
            }
            result = response.body().string();
        } catch (Exception e) {
            json.put("code", "9999");
            json.put("userId", params.getUid());
            json.put("serverCode", params.getServerCode());
            logger.error("award error: {}", e.getMessage());
        } finally {
            if (response != null){
                response.close();
            }
        }

        if (StringUtils.isNotBlank(result)) {
            try {
                return JSON.parseObject(result);
            } catch (Exception e) {
                logger.error("award error: {}", e.getMessage());
                json.put("userId", params.getUid());
                json.put("serverCode", params.getServerCode());
                json.put("code", "9999");
                return json;
            }
        }
        return json;
    }
}
