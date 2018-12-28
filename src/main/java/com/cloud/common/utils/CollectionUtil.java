package com.cloud.common.utils;

import java.util.*;

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
            List<T> value;
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
     * 从一个两层的map中得到唯一的值
     * V可以是map,则也可以是三层map结构
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

    /**
     * 从一个两层的map中得到唯一的值,如果不存在,则新创建
     * V可以是map,则也可以是三层map结构
     * @return
     */
    public static <K1,K2,V> V getTwoKeyMapValue(K1 firstKey,K2 secondKey,Map<K1,Map<K2,V>> dataMaps,V initValue) {
        if(dataMaps.containsKey(firstKey)){
            Map<K2, V> k2VMap = dataMaps.get(firstKey);
            if(k2VMap.containsKey(secondKey)){
                return k2VMap.get(secondKey);
            }
        }
        return setTwoKeyMapValue(firstKey,secondKey,dataMaps,initValue);
    }

    /**
     * 从一个两层的map中设置值
     * V可以是map,则也可以是三层map结构
     * @return
     */
    public static <K1,K2,V> V setTwoKeyMapValue(K1 firstKey,K2 secondKey,Map<K1,Map<K2,V>> dataMaps,V value) {
        if(dataMaps.containsKey(firstKey)){
            dataMaps.get(firstKey).put(secondKey,value);
        }else{
            Map<K2, V> k2VMap = new HashMap<>();
            k2VMap.put(secondKey,value);
            dataMaps.put(firstKey,k2VMap);
        }
        return value;
    }

    /**
     * list去重（不保证原来的顺序）
     * @param list
     * @return
     */
    public static <T> List<T> removeRepeatElement(List<T> list) {
        HashSet<T> h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     * list去重保证原来的顺序
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> removeDuplicateWithOrder(List<T> list) {
        Set<T> set = new HashSet();
        List<T> newList = new ArrayList();
        for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
            T element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        return list;
    }

    /**
     * 去掉集合中的多个元素
     * @param collection
     * @param elesRemoved
     * @param <T>
     * @return
     */
    public static <T> Collection<T> removeAny(Collection<T> collection, T... elesRemoved) {
        HashSet<T> set = new HashSet<>();
        for (T t : elesRemoved) {
            set.add(t);
        }
        collection.removeAll(set);
        return collection;
    }

    /**
     * 获取(A-B)集合,也就是A集合除去B集合中已存在的对象
     * @param aCollect
     * @param bCollect
     * @param elesRemoved
     * @param <T>
     * @return
     */
    public static <T> Collection<T> aSubtractB(Collection<T> aCollect,Collection<T> bCollect, T... elesRemoved) {
        Set set = new HashSet();
        Set<T> aSet = new HashSet<>(aCollect);
        Set<T> bSet = new HashSet<>(bCollect);
        for (T t : aSet) {
            if(!bSet.contains(t)){
                set.add(t);
            }
        }
        return set;
    }

}
