package net.wanho.po;

import java.util.Date;
	
/**
 * 部门表 sys_dept
 * 
 * 
 */
public class Dept extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 部门id */
		private Integer deptId;
					
		/** 父部门id */
		private Integer parentId;
					
		/** 部门名称 */
		private String deptName;
					
		/** 显示顺序 */
		private Integer orderNum;
					
		/** 负责人 */
		private String leader;
					
		/** 联系电话 */
		private String phone;
					
		/** 邮箱 */
		private String email;
					
		/** 部门状态:0正常,1停用 */
		private Integer status;
								
		/** 备注 */
		private String remark;
		
		private String parentDeptName;

		public Integer getDeptId() {
			return deptId;
		}

		public void setDeptId(Integer deptId) {
			this.deptId = deptId;
		}

		public Integer getParentId() {
			return parentId;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		public Integer getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(Integer orderNum) {
			this.orderNum = orderNum;
		}

		public String getLeader() {
			return leader;
		}

		public void setLeader(String leader) {
			this.leader = leader;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public String getParentDeptName() {
			return parentDeptName;
		}

		public void setParentDeptName(String parentDeptName) {
			this.parentDeptName = parentDeptName;
		}

		public Dept(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer deptId, Integer parentId, String deptName, Integer orderNum, String leader,
				String phone, String email, Integer status, String remark2, String parentDeptName) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.deptId = deptId;
			this.parentId = parentId;
			this.deptName = deptName;
			this.orderNum = orderNum;
			this.leader = leader;
			this.phone = phone;
			this.email = email;
			this.status = status;
			remark = remark2;
			this.parentDeptName = parentDeptName;
		}

		public Dept() {
			
		}

		
			

}
