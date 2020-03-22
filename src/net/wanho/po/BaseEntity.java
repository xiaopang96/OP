package net.wanho.po;

import java.util.Date;

import net.wanho.util.DateUtils;


public class BaseEntity {
	/** 搜索值 */
    private String searchValue;
    /** 创建者 */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /** 更新者 */
    private String updateBy;
    /** 更新时间 */
    private Date updateTime;
    /** 备注 */
    private String remark;
    
    public String getCreateTimeStr()
    {
        return createTime != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, createTime) : "";
    }
    
    public String getUpdateTimeStr()
    {
        return updateTime != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, updateTime) : "";
    }

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BaseEntity(String searchValue, String createBy, Date createTime, String updateBy, Date updateTime,
			String remark) {
		super();
		this.searchValue = searchValue;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.remark = remark;
	}

	public BaseEntity() {
		super();
	}


   

    
}
