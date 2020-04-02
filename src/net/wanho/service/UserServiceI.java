package net.wanho.service;

import net.wanho.po.DictData;
import net.wanho.po.Post;
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
public interface UserServiceI
{

	
	/**
     * 分页查询员工列表
     * 
     * @param user 员工信息
     * @return 员工相关Page
     */
	 void selectUserByPage(User user, Page Page);


    Long saveOrUpdatePost(User user);

    User selectUserById(int parseInt);

    int[] deleteUserByIds(String ids);

    void reset(String id);
}
