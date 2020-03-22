package net.wanho.trans;

public interface TransMgrI {

	// 开启事务
	void beginTrans();

	// 提交事务
	void commitTrans();

	// 回滚事务
	void rollbackTrans();

}