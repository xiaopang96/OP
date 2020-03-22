package net.wanho.po;

import java.util.Date;
	
/**
 * 字典类型表 sys_dict_type
 * 
 * 
 * @date 2019-05-28
 */
public class DictType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 字典主键 */
		private Integer dictId;
					
		/** 字典名称 */
		private String dictName;
					
		/** 字典类型 */
		private String dictType;
					
		/** 状态（0正常 1停用） */
		private String status;
										
		/** 备注 */
		private String remark;

		public Integer getDictId() {
			return dictId;
		}

		public void setDictId(Integer dictId) {
			this.dictId = dictId;
		}

		public String getDictName() {
			return dictName;
		}

		public void setDictName(String dictName) {
			this.dictName = dictName;
		}

		public String getDictType() {
			return dictType;
		}

		public void setDictType(String dictType) {
			this.dictType = dictType;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
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

		public DictType(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
		}

		public DictType(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer dictId, String dictName, String dictType, String status, String remark2) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.dictId = dictId;
			this.dictName = dictName;
			this.dictType = dictType;
			this.status = status;
			remark = remark2;
		}

		@Override
		public String toString() {
			return "DictType [dictId=" + dictId + ", dictName=" + dictName + ", dictType=" + dictType + ", status="
					+ status + ", remark=" + remark + "]";
		}
			

}
