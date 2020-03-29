package net.wanho.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.alibaba.druid.util.StringUtils;

import net.wanho.util.DateUtils;
import net.wanho.vo.Page;
import net.wanho.vo.TableDataInfo;

/**
 * 
 * @包名: [ net.wanho.servlet ]
 * @类名: [ BaseServlet ]
 * @描述: [ Servlet的基础类 ]
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @方法名:		[ service ]
	 * @功能描述:		[ 使用反射处理Servlet中多个方法]   
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodName = req.getParameter("method");


		if (StringUtils.isEmpty(methodName)) {
			methodName = "doGet";
		}

		//method 是一种反射的方法
		Method method = null;

		try {
			//获取前台方法to_list--->list getMethod

			method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("您调用的  "  +methodName + "（） 方法不存在", e);
		}


		try {
			//设置可访问
			method.setAccessible(true);
			//调用方法，获取返回值
			String res = (String) method.invoke(this, req, resp);
			if(res==null) return;
			String[] arrs = res.split(":");
			if (arrs[0].equalsIgnoreCase("forward")) {
				req.getRequestDispatcher("/" + arrs[1]).forward(req, resp);
			} else {
				resp.sendRedirect(arrs[1]);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	 
	/**
	 * 
	 * @方法名:		[ getTableData ]		
	 * @返回类型:		[ TableInfo ]
	 * @功能描述:     	[ 返回bootstrap_table的json数据]
	 */
	public TableDataInfo getTableDataInfo(Page page){
		TableDataInfo tableInfo = new TableDataInfo();
		tableInfo.setTotal(page.getTotalItemNumber());
		tableInfo.setRows(page.getData());
		return tableInfo;
	}
	
	public void convertStringToDate(){
		//日期格式转换器String-->Date
		ConvertUtils.register(new Converter() {
			public Object convert(Class type, Object value) {
				return DateUtils.parseStrToDate(DateUtils.YYYY_MM_DD, value.toString());
			}
		}, Date.class);
	}

}
