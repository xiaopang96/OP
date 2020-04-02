package net.wanho.servlet;

import com.alibaba.fastjson.JSON;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.*;
import net.wanho.service.*;
import net.wanho.util.StringUtils;
import net.wanho.vo.AjaxResult;
import net.wanho.vo.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.velocity.runtime.directive.Foreach;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 用户信息操作处理
 *
 * @description:
 * @author: Mr.songbeichang
 * @create: 2020-03-29 19:26
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    /**
     * 页面所在路径前缀
     * http://localhost:8080/FourProject/PostServlet?method=to_list
     */
    private String prefix = "WEB-INF/admin/system/" + "user/";

    /**
     * 工厂，建立对象
     */
    private UserServiceI userService = (UserServiceI) ObjectFactory.getObject("userService");
    private DeptServiceI deptService = (DeptServiceI) ObjectFactory.getObject("deptService");
    private RoleServiceI roleService = (RoleServiceI) ObjectFactory.getObject("roleService");
    private PostServiceI postService = (PostServiceI) ObjectFactory.getObject("postService");
    private DictDataServiceI dictDataService = (DictDataServiceI) ObjectFactory.getObject("dictDataService");

    protected String to_list(HttpServletRequest request, HttpServletResponse resp) {

        return "forward:" + prefix + "list.jsp";
    }

    protected String to_add(HttpServletRequest request, HttpServletResponse resp) {
        //查询字典，性别字段
        DictData dictData = new DictData();
        dictData.setDictType("sys_user_sex");
        List<DictData> genders = dictDataService.selectDictDataByType(dictData);
        //将性别放到request作用域中，前台用el表达式访问
        request.setAttribute("genders", genders);
        //查询所有岗位
        List<Post> posts = postService.selectPostList(new Post());
        request.setAttribute("posts", posts);
        //查询所有角色
        List<Role> roles = roleService.selectRoleList(new Role());
        request.setAttribute("roles",roles);
        return "forward:" + prefix + "edit.jsp";
    }

    protected String to_update(HttpServletRequest request, HttpServletResponse resp) {
        String id = request.getParameter("id");
        if (StringUtils.isNotEmpty(id)) {
            //根据用户id查询用户
            User user = userService.selectUserById(Integer.parseInt(id));
            //查询部门名称
            Dept dept = deptService.selectDeptById(user.getDeptId());
            if(StringUtils.isNotEmpty(dept.getDeptName())){
                user.setDeptName(dept.getDeptName());
            }
            //查询角色
            List<Role> roleList = roleService.selectRoleListByUserId(Integer.parseInt(id));
            if(roleList!=null && roleList.size()>0){
                Long[] roleIds = new Long[roleList.size()];
                for (int i = 0; i < roleList.size(); i++) {
                    roleIds[i] = Long.valueOf(roleList.get(i).getRoleId());
                }
                user.setRoleIds( roleIds);
                user.setRoles(roleList);
            }
            //查询岗位
            List<Post> postList =  postService.selectPostListUserId(Integer.parseInt(id));
            if(postList!=null && postList.size()>0){
                Long[] postIds = new Long[postList.size()];
                for (int i = 0; i < postList.size(); i++) {
                    postIds[i] = Long.valueOf(postList.get(i).getPostId());
                }
                user.setPostIds(postIds);
                user.setPosts(postList);
            }
            request.setAttribute("user", user);


            //查询字典，性别字段
            DictData dictData = new DictData();
            dictData.setDictType("sys_user_sex");
            List<DictData> genders = dictDataService.selectDictDataByType(dictData);
            //将性别放到request作用域中，前台用el表达式访问
            request.setAttribute("genders", genders);
            //查询所有岗位
            List<Post> posts = postService.selectPostList(new Post());
            request.setAttribute("posts", posts);
            //查询所有角色
            List<Role> roles = roleService.selectRoleList(new Role());
            request.setAttribute("roles",roles);

        }
        return "forward:" + prefix + "edit.jsp";
    }

    /**
     * protected
     *
     * @param request
     * @param resp
     * @throws IOException
     */
    public void list(HttpServletRequest request, HttpServletResponse resp) throws IOException {

        Map map = request.getParameterMap();
        PrintWriter out = resp.getWriter();
        Page page = new Page();
        User user = new User();
        try {
            // 日期格式转换器String-->Date
            this.convertStringToDate();
            // 获取分页相关数据 表单
            BeanUtils.populate(user, map);
            BeanUtils.populate(page, map);
            //通过service和dao层可以得到page信息，然后页面输出
            userService.selectUserByPage(user, page);
            //这个方法？
            page.getData();
            //将page转化为TableDataInfo类型发送给前台
            out.println(JSON.toJSON(this.getTableDataInfo(page)));
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    protected void saveOrUpdate(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        Map map = request.getParameterMap();
        PrintWriter out = resp.getWriter();
        User user = new User();
        user.setLoginName("admin");
        try {
            //赋值操作
            BeanUtils.populate(user, map);
            //修改排序存在bug,传值获得不到
            userService.saveOrUpdatePost(user);

            out.print(JSON.toJSON(new AjaxResult(0, "操作成功", null)));
        } catch (IllegalAccessException | ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.print(JSON.toJSON(new AjaxResult(1, e.getMessage(), null)));

        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    protected void remove(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String ids = request.getParameter("ids");
        PrintWriter out = resp.getWriter();
        if (StringUtils.isNotEmpty(ids)) {
            //执行删除，并返回结果
            int[] keys = userService.deleteUserByIds(ids);
            if (keys.length > 0) {
                out.print(JSON.toJSON(new AjaxResult(0, "删除成功！", null)));
            } else {
                out.print(JSON.toJSON(new AjaxResult(1, "删除失败！", null)));

            }
        }

    }

    protected void reset(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String id = request.getParameter("id");
        PrintWriter out = resp.getWriter();


        try {
            userService.reset(id);
            out.print(JSON.toJSON(new AjaxResult(0, "重置成功！", null)));
        } catch (Exception e) {
            e.printStackTrace();
            out.print(JSON.toJSON(new AjaxResult(1, "重置失败！", null)));
        }finally {
            out.flush();
            out.close();
        }


    }
}



