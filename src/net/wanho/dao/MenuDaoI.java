package net.wanho.dao;

import net.wanho.po.Menu;
import net.wanho.po.User;
import net.wanho.vo.Page;

import java.util.List;

/**
 * 菜单权限 数据层
 * 
 */
public interface MenuDaoI
{
	

	/**
     * 分页查询菜单权限列表
     * 
     * @param Menu 菜单权限
     * @return 菜单权限集合
     */
	 void selectMenuByPage(Menu menu, Page page);



    Long updateMenu(Menu menu);

    Long insertMenu(Menu menu);

    Menu selectMenuById(int parseInt);


    int[] batchDeleteMenu(Integer[] idsArray);

    List<Menu> selectMenuList(Menu menu);
}