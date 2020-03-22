package net.wanho.po;

import net.wanho.util.DateUtils;
import java.util.Date;
	
/**
 * 系统访问记录表 sys_logininfor
 * 
 */
public class Logininfor {
	private static final long serialVersionUID = 1L;
	
			
		/** 访问ID */
		private Integer infoId;
				
					
		/** 登录账号 */
		private String loginName;
				
					
		/** 登录IP地址 */
		private String ipaddr;
				
					
		/** 登录地点 */
		private String loginLocation;
				
					
		/** 浏览器类型 */
		private String browser;
				
					
		/** 操作系统 */
		private String os;
				
					
		/** 登录状态 0成功 1失败 */
		private Integer status;
				
					
		/** 提示消息 */
		private String msg;
				
					
		/** 访问时间 */
		private Date loginTime;
				
		public String getLoginTimeStr(){
	        return loginTime != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, loginTime) : "";
	    }

		public Integer getInfoId() {
			return infoId;
		}

		public void setInfoId(Integer infoId) {
			this.infoId = infoId;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getIpaddr() {
			return ipaddr;
		}

		public void setIpaddr(String ipaddr) {
			this.ipaddr = ipaddr;
		}

		public String getLoginLocation() {
			return loginLocation;
		}

		public void setLoginLocation(String loginLocation) {
			this.loginLocation = loginLocation;
		}

		public String getBrowser() {
			return browser;
		}

		public void setBrowser(String browser) {
			this.browser = browser;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Date getLoginTime() {
			return loginTime;
		}

		public void setLoginTime(Date loginTime) {
			this.loginTime = loginTime;
		}

		

		public Logininfor(Integer infoId, String loginName, String ipaddr, String loginLocation, String browser,
				String os, Integer status, String msg, Date loginTime) {
			super();
			this.infoId = infoId;
			this.loginName = loginName;
			this.ipaddr = ipaddr;
			this.loginLocation = loginLocation;
			this.browser = browser;
			this.os = os;
			this.status = status;
			this.msg = msg;
			this.loginTime = loginTime;
		}

		public Logininfor() {
		}
		
		
			

}
