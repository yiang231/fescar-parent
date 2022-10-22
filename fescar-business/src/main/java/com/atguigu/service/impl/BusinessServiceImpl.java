package com.atguigu.service.impl;

import com.atguigu.dao.LogInfoMapper;
import com.atguigu.feign.OrderInfoFeign;
import com.atguigu.feign.UserInfoFeign;
import com.atguigu.pojo.LogInfo;
import com.atguigu.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private OrderInfoFeign orderInfoFeign;

	@Autowired
	private UserInfoFeign userInfoFeign;

	@Resource
	private LogInfoMapper logInfoMapper;

	/***
	 * ①
	 * 下单
	 * @GlobalTransactional:全局事务入口
	 * @param username
	 * @param id
	 * @param count
	 */
	@GlobalTransactional
	@Override
	public void add(String username, int id, int count) {
		//添加订单日志
		LogInfo logInfo = new LogInfo();
		logInfo.setContent("添加订单数据---" + new Date());
		logInfo.setCreatetime(new Date());
		//insert 不会判断是否有null值，有属性是null，就写入null
		//insert into t_user values(5,"zhangsan",null,null,null)
		//insertSelective 判断是否有null值，有属性是null，不写该字段，写入的就是默认值 支持动态SQL
		//insert into t_user (id,name)values(5,"zhangsan")
		//logInfoMapper.insert();
		int logcount = logInfoMapper.insertSelective(logInfo);
		//logInfoMapper.updateByPrimaryKeySelective()
		System.out.println("添加日志受影响行数：" + logcount);

		//添加订单
		orderInfoFeign.add(username, id, count);

		int price = 10;

		//用户账户余额递减
		userInfoFeign.decrMoney(username, count * price);
	}
}
