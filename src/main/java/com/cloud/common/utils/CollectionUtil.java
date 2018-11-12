package com.cloud.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 集合处理工具类
 */
public class CollectionUtil {

    /**
     * 均分List列表
     * @param source
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        int remaider = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * 按固定大小切分List列表
     * @param list
     * @param len
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    /**
     * 从一个两层的map中得到value值
     * @param firstKey
     * @param secondKey
     * @param dataMaps
     * @param <K1>
     * @param <K2>
     * @param <V>
     * @return
     */
    public static <K1,K2,V> V getTwoKeyMapValue(K1 firstKey,K2 secondKey,Map<K1,Map<K2,V>> dataMaps) {
        if(dataMaps.containsKey(firstKey)){
            Map<K2, V> k2VMap = dataMaps.get(firstKey);
            if(k2VMap.containsKey(secondKey)){
                return k2VMap.get(secondKey);
            }
        }
        return null;
    }

}
