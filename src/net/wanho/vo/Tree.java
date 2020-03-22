package net.wanho.vo;


public class Tree {
	private int id;
	private String name;
	private int pId;
	private boolean checked=false;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Tree(int id, String name, int pId, boolean checked) {
		super();
		this.id = id;
		this.name = name;
		this.pId = pId;
		this.checked = checked;
	}
	public Tree() {
		super();
	}
	
}
