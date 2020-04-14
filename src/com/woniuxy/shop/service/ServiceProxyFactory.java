package com.woniuxy.shop.service;

import java.lang.reflect.Method;
import java.sql.Connection;

import com.woniuxy.shop.dao.DbHelper;
import com.woniuxy.shop.exception.ServiceException;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理工具类，用于提供对业务逻辑对象的代理，提供事务控制
 * 
 * @author 谭岚 (<a href="http://www.woniuxy.com">蜗牛学院</a>)
 */
@SuppressWarnings("all")
public class ServiceProxyFactory {
	public static <T> T getProxy(Class<T> c) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(c);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = null;
				Connection connection = null;
				try {
					connection = DbHelper.getConnection();
					connection.setAutoCommit(false);// 设置事务不自动提交
					result = proxy.invokeSuper(obj, args);// 调用目标对象的方法
					connection.commit(); // 事务提交
				} catch (Exception e) {
//					e.printStackTrace();
					if(connection!=null) {
						connection.rollback(); // 事务回滚
					}
					throw new ServiceException(e);// 一定要加上，为了给表现层处理各种异常
				} finally {
					DbHelper.closeConnection(connection);
				}
				return result;
			}
		});
		return (T) enhancer.create();
	}
}
