package cn.com.gome.generator.service.impl;

import cn.com.gome.generator.service.ImportExcelService;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: ImportExcelServiceImpl
 * @description: (生成接口文件 服务类)
 * @author: songzhengjie
 * @date: 2017/12/5 11:19:10
 */

@Service
public class ImportExcelServiceImpl implements ImportExcelService{

    private Logger logger = LoggerFactory.getLogger("ImportExcelServiceImpl");

    @Resource
    private ILogics<HashMap<String,Object>> importExcelLogic;

    /**
     * 读取文件服务
     *
     * @param inputStream
     * @return
     */
    @Override
    public boolean importService(HashMap<String,Object> paramMap) throws Exception {

        if (ResultEnum.OK==importExcelLogic.exec(paramMap)) {
            logger.info("导入文件成功");
            return true;
        }

        logger.info("导入文件失败");
        return false;
    }
}
