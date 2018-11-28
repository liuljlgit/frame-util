package com.cloud.common.constant;

/**
 * 系统常量
 */
public class IConst {
    /**
     * field comment: 默认不使用分页
     */
    public static final Integer PAGE_NO_USE = -1 ;

    /**
     * field comment: 默认分页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 1000 ;

    /**
     * field comment: 缓存15分钟
     */
    public static final Long MINUTE_15_EXPIRE = 900L ;

    /**
     * field comment: 缓存30分钟
     */
    public static final Long MINUTE_30_EXPIRE = 1800L ;

    /**
     * field comment: 缓存一个小时
     */
    public static final Long HOUR_1_EXPIRE = 3600L ;

    /**
     * field comment: 永不过期
     */
    public static final Long ZERO_EXPIRE = 0L ;
}
