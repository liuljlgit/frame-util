package com.cloud.common.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface ExcelField {

    /**
     * 是否是导入列
     */
    boolean isImport() default false;

    /**
     * 是否是导出列
     */
    boolean isExport() default false;

    /**
     * 日期类型
     */
    String dateFormat() default "yyyy-MM-dd";

    /**
     * 导入/导出时配置的列位置,对应A,B,C,D....
     */
    String col();
}
