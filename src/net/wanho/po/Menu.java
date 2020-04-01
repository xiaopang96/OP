package net.wanho.po;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
	
/**
 * 菜单权限表 sys_menu
 * 
 * 
 * @date 2019-05-28
 */
public class Menu extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 菜单ID */
		private Integer menuId;
					
		/** 菜单名称 */
		private String menuName;
					
		/** 父菜单ID */
		private Integer parentId;
					
		/** 显示顺序 */
		private Integer orderNum;
					
		/** 请求地址 */
		private String url;
					
		/** 类型:M目录,C菜单,F按钮 */
		private String menuType;
					
		/** 菜单状态:0显示,1隐藏 */
		private Integer visible;
					
		/** 权限标识 */
		private String perms;
					
		/** 菜单图标 */
		private String icon;
								
		/** 备注 */
		private String remark;

		private Menu parentMenu;
		
		private String parentMenuName;
		
		private List<Menu> children = new ArrayList<>() ;
		
		
		public Menu() {}


		public Menu(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer menuId, String menuName, Integer parentId, Integer orderNum, String url,
				String menuType, Integer visible, String perms, String icon, String remark2, Menu parentMenu,
				String parentMenuName, List<Menu> children) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.menuId = menuId;
			this.menuName = menuName;
			this.parentId = parentId;
			this.orderNum = orderNum;
			this.url = url;
			this.menuType = menuType;
			this.visible = visible;
			this.perms = perms;
			this.icon = icon;
			remark = remark2;
			this.parentMenu = parentMenu;
			this.parentMenuName = parentMenuName;
			this.children = children;
		}


		public Integer getMenuId() {
			return menuId;
		}


		public void setMenuId(Integer menuId) {
			this.menuId = menuId;
		}


		public String getMenuName() {
			return menuName;
		}


		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}


		public Integer getParentId() {
			return parentId;
		}


		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}


		public Integer getOrderNum() {
			return orderNum;
		}


		public void setOrderNum(Integer orderNum) {
			this.orderNum = orderNum;
		}


		public String getUrl() {
			return url;
		}


		public void setUrl(String url) {
			this.url = url;
		}


		public String getMenuType() {
			return menuType;
		}


		public void setMenuType(String menuType) {
			this.menuType = menuType;
		}


		public Integer getVisible() {
			return visible;
		}


		public void setVisible(Integer visible) {
			this.visible = visible;
		}


		public String getPerms() {
			return perms;
		}


		public void setPerms(String perms) {
			this.perms = perms;
		}


		public String getIcon() {
			return icon;
		}


		public void setIcon(String icon) {
			this.icon = icon;
		}


		public String getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = remark;
		}


		public Menu getParentMenu() {
			return parentMenu;
		}


		public void setParentMenu(Menu parentMenu) {
			this.parentMenu = parentMenu;
		}


		public String getParentMenuName() {
			return parentMenuName;
		}


		public void setParentMenuName(String parentMenuName) {
			this.parentMenuName = parentMenuName;
		}


		public List<Menu> getChildren() {
			return children;
		}


		public void setChildren(List<Menu> children) {
			this.children = children;
		}

		public Menu(String url) {
			super();
			this.url = url;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((url == null) ? 0 : url.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (getClass() != obj.getClass())
				return false;
			Menu other = (Menu) obj;
			if (url == null) {
				if (other.url != null)
					return false;
			} else if (!url.equals(other.url))
				return false;
			return true;
		}

}
