package net.wanho.service;

import net.wanho.po.Dept;
import net.wanho.po.Menu;
import net.wanho.po.User;
import net.wanho.vo.Page;

import java.util.List;

/**
 * 员工 服务层接口
 * 
 *  @description:
 *  @author: Mr.songbeichang
 *  @create: 2020-03-29 19:26
 */
public interface MenuServiceI
{

	
	/**
     * 分页查询菜单列表
     * @param Menu 菜单信息
     * @return 菜单相关Page
     */
	void selectMenuByPage(Menu menu, Page Page);

    Long saveOrUpdatePost(Menu Menu);

    Menu selectMenuById(int parseInt);

    int[] deleteMenuByIds(String ids);

    List<Menu> selectMenuList(Menu Menu);
}
