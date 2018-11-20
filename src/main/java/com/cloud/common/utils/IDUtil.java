package com.cloud.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 主键生成器
 */
public class IDUtil {

    private static final AtomicLong increator = new AtomicLong();
    private static long MAX_INCREATOR = 1048570l; //20位的递增最大值
    private static LocalDateTime startDateTime = LocalDateTime.of(2017, 1, 1, 0, 0);
    public static long create(long areaCode, long machineNum) {
        long seconds = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - startDateTime.toEpochSecond(ZoneOffset.UTC);
        long incre = increator.accumulateAndGet(1, (x,y)->(x+y) > MAX_INCREATOR ? 0 : (x+y));
        return (seconds << 32) | (areaCode << 26) | (machineNum << 20) | incre;
    }

    /**
     * 获取UUId
     * @return
     */
    public static String genUUId(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 获取LongId
     * @return
     */
    public static Long genLongId(){
        return create(1,1);
    }
}
