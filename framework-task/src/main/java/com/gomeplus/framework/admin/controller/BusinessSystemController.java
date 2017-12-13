/**
 * @Title: BusinessSystemContorller.java
 * @Package com.gome.login.controller
 * @Description: 业务系统表的控制器
 * @author chenmin-ds
 * @date Fri Jan 06 10:14:44 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package com.gomeplus.framework.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gome.framework.dao.entity.TblBusinessSystem;
import cn.com.gome.framework.dao.entity.TblLoginSysDic;
import cn.com.gome.framework.dao.mapper.ser.TblBusinessSystemSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblLoginSysDicSerMapper;

import com.gomeplus.frame.cache.DictionaryVo;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.frame.cache.GlobalDataDictionaryCache;
import com.gomeplus.frame.controller.AbstractAdminController;

/**
 * @ClassName: BusinessSystemContorller
 * @Description: 业务系统表的控制器
 * @author chenmin-ds
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/businesssystem")
public class BusinessSystemController extends AbstractAdminController  implements InitializingBean {

	@Autowired
	private TblBusinessSystemSerMapper tblBusinessSystemSerMapper;
	
	@Autowired
	private TblLoginSysDicSerMapper tblLoginSysDicSerMapper;


	ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
		public Thread newThread(Runnable r) {
			return new Thread(r, "businesssystemRefresh"  );
		}
	});

	private synchronized void inits(){
		try {
			List<TblBusinessSystem> list = tblBusinessSystemSerMapper.queryList(new TblBusinessSystem());
			if (list != null) {
				List<DictionaryVo> ivList = new ArrayList<DictionaryVo>();
				DictionaryVo idval = null;
				for (TblBusinessSystem tblBusinessSystem : list) {
					GlobalApplicationCache.getInstance().put("SYS_NO." + tblBusinessSystem.getSysNo(),
							tblBusinessSystem.getSysName());
					idval = new DictionaryVo(tblBusinessSystem.getSysNo()+"", tblBusinessSystem.getSysName(), false);
					ivList.add(idval);
				}
				GlobalDataDictionaryCache.getInstance().putIdValue("SYS_NO", ivList);
			} else {
				logger.info("温馨提示：没有查询到系统注册信息的记录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void initsTblLoginSysDic(){
		try {
			TblLoginSysDic obj = new TblLoginSysDic();
			List<TblLoginSysDic> list = tblLoginSysDicSerMapper.queryList(obj);
			if (list != null) {
				List<DictionaryVo> ivList = null;
				Map<String, List<DictionaryVo>> ivMap = new HashMap<String, List<DictionaryVo>>();
				DictionaryVo idval = null;
				for (TblLoginSysDic tblLoginSysDic : list) {
					GlobalApplicationCache.getInstance().put(tblLoginSysDic.getL2Code() + "." + tblLoginSysDic.getCodeParam(),
							tblLoginSysDic.getCodeValue());
					ivList = ivMap.get(tblLoginSysDic.getL2Code());
					idval = new DictionaryVo(tblLoginSysDic.getCodeParam(), tblLoginSysDic.getCodeValue(), false);
					if (ivList == null) {
						ivList = new ArrayList<DictionaryVo>();
						ivList.add(idval);
						ivMap.put(tblLoginSysDic.getL2Code(), ivList);
					} else {
						ivList.add(idval);
					}
				}
				Iterator iterator = ivMap.entrySet().iterator();
				Entry<String, List<DictionaryVo>> entry = null;
				while (iterator.hasNext()) {
					entry = (Entry<String, List<DictionaryVo>>) iterator.next();
					GlobalDataDictionaryCache.getInstance().putIdValue(entry.getKey(), entry.getValue());
				}
				logger.info("查询到数据字典的记录数：" + ivMap.size());
			} else {
				logger.info("没有查询到数据字典的记录");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		scheduledExecutorService.scheduleAtFixedRate(new RefreshTask(), 30 , 30 , TimeUnit.SECONDS );
	}

	class  RefreshTask implements Runnable{

		@Override
		public void run() {
			inits();
			initsTblLoginSysDic();
		}
	}
}
