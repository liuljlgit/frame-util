package com.cloud.common.webcomm;

import com.alibaba.fastjson.JSONObject;

public class RespEntity {
	private JSONObject respHeader;
	private JSONObject respBody;

	public void setRespHeader(JSONObject respHeader) {
		this.respHeader = respHeader;
	}

	public void setRespBody(JSONObject respBody) {
		this.respBody = respBody;
	}

	public JSONObject getRespHeader() {

		return respHeader;
	}

	public JSONObject getRespBody() {
		return respBody;
	}
}
