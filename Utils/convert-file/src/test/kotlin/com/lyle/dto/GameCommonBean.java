package com.lyle.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GameCommonBean {

    private String productId;
    private String appId;
    private String oneStoreId;
    private String gpId;
    private Integer krw;
    private Double usd;
    private Integer stone;
    private Long uid;
    private String amount;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOneStoreId() {
        return oneStoreId;
    }

    public void setOneStoreId(String oneStoreId) {
        this.oneStoreId = oneStoreId;
    }

    public String getGpId() {
        return gpId;
    }

    public void setGpId(String gpId) {
        this.gpId = gpId;
    }

    public Integer getKrw() {
        return krw;
    }

    public void setKrw(Integer krw) {
        this.krw = krw;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public Integer getStone() {
        return stone;
    }

    public void setStone(Integer stone) {
        this.stone = stone;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
            return ToStringBuilder.reflectionToString(this,
                            ToStringStyle.MULTI_LINE_STYLE);
    }
}
