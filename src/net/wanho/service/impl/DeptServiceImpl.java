package net.wanho.service.impl;

import net.wanho.dao.DeptDaoI;
import net.wanho.exception.DaoException;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Dept;
import net.wanho.service.DeptServiceI;
import net.wanho.util.Convert;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;
import net.wanho.vo.Tree;

import java.util.ArrayList;
import java.util.List;


/**
 * 部门 服务层实现
 * @author songbeichang
 */
public class DeptServiceImpl implements DeptServiceI {
	private DeptDaoI deptDao = (DeptDaoI) ObjectFactory.getObject("deptDao");



    @Override
    public void selectDeptByPage(Dept dept, Page page) {
        deptDao.selectDeptByPage(dept,page);
    }

    @Override
    public Long saveOrUpdatePost(Dept dept) {
        Long key=0L;
        if(StringUtils.isNotEmpty(dept.getDeptId())){
            //修改
            try{
                key=deptDao.updateDept(dept);
            }catch(DaoException e){
             //自定义异常
                throw new ServiceException("修改失败",e);
            }
        }else{
            //新增
            try{
                key=deptDao.insertDept(dept);
            }catch(DaoException e){
                throw new  ServiceException("新增失败",e);
            }
        }

        return key;
    }

    @Override
    public Dept selectDeptById(int parseInt) {
        return deptDao.selectDeptById(parseInt);
    }

    @Override
    public int[] deleteDeptByIds(String ids) {
        Integer [] idsArray= Convert.toIntArray(",", ids);
        // batch 分批处理
        return deptDao.batchDeleteDept(idsArray);
    }

    @Override
    public List<Dept> selectDeptList(Dept dept) {
        return deptDao.selectDeptList(dept);
    }

    @Override
    public List<Tree> selectDeptListTree(Dept dept, String deptId) {
        try {
            // 根据角色id，查询已经的角色菜单
           /* List<RoleMenu> selMenu = null;
            if(StringUtils.isNotEmpty(deptId)) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(Integer.parseInt(roleId));
                selMenu = roleMenuDao.selectRoleMenuList(roleMenu);
            }*/

            //所有菜单集合
            List<Dept> depts = deptDao.selectDeptList(new Dept());
            //要转成的树的数据集合
            List<Tree> trees = new ArrayList<Tree>();
            for(Dept item:depts) {
                Tree tree = new Tree();
                tree.setId(item.getDeptId());
                tree.setName(item.getDeptName());
                tree.setpId(item.getParentId());
                // 如果有菜单，设置选中
                /*if (selMenu != null) {
                    for (RoleMenu roleMenu : selMenu) {
                        if (item.getMenuId() == roleMenu.getMenuId()) {
                            tree.setChecked(true);
                        }
                    }
                }*/
                trees.add(tree);
            }
            return trees;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}