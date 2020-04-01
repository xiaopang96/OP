package net.wanho.dao;

import net.wanho.po.Menu;
import net.wanho.po.Role;
import net.wanho.vo.Page;

import java.util.List;

/**
 * 菜单权限 数据层
 * 
 */
public interface RoleDaoI
{
	

	/**
     * 分页查询菜单权限列表
     * 
     * @param Role 菜单权限
     * @return 菜单权限集合
     */
	 void selectRoleByPage(Role role, Page page);



    Long updateRole(Role role);

    Long insertRole(Role role);

    Role selectRoleById(int parseInt);


    int[] batchDeleteRole(Integer[] idsArray);

    List<Role> selectRoleList(Role role);

    List<Role> selectRoleListByUserId(int userId);
}