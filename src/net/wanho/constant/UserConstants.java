package net.wanho.constant;

public interface UserConstants {

	//vcode
	String VALIDATE_CODE="vcode";
	
	//session中的user
	String CURRENT_SESSION_USER="currentUser";
	
	//可用
	String USER_STATUS_USE  = "0";
	//禁用
	String USER_STATUS_NOT_USE  = "1";
	
	//登录成功
	Integer USER_LOGIN_SUCCESS=0;
	//登录失败
	Integer USER_LOGIN_ERROR=1;
	
}
