package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblDubboClassCreateConfig;
import cn.com.gome.framework.dao.entity.TblInterfaceInfo;
import cn.com.gome.framework.dao.mapper.edit.TblDubboClassCreateConfigEditMapper;
import cn.com.gome.framework.dao.mapper.edit.TblInterfaceInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblDubboClassCreateConfigSerMapper;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @className: ImportExcelLogic
 * @description: (dubbo接口文件生成业务实现)
 * @author: songzhengjie
 * @date: 2017/12/5 11:22:53
 */
@Transactional
@Service
public class ImportExcelLogic implements ILogics<HashMap<String, Object>> {

    private Logger logger = LoggerFactory.getLogger("ImportExcelLogic");

    private static final String REQUEST = "request";
    private static final String RESPONSE = "response";

    @Autowired
    private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;

    @Autowired
    private TblDubboClassCreateConfigEditMapper tblDubboClassCreateConfigEditMapper;

    @Autowired
    private TblInterfaceInfoEditMapper tblInterfaceInfoEditMapper;


    @Override
    public ResultEnum exec(HashMap<String, Object> paramMap) throws LogicsException {


        String childProjectCode = MapUtils.getString(paramMap, "childProjectCode");
        InputStream fileStream = (InputStream) MapUtils.getObject(paramMap, "fileStream");
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fileStream);
            XSSFSheet sheet = null;
            //遍历sheet页 从第二页开始
            for (int i = 1; i < workbook.getNumberOfSheets(); i++) {
                sheet = workbook.getSheetAt(i);
                boolean isOk = convertSheet(sheet, childProjectCode);
                if (isOk) {
                    logger.info("接口sheet页{}读取完成", sheet.getSheetName());
                } else {
                    logger.info("接口sheet页{}读取失败", sheet.getSheetName());
                    return ResultEnum.PART_CASE_01;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Excel读取结束");
        return ResultEnum.OK;
    }


    public boolean convertSheet(XSSFSheet sheet,String childProjectCode) throws LogicsException {
        if (sheet == null) {
            logger.info("excel表格为null");
            return false;
        }

        String sheetName = sheet.getSheetName();
        TblDubboClassCreateConfig dubboClass = new TblDubboClassCreateConfig();
        TblInterfaceInfo interfaceInfo = null;
        Integer classNo = null;
        int rowNum = -1;
        //sheet页 行循环
        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
            XSSFRow row = sheet.getRow(j);
            if (row == null) {
                logger.info("接口sheet页{}第{}行为空", sheetName, j + 1);
                continue;
            }

            interfaceInfo = new TblInterfaceInfo();
            //sheet页  列循环
            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                XSSFCell cell = row.getCell(k);
                if (cell == null) {
                    logger.info("接口sheet页{}第{}行第{}列为空", sheetName, j + 1, k + 1);
                    continue;
                }
                String cellName = cell.toString();
                //1.采集dubbo接口名信息
                if (j == 1 && k == 2) {//name
                    dubboClass.setDubboClassAsk(cellName);
                }
                if (j == 4 && k == 2) {//name
                    dubboClass.setDubboClassMethodAsk(cellName+"-"+dubboClass.getDubboClassAsk());
                    dubboClass.setServiceCalssMethodAsk(cellName+"-"+dubboClass.getDubboClassAsk());
                }
                if (j == 6 && k == 2) {//className
                    dubboClass.setDubboClass(cellName);
                    String shortName = cellName.substring(0, cellName.length() - 13);
                    dubboClass.setServiceCalss(shortName + "Service");
                    dubboClass.setServiceCalssAsk(shortName + "Service");
                    dubboClass.setLogicCalss(shortName + "Logic");
                    dubboClass.setLogicCalssAsk(shortName + "Logic");
                }
                if (j == 7 && k == 2) {//methodName
                    dubboClass.setDubboClassMethod(cellName);
                    dubboClass.setServiceCalssMethod(cellName);
                    dubboClass.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                    dubboClass.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                    dubboClass.setChildProjectCode(Integer.valueOf(childProjectCode));
                    dubboClass.setCreateType("010");

                    boolean bool = tblDubboClassCreateConfigEditMapper.save(dubboClass);
                    if (bool) {
                        logger.info("接口sheet页{}主体信息入库成功", sheetName);
                        classNo = dubboClass.getClassNo();
                    } else {
                        logger.info("接口sheet页{}主体信息入库失败", sheetName);
                        return false;
                    }
                }
                //2.收集接口字段信息
                if ("request".equals(cellName)) {
                    rowNum = j+2;//request之后第二行为接口字段信息
                }
                if (rowNum == -1 ) {
                    continue;//读到request所在行之后rowNum重新赋值
                }
                if (j >= rowNum && k == 2) {//request or response
                    interfaceInfo.setRequestType(REQUEST);
                }
                if (j >= rowNum && k == 3) {//字段名称
                    if (StringUtils.isEmpty(cellName)) {
                        return true;//该列数据为空时说明sheet页参数字段已读取完毕
                    }
                    interfaceInfo.setParamnameCn(cellName);
                }
                if (j >= rowNum && k == 4) {//可否为空
                    interfaceInfo.setIsRequire(cellName);
                }
                if (j >= rowNum && k == 5) {//是否打印日志
                    interfaceInfo.setIsPrint(cellName);
                }
                if (j >= rowNum && k == 6) {//字段名
                    interfaceInfo.setParamname(cellName);
                }
                if (j >= rowNum && k == 7) {//类型
                    interfaceInfo.setParamtype(cellName);
                    if (row.getCell(k + 1)!=null) {
                        interfaceInfo.setDescription(row.getCell(k + 1).toString());//说明
                    }
                    if (row.getCell(k + 2)!=null) {
                        interfaceInfo.setIsLength(row.getCell(k + 2).toString());//是否判断长度
                    }
                    if (row.getCell(k + 3)!=null) {
                        interfaceInfo.setRegex(row.getCell(k + 3).toString());//是否判断格式
                    }
                    interfaceInfo.setDubboId(String.valueOf(classNo));
                    interfaceInfo.setClassName(dubboClass.getDubboClass());
                    interfaceInfo.setUpdateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                    interfaceInfo.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                    boolean interfaceSave = tblInterfaceInfoEditMapper.save(interfaceInfo);
                    if (interfaceSave) {
                        continue;
                    } else {
                        logger.info("接口字段信息插入出错:{}", interfaceInfo.getClassName());
                        return false;
                    }
                }

            }
        }

        return true;
    }
}
