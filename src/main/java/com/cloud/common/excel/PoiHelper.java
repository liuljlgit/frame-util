package com.cloud.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * poi导入导出工具类
 * @author lijun
 */
public class PoiHelper {
    private static final Logger logger = LoggerFactory.getLogger(PoiHelper.class);

    /**
     * 得到workbook
     * @param file
     */
    public Workbook getWorkBook(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith(".xls")) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(".xlsx")) {
                // 2007 及2007以上
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        return workbook;
    }


    /**
     * 解析导入文件,生成列表
     * @param file
     * @return
     */
    public List<?> parseTable(Class classz, MultipartFile file, Integer sheetIndex, Integer rowStart) throws Exception {
        List<Object> list = new ArrayList<>();
        Workbook workBook = getWorkBook(file);
        Sheet sheet = workBook.getSheetAt(sheetIndex);
        Integer rowEnd = sheet.getLastRowNum();
        Map<Integer, Field> cellIndexFieldMap = ExcelUtil.parseColField(classz, OptTypeEnum.IMPORT_ANNOTATION.getCode());
        for(int i=rowStart;i<=rowEnd;i++){
            Object o = classz.newInstance();
            ExcelUtil.setRowObj(o,sheet.getRow(i),cellIndexFieldMap);
            list.add(o);
        }
        return list;
    }

}
