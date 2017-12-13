package cn.com.gome.generator.service;

import com.gomeplus.jdbc.page.PageQueryEntitys;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 * @author wangheming:
 * @date 2017年6月15日 下午3:39:57
 */
public interface ImportDocService {
	PageQueryEntitys importExcelDoc(MultipartFile file) throws IOException;

}
