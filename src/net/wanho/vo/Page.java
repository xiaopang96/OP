package net.wanho.vo;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Page {
	//当前页
	private int pageNo=1; //页面获取 
	//页面大小 
	private int pageSize=10; //页面获取
	//页面数据
	private List data; //数据库获取
	//数据总条数
	private long totalItemNumber; //数据库获取
	//排序列
	private String orderColumn ="id";
	//排序方式
	private String orderStyle ="asc";
	
	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderStyle() {
		return orderStyle;
	}

	public void setOrderStyle(String orderStyle) {
		this.orderStyle = orderStyle;
	}

	/**
	 * 计算出总页数
	 * @return
	 */
	public int getTotalPageNumber() {
		return (int)(totalItemNumber%pageSize==0?totalItemNumber/pageSize:totalItemNumber/pageSize+1);
	}
	
	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean isHasPrevPage() {
		if(pageNo==1) 
			return false;
		return true;
	}
	
	public boolean isHasNextPage() {
		if(pageNo==getTotalPageNumber()) 
			return false;
		return true;
	}
	
	//得到下一页:页号
	public int getNextPage() {
		if(isHasNextPage())
			return pageNo+1;
		return pageNo;
	}
	//得到上一页:页号
	public int getPrevPage() {
		if(isHasPrevPage()) 
			return pageNo-1;
		return pageNo;
	}
	
	/**
	 * 得到页码
	 * @return
	 */
	public int getPageNo() {
		//验证
		if(pageNo<1) 
			pageNo=1;
		else if(pageNo>getTotalPageNumber())
			pageNo=getTotalPageNumber();
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public long getTotalItemNumber() {
		return totalItemNumber;
	}
	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	
	
}
