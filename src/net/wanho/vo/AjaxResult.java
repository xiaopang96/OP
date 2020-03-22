package net.wanho.vo;


public class AjaxResult {
	
	private Integer code; //0成功 -1失败
	
	private String message;
	
	private Object obj;
	
	
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public AjaxResult(int i, String string) {
		this.code = i;
		this.message = string;
	}

	public AjaxResult(int i, String message, Object obj) {
		this(i,message);
		this.obj = obj;
	}

}






