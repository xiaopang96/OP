package net.wanho.po;

import java.util.Date;


/**
 * 角色和菜单关联表 sys_role_menu
 * 
 */
 
public class RoleMenu extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 角色ID */
		private Integer roleId;
					
		/** 菜单ID */
		private Integer menuId;

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		public Integer getMenuId() {
			return menuId;
		}

		public void setMenuId(Integer menuId) {
			this.menuId = menuId;
		}

	

		public RoleMenu(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer roleId, Integer menuId) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.roleId = roleId;
			this.menuId = menuId;
		}

		

		public RoleMenu() {
		}
			

}
