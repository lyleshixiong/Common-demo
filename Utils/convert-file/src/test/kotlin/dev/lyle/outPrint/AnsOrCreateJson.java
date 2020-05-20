package dev.lyle.outPrint;

import com.alibaba.fastjson.JSONObject;
import dev.lyle.dto.GameMore;
import dev.lyle.dto.GameMoreItem;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AnsOrCreateJson {
    public JSONObject uploadFileUpdateGame(GameMore upGameMore, MultipartFile file, String itemMenuId) {

        JSONObject jo = new JSONObject();

        if (file == null || "".equals(file) || file.getSize() >= 10000) {
            jo.put("msg", "請上傳更新的josn 文件");
            return jo;
        }
        if (StringUtils.isEmpty(upGameMore.getGameCode())) {
            jo.put("msg", "請選擇游戲！");
            return jo;
        }
        //upload json file覆蓋更新
        List<GameMoreItem> upItemList = null;
        try {
            upItemList = fmtFileToList(file, itemMenuId);
        } catch (IOException e) {
            e.printStackTrace();
            jo.put("msg", "解析JSON文件失敗了！");
            return jo;
        }
        if (upItemList == null || upItemList.size() < 1) {
            jo.put("msg", "bulabula 500了");
            return jo;
        }
        if (!upItemList.get(0).getMenuId().contains("jlts")) {
            jo.put("msg", "僅可上傳 君臨天下（jlts）模板");
            return jo;
        }

        upItemList.sort(Comparator.comparing(GameMoreItem::getMenuId).thenComparingDouble((r -> Double.parseDouble(r.getGamePay()))));

        final Integer[] ii = {100};
        upItemList.forEach(en -> {
            en.setCreateTime(new Date());
            en.setMenuId(en.getMenuId().replace("jlts", upGameMore.getGameCode()));
            en.setEndTime(new Date());
            en.setStartTime(new Date());
            en.setFlag("是");
            en.setSequenceId(++ii[0]);
            en.setPayFrom("efun");
            en.setProductId(en.getProductId() == null ? "" : en.getProductId());
        });




        jo.put("code", "0000");
        jo.put("msg", "更新成功！");
        return jo;
    }


    private List<GameMoreItem> fmtFileToList(MultipartFile file, String itemMenuId) throws IOException {
        List<GameMoreItem> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(file.getBytes());


        root.findValues(itemMenuId).forEach(node -> node.forEach(en -> {
            list.add(mapper.convertValue(en, GameMoreItem.class));
        }));

        return list;
    }

    private void changeItemModelGameCode(List<GameMoreItem> listItemModel, String mGameCode, String gameCode) {
        listItemModel.forEach(en -> {
            en.setId(null);
            en.setMenuId(en.getMenuId() == null ? en.getMenuId() : en.getMenuId().replace(mGameCode, gameCode));
            en.setFlag("是");
        });
    }

    private void changeModelGameCode(List<GameMore> listModel, String gameCode) {
        listModel.forEach(en -> {
            en.setId(null);
            en.setMenuId(en.getMenuId() == null ? en.getMenuId() : en.getMenuId().replace(en.getGameCode(), gameCode));
            en.setGameCode(gameCode);
            en.setFlag("是");
        });
    }

}
