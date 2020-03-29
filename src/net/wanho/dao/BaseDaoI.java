package net.wanho.dao;

import java.util.List;

public interface BaseDaoI<T> {

	/**
	 * 
	 * @方法名:		[ execQueryObject ]		
	 * @返回类型:		[ Object ]
	 * @功能描述:     	[ 查询首行首列 ]
	 */
	Object execQueryObject(String sql, Object... params);

	/**
	 * 
	 * @方法名:		[ execQueryOne ]		
	 * @返回类型:		[ T ]
	 * @功能描述:     	[ 查询一行记录 ]
	 */
	T execQueryOne(String sql, Object... params);

	/**
	 * 
	 * @方法名:		[ execQuery ]		
	 * @返回类型:		[ List<T> ]
	 * @功能描述:     	[ 查询多条记录 ]
	 */
	List<T> execQuery(String sql, Object... params);

	/**
	 * 
	 * @方法名:		[ execUpdate ]		
	 * @返回类型:		[ int ]
	 * @功能描述:     	[ 增删改操作 ]
	 */
	int execUpdate(String sql, Object... params);

	/**
	 * 
	 * @方法名:		[ execAdd ]		
	 * @返回类型:		[ long ]
	 * @功能描述:     	[ 增加时返回自动增长列的值 ]
	 */
	long execInsert(String sql, Object... params);
	
	public int[] batch(String sql, Object[]... params);

}