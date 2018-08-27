package com.cloud.common.base;

import com.alibaba.fastjson.JSONObject;

public class ReqEntity {
    /**
     * field comment: 使用reqBody来统一接收参数
     */
    private JSONObject reqBody;

    public JSONObject getReqBody() {
        return reqBody;
    }

    public void setReqBody(JSONObject reqBody) {
        this.reqBody = reqBody;
    }
}
