package net.wanho.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import net.wanho.dao.BaseDaoI;
import net.wanho.exception.DaoException;
import net.wanho.util.JdbcUtils;

/**
 * 
 * @项目名:		[ cms ] 
 * @包名:			[ net.wanho.dao.impl ]  
 * @类名:			[ BaseDaoImpl ]  
 * @描述:			[ 数据访问层的基本类 ] 
 
 */
public class BaseDaoImpl<T> implements BaseDaoI<T>  {
	private Class clazz;
	public BaseDaoImpl(){
		//创建Class对象
		clazz = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	//psvm
	/*public static void main(String[] args) {

	}*/
	
	/**  
	 * @方法名:		[ execQueryObject ]
	 * @功能描述:		[ 查询首行首列 ]   
	 */
	
	public Object execQueryObject(String sql,Object...params) {
		QueryRunner qr = new QueryRunner();
		Connection con=null;
		try {
			con= JdbcUtils.getConnection();
			return qr.query(con, sql, new ScalarHandler(),params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			JdbcUtils.releaseCon(con);
		}
	}
	
	
	/**  
	 * @方法名:		[ execQueryOne ]
	 * @功能描述:		[ 查询一行记录  ]   
	 */
	
	public T execQueryOne(String sql, Object ...params) {
		QueryRunner qr = new QueryRunner();
		Connection con=null;
		try {
			con= JdbcUtils.getConnection();
			return qr.query(con, sql, new BeanHandler<T>(clazz),params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			JdbcUtils.releaseCon(con);
		}
	}
	
	
	/**  
	 * @方法名:		[ execQuery ]
	 * @功能描述:		[ 查询多条记录 ]   
	 */
	
	public List<T> execQuery(String sql, Object ...params) { 
		QueryRunner qr = new QueryRunner();
		Connection con=null;
		try {
			con= JdbcUtils.getConnection();
			return qr.query(con, sql, new BeanListHandler<T>(clazz),params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			JdbcUtils.releaseCon(con);
		}
		
	}

	
	/**  
	 * @方法名:		[ execUpdate ]
	 * @功能描述:		[ 增删改操作 ]   
	 */
	
	public int execUpdate(String sql, Object ...params) {
		QueryRunner qr = new QueryRunner();
		Connection con=null;
		try {
			con= JdbcUtils.getConnection();
			return qr.update(con, sql, params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			JdbcUtils.releaseCon(con);
		}
	}
	
	
	/**  
	 * @方法名:		[ execInsert ]
	 * @功能描述:		[ 增加时返回自动增长列的值 ]   
	 */
	
	public long execInsert(String sql, Object ...params) {
		Connection con = JdbcUtils.getConnection();
		// new String[] {"id"} 第二参数，表示要序列所在的列
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			//设置参数
			for(int i=0;i<params.length;i++){
				ps.setObject(i + 1, params[i]);
			}
			//先增加
			ps.executeUpdate();
			//再查询
			rs = ps.getGeneratedKeys ();  
			if(rs.next())
			    //获取序列的值
				return rs.getLong(1);
			throw new DaoException("增加返回序列出错");
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			JdbcUtils.release(rs, ps, con);
		}
	}
	
	/**
	 * 批处理
	 * 
	 * @throws SQLException
	 */
	@Override
	public int[] batch(String sql, Object[]... params) {
		Connection con = JdbcUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		try {
			return qr.batch(con, sql, params);
		} catch (Exception e) {
			throw new DaoException(e);
		}finally {
			JdbcUtils.releaseCon(con);
		}
		
	}
	
	

}
