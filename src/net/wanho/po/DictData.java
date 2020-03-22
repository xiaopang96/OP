package net.wanho.po;

import java.util.Date;
	
/**
 * 字典数据表 sys_dict_data
 * 
 * 
 */
public class DictData extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
			
		/** 字典编码 */
		private Integer dictCode;
					
		/** 字典排序 */
		private Integer dictSort;
					
		/** 字典标签 */
		private String dictLabel;
					
		/** 字典键值 */
		private String dictValue;
					
		/** 字典类型 */
		private String dictType;
					
		/** 样式属性 */
		private String cssClass;
					
		/** 是否默认（Y是 N否） */
		private String isDefault;
					
		/** 状态（0正常 1停用） */
		private String status;
								
		/** 备注 */
		private String remark;

		public Integer getDictCode() {
			return dictCode;
		}

		public void setDictCode(Integer dictCode) {
			this.dictCode = dictCode;
		}

		public Integer getDictSort() {
			return dictSort;
		}

		public void setDictSort(Integer dictSort) {
			this.dictSort = dictSort;
		}

		public String getDictLabel() {
			return dictLabel;
		}

		public void setDictLabel(String dictLabel) {
			this.dictLabel = dictLabel;
		}

		public String getDictValue() {
			return dictValue;
		}

		public void setDictValue(String dictValue) {
			this.dictValue = dictValue;
		}

		public String getDictType() {
			return dictType;
		}

		public void setDictType(String dictType) {
			this.dictType = dictType;
		}

		public String getCssClass() {
			return cssClass;
		}

		public void setCssClass(String cssClass) {
			this.cssClass = cssClass;
		}

		public String getIsDefault() {
			return isDefault;
		}

		public void setIsDefault(String isDefault) {
			this.isDefault = isDefault;
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

		public DictData(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
		}

		public DictData(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
				String remark, Integer dictCode, Integer dictSort, String dictLabel, String dictValue, String dictType,
				String cssClass, String isDefault, String status, String remark2) {
			super(searchValue, createBy, createTime, updateBy, updateTime, remark);
			this.dictCode = dictCode;
			this.dictSort = dictSort;
			this.dictLabel = dictLabel;
			this.dictValue = dictValue;
			this.dictType = dictType;
			this.cssClass = cssClass;
			this.isDefault = isDefault;
			this.status = status;
			remark = remark2;
		}

		public DictData() {
		}

			

}
