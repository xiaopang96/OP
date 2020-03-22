package net.wanho.po;

import java.util.Date;
	
/**
 * 岗位表 sys_post
 * 
 *
 */
public class Post extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 岗位ID */
		private Integer postId;
					
		/** 岗位编码 */
		private String postCode;
					
		/** 岗位名称 */
		private String postName;
					
		/** 显示顺序 */
		private Integer postSort;
					
		/** 状态（0正常 1停用） */
		private Integer status;
					
		/** 备注 */
		private String remark;

		public Integer getPostId() {
			return postId;
		}

		public void setPostId(Integer postId) {
			this.postId = postId;
		}

		public String getPostCode() {
			return postCode;
		}

		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}

		public String getPostName() {
			return postName;
		}

		public void setPostName(String postName) {
			this.postName = postName;
		}

		public Integer getPostSort() {
			return postSort;
		}

		public void setPostSort(Integer postSort) {
			this.postSort = postSort;
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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Post(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer postId, String postCode, String postName, Integer postSort, Integer status,
				String remark2) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.postId = postId;
			this.postCode = postCode;
			this.postName = postName;
			this.postSort = postSort;
			this.status = status;
			remark = remark2;
		}

		public Post(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
		}

		public Post() {
		}

		@Override
		public String toString() {
			return "Post [postId=" + postId + ", postCode=" + postCode + ", postName=" + postName + ", postSort="
					+ postSort + ", status=" + status + ", remark=" + remark + "]";
		}
			

}
