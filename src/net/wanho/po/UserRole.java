package net.wanho.po;

import java.util.Date;


/**
 * 用户和角色关联表 sys_user_role
 * 
 */
public class UserRole extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 用户ID */
		private Integer userId;
					
		/** 角色ID */
		private Integer roleId;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		@Override
		public String toString() {
			return "UserRole [userId=" + userId + ", roleId=" + roleId + "]";
		}

		public UserRole(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer userId, Integer roleId) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.userId = userId;
			this.roleId = roleId;
		}

		public UserRole(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
		}

		public UserRole() {
		}
			

}
