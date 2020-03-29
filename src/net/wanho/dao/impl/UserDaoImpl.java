package net.wanho.dao.impl;

import net.wanho.dao.UserDaoI;
import net.wanho.po.Post;
import net.wanho.po.User;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工 数据层
 * 
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDaoI {



	/**
	 * 分页查询员工列表
	 * 
	 * @param user
	 *            员工信息
	 * @return 员工集合
	 */
    @Override
	public void selectUserByPage(User user, Page page) {
		StringBuilder sql = new StringBuilder(
				//一定要取别名，以便查数据
                "SELECT sys_user.user_id userId,sys_user.dept_id deptId,sys_user.login_name loginName,sys_user.user_name userName,sys_user.user_type userType,sys_user.email email,sys_user.phonenumber phonenumber,sys_user.sex sex,sys_user.avatar avatar,sys_user.`password` password,sys_user.salt salt,sys_user.`status` status,sys_user.del_flag delFlag,sys_user.login_ip loginIp,sys_user.login_date loginDate,sys_user.create_by,sys_user.create_time,sys_user.update_by,sys_user.update_time,sys_user.remark remark FROM sys_user WHERE 1=1 " );
		List<Object> params = new ArrayList<>();
		//查询语句都是模糊查询 AND post_id like concat('%',?, '%')"
		if (StringUtils.isNotEmpty(user.getPhonenumber())) {
			//sql是查询语句，params是什么？
			sql.append("AND phonenumber like concat('%',?, '%')");
			params.add(user.getPhonenumber());
		}
		if (StringUtils.isNotEmpty(user.getStatus())) {
			sql.append("AND status like concat('%',?, '%')");
			params.add(user.getStatus());
		}
		if (StringUtils.isNotEmpty(user.getLoginName())) {
			sql.append("AND login_name like concat('%',?, '%')");
			params.add(user.getLoginName());
		}

		//order by page.getOrderColumn() + page.getOrderStyle() +limit 拼接对应页数据的where条件
		//where+=" limit "+page.getIndex()+","+page.getPageSize();
		sql.append(" order by  ").append(page.getOrderColumn()).append(" ").append(page.getOrderStyle())
				.append(" limit ?,?");
		page.setTotalItemNumber(selectRecordCount(user));
		params.add((page.getPageNo() - 1) * page.getPageSize());
		params.add(page.getPageSize());
		//将查询的结果封装到page对象（List,怎么会有两个参数）
		page.setData(this.execQuery(sql.toString(), params.toArray()));

	}



    /**
	 * private 员工的总行数
	 *
	 */
	private Long selectRecordCount(User user) {
		StringBuilder sql = new StringBuilder(" select count(0) from sys_user where 1=1 ");
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotEmpty(user.getPhonenumber())) {
			sql.append("AND phonenumber like concat('%',?, '%')");
			params.add(user.getPhonenumber());
		}
		if (StringUtils.isNotEmpty(user.getStatus())) {
			sql.append("AND status like concat('%',?, '%')");
			params.add(user.getStatus());
		}
		if (StringUtils.isNotEmpty(user.getLoginName())) {
			sql.append("AND login_name like concat('%',?, '%')");
			params.add(user.getLoginName());
		}
		return ((Long) this.execQueryObject(sql.toString(), params.toArray()));
	}

    @Override
    public Long updateUser(User user) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(" update sys_user set ");
        if (StringUtils.isNotEmpty(user.getPhonenumber())) {
            sql.append("phonenumber=?,");
            params.add(user.getPhonenumber());
        }
        if (StringUtils.isNotEmpty(user.getStatus())) {
            sql.append("status=?,");
            params.add(user.getStatus());
        }
        if (StringUtils.isNotEmpty(user.getLoginName())) {
            sql.append("login_name=?,");
            params.add(user.getLoginName());
        }
        if (user.getStatus().equals(0) || user.getStatus().equals(1)) {
            sql.append("status=?,");
            params.add(user.getStatus());
        }
        if (StringUtils.isNotEmpty(user.getUpdateBy())) {
            sql.append("update_by=?,");
            params.add(user.getUpdateBy());
        }
        if (StringUtils.isNotEmpty(user.getUpdateTimeStr())) {
            sql.append("update_time=?,");
            params.add(user.getUpdateTimeStr());
        }
        if (StringUtils.isNotEmpty(user.getRemark())) {
            sql.append("remark=?");
            params.add(user.getRemark());
        }
        StringUtils.trimEndComma(sql);
        sql.append(" where user_id = ? ");
        params.add(user.getUserId());
        return new Long(this.execUpdate(sql.toString(), params.toArray()));
    }

    @Override
    public Long insertUser(User user) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("");

        sql.append("insert into sys_user(");
        if (StringUtils.isNotEmpty(user.getUserId())) {
            sql.append("user_id,");
        }
        if (StringUtils.isNotEmpty(user.getPhonenumber())) {
            sql.append("phonenumber,");
        }
        if (StringUtils.isNotEmpty(user.getStatus())) {
            sql.append("status,");
        }
        if (StringUtils.isNotEmpty(user.getLoginName())) {
            sql.append("login_name,");
        }
        if (user.getStatus().equals(0) || user.getStatus().equals(1)) {
            sql.append("status,");
        }
        if (StringUtils.isNotEmpty(user.getCreateBy())) {
            sql.append("create_by,");
        }
        if (StringUtils.isNotEmpty(user.getCreateTimeStr())) {
            sql.append("create_time,");
        }
        if (StringUtils.isNotEmpty(user.getRemark())) {
            sql.append("remark");
        }
        StringUtils.trimEndComma(sql);
        sql.append(")values(");
        if (StringUtils.isNotEmpty(user.getUserId())) {
            sql.append("?,");
            params.add(user.getUserId());
        }
        if (StringUtils.isNotEmpty(user.getPhonenumber())) {
            sql.append("?,");
            params.add(user.getPhonenumber());
        }
        if (StringUtils.isNotEmpty(user.getStatus())) {
            sql.append("?,");
            params.add(user.getStatus());
        }
        if (StringUtils.isNotEmpty(user.getCreateBy())) {
            sql.append("?,");
            params.add(user.getCreateBy());
        }
        if (user.getStatus().equals(0) || user.getStatus().equals(1)) {
            sql.append("?,");
            params.add(user.getStatus());
        }
        if (StringUtils.isNotEmpty(user.getCreateBy())) {
            sql.append("?,");
            params.add(user.getCreateBy());
        }
        if (StringUtils.isNotEmpty(user.getCreateTimeStr())) {
            sql.append("?,");
            params.add(user.getCreateTimeStr());
        }
        if (StringUtils.isNotEmpty(user.getRemark())) {
            sql.append("?");
            params.add(user.getRemark());
        }
        StringUtils.trimEndComma(sql);
        sql.append(")");
        return this.execInsert(sql.toString(), params.toArray());
    }

    @Override
    public User selectUserById(int userId) {
        StringBuilder sql = new StringBuilder(
                "SELECT sys_user.user_id userId,sys_user.dept_id deptId,sys_user.login_name loginName,sys_user.user_name userName,sys_user.user_type userType,sys_user.email email,sys_user.phonenumber phonenumber,sys_user.sex sex,sys_user.avatar avatar,sys_user.`password` password,sys_user.salt salt,sys_user.`status` status,sys_user.del_flag delFlag,sys_user.login_ip loginIp,sys_user.login_date loginDate,sys_user.create_by,sys_user.create_time,sys_user.update_by,sys_user.update_time,sys_user.remark remark FROM sys_user  " );
        sql.append(" where user_id = ? ");
        return this.execQueryOne(sql.toString(), userId);
    }

    @Override
    public int[] batchDeleteUser(Integer[] idsArray) {
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


}