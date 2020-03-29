package net.wanho.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


import com.alibaba.fastjson.JSON;

import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Post;
import net.wanho.po.User;
import net.wanho.service.PostServiceI;
import net.wanho.util.StringUtils;
import net.wanho.vo.AjaxResult;
import net.wanho.vo.Page;

/**
 * 岗位 信息操作处理
 * 
 */
@WebServlet("/PostServlet")
public class PostServlet extends BaseServlet{
	
	/**
	 *	页面所在路径前缀
	 *	http://localhost:8080/FourProject/PostServlet?method=to_list
	 */
	private String prefix="WEB-INF/admin/system/"+"post/";

    /**
     * 工厂，建立对象
     */
	private PostServiceI postService=(PostServiceI) ObjectFactory.getObject("postService");
	
	
	protected String to_list(HttpServletRequest request,HttpServletResponse resp) {

		return "forward:"+prefix+"list.jsp";
	}

	protected String to_add(HttpServletRequest request,HttpServletResponse resp) {

		return "forward:"+prefix+"edit.jsp";
	}

	protected String to_update(HttpServletRequest request,HttpServletResponse resp) {
		String id = request.getParameter("id");
		if(StringUtils.isNotEmpty(id)){
			Post post = postService.selectPostById(Integer.parseInt(id));
			request.setAttribute("post",post);
		}
		return "forward:"+prefix+"edit.jsp";
	}


    /**
     * protected
     * @param request
     * @param resp
     * @throws IOException
     */
	public void list(HttpServletRequest request,HttpServletResponse resp) throws IOException {

		Map map = request.getParameterMap();
		PrintWriter out = resp.getWriter();
		Page page = new Page();
		Post post = new Post();
		try {
			BeanUtils.populate(post, map);
			BeanUtils.populate(page, map);
			//通过service和dao层可以得到page信息，然后页面输出
			postService.selectPostByPage(post, page);
			page.getData();
			out.println(JSON.toJSON(this.getTableDataInfo(page)));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void remove(HttpServletRequest request,HttpServletResponse resp) throws IOException {
		String ids=request.getParameter("ids");
		PrintWriter out = resp.getWriter();
		if(StringUtils.isNotEmpty(ids)){
			//执行删除，并返回结果
			int[] keys=postService.deletePostByIds(ids);
			if(keys.length>0){
				out.print(JSON.toJSON(new AjaxResult(0,"删除成功！",null)));
			}else{
				out.print(JSON.toJSON(new AjaxResult(1,"删除失败！",null)));

			}
		}
		
	}


	protected void saveOrUpdate(HttpServletRequest request,HttpServletResponse resp) throws IOException {
        Map map = request.getParameterMap();
        PrintWriter out = resp.getWriter();
        Post post = new Post();
        User user = new User();
        user.setLoginName("admin");

		try {
		    //赋值操作
			BeanUtils.populate(post, map);
            //修改排序存在bug,传值获得不到
			postService.saveOrUpdatePost(post,user);

			out.print(JSON.toJSON(new AjaxResult(0,"操作成功",null)));
		} catch (IllegalAccessException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print(JSON.toJSON(new AjaxResult(1,e.getMessage(),null)));

		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}