/**
* @Title: BusinessSystemPo.java
* @Package com.gome.login.po
* @Description: 业务系统表实体类
* @author renhanxiang
* @date Fri Jan 06 10:14:43 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: BusinessSystemPo
 * @Description: 业务系统表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_BUSINESS_SYSTEM
 */
@SuppressWarnings("unused")
public class BusinessSystemPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**系统编码:SYS_NO*/
	private Integer sysNo;

	/**系统编码:SYS_NO*/
	private String sysNo_colmun;

	/**系统名称:SYS_NAME*/
	private String sysName;

	/**系统名称:SYS_NAME*/
	private String sysName_colmun;

	/**系统英文名:SYS_ENG*/
	private String sysEng;

	/**系统英文名:SYS_ENG*/
	private String sysEng_colmun;

	/**系统负责人:SYS_PERSON*/
	private String sysPerson;

	/**系统负责人:SYS_PERSON*/
	private String sysPerson_colmun;

	/**联系号码:PHONE*/
	private String phone;

	/**联系号码:PHONE*/
	private String phone_colmun;

	/**添加时间:CREATE_TIME*/
	private String createTime;

	/**添加时间:CREATE_TIME*/
	private String createTime_colmun;

	/**修改时间:UP_TIME*/
	private String upTime;

	/**修改时间:UP_TIME*/
	private String upTime_colmun;

	/**修改人:UP_PERSON*/
	private String upPerson;

	/**修改人:UP_PERSON*/
	private String upPerson_colmun;

	/**系统编码:SYS_NO*/
	public Integer getSysNo() {
		return sysNo;
	}

	/**系统编码:SYS_NO*/
	public String getSysNo_colmun() {
		String temp = "";
		if(null != sysNo){
			temp = String.valueOf(sysNo);
		}
		return temp;
	}

	/**系统编码:SYS_NO*/
	public void setSysNo(Integer sysNo) {
		this.sysNo = sysNo;
	}

	/**系统名称:SYS_NAME*/
	public String getSysName() {
		return sysName;
	}

	/**系统名称:SYS_NAME*/
	public String getSysName_colmun() {
		return sysName;
	}

	/**系统名称:SYS_NAME*/
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**系统英文名:SYS_ENG*/
	public String getSysEng() {
		return sysEng;
	}

	/**系统英文名:SYS_ENG*/
	public String getSysEng_colmun() {
		return sysEng;
	}

	/**系统英文名:SYS_ENG*/
	public void setSysEng(String sysEng) {
		this.sysEng = sysEng;
	}

	/**系统负责人:SYS_PERSON*/
	public String getSysPerson() {
		return sysPerson;
	}

	/**系统负责人:SYS_PERSON*/
	public String getSysPerson_colmun() {
		return sysPerson;
	}

	/**系统负责人:SYS_PERSON*/
	public void setSysPerson(String sysPerson) {
		this.sysPerson = sysPerson;
	}

	/**联系号码:PHONE*/
	public String getPhone() {
		return phone;
	}

	/**联系号码:PHONE*/
	public String getPhone_colmun() {
		return phone;
	}

	/**联系号码:PHONE*/
	public void setPhone(String phone) {
		this.phone = phone;
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

	/**修改时间:UP_TIME*/
	public String getUpTime() {
		return upTime;
	}

	/**修改时间:UP_TIME*/
	public String getUpTime_colmun() {
		return upTime;
	}

	/**修改时间:UP_TIME*/
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	/**修改人:UP_PERSON*/
	public String getUpPerson() {
		return upPerson;
	}

	/**修改人:UP_PERSON*/
	public String getUpPerson_colmun() {
		return upPerson;
	}

	/**修改人:UP_PERSON*/
	public void setUpPerson(String upPerson) {
		this.upPerson = upPerson;
	}

}
