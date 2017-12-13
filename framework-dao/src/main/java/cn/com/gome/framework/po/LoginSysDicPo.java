/**
* @Title: LoginSysDicPo.java
* @Package com.gome.login.po
* @Description: 统一登录数据字典表实体类
* @author renhanxiang
* @date Fri Jan 13 18:29:54 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: LoginSysDicPo
 * @Description: 统一登录数据字典表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_LOGIN_SYS_DIC
 */
@SuppressWarnings("unused")
public class LoginSysDicPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**主键ID:ID*/
	private String id;

	/**主键ID:ID*/
	private String id_colmun;

	/**二级字典码:L2_CODE*/
	private String l2Code;

	/**二级字典码:L2_CODE*/
	private String l2Code_colmun;

	/**二级字典描述:L2_DESC*/
	private String l2Desc;

	/**二级字典描述:L2_DESC*/
	private String l2Desc_colmun;

	/**码表参数:CODE_PARAM*/
	private String codeParam;

	/**码表参数:CODE_PARAM*/
	private String codeParam_colmun;

	/**实际值:CODE_VALUE*/
	private String codeValue;

	/**实际值:CODE_VALUE*/
	private String codeValue_colmun;

	/**排序:CODE_IDX*/
	private Integer codeIdx;

	/**排序:CODE_IDX*/
	private String codeIdx_colmun;

	/**记录创建时间:RECORD_CREATE_TM*/
	private String recordCreateTm;

	/**记录创建时间:RECORD_CREATE_TM*/
	private String recordCreateTm_colmun;

	/**主键ID:ID*/
	public String getId() {
		return id;
	}

	/**主键ID:ID*/
	public String getId_colmun() {
		return id;
	}

	/**主键ID:ID*/
	public void setId(String id) {
		this.id = id;
	}

	/**二级字典码:L2_CODE*/
	public String getL2Code() {
		return l2Code;
	}

	/**二级字典码:L2_CODE*/
	public String getL2Code_colmun() {
		return l2Code;
	}

	/**二级字典码:L2_CODE*/
	public void setL2Code(String l2Code) {
		this.l2Code = l2Code;
	}

	/**二级字典描述:L2_DESC*/
	public String getL2Desc() {
		return l2Desc;
	}

	/**二级字典描述:L2_DESC*/
	public String getL2Desc_colmun() {
		return l2Desc;
	}

	/**二级字典描述:L2_DESC*/
	public void setL2Desc(String l2Desc) {
		this.l2Desc = l2Desc;
	}

	/**码表参数:CODE_PARAM*/
	public String getCodeParam() {
		return codeParam;
	}

	/**码表参数:CODE_PARAM*/
	public String getCodeParam_colmun() {
		return codeParam;
	}

	/**码表参数:CODE_PARAM*/
	public void setCodeParam(String codeParam) {
		this.codeParam = codeParam;
	}

	/**实际值:CODE_VALUE*/
	public String getCodeValue() {
		return codeValue;
	}

	/**实际值:CODE_VALUE*/
	public String getCodeValue_colmun() {
		return codeValue;
	}

	/**实际值:CODE_VALUE*/
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	/**排序:CODE_IDX*/
	public Integer getCodeIdx() {
		return codeIdx;
	}

	/**排序:CODE_IDX*/
	public String getCodeIdx_colmun() {
		String temp = "";
		if(null != codeIdx){
			temp = String.valueOf(codeIdx);
		}
		return temp;
	}

	/**排序:CODE_IDX*/
	public void setCodeIdx(Integer codeIdx) {
		this.codeIdx = codeIdx;
	}

	/**记录创建时间:RECORD_CREATE_TM*/
	public String getRecordCreateTm() {
		return recordCreateTm;
	}

	/**记录创建时间:RECORD_CREATE_TM*/
	public String getRecordCreateTm_colmun() {
		return recordCreateTm;
	}

	/**记录创建时间:RECORD_CREATE_TM*/
	public void setRecordCreateTm(String recordCreateTm) {
		this.recordCreateTm = recordCreateTm;
	}

}
