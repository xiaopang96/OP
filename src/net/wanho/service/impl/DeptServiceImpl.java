package net.wanho.service.impl;

import net.wanho.dao.DeptDaoI;
import net.wanho.dao.UserDaoI;
import net.wanho.exception.DaoException;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Dept;
import net.wanho.po.User;
import net.wanho.service.DeptServiceI;
import net.wanho.service.UserServiceI;
import net.wanho.util.Convert;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.Date;
import java.util.List;


/**
 * 员工 服务层实现
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


}