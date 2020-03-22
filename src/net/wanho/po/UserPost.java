package net.wanho.po;

import java.util.Date;


/**
 * 用户与岗位关联表 sys_user_post
 * 
 */
public class UserPost extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 用户ID */
		private String userId;
					
		/** 岗位ID */
		private String postId;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPostId() {
			return postId;
		}

		public void setPostId(String postId) {
			this.postId = postId;
		}

		@Override
		public String toString() {
			return "UserPost [userId=" + userId + ", postId=" + postId + "]";
		}

		public UserPost(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, String userId, String postId) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.userId = userId;
			this.postId = postId;
		}

		public UserPost(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
		}

		public UserPost() {
		}
			

}
