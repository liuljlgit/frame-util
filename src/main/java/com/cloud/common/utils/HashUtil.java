package com.cloud.common.utils;

/**
 * 使用Hash算法来实现分表
 */
public class HashUtil {

    /**
     * 表名称使用test001,test002的方式
     * 优点:简单方便
     * 确定:当hashTimes的次数发生改变时,必须查询出来每个表的数据，重新进行hash数据分表
     * 适用场景:此算法特别适合一个key（即我们业务中对应的xxid）值包含一批数据的情况，各批数据各不关联
     * @param tablename
     * @param hashTimes
     * @param key
     * @return
     * @throws Exception
     */
    public static String hashTableName(String tablename,Integer hashTimes,Object key) throws Exception {
        String suffer;
        if(key instanceof Integer){
            suffer = String.valueOf((Integer)key % hashTimes);
        }else if(key instanceof Long){
            suffer = String.valueOf((Long)key % hashTimes);
        }else{
            throw new Exception("key的数据类型不支持");
        }
        return tablename.concat("00").concat(suffer);
    }
}
