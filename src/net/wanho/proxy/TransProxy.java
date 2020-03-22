package net.wanho.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.wanho.factory.ObjectFactory;
import net.wanho.trans.TransMgrI;

/**
 * 
 *  功能描述 :事务代理 
 */
public class TransProxy implements InvocationHandler {
	private Object target;

	/**
	 * 得到代理类
	 * 
	 * @param target
	 * @return
	 */
	public Object createProxy(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		TransMgrI transMgr = null;
		Object res = null;
		String methodName = method.getName(); // insert add delete remove update
		// 增、删、改增加事务
		if (methodName.startsWith("insert") || methodName.startsWith("save") || methodName.startsWith("delete")
				|| methodName.startsWith("remove") || methodName.startsWith("update")) {
			try {
				// 事务管理
				transMgr = (TransMgrI) ObjectFactory.getObject("transMgr");
				// 开启事务
				transMgr.beginTrans();
				// 核心业务
				res = method.invoke(target.getClass().newInstance(), args);
				// 提交事务
				transMgr.commitTrans();
			} catch (Exception e) {
				if (transMgr != null)
					transMgr.rollbackTrans();
				throw e.getCause();
			}
		} else {
			try {
				// 核心业务
				res = method.invoke(target.getClass().newInstance(), args);
			} catch (Exception e) {
				// 抛出核心业务的真实异常
				throw e.getCause();
			}
		}

		return res;
	}

}
