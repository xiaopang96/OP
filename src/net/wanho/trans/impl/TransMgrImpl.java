package net.wanho.trans.impl;

import java.sql.Connection;
import java.sql.SQLException;

import net.wanho.trans.TransMgrI;
import net.wanho.util.JdbcUtils;

public class TransMgrImpl implements TransMgrI {
	// 开启事务
	/* (non-Javadoc)
	 * @see net.wanho.trans.impl.TransMgrI#beginTrans()
	 */
	@Override
	public void beginTrans() {
		Connection con = JdbcUtils.getConnection();
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 提交事务
	/* (non-Javadoc)
	 * @see net.wanho.trans.impl.TransMgrI#commitTrans()
	 */
	@Override
	public void commitTrans() {
		Connection con = JdbcUtils.getConnection();
		try {
			// 提交
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 重置为默认提交
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtils.release(null, null, con);
			}
		}

	}

	// 回滚事务
	/* (non-Javadoc)
	 * @see net.wanho.trans.impl.TransMgrI#rollbackTrans()
	 */
	@Override
	public void rollbackTrans() {
		Connection con = JdbcUtils.getConnection();
		try {
			// 回滚
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 重置为默认提交
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtils.release(null, null, con);
			}
		}
	}
}
