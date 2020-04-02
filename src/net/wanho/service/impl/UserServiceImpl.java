package net.wanho.service.impl;

import net.wanho.dao.UserDaoI;
import net.wanho.exception.DaoException;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.User;
import net.wanho.service.UserServiceI;
import net.wanho.util.Convert;
import net.wanho.util.PwdUtils;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.Date;


/**
 * 员工 服务层实现
 * @author songbeichang
 */
public class UserServiceImpl implements UserServiceI {
	private UserDaoI userDao = (UserDaoI) ObjectFactory.getObject("userDao");



    @Override
    public void selectUserByPage(User user, Page page) {
        userDao.selectUserByPage(user,page);
    }

    @Override
    public Long saveOrUpdatePost(User user) {
        Long key=0L;
        if(StringUtils.isNotEmpty(user.getUserId())){
            //修改
            user.setUpdateBy(user.getLoginName());
            user.setUpdateTime(new Date());
            try{
                key=userDao.updateUser(user);
            }catch(DaoException e){
                //自定义异常
                throw new ServiceException("修改失败",e);
            }
        }else{
            //新增
            user.setCreateBy(user.getLoginName());
            user.setCreateTime(new Date());
            try{
                key=userDao.insertUser(user);
            }catch(DaoException e){
                throw new  ServiceException("新增失败",e);
            }
        }

        return key;
    }

    @Override
    public User selectUserById(int parseInt) {
        return userDao.selectUserById(parseInt);
    }

    @Override
    public int[] deleteUserByIds(String ids) {
        Integer [] idsArray= Convert.toIntArray(",", ids);
        // batch 分批处理
        return userDao.batchDeleteUser(idsArray);
    }

    @Override
    public void reset(String id) {
        User user  = new User();
        try {
            user.setUserId(Integer.valueOf(id));
            User dbUser = userDao.selectUserById(user.getUserId());
            user.setPassword(PwdUtils.getPwd("123456", dbUser.getLoginName(),dbUser.getSalt()));
            userDao.updateUser(user);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }


}