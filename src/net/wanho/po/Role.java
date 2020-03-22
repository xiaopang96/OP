package net.wanho.po;

import java.util.Date;
import java.util.List;
	
/**
 * 角色表 sys_role
 * 
 * 
 */
public class Role extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 角色ID */
		private Integer roleId;
					
		/** 角色名称 */
		private String roleName;
					
		/** 角色权限字符串 */
		private String roleKey;
					
		/** 显示顺序 */
		private Integer roleSort;
					
		/** 角色状态:0正常,1禁用 */
		private Integer status;
				
		/** 备注 */
		private String remark;
		
		 /** 菜单组 */
		private String ids;
	    private Long[] menuIds;
	    private List<Menu> menuLst;
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public String getRoleKey() {
			return roleKey;
		}
		public void setRoleKey(String roleKey) {
			this.roleKey = roleKey;
		}
		public Integer getRoleSort() {
			return roleSort;
		}
		public void setRoleSort(Integer roleSort) {
			this.roleSort = roleSort;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		public Long[] getMenuIds() {
			return menuIds;
		}
		public void setMenuIds(Long[] menuIds) {
			this.menuIds = menuIds;
		}
		public List<Menu> getMenuLst() {
			return menuLst;
		}
		public void setMenuLst(List<Menu> menuLst) {
			this.menuLst = menuLst;
		}
		public Role(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer roleId, String roleName, String roleKey, Integer roleSort, Integer status,
				String remark2, String ids, Long[] menuIds, List<Menu> menuLst) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.roleId = roleId;
			this.roleName = roleName;
			this.roleKey = roleKey;
			this.roleSort = roleSort;
			this.status = status;
			remark = remark2;
			this.ids = ids;
			this.menuIds = menuIds;
			this.menuLst = menuLst;
		}
		public Role() {
		}

		
			

}
