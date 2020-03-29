package net.wanho.servlet;

import com.alibaba.fastjson.JSON;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Dept;
import net.wanho.po.User;
import net.wanho.service.DeptServiceI;
import net.wanho.service.UserServiceI;
import net.wanho.util.StringUtils;
import net.wanho.vo.AjaxResult;
import net.wanho.vo.Page;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 用户信息操作处理
 * @description:
 * @author: Mr.songbeichang
 * @create: 2020-03-29 19:26
 */
@WebServlet("/DeptServlet")
public class DeptServlet extends BaseServlet{
    /**
     *	页面所在路径前缀
     *	http://localhost:8080/FourProject/PostServlet?method=to_list
     */
    private String prefix="WEB-INF/admin/system/"+"dept/";

    /**
     * 工厂，建立对象
     */
    private DeptServiceI deptService=(DeptServiceI) ObjectFactory.getObject("deptService");


    protected String to_list(HttpServletRequest request, HttpServletResponse resp) {

        return "forward:"+prefix+"list.jsp";
    }

    protected String to_add(HttpServletRequest request,HttpServletResponse resp) {

        return "forward:"+prefix+"edit.jsp";
    }

    protected String to_update(HttpServletRequest request,HttpServletResponse resp) {
        String id = request.getParameter("id");
        if(StringUtils.isNotEmpty(id)){
            Dept dept = deptService.selectDeptById(Integer.parseInt(id));
            request.setAttribute("Dept",dept);
        }
        return "forward:"+prefix+"edit.jsp";
    }

    protected void treeTableList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Map<String, String[]> map = request.getParameterMap();
        Dept dept = new Dept();

        try {
            // 日期格式转换器String-->Date
            this.convertStringToDate();
            // 获取查询表单相关数据
            BeanUtils.populate(dept, map);
            out.println(JSON.toJSON(deptService.selectDeptList(new Dept())));
        } catch (IllegalAccessException | InvocationTargetException | ServiceException e) {
            out.println(new AjaxResult(1,e.getMessage(),null));
        } finally {
            out.flush();
            out.close();
        }
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
        Dept dept = new Dept();
        try {
            BeanUtils.populate(dept, map);
            BeanUtils.populate(page, map);
            //通过service和dao层可以得到page信息，然后页面输出
            deptService.selectDeptByPage(dept, page);
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


    protected void saveOrUpdate(HttpServletRequest request,HttpServletResponse resp) throws IOException {
        Map map = request.getParameterMap();
        PrintWriter out = resp.getWriter();
        Dept dept = new Dept();
        try {
            //赋值操作
            BeanUtils.populate(dept,map);
            //修改排序存在bug,传值获得不到
            deptService.saveOrUpdatePost(dept);

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


    protected void remove(HttpServletRequest request,HttpServletResponse resp) throws IOException {
        String ids=request.getParameter("ids");
        PrintWriter out = resp.getWriter();
        if(StringUtils.isNotEmpty(ids)){
            //执行删除，并返回结果
            int[] keys=deptService.deleteDeptByIds(ids);
            if(keys.length>0){
                out.print(JSON.toJSON(new AjaxResult(0,"删除成功！",null)));
            }else{
                out.print(JSON.toJSON(new AjaxResult(1,"删除失败！",null)));

            }
        }

    }




}



