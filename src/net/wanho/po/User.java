package net.wanho.po;

import net.wanho.util.DateUtils;

import java.util.Date;
import java.util.List;
	
/**
 * 用户表 sys_user
 * 
 * 
 */
public class User extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 用户ID */
		private Integer userId;
					
		/** 部门ID */
		private Integer deptId;
					
		/** 登录账号 */
		private String loginName;
					
		/** 用户昵称 */
		private String userName;
					
		/** 用户类型（00系统用户） */
		private String userType;
					
		/** 用户邮箱 */
		private String email;
					
		/** 手机号码 */
		private String phonenumber;
					
		/** 用户性别（0男 1女 2未知） */
		private String sex;
					
		/** 头像路径 */
		private String avatar;
					
		/** 密码 */
		private String password;
					
		/** 盐加密 */
		private String salt;
					
		/** 帐号状态（0正常 1停用） */
		private String status;
					
		/** 删除标志（0代表存在 2代表删除） */
		private String delFlag;
					
		/** 最后登陆IP */
		private String loginIp;
					
		/** 最后登陆时间 */
		private Date loginDate;


		public String getLoginDateStr(){
	        return loginDate != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, loginDate) : "";
	    }
			
		/** 所属部门们*/
		private String deptIds;
		
		/**部门名称 */
		private String deptName;
		
		 /** 部门对象 */
	    private Dept dept;
	    /** 角色组 */
	    private Long[] roleIds;
	    private List<Role> roles;
	    /** 岗位组 */
	    private Long[] postIds;
	    private List<Post> posts;
	    
	    /**权限*/
	    private Long[] menuIds;
	    private List<Menu> menuLst;

		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public Integer getDeptId() {
			return deptId;
		}
		public void setDeptId(Integer deptId) {
			this.deptId = deptId;
		}
		public String getLoginName() {
			return loginName;
		}
		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAvatar() {
			return avatar;
		}
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getSalt() {
			return salt;
		}
		public void setSalt(String salt) {
			this.salt = salt;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDelFlag() {
			return delFlag;
		}
		public void setDelFlag(String delFlag) {
			this.delFlag = delFlag;
		}
		public String getLoginIp() {
			return loginIp;
		}
		public void setLoginIp(String loginIp) {
			this.loginIp = loginIp;
		}
		public Date getLoginDate() {
			return loginDate;
		}
		public void setLoginDate(Date loginDate) {
			this.loginDate = loginDate;
		}

		public String getDeptIds() {
			return deptIds;
		}
		public void setDeptIds(String deptIds) {
			this.deptIds = deptIds;
		}
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		public Dept getDept() {
			return dept;
		}
		public void setDept(Dept dept) {
			this.dept = dept;
		}
		public Long[] getRoleIds() {
			return roleIds;
		}
		public void setRoleIds(Long[] roleIds) {
			this.roleIds = roleIds;
		}
		public List<Role> getRoles() {
			return roles;
		}
		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}
		public Long[] getPostIds() {
			return postIds;
		}
		public void setPostIds(Long[] postIds) {
			this.postIds = postIds;
		}
		public List<Post> getPosts() {
			return posts;
		}
		public void setPosts(List<Post> posts) {
			this.posts = posts;
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
		public User() {
			super();
		}
	    
	    
}
