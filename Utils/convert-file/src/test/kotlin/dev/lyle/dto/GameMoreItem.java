package dev.lyle.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GameMoreItem implements java.io.Serializable {

	// Fields

	/**
     *
	 */
	private static final long serialVersionUID = -3836871290629989271L;
	private Integer id;
	private String menuId;
	private String productId;
	private String thirdProductId;
	private String productRule;
	private Integer sequenceId;
	private String payFrom;
	private String gamePay;
	private String stone;
	private String img;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String content;
	private String remark;
	private String cashType;
	private String activityExtra;
	private String moneyType;
	private String paid;
	private String pointArea;
	private String flag;

	// Constructors

	/** default constructor */
	public GameMoreItem() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getThirdProductId() {
		return this.thirdProductId;
	}

	public void setThirdProductId(String thirdProductId) {
		this.thirdProductId = thirdProductId;
	}

	public String getProductRule() {
		return this.productRule;
	}

	public void setProductRule(String productRule) {
		this.productRule = productRule;
	}

	public Integer getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getPayFrom() {
		return this.payFrom;
	}

	public void setPayFrom(String payFrom) {
		this.payFrom = payFrom;
	}

	public String getGamePay() {
		return this.gamePay;
	}

	public void setGamePay(String gamePay) {
		this.gamePay = gamePay;
	}

	public String getStone() {
		return this.stone;
	}

	public void setStone(String stone) {
		this.stone = stone;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCashType() {
		return this.cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public String getActivityExtra() {
		return this.activityExtra;
	}

	public void setActivityExtra(String activityExtra) {
		this.activityExtra = activityExtra;
	}

	public String getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getPointArea() {
		return this.pointArea;
	}

	public void setPointArea(String pointArea) {
		this.pointArea = pointArea;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}