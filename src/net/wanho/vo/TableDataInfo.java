package net.wanho.vo;

import java.util.List;


public class TableDataInfo<T> {
	private long total;
	private List<T> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public TableDataInfo(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public TableDataInfo() {
		super();
	}

	
	
}
