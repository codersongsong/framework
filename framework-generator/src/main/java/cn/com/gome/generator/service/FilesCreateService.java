/**   
 * @Title: FilesCreateService.java 
 * @Package cn.com.gome.generator.service 
 * @Description: 持久层、控制器层、页面、定时任务代码生成
 * @author GOME
 * @date 2017年5月26日 下午2:38:12 
 * @company cn.com.gome
 * @version V1.0   
 */

package cn.com.gome.generator.service;

/**
 * @ClassName: FilesCreateService
 * @Description: 持久层、控制器层、页面、定时任务代码生成
 * @author GOME
 * @date 2017年5月26日 下午2:38:12
 */
public interface FilesCreateService {

	/**
	 * @Title: daoControllerPage
	 * @Description: 持久层代码生成
	 * @param id 表ID
	 * @return boolean 返回类型
	 */
	boolean daoControllerPage(String id,String flag);

	/**
	 * @Title: task
	 * @Description: 定时任务代码生成
	 * @param id 表ID
	 * @return boolean 返回类型
	 */
	boolean task(String id);
}
