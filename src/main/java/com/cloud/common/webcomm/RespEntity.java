package com.cloud.common.webcomm;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class RespEntity {

	/**
	 * 无返回体的返回
	 * @return
	 */
	public static String ok(){
		JSONObject resObject = new JSONObject();
		JSONObject respHeader = new JSONObject();
		respHeader.put("code",CodeEnum.EXEC_OK.getCode());
		respHeader.put("msg",CodeEnum.EXEC_OK.getMsg());
		resObject.put("respHeader",respHeader);
		return resObject.toJSONString();
	}

	/**
	 * 有返回体的返回
	 * @param resBody
	 * @return
	 */
	public static String ok(JSONObject resBody){
		JSONObject resObject = new JSONObject();
		JSONObject respHeader = new JSONObject();
		respHeader.put("code",CodeEnum.EXEC_OK.getCode());
		respHeader.put("msg",CodeEnum.EXEC_OK.getMsg());
		resObject.put("respBody",resBody);
		resObject.put("respHeader",respHeader);
		return resObject.toJSONString();
	}

	/**
	 * 公共的返回方法
	 * @param codeEnum
	 * @param resBody
	 * @return
	 */
	public static String commonResp(CodeEnum codeEnum,JSONObject resBody){
		if(Objects.isNull(codeEnum)){
			codeEnum = CodeEnum.EXEC_OK;
		}
		JSONObject resObject = new JSONObject();
		JSONObject respHeader = new JSONObject();
		respHeader.put("code",codeEnum.getCode());
		respHeader.put("msg",codeEnum.getMsg());
		resObject.put("respBody",resBody);
		resObject.put("respHeader",respHeader);
		return resObject.toJSONString();
	}

	/**
	 * 公共的返回方法
	 * @param code
	 * @param msg
	 * @param resBody
	 * @return
	 */
	public static String commonResp(Integer code,String msg,JSONObject resBody){
		JSONObject resObject = new JSONObject();
		JSONObject respHeader = new JSONObject();
		respHeader.put("code",code);
		respHeader.put("msg",msg);
		resObject.put("respBody",resBody);
		resObject.put("respHeader",respHeader);
		return resObject.toJSONString();
	}

}
