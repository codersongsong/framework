/**   
 * @Title: ProjectCreateService.java 
 * @Package cn.com.gome.generator.service 
 * @Description: 项目创建服务接口
 * @author GOME
 * @date 2017年5月26日 下午2:38:12 
 * @company cn.com.gome
 * @version V1.0   
 */

package cn.com.gome.generator.service;

/**
 * @ClassName: ProjectCreateService
 * @Description: 项目创建服务接口
 * @author GOME
 * @date 2017年5月26日 下午2:38:12
 */
public interface ProjectCreateService {

	/**
	 * @Title: mainProjectCreate
	 * @Description: 主项目创建
	 * @param id 主项目ID
	 * @return boolean 返回类型
	 */
	boolean mainProjectCreate(String from, String id);

	/**
	 * @Title: childProjectCreate
	 * @Description: 子项目创建
	 * @param id 子项目ID
	 * @return boolean 返回类型
	 */
	boolean childProjectCreate(String id);

}
