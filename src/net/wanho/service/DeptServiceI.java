package net.wanho.service;

import net.wanho.po.Dept;
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
public interface DeptServiceI
{

	
	/**
     * 分页查询部门列表
     * 
     * @param Dept 部门信息
     * @return 部门相关Page
     */
	 void selectDeptByPage(Dept dept, Page Page);


    Long saveOrUpdatePost(Dept dept);

    Dept selectDeptById(int parseInt);

    int[] deleteDeptByIds(String ids);

    List<Dept> selectDeptList(Dept dept);
}
