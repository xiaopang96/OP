package net.wanho.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import net.wanho.exception.DaoException;

/**
 * 
 * @包名:			[ net.wanho.util ]  
 * @类名:			[ JdbcUtils ]  
 * @描述:			[ 数据操作工具类 ] 
 */
public class JdbcUtils {

	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	private static Properties p;
	private static DataSource ds;
	static {
		try {
			p = new Properties();
			p.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			//创建连接池
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			throw new DaoException("数据配置文件异常", e);
		}
	}

	/**
	 * 
	 * @方法名:		[ getConnection ]		
	 * @返回类型:		[ Connection ]
	 * @功能描述:     	[ 获取数据库连接 ]
	 */
	public static Connection getConnection() {
		try {
			// 先从本地线程获取
			Connection con = threadLocal.get();
			// 不存在，
			if (con == null) {
				// 则创建一个新的连接
				// con = DriverManager.getConnection(p.getProperty("url"),
				// p.getProperty("username"), p.getProperty("password"));
				// 从连接池中获取连接
				con = ds.getConnection();
				// 把连接放置到本地线程中
				threadLocal.set(con);
			}
			return con;
		} catch (Exception e) {
			throw new DaoException("数据连接异常", e);
		}
	}

	/**
	 * 
	 * @方法名:		[ release ]		
	 * @返回类型:		[ void ]
	 * @功能描述:     	[ 释放资源 ]
	 */
	public static void release(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (st != null) {
				st.close();
				st = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			// 如果未用到事务，自己关闭
			if (con != null && con.getAutoCommit()) {
				con.close();
				// 从本地线程中移除
				threadLocal.remove();
				con = null;
			}
			// 如果用到事务，事务关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @方法名:		[ releaseCon ]		
	 * @返回类型:		[ void ]
	 * @功能描述:     	[ 释放con资源 ]
	 */
	public static void releaseCon(Connection con) {
		try {
			// 如果未用到事务，自己关闭
			if (con != null && con.getAutoCommit()) {
				con.close();
				// 从本地线程中移除
				threadLocal.remove();
				con = null;
			}
			// 如果用到事务，事务关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
