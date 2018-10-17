package com.cloud.common.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    protected static final String BUSI_CODE_MESSGE = "message";
    protected static final String RESP_HEADER = "respHeader" ;

    protected static final Integer EXEC_OK = 0000 ;
    protected static final Integer EXEC_ERROR = 9999 ;
    protected static final Integer EXEC_401 =401;
    protected static final Integer EXEC_403 =403;

    private static final Map<Integer,String> codeMap = new HashMap(){{
        put(EXEC_OK, "正确执行") ;
        put(EXEC_ERROR, "系统繁忙，请稍后再试！") ;
        put(EXEC_401,"权限确认失败");
        put(EXEC_403,"无权限访问");
    }} ;

    /**
     * 得到头信息
     * @param code
     * @param args
     * @return
     */
    public static JSONObject getRespHeader(Integer code, String ...args) {
        String errorMsg = codeMap.get(code) ;
        if (StringUtils.isEmpty(errorMsg)) {
            code = 9999;
            errorMsg = "未定义的错误";
        }
        JSONObject respMsg = new JSONObject();
        respMsg.put("resultCode", code);
        if (args != null && args.length > 0) {
            errorMsg = MessageFormat.format(errorMsg, args);
        }
        respMsg.put(BUSI_CODE_MESSGE, errorMsg);
        JSONObject respHeader = new JSONObject() ;
        respHeader.put(RESP_HEADER, respMsg);
        return respHeader ;
    }

    /**
     * 生成响应结果
     * @param resultCode
     * @param respBody
     * @param args
     * @return
     */
    public static String formatResponseParams(Integer resultCode, JSONObject respBody, String ...args) {
        JSONObject RespHeader = getRespHeader(resultCode, args);
        if( respBody != null ){
            RespHeader.put("respBody", respBody);
        }
        return RespHeader.toString();
    }
}
