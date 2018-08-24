package com.cloud.common.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * excel操作工具类
 * @author lijun
 */
public class ExcelUtil {
    /**
     * 把一个obj转换成excel中的一行
     * @param obj
     * @param row
     * @param colFieldMap
     * @throws Exception
     */
    public static void setObjRow(Object obj, Row row, Map<Integer, Field> colFieldMap) throws Exception {
        for(Map.Entry<Integer, Field> entry : colFieldMap.entrySet()){
            Integer cellIndex = entry.getKey();
            Field field = entry.getValue();
            Cell cell = row.getCell(cellIndex);
            //cell.setCellStyle();
            //cell.setCellType(CellType.FORMULA);
            Object o = field.get(obj);
            if(o instanceof String){
                cell.setCellValue((String)o);
            }else if(o instanceof Double){
                cell.setCellValue((Double)o);
            }else if(o instanceof Boolean){
                cell.setCellValue((Boolean)o);
            }else if(o instanceof Date){
                cell.setCellValue((Date)o);
            }else{
                cell.setCellValue((String)o);
            }
        }
    }

    /**
     * 把excel一行转换成一个object
     * @param obj
     * @param row
     * @param colFieldMap
     * @throws Exception
     */
    public static void setRowObj(Object obj,Row row,Map<Integer, Field> colFieldMap) throws Exception {
        for(Map.Entry<Integer, Field> entry : colFieldMap.entrySet()){
            Integer cellIndex = entry.getKey();
            Field field = entry.getValue();
            field.setAccessible(true);
            String dateFormat = field.getAnnotation(ExcelField.class).dateFormat();
            String fieldTypeName = field.getType().getSimpleName();
            Cell cell = row.getCell(cellIndex);
            //CELL_TYPE_NUMERIC 数值型 0
            //CELL_TYPE_STRING 字符串型 1
            //CELL_TYPE_FORMULA 公式型 2
            //CELL_TYPE_BLANK 空值 3
            //CELL_TYPE_BOOLEAN 布尔型 4
            //CELL_TYPE_ERROR 错误 5
            switch (cell.getCellType()){
                case 0:
                    //String dataFormatString = cell.getCellStyle().getDataFormatString(); 得到数字格式
                    //short dataFormat = cell.getCellStyle().getDataFormat();   得到数字格式
                    Double doubleVal = cell.getNumericCellValue();
                    String strVal = doubleVal.toString();
                    String strValNoDot = strVal.substring(0,strVal.lastIndexOf("."));
                    switch (fieldTypeName){
                        case "String":
                            field.set(obj,strVal);
                            break;
                        case "Integer":
                            field.set(obj,Integer.valueOf(strValNoDot));
                            break;
                        case "Long":
                            field.set(obj,Long.valueOf(strValNoDot));
                            break;
                        case "Double":
                            field.set(obj,doubleVal);
                            break;
                        case "Float":
                            field.set(obj,Float.valueOf(strVal));
                            break;
                        case "Byte":
                            field.set(obj,Byte.valueOf(strValNoDot));
                            break;
                        case "BigDecimal":
                            field.set(obj, BigDecimal.valueOf(doubleVal));
                        default:
                            break;
                    }
                    break;
                case 1:
                    //日期类型的使用excel日期格式来传递
                    switch (fieldTypeName){
                        case "String":
                            field.set(obj,cell.getStringCellValue());
                            break;
                        case "Date":
                            field.set(obj,new SimpleDateFormat(dateFormat).parse(cell.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    field.set(obj,cell.getBooleanCellValue());
                default:
                    break;
            }
        }
    }

    /**
     * 得到列属性和excel中的index的对应关系
     * @param clazz
     * @param optType
     * @return
     */
    public static Map<Integer, Field> parseColField(Class clazz,Byte optType){
        List<Field> mappedFiled = ExcelUtil.getMappedFiled(clazz, null, optType);
        return mappedFiled.stream().collect(Collectors.toMap(e -> getExcelCol(e.getAnnotation(ExcelField.class).col()), e -> e));
    }


    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     * @param col
     */
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

    /**
     * 得到实体类所有通过注解映射了数据表的字段
     * @param clazz
     * @param fields
     * @param optType 1:导入类型 2：导出类型
     */
    @SuppressWarnings({ "rawtypes" })
    public static List<Field> getMappedFiled(Class clazz, List<Field> fields,Byte optType) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelField.class)) {
                ExcelField annotation = field.getAnnotation(ExcelField.class);
                if(optType.equals(OptTypeEnum.IMPORT_ANNOTATION.getCode()) && annotation.isImport()){
                    fields.add(field);
                }
                if(optType.equals(OptTypeEnum.EXPORT_ANNOTATION.getCode()) && annotation.isExport()){
                    fields.add(field);
                }
            }
        }
        if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
            getMappedFiled(clazz.getSuperclass(), fields,optType);
        }
        return fields;
    }
}
