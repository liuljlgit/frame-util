package com.cloud.common.webcomm;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class RespEntity {

	/**
	 * 无返回体的返回
	 * @return
	 */
	public static String ok(){
		JSONObject respObject = new JSONObject();
		respObject.put("code",CodeEnum.EXEC_OK.getCode());
		respObject.put("msg",CodeEnum.EXEC_OK.getMsg());
		return respObject.toJSONString();
	}

	/**
	 * 有返回体的返回
	 * @param resBody
	 * @return
	 */
	public static String ok(JSONObject resBody){
		JSONObject respObject = new JSONObject();
		respObject.put("code",CodeEnum.EXEC_OK.getCode());
		respObject.put("msg",CodeEnum.EXEC_OK.getMsg());
		respObject.put("object",resBody);
		return respObject.toJSONString();
	}

	/**
	 * 有返回体的返回
	 * @param value
	 * @return
	 */
	public static String ok(Object value){
		JSONObject respObject = new JSONObject();
		respObject.put("msg",CodeEnum.EXEC_OK.getMsg());
		respObject.put("code",CodeEnum.EXEC_OK.getCode());
		respObject.put("object",value);
		return respObject.toJSONString();
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
		JSONObject respObject = new JSONObject();
		respObject.put("code",codeEnum.getCode());
		respObject.put("msg",codeEnum.getMsg());
		respObject.put("object",resBody);
		return respObject.toJSONString();
	}

	/**
	 * 公共的返回方法
	 * @param code
	 * @param msg
	 * @param resBody
	 * @return
	 */
	public static String commonResp(Integer code,String msg,JSONObject resBody){
		JSONObject respObject = new JSONObject();
		respObject.put("code",code);
		respObject.put("msg",msg);
		respObject.put("object",resBody);
		return respObject.toJSONString();
	}

}
