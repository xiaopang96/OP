package net.wanho.dao;

import net.wanho.po.Dept;
import net.wanho.po.User;
import net.wanho.vo.Page;

import java.util.List;

/**
 * 员工 数据层
 * 
 */
public interface DeptDaoI
{
	

	/**
     * 分页查询员工列表
     * 
     * @param Dept 员工信息
     * @return 员工集合
     */
	 void selectDeptByPage(Dept dept, Page page);



    Long updateDept(Dept dept);

    Long insertDept(Dept dept);

    Dept selectDeptById(int parseInt);


    int[] batchDeleteDept(Integer[] idsArray);

    List<Dept> selectDeptList(Dept dept);
}