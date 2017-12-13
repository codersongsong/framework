package cn.com.gome.generator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * <Description> </Description>
 * <ClassName> TestPoi</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年11月29日 10:44
 */
public class TestPoi {
    private static Logger logger = LoggerFactory.getLogger(TestPoi.class);

    @Test
    public void testExcelParser() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("F:\\svn_code\\framework\\branches\\framework_0.0.1_BR\\framework-generator\\src\\test\\resources\\保险_PC接口-V1.0-20170818.xlsx");
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

            Iterator<Sheet> sheetIterator = xssfWorkbook.sheetIterator();
            while (sheetIterator != null && sheetIterator.hasNext()) {
                XSSFSheet sheet = (XSSFSheet) sheetIterator.next();
                logger.info("开始处理sheet页：{}", sheet.getSheetName());
                if ("接口总览".equals(sheet.getSheetName())) {
                    logger.info("接口总览页：{}", sheet.getSheetName());
                    int firstRowNum = sheet.getFirstRowNum();
                    int lastRowNum = sheet.getLastRowNum();
                    XSSFRow row;
                    for (int i = firstRowNum; i <= lastRowNum; i++) {
                        row = sheet.getRow(i);
                        short firstCellNum = row.getFirstCellNum();
                        short lastCellNum = row.getLastCellNum();
                        StringBuilder sb = new StringBuilder();
                        for (int j = firstCellNum; j < lastCellNum; j++) {
                            XSSFCell cell = row.getCell(j);
                            if (j == lastCellNum) {
                                sb.append(getValue(cell));
                            } else {
                                sb.append(getValue(cell)).append("|");
                            }
                        }
                        logger.info(sb.toString());
                    }
                } else {

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
