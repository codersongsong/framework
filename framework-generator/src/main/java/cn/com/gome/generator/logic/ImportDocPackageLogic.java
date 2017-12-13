package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblDubboClassCreateConfig;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 导入的Excel的参数封装
 * Created by wangheming on 2017/6/28.
 */
@Service
public class ImportDocPackageLogic implements ILogics<Map<String,Object>> {

    private Logger logger =  LoggerFactory.getLogger("ImportDocPackageLogic");

    @Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {

        String excelList = map.get("excelList")==null?"":(String)map.get("excelList");
        PageQueryEntitys entitys = (PageQueryEntitys)map.get("entitys");
        List listEntitys = new ArrayList<TblDubboClassCreateConfig>();
        String[] split = excelList.split(",,");
        for (String string : split) {
            if(StringUtils.isEmpty(string)) {
                continue;
            }
            TblDubboClassCreateConfig config = new TblDubboClassCreateConfig();
            String[] split2 = string.split(",");
            if (split2.length != 13) {
                logger.info("读取excel文件出现错误，请检查Excel文件是否符合要求");
                entitys.addF("errorInfo","读取excel文件出现错误，请检查Excel文件是否符合要求");
                return ResultEnum.PART_CASE_01;
            }else{
                //这个取出来是10
                //对各个字段进行非空判断，如果Excel为空取什么值？
                config.setChildProjectCode(Integer.parseInt(split2[0]));
                config.setCreateType(split2[1]);
                config.setDubboClass(split2[2]);
                config.setDubboClassAsk(split2[3]);
                config.setDubboClassMethod(split2[4]);
                config.setDubboClassMethodAsk(split2[5]);
                config.setServiceCalss(split2[6]);
                config.setServiceCalssAsk(split2[7]);
                config.setServiceCalssMethod(split2[8]);
                config.setServiceCalssMethodAsk(split2[9]);
                config.setLogicCalss(split2[10]);
                config.setLogicCalssAsk(split2[11]);
                config.setSaveAddress(split2[12]);
            }
            listEntitys.add(config);
        }
        entitys.setList(listEntitys);
        return ResultEnum.OK;
    }
}
