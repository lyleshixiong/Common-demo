package com.lyle.dto;

import java.util.Date;

public class AnsGameDto {
        private Long uid;
        private Date orderTime;
        private String creditId;
        private String serverCode;
        private Double twCoin;
        private String flag;
        private Double stone;
        private Integer payStatus;

        public Integer getPayStatus() {
            return payStatus;
        }

        void setPayStatus(Integer payStatus) {
            this.payStatus = payStatus;
        }
        void setNull() {
            this.orderTime = null;
            this.creditId = null;
            this.serverCode = null;
            this.twCoin = null;
            this.flag = null;
            this.stone = null;
        }

        public AnsGameDto() {
        }

        public AnsGameDto(Long uid, Double twCoin) {
            this.uid = uid;
            this.twCoin = twCoin;
        }

        AnsGameDto(Long uid, Date orderTime, String creditId, String serverCode, Double twCoin, String flag, Double stone) {
            this.uid = uid;
            this.orderTime = orderTime;
            this.creditId = creditId;
            this.serverCode = serverCode;
            this.twCoin = twCoin;
            this.flag = flag;
            this.stone = stone;
        }

        Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }

        public Date getOrderTime() {
            return orderTime;
        }
        public Date getOrderTimeFmt() {
            return orderTime;
        }

        public void setOrderTime(Date orderTime) {
            this.orderTime = orderTime;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public String getServerCode() {
            return serverCode;
        }

        public void setServerCode(String serverCode) {
            this.serverCode = serverCode;
        }

        public Double getTwCoin() {
            return twCoin;
        }

        public void setTwCoin(Double twCoin) {
            this.twCoin = twCoin;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public Double getStone() {
            return stone;
        }

        public void setStone(Double stone) {
            this.stone = stone;
        }
    }