package com.cloud.common.excel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作类型枚举类
 */
public enum OptTypeEnum {
    IMPORT_ANNOTATION((byte) 1,"导入类型的注解"),
    EXPORT_ANNOTATION((byte) 2, "导出类型的注解");

    private Byte code;
    private String desc;

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<Byte, OptTypeEnum> map = new HashMap<>();
    static {
        if (map == null) {
            map = new HashMap<>();
        }
        Arrays.stream(OptTypeEnum.values()).forEach(e -> map.put(e.getCode(), e));
    }

    OptTypeEnum(Byte code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static OptTypeEnum getOptTypeEnum(byte code){
        return map.get(code);
    }
}
