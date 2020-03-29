package net.wanho.dao;

import net.wanho.po.Post;
import net.wanho.po.User;
import net.wanho.vo.Page;

import java.util.List;

/**
 * 员工 数据层
 * 
 */
public interface UserDaoI
{
	

	/**
     * 分页查询员工列表
     * 
     * @param User 员工信息
     * @return 员工集合
     */
	 void selectUserByPage(User user, Page page);



    Long updateUser(User user);

    Long insertUser(User user);

    User selectUserById(int parseInt);


    int[] batchDeleteUser(Integer[] idsArray);
}