package com.lyle.dto;

public class GameParameters {
	private String uid;
	private String serverCode;
	private String gameCode;
//	private String level;
//	private String role
	private String flag;
	//類型
	private String type;
	//範圍
	private String range;
	//角色id
    private String roleid;
    //title邮件标题()
    private String title;
    //邮件内容
    private String content;
    //请求编号
    private String requestid;
	
	public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRange() {
        return range;
    }
    public void setRange(String range) {
        this.range = range;
    }
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	
	
	//	public void setSign(String sign) {
//		this.sign = sign;
//	}
	public GameParameters() {
		super();
	}
	public GameParameters(String uid, String serverCode) {
		super();
		this.uid = uid;
		this.serverCode = serverCode;
	}
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "GameParameters{" +
				"uid='" + uid + '\'' +
				", serverCode='" + serverCode + '\'' +
				'}';
	}
}
