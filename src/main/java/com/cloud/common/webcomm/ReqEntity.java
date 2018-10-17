package com.cloud.common.webcomm;

import com.alibaba.fastjson.JSONObject;

public class ReqEntity {
	private ReqHeader reqHeader;
	private JSONObject reqBody;
	public ReqHeader getReqHeader() {
		return reqHeader;
	}
	public void setReqHeader(ReqHeader reqHeader) {
		this.reqHeader = reqHeader;
	}
	public JSONObject getReqBody() {
		return reqBody;
	}
	public void setReqBody(JSONObject reqBody) {
		this.reqBody = reqBody;
	}
	
	
	

}
