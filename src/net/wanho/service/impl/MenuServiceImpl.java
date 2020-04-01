package net.wanho.service.impl;

import net.wanho.dao.MenuDaoI;
import net.wanho.dao.UserDaoI;
import net.wanho.exception.DaoException;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Menu;
import net.wanho.po.User;
import net.wanho.service.MenuServiceI;
import net.wanho.service.UserServiceI;
import net.wanho.util.Convert;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.Date;
import java.util.List;


/**
 * 菜单权限 服务层实现
 * @author songbeichang
 */
public class MenuServiceImpl implements MenuServiceI {
	private MenuDaoI menuDao = (MenuDaoI) ObjectFactory.getObject("menuDao");



    @Override
    public void selectMenuByPage(Menu menu, Page page) {
        menuDao.selectMenuByPage(menu,page);
    }

    @Override
    public Long saveOrUpdatePost(Menu menu) {
        Long key=0L;
        if(StringUtils.isNotEmpty(menu.getMenuId())){
            //修改
            menu.setUpdateTime(new Date());
            try{
                key=menuDao.updateMenu(menu);
            }catch(DaoException e){
                //自定义异常
                throw new ServiceException("修改失败",e);
            }
        }else{
            //新增
            menu.setCreateTime(new Date());
            try{
                key=menuDao.insertMenu(menu);
            }catch(DaoException e){
                throw new  ServiceException("新增失败",e);
            }
        }

        return key;
    }

    @Override
    public Menu selectMenuById(int parseInt) {
        return menuDao.selectMenuById(parseInt);
    }

    @Override
    public int[] deleteMenuByIds(String ids) {
        Integer [] idsArray= Convert.toIntArray(",", ids);
        // batch 分批处理
        return menuDao.batchDeleteMenu(idsArray);
    }

    @Override
    public List<Menu> selectMenuList(Menu Menu) {
        return menuDao.selectMenuList(Menu);
    }


}