package net.wanho.servlet;

import com.alibaba.fastjson.JSON;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Menu;
import net.wanho.po.Role;
import net.wanho.service.MenuServiceI;
import net.wanho.service.RoleServiceI;
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
 * 角色信息操作处理
 * @description:
 * @author: Mr.songbeichang
 * @create: 2020-03-29 19:26
 */
@WebServlet("/RoleServlet")
public class RoleServlet extends BaseServlet{

    private String prefix="WEB-INF/admin/system/"+"role/";

    private RoleServiceI roleService=(RoleServiceI) ObjectFactory.getObject("roleService");


    protected String to_list(HttpServletRequest request, HttpServletResponse resp) {

        return "forward:"+prefix+"list.jsp";
    }

    protected String to_add(HttpServletRequest request,HttpServletResponse resp) {

        return "forward:"+prefix+"edit.jsp";
    }

    protected String to_update(HttpServletRequest request,HttpServletResponse resp) {
        String id = request.getParameter("id");
        if(StringUtils.isNotEmpty(id)){
            Role role= roleService.selectRoleById(Integer.parseInt(id));
            request.setAttribute("role",role);
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
        Role role = new Role();
        try {
            BeanUtils.populate(role, map);
            BeanUtils.populate(page, map);
            roleService.selectRoleByPage(role, page);
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
        Role role = new Role();

        try {
            BeanUtils.populate(role,map);
            roleService.saveOrUpdatePost(role);
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
            int[] keys=roleService.deleteRoleByIds(ids);
            if(keys.length>0){
                out.print(JSON.toJSON(new AjaxResult(0,"删除成功！",null)));
            }else{
                out.print(JSON.toJSON(new AjaxResult(1,"删除失败！",null)));

            }
        }

    }


    protected void treeTableList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Map<String, String[]> map = request.getParameterMap();
        Menu menu = new Menu();

        try {
            // 日期格式转换器String-->Date
            this.convertStringToDate();
            // 获取查询表单相关数据
            BeanUtils.populate(menu, map);
            out.println(JSON.toJSON(roleService.selectRoleList(new Role())));
        } catch (IllegalAccessException | InvocationTargetException | ServiceException e) {
            out.println(new AjaxResult(1,e.getMessage(),null));
        } finally {
            out.flush();
            out.close();
        }
    }





}



