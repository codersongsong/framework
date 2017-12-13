package cn.com.gome.generator.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @className: ImportExcelService
 * @description: (接口生成 读取excel文件)
 * @author: songzhengjie
 * @date: 2017/12/5 11:16:36
 */
public interface ImportExcelService {

    /**
     *  读取文件服务
     * @return
     */
    boolean importService(HashMap<String,Object> paramMap) throws IOException, Exception;
}
