package net.wanho.dao.impl;

import net.wanho.dao.MenuDaoI;
import net.wanho.dao.UserDaoI;
import net.wanho.po.Menu;
import net.wanho.po.User;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限 数据层
 * 
 */
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDaoI {



	/**
	 * 分页查询菜单权限列表
	 * 
	 * @param menu
	 *            菜单权限信息
	 * @return 菜单权限集合
	 */
    @Override
	public void selectMenuByPage(Menu menu, Page page) {
		StringBuilder sql = new StringBuilder(
                "SELECT m.menu_id AS menuId,m.menu_name menuName,m.parent_id parentId,m.order_num orderNum,m.url,m.menu_type menuType,m.visible,m.perms,m.icon,m.create_by,m.create_time,m.update_by,m.update_time,m.remark FROM sys_menu AS m  WHERE 1=1 " );
		List<Object> params = new ArrayList<>();

		sql.append(" order by  ").append(page.getOrderColumn()).append(" ").append(page.getOrderStyle())
				.append(" limit ?,?");
		page.setTotalItemNumber(selectRecordCount(menu));
		params.add((page.getPageNo() - 1) * page.getPageSize());
		params.add(page.getPageSize());
		//将查询的结果封装到page对象（List,怎么会有两个参数）
		page.setData(this.execQuery(sql.toString(), params.toArray()));

	}



    /**
	 * private 菜单权限的总行数
	 *
	 */
	private Long selectRecordCount(Menu menu) {
		StringBuilder sql = new StringBuilder(" select count(0) from sys_menu where 1=1 ");
		List<Object> params = new ArrayList<>();
		return ((Long) this.execQueryObject(sql.toString(), params.toArray()));
	}

    @Override
    public Long updateMenu(Menu menu) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(" update sys_menu set ");
        if (StringUtils.isNotEmpty(menu.getParentId())) {
            sql.append("parent_id=?,");
            params.add(menu.getParentId());
        }
        if (StringUtils.isNotEmpty(menu.getMenuType())) {
            sql.append("menu_type=?,");
            params.add(menu.getMenuType());
        }
        if (StringUtils.isNotEmpty(menu.getMenuName())) {
            sql.append("menu_name=?,");
            params.add(menu.getMenuName());
        }
        if (StringUtils.isNotEmpty(menu.getIcon())) {
            sql.append("icon=?,");
            params.add(menu.getIcon());
        }
        if (StringUtils.isNotEmpty(menu.getUrl())) {
            sql.append("url=?,");
            params.add(menu.getUrl());
        }
        if (StringUtils.isNotEmpty(menu.getPerms())) {
            sql.append("perms=?,");
            params.add(menu.getPerms());
        }
        if (StringUtils.isNotEmpty(menu.getOrderNum())) {
            sql.append("order_num=?");
            params.add(menu.getOrderNum());
        }
        StringUtils.trimEndComma(sql);
        sql.append(" where menu_id = ? ");
        params.add(menu.getMenuId());
        return new Long(this.execUpdate(sql.toString(), params.toArray()));
    }

    @Override
    public Long insertMenu(Menu menu) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("");

        sql.append("insert into sys_menu(");
        if (StringUtils.isNotEmpty(menu.getParentId())) {
            sql.append("parent_id,");
        }
        if (StringUtils.isNotEmpty(menu.getMenuType())) {
            sql.append("menu_type,");
        }
        if (StringUtils.isNotEmpty(menu.getMenuName())) {
            sql.append("menu_name,");
        }
        if (StringUtils.isNotEmpty(menu.getIcon())) {
            sql.append("icon,");
        }
        if (StringUtils.isNotEmpty(menu.getUrl())) {
            sql.append("url,");
        }
        if (StringUtils.isNotEmpty(menu.getPerms())) {
            sql.append("perms,");
        }
        if (StringUtils.isNotEmpty(menu.getOrderNum())) {
            sql.append("order_num");
        }

        StringUtils.trimEndComma(sql);
        sql.append(")values(");
        if (StringUtils.isNotEmpty(menu.getParentId())) {
            sql.append("?,");
            params.add(menu.getParentId());
        }
        if (StringUtils.isNotEmpty(menu.getMenuType())) {
            sql.append("?,");
            params.add(menu.getMenuType());
        }
        if (StringUtils.isNotEmpty(menu.getMenuName())) {
            sql.append("?,");
            params.add(menu.getMenuName());
        }
        if (StringUtils.isNotEmpty(menu.getIcon())) {
            sql.append("?,");
            params.add(menu.getIcon());
        }
        if (StringUtils.isNotEmpty(menu.getUrl())) {
            sql.append("?,");
            params.add(menu.getUrl());
        }
        if (StringUtils.isNotEmpty(menu.getPerms())) {
            sql.append("?,");
            params.add(menu.getPerms());
        }
        if (StringUtils.isNotEmpty(menu.getOrderNum())) {
            sql.append("?");
            params.add(menu.getOrderNum());
        }
        StringUtils.trimEndComma(sql);
        sql.append(")");
        return this.execInsert(sql.toString(), params.toArray());
    }

    @Override
    public Menu selectMenuById(int menuId) {
        StringBuilder sql = new StringBuilder(
                "SELECT m.menu_id AS menuId,m.menu_name menuName,m.parent_id parentId,m.order_num orderNum,m.url,m.menu_type menuType,m.visible,m.perms,m.icon,m.create_by,m.create_time,m.update_by,m.update_time,m.remark FROM sys_menu AS m  " );
        sql.append(" where menu_id = ? ");
        return this.execQueryOne(sql.toString(), menuId);
    }

    @Override
    public int[] batchDeleteMenu(Integer[] idsArray) {
        StringBuilder sql = new StringBuilder("delete from sys_menu where menu_id in (?)");
        Object[][] params = new Object[idsArray.length][];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[] { idsArray[i] };
        }
        return this.batch(sql.toString(), params);
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        StringBuilder sql = new StringBuilder(
                "SELECT m.menu_id AS menuId,m.menu_name menuName,m.parent_id parentId,m.order_num orderNum,m.url,m.menu_type menuType,m.visible,m.perms,m.icon,m.create_by,m.create_time,m.update_by,m.update_time,m.remark FROM sys_menu AS m Where 1=1 " );
        List<Object> params = new ArrayList<>();
        return this.execQuery(sql.toString(), params.toArray());
    }


}