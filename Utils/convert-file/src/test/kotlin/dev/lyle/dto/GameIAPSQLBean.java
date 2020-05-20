package dev.lyle.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GameIAPSQLBean {

    private String productId;
    private String gamePay;
    private String content;
    private String appId;
    private Integer sequenceId=100;
    private String menuId="efunjlts834";
    private String payFrom="efun";
    private String stone="0";
    private String createTime="2019-01-01 00:00:00";
    private String startTime="2019-01-01 00:00:00";
    private String endTime="2035-01-01 00:00:00";
    private String cashType="googleplay";
    private String paid="googleplay";
    private String flag = "是";
    private String pointArea = "TW";
    private String moneyType = "USD";


//    id: 13042
//    menuId: efunjlts834
//    productId: com.baplay.zlsg.weekgift101
//    payFrom: efun
//    gamePay: 1.99
//    stone: 0
//    startTime: 2016-01-31 23:25:06.0
//    endTime: 2035-01-01 00:00:00
//    content: 1.99美元 兌換 精美周禮包
//    paid: googleplay
//    flag: 是


    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPayFrom() {
        return payFrom;
    }

    public void setPayFrom(String payFrom) {
        this.payFrom = payFrom;
    }

    public String getGamePay() {
        return gamePay;
    }

    public void setGamePay(String gamePay) {
        this.gamePay = gamePay;
    }

    public String getStone() {
        return stone;
    }

    public void setStone(String stone) {
        this.stone = stone;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    public String getPointArea() {
        return pointArea;
    }

    public void setPointArea(String pointArea) {
        this.pointArea = pointArea;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
            return ToStringBuilder.reflectionToString(this,
                            ToStringStyle.MULTI_LINE_STYLE);
    }
}
