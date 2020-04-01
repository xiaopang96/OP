package net.wanho.service;

import net.wanho.po.Role;
import net.wanho.vo.Page;

import java.util.List;

/**
 * 员工 服务层接口
 * 
 *  @description:
 *  @author: Mr.songbeichang
 *  @create: 2020-03-29 19:26
 */
public interface RoleServiceI
{

	
	/**
     * 分页查询角色列表
     * 
     * @param Role 角色信息
     * @return 角色相关Page
     */
	void selectRoleByPage(Role role, Page Page);


    Long saveOrUpdatePost(Role role);

    Role selectRoleById(int parseInt);

    int[] deleteRoleByIds(String ids);

    List<Role> selectRoleList(Role role);

    List<Role> selectRoleListByUserId(int userId);
}
