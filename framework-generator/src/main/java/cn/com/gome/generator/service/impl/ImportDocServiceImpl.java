package cn.com.gome.generator.service.impl;

import cn.com.gome.generator.logic.ImportDocPackageLogic;
import cn.com.gome.generator.service.ImportDocService;
import cn.com.gome.generator.util.ExcelTools;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wangheming:
 * @date 2017年6月15日 下午3:41:35
 */
@Service
public class ImportDocServiceImpl implements ImportDocService {

	@Resource
	ImportDocPackageLogic importDocPackageLogic;
	
	private Logger logger = LoggerFactory.getLogger("ImportDocServiceImpl");

	@Override
    public PageQueryEntitys importExcelDoc(MultipartFile file) throws IOException {
		PageQueryEntitys entitys = new PageQueryEntitys();
		String excelList = readExcel(file);
		try {
			Map map = new HashMap<String,Object>();
			map.put("excelList",excelList);
			map.put("entitys",entitys);
			if ("PartOK".equals(importDocPackageLogic.exec(map))){
				logger.info("封装Excel文件成功+++++++++++++++++++++++++++ImportDocServiceImpl.importExcelDoc");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导入Excel文件异常++++++++++++++++++++++++++++++ImportDocServiceImpl.importExcelDoc");
			entitys.addF("errorInfo","导入Excel文件异常");
			return	entitys;
		}
		return entitys;
	}

	/**
	 * 读取Excel文件2003
	 * @param file
	 * @return
	 */
	private String readExcel(MultipartFile file) throws IOException {
		byte[] a =file.getBytes();
		InputStream is = new ByteArrayInputStream(a);
		List<List<String>> list = new ExcelTools().excelToList(is);
		StringBuffer sb = new StringBuffer();
		for (List<String> list2 : list) {
			for (String string : list2) {
				sb.append(string);
				sb.append(",");
			}
		}
		String excelList = sb.toString();
		return excelList;
	}

}
