package net.wanho.service.impl;

import net.wanho.dao.MenuDaoI;
import net.wanho.dao.RoleDaoI;
import net.wanho.exception.DaoException;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Menu;
import net.wanho.po.Role;
import net.wanho.service.MenuServiceI;
import net.wanho.service.RoleServiceI;
import net.wanho.util.Convert;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.Date;
import java.util.List;


/**
 * 角色权限 服务层实现
 * @author songbeichang
 */
public class RoleServiceImpl implements RoleServiceI {
	private RoleDaoI roleDao = (RoleDaoI) ObjectFactory.getObject("roleDao");



    @Override
    public void selectRoleByPage(Role role, Page page) {
        roleDao.selectRoleByPage(role,page);
    }

    @Override
    public Long saveOrUpdatePost(Role role) {
        Long key=0L;
        if(StringUtils.isNotEmpty(role.getRoleId())){
            //修改
            role.setUpdateTime(new Date());
            try{
                key=roleDao.updateRole(role);
            }catch(DaoException e){
                //自定义异常
                throw new ServiceException("修改失败",e);
            }
        }else{
            //新增
            role.setCreateTime(new Date());
            try{
                key=roleDao.insertRole(role);
            }catch(DaoException e){
                throw new  ServiceException("新增失败",e);
            }
        }

        return key;
    }

    @Override
    public Role selectRoleById(int parseInt) {
        return roleDao.selectRoleById(parseInt);
    }

    @Override
    public int[] deleteRoleByIds(String ids) {
        Integer [] idsArray= Convert.toIntArray(",", ids);
        // batch 分批处理
        return roleDao.batchDeleteRole(idsArray);
    }

    @Override
    public List<Role> selectRoleList(Role role) {
        return roleDao.selectRoleList(role);
    }

    @Override
    public List<Role> selectRoleListByUserId(int userId) {
        return roleDao.selectRoleListByUserId(userId);
    }


}