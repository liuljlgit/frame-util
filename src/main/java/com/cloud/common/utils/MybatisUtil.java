package com.cloud.common.utils;


import java.lang.reflect.Field;
import java.util.*;

/**
 * mybatis返回值映射
 */
public class MybatisUtil {

    /**
     * 把Map转换成Bean
     * @param resultMap
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapConvertToBean(Map<String,Object> resultMap, Class<T> cls) throws Exception {
        T t = cls.newInstance();
        reflectMapToBean(resultMap, (T) t);
        return t;
    }

    /**
     * 把Map转换成List<Bean>
     * @param list
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> mapConvertToBeans(List<Map<String,Object>> list, Class<T> cls) throws Exception {
        List<T> resList = new ArrayList<>();
        for(Map<String,Object> resultMap : list){
            T t = cls.newInstance();
            reflectMapToBean(resultMap, (T) t);
            resList.add(t);
        }
        return resList;
    }

    /**
     * 转换
     * @param resultMap
     * @param t
     * @param <T>
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static <T> void reflectMapToBean(Map<String, Object> resultMap, T t) throws NoSuchFieldException, IllegalAccessException {
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            String fieldName = HumpUtil.convertToJava(entry.getKey());
            Field field = t.getClass().getDeclaredField(fieldName);
            if (Objects.nonNull(field)){
                field.setAccessible(true);
                field.set(t,entry.getValue());
            }
        }
    }

    public static void main(String[] args) throws Exception {

    }
}
