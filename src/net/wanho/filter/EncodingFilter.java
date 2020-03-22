package net.wanho.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*@WebFilter(urlPatterns={"/*"},dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD, 
		DispatcherType.INCLUDE})*/
/**
 * 
 * @项目名:		[ 12_cms_project ] 
 * @包名:			[ net.wanho.filter ]  
 * @类名:			[ EncodingFilter ]  
 * @描述:			[ 编码过滤器 ] 
 */
public class EncodingFilter implements Filter {
	
	private FilterConfig config;
	
    public EncodingFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//从全局变量文件中获取编码格式 
		String encodingName= config.getServletContext().getInitParameter("Encoding");
		if(encodingName==null || encodingName.isEmpty()){
			encodingName="UTF-8";
		}
		//处理请求导致的乱码
		request.setCharacterEncoding(encodingName);
		//处理响应导致的乱码
		response.setContentType("text/html;charset="+encodingName);
		
		//放行
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		this.config=fConfig;
	}

}
