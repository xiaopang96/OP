package net.wanho.dao.impl;

import net.wanho.dao.DeptDaoI;
import net.wanho.dao.UserDaoI;
import net.wanho.po.Dept;
import net.wanho.po.User;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门 数据层
 * 
 */
public class DeptDaoImpl extends BaseDaoImpl<Dept> implements DeptDaoI {



	/**
	 * 分页查询部门列表
	 * @param Dept 部门信息
	 * @return 部门集合
	 */
    @Override
	public void selectDeptByPage(Dept dept, Page page) {
		StringBuilder sql = new StringBuilder(
                "SELECT d.dept_id deptId,d.parent_id parentId,d.dept_name deptName,d.order_num orderNum,d.leader,d.phone,d.email,d.`status`,d.create_by,d.create_time,d.update_by,d.update_time,d.remark FROM sys_dept AS d WHERE 1=1 " );
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotEmpty(dept.getDeptName())) {
			sql.append("AND dept_name like concat('%',?, '%')");
			params.add(dept.getDeptName());
		}

		sql.append(" order by  ").append(page.getOrderColumn()).append(" ").append(page.getOrderStyle())
				.append(" limit ?,?");
		page.setTotalItemNumber(selectRecordCount(dept));
		params.add((page.getPageNo() - 1) * page.getPageSize());
		params.add(page.getPageSize());
		page.setData(this.execQuery(sql.toString(), params.toArray()));

	}



    /**
	 * private 部门的总行数
	 *
	 */
	private Long selectRecordCount(@NotNull Dept dept) {
		StringBuilder sql = new StringBuilder(" select count(0) from sys_dept where 1=1 ");
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotEmpty(dept.getDeptName())) {
			sql.append("AND dept_name like concat('%',?, '%')");
			params.add(dept.getDeptName());
		}
		return ((Long) this.execQueryObject(sql.toString(), params.toArray()));
	}

    @Override
    public Long updateDept(Dept dept) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(" update sys_dept set ");
        if (StringUtils.isNotEmpty(dept.getDeptName())) {
            sql.append("dept_name=?,");
            params.add(dept.getDeptName());
        }
        if (StringUtils.isNotEmpty(dept.getStatus())) {
            sql.append("status=?,");
            params.add(dept.getStatus());
        }
        if (dept.getStatus().equals(0) || dept.getStatus().equals(1)) {
            sql.append("status=?,");
            params.add(dept.getStatus());
        }
        if (StringUtils.isNotEmpty(dept.getRemark())) {
            sql.append("remark=?");
            params.add(dept.getRemark());
        }
        StringUtils.trimEndComma(sql);
        sql.append(" where dept_id = ? ");
        params.add(dept.getDeptId());
        return new Long(this.execUpdate(sql.toString(), params.toArray()));
    }

    @Override
    public Long insertDept(Dept dept) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("");

        sql.append("insert into sys_user(");
        if (StringUtils.isNotEmpty(dept.getDeptId())) {
            sql.append("user_id,");
        }
        if (StringUtils.isNotEmpty(dept.getDeptName())) {
            sql.append("dept_name,");
        }
        if (StringUtils.isNotEmpty(dept.getStatus())) {
            sql.append("status,");
        }
        if (dept.getStatus().equals(0) || dept.getStatus().equals(1)) {
            sql.append("status,");
        }
        if (StringUtils.isNotEmpty(dept.getRemark())) {
            sql.append("remark");
        }
        StringUtils.trimEndComma(sql);
        sql.append(")values(");
        if (StringUtils.isNotEmpty(dept.getDeptId())) {
            sql.append("?,");
            params.add(dept.getDeptId());
        }
        if (StringUtils.isNotEmpty(dept.getDeptName())) {
            sql.append("?,");
            params.add(dept.getDeptName());
        }
        if (StringUtils.isNotEmpty(dept.getStatus())) {
            sql.append("?,");
            params.add(dept.getStatus());
        }
        if (dept.getStatus().equals(0) || dept.getStatus().equals(1)) {
            sql.append("?,");
            params.add(dept.getStatus());
        }
        if (StringUtils.isNotEmpty(dept.getRemark())) {
            sql.append("?");
            params.add(dept.getRemark());
        }
        StringUtils.trimEndComma(sql);
        sql.append(")");
        return this.execInsert(sql.toString(), params.toArray());
    }

    @Override
    public Dept selectDeptById(int deptId) {
        StringBuilder sql = new StringBuilder(
                "SELECT d.dept_id,d.parent_id,d.dept_name,d.order_num,d.leader,d.phone,d.email,d.`status`,d.create_by,d.create_time,d.update_by,d.update_time,d.remark FROM sys_dept AS d " );
        sql.append(" where dept_id = ? ");
        return this.execQueryOne(sql.toString(), deptId);
    }

    @Override
    public int[] batchDeleteDept(Integer[] idsArray) {
        /*
         * String：char数组是final的，不可变，修改String时实际上是new一个新String对象返回，线程安全，频繁的增删操作时不建议使用
         * StringBuffer：线程安全（StringBuffer中的方法中加synchronized锁），多线程建议使用这个，修改值时实际上是修改底层的char数组，相比String，开销更小
         * StringBuilder：非线程安全的（StringBuilder中的方法中没加synchronized锁）, 单线程使用这个更快，修改值时实际上是修改底层的char数组，相比String，开销更小
         * 取别名为了查数据库时取出名字
         * 遍历出id,然后跳转到BaseDaoImpl的batch()方法及进行删除
         */
        StringBuilder sql = new StringBuilder("delete from sys_user where user_id in (?)");
        Object[][] params = new Object[idsArray.length][];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[] { idsArray[i] };
        }
        return this.batch(sql.toString(), params);
    }

    @Override
    public List<Dept> selectDeptList(Dept dept) {
        StringBuilder sql = new StringBuilder(
                "SELECT d.dept_id deptId,d.parent_id parentId,d.dept_name deptName,d.order_num orderNum,d.leader,d.phone,d.email,d.`status`,d.create_by,d.create_time,d.update_by,d.update_time,d.remark FROM sys_dept AS d WHERE 1=1 " );
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(dept.getDeptName())) {
            sql.append("AND dept_name like concat('%',?, '%')");
            params.add(dept.getDeptName());
        }
        return this.execQuery(sql.toString(), params.toArray());
    }


}