package net.wanho.factory;

import java.io.IOException;
import java.util.Properties;

import net.wanho.exception.DaoException;
import net.wanho.proxy.TransProxy;

/**
 * 
 * @包名:			[ net.wanho.factory ]  
 * @类名:			[ ObjectFactory ]  
 * @描述:			[ 对象工厂 ] 
 */
public class ObjectFactory {

	private static Properties p;
	
	/**
	 * 加载所有对象
	 */
	static {
		 p = new Properties();
		try {
			p.load(ObjectFactory.class.getClassLoader().getResourceAsStream("obj.properties"));
		} catch (IOException e) {
			throw new DaoException("对象配置文件读取错误",e);
		}
	}
	
	/**
	 * @方法名:		[ getObject ]		
	 * @返回类型:		[ Object ]
	 * @功能描述:     	[ 根据名称获取对象 ]
	 */
	public static Object getObject(String name) {
		String className = p.get(name).toString();
		//如果业务层，通过代理返回
		if(name.endsWith("Service")) {
			try {
				TransProxy proxy = new TransProxy();
				Object target = Class.forName(className).newInstance();
				return proxy.createProxy(target );
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new DaoException("创建代理错误",e);
			}
		}else {
			//其它层直接返回,创建新的对象
			try {
				return  Class.forName(className).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new DaoException("读取对象错误",e);
			}
		}
	}
}
