/**
* @Title: TableDaoInfoPo.java
* @Package com.gome.framework.po
* @Description: 表持久化信息表实体类
* @author renhanxiang
* @date Fri May 05 14:26:22 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: TableDaoInfoPo
 * @Description: 表持久化信息表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_TABLE_DAO_INFO
 */
@SuppressWarnings("unused")
public class TableDaoInfoPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**表DAO序号:TABLE_NUMBERS*/
	private Integer tableNumbers;

	/**表DAO序号:TABLE_NUMBERS*/
	private String tableNumbers_colmun;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private Integer childProjectCode;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private String childProjectCode_colmun;

	/**表中文名:TABLE_CHAN*/
	private String tableChan;

	/**表中文名:TABLE_CHAN*/
	private String tableChan_colmun;

	/**表英文名:TABLE_ENG*/
	private String tableEng;

	/**表英文名:TABLE_ENG*/
	private String tableEng_colmun;

	/**显示字段:SHOW_COLUMNS*/
	private String showColumns;

	/**显示字段:SHOW_COLUMNS*/
	private String showColumns_colmun;

	/**查询字段:QUERY_COLUMNS*/
	private String queryColumns;

	/**查询字段:QUERY_COLUMNS*/
	private String queryColumns_colmun;

	/**添加字段:ADD_COLUMNS*/
	private String addColumns;

	/**添加字段:ADD_COLUMNS*/
	private String addColumns_colmun;

	/**页面元素:PAGE_FORM*/
	private String pageForm;

	/**页面元素:PAGE_FORM*/
	private String pageForm_colmun;

	/**修改字段:EDIT_COLUMNS*/
	private String editColumns;

	/**修改字段:EDIT_COLUMNS*/
	private String editColumns_colmun;

	/**浏览字段:VIEW_COLUMNS*/
	private String viewColumns;

	/**浏览字段:VIEW_COLUMNS*/
	private String viewColumns_colmun;

	/**字段类型:COLUMN_TYPE*/
	private String columnType;

	/**字段类型:COLUMN_TYPE*/
	private String columnType_colmun;

	/**主键字段:KEY_TEXT*/
	private String keyText;

	/**主键字段:KEY_TEXT*/
	private String keyText_colmun;

	/**字段注释:COLUMNS_NAME*/
	private String columnsName;

	/**字段注释:COLUMNS_NAME*/
	private String columnsName_colmun;

	/**添加时间:CREATE_TIME*/
	private String createTime;

	/**添加时间:CREATE_TIME*/
	private String createTime_colmun;
	
	/**校验类型:VALIDATE_NAME*/
	private String validateName;
	
	/**增加字典:ADD_DIC*/
	private String addDic;
	
	/**增加字典:ADD_DIC*/
	private String addDic_colmun;
	
	/**创建文件:CREATE_FILES*/
	private String createFiles;
	
	/**创建状态:CREATE_STATUS*/
	private String createStatus;
	
	/**创建文件:CREATE_FILES*/
	private String createFiles_colmun;
	
	/**创建状态:CREATE_STATUS*/
	private String createStatus_colmun;
	
	public String getCreateFiles_colmun() {
		return createFiles_colmun;
	}

	public String getCreateStatus_colmun() {
		if("010".equals(createStatus)){
			return "持久层";
		}else if("020".equals(createStatus)){
			return "前端WEB";
		}else if("030".equals(createStatus)){
			return "页面层";
		}
		return createStatus;
	}

	/**创建文件:CREATE_FILES*/
	public String getCreateFiles() {
		return createFiles;
	}

	/**创建文件:CREATE_FILES*/
	public void setCreateFiles(String createFiles) {
		this.createFiles = createFiles;
	}

	/**创建状态:CREATE_STATUS*/
	public String getCreateStatus() {
		return createStatus;
	}

	/**创建状态:CREATE_STATUS*/
	public void setCreateStatus(String createStatus) {
		this.createStatus = createStatus;
	}
	
	public String getValidateName() {
		return validateName;
	}

	public void setValidateName(String validateName) {
		this.validateName = validateName;
	}

	public String getAddDic() {
		return addDic;
	}

	public void setAddDic(String addDic) {
		this.addDic = addDic;
	}

	public String getAddDic_colmun() {
		return addDic_colmun;
	}

	/**表DAO序号:TABLE_NUMBERS*/
	public Integer getTableNumbers() {
		return tableNumbers;
	}

	/**表DAO序号:TABLE_NUMBERS*/
	public String getTableNumbers_colmun() {
		String temp = "";
		if(null != tableNumbers){
			temp = String.valueOf(tableNumbers);
		}
		return temp;
	}

	/**表DAO序号:TABLE_NUMBERS*/
	public void setTableNumbers(Integer tableNumbers) {
		this.tableNumbers = tableNumbers;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public Integer getChildProjectCode() {
		return childProjectCode;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public String getChildProjectCode_colmun() {
		String temp = "";
		if(null != childProjectCode){
			temp = GlobalApplicationCache.getInstance().getStr("CHILD_PROJECT_CODE." + childProjectCode);
		}
		return temp;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public void setChildProjectCode(Integer childProjectCode) {
		this.childProjectCode = childProjectCode;
	}

	/**表中文名:TABLE_CHAN*/
	public String getTableChan() {
		return tableChan;
	}

	/**表中文名:TABLE_CHAN*/
	public String getTableChan_colmun() {
		return tableChan;
	}

	/**表中文名:TABLE_CHAN*/
	public void setTableChan(String tableChan) {
		this.tableChan = tableChan;
	}

	/**表英文名:TABLE_ENG*/
	public String getTableEng() {
		return tableEng;
	}

	/**表英文名:TABLE_ENG*/
	public String getTableEng_colmun() {
		return tableEng;
	}

	/**表英文名:TABLE_ENG*/
	public void setTableEng(String tableEng) {
		this.tableEng = tableEng;
	}

	/**显示字段:SHOW_COLUMNS*/
	public String getShowColumns() {
		return showColumns;
	}

	/**显示字段:SHOW_COLUMNS*/
	public String getShowColumns_colmun() {
		return showColumns;
	}

	/**显示字段:SHOW_COLUMNS*/
	public void setShowColumns(String showColumns) {
		this.showColumns = showColumns;
	}

	/**查询字段:QUERY_COLUMNS*/
	public String getQueryColumns() {
		return queryColumns;
	}

	/**查询字段:QUERY_COLUMNS*/
	public String getQueryColumns_colmun() {
		return queryColumns;
	}

	/**查询字段:QUERY_COLUMNS*/
	public void setQueryColumns(String queryColumns) {
		this.queryColumns = queryColumns;
	}

	/**添加字段:ADD_COLUMNS*/
	public String getAddColumns() {
		return addColumns;
	}

	/**添加字段:ADD_COLUMNS*/
	public String getAddColumns_colmun() {
		return addColumns;
	}

	/**添加字段:ADD_COLUMNS*/
	public void setAddColumns(String addColumns) {
		this.addColumns = addColumns;
	}

	/**页面元素:PAGE_FORM*/
	public String getPageForm() {
		return pageForm;
	}

	/**页面元素:PAGE_FORM*/
	public String getPageForm_colmun() {
		return pageForm;
	}

	/**页面元素:PAGE_FORM*/
	public void setPageForm(String pageForm) {
		this.pageForm = pageForm;
	}

	/**修改字段:EDIT_COLUMNS*/
	public String getEditColumns() {
		return editColumns;
	}

	/**修改字段:EDIT_COLUMNS*/
	public String getEditColumns_colmun() {
		return editColumns;
	}

	/**修改字段:EDIT_COLUMNS*/
	public void setEditColumns(String editColumns) {
		this.editColumns = editColumns;
	}

	/**浏览字段:VIEW_COLUMNS*/
	public String getViewColumns() {
		return viewColumns;
	}

	/**浏览字段:VIEW_COLUMNS*/
	public String getViewColumns_colmun() {
		return viewColumns;
	}

	/**浏览字段:VIEW_COLUMNS*/
	public void setViewColumns(String viewColumns) {
		this.viewColumns = viewColumns;
	}

	/**字段类型:COLUMN_TYPE*/
	public String getColumnType() {
		return columnType;
	}

	/**字段类型:COLUMN_TYPE*/
	public String getColumnType_colmun() {
		return columnType;
	}

	/**字段类型:COLUMN_TYPE*/
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	/**主键字段:KEY_TEXT*/
	public String getKeyText() {
		return keyText;
	}

	/**主键字段:KEY_TEXT*/
	public String getKeyText_colmun() {
		return keyText;
	}

	/**主键字段:KEY_TEXT*/
	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}

	/**字段注释:COLUMNS_NAME*/
	public String getColumnsName() {
		return columnsName;
	}

	/**字段注释:COLUMNS_NAME*/
	public String getColumnsName_colmun() {
		return columnsName;
	}

	/**字段注释:COLUMNS_NAME*/
	public void setColumnsName(String columnsName) {
		this.columnsName = columnsName;
	}

	/**添加时间:CREATE_TIME*/
	public String getCreateTime() {
		return createTime;
	}

	/**添加时间:CREATE_TIME*/
	public String getCreateTime_colmun() {
		return createTime;
	}

	/**添加时间:CREATE_TIME*/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
