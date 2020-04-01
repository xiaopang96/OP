package net.wanho.dao.impl;

import net.wanho.dao.MenuDaoI;
import net.wanho.dao.RoleDaoI;
import net.wanho.po.Menu;
import net.wanho.po.Role;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限 数据层
 * @author songbeichang
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDaoI {



	/**
	 * 分页查询菜单权限列表
	 * @param Role 菜单权限信息
	 * @return 菜单权限集合
	 */
    @Override
	public void selectRoleByPage(Role role, Page page) {
		StringBuilder sql = new StringBuilder(
		        "SELECT r.role_id roleId,r.role_name roleName,r.role_key roleKey,r.role_sort roleSort,r.`status`,r.create_by,r.create_time,r.update_by,r.update_time,r.remark FROM sys_role AS r WHERE 1=1 " );
		List<Object> params = new ArrayList<>();
		// order by id desc ?
		sql.append(" order by  ").append(page.getOrderColumn()).append(" ").append(page.getOrderStyle())
				.append(" limit ?,?");
		page.setTotalItemNumber(selectRecordCount(role));
		params.add((page.getPageNo() - 1) * page.getPageSize());
		params.add(page.getPageSize());
		//将查询的结果封装到page对象（List,怎么会有两个参数）
		page.setData(this.execQuery(sql.toString(), params.toArray()));

	}




	private Long selectRecordCount(Role role) {
		StringBuilder sql = new StringBuilder(" select count(0) from sys_role where 1=1 ");
		List<Object> params = new ArrayList<>();
		return ((Long) this.execQueryObject(sql.toString(), params.toArray()));
	}

    @Override
    public Long updateRole(Role role) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(" update sys_role set ");
        if (StringUtils.isNotEmpty(role.getRoleName())) {
            sql.append("role_name=?,");
            params.add(role.getRoleName());
        }
        if (StringUtils.isNotEmpty(role.getRoleKey())) {
            sql.append("role_key=?,");
            params.add(role.getRoleKey());
        }
        if (StringUtils.isNotEmpty(role.getRoleSort())) {
            sql.append("role_sort=?,");
            params.add(role.getRoleSort());
        }
        if (StringUtils.isNotEmpty(role.getStatus())) {
            sql.append("status=?,");
            params.add(role.getStatus());
        }
        if (StringUtils.isNotEmpty(role.getRemark())) {
            sql.append("remark=?,");
            params.add(role.getRemark());
        }
        StringUtils.trimEndComma(sql);
        sql.append(" where role_id = ? ");
        params.add(role.getRoleId());
        return new Long(this.execUpdate(sql.toString(), params.toArray()));
    }

    @Override
    public Long insertRole(Role role) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("");

        sql.append("insert into sys_role(");
        if (StringUtils.isNotEmpty(role.getRoleName())) {
            sql.append("role_name,");
        }
        if (StringUtils.isNotEmpty(role.getRoleKey())) {
            sql.append("role_key,");
        }
        if (StringUtils.isNotEmpty(role.getRoleSort())) {
            sql.append("role_sort,");
        }
        if (StringUtils.isNotEmpty(role.getStatus())) {
            sql.append("status,");
        }
        if (StringUtils.isNotEmpty(role.getRemark())) {
            sql.append("remark,");
        }
        StringUtils.trimEndComma(sql);
        sql.append(")values(");
        if (StringUtils.isNotEmpty(role.getRoleName())) {
            sql.append("?,");
            params.add(role.getRoleName());
        }
        if (StringUtils.isNotEmpty(role.getRoleKey())) {
            sql.append("?,");
            params.add(role.getRoleKey());
        }
        if (StringUtils.isNotEmpty(role.getRoleSort())) {
            sql.append("?,");
            params.add(role.getRoleSort());
        }
        if (StringUtils.isNotEmpty(role.getStatus())) {
            sql.append("?,");
            params.add(role.getStatus());
        }
        if (StringUtils.isNotEmpty(role.getRemark())) {
            sql.append("?,");
            params.add(role.getRemark());
        }
        StringUtils.trimEndComma(sql);
        sql.append(")");
        return this.execInsert(sql.toString(), params.toArray());
    }

    @Override
    public Role selectRoleById(int roleId) {
        StringBuilder sql = new StringBuilder(
                "SELECT r.role_id roleId,r.role_name roleName,r.role_key roleKey,r.role_sort roleSort,r.`status`,r.create_by,r.create_time,r.update_by,r.update_time,r.remark FROM sys_role AS r  " );
        sql.append(" where role_id = ? ");
        return this.execQueryOne(sql.toString(), roleId);
    }

    @Override
    public int[] batchDeleteRole(Integer[] idsArray) {
        StringBuilder sql = new StringBuilder("delete from sys_role where role_id in (?)");
        Object[][] params = new Object[idsArray.length][];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[] { idsArray[i] };
        }
        return this.batch(sql.toString(), params);
    }

    @Override
    public List<Role> selectRoleList(Role role) {
        StringBuilder sql = new StringBuilder(
                "SELECT r.role_id roleId,r.role_name roleName,r.role_key roleKey,r.role_sort roleSort,r.`status`,r.create_by createBy,r.create_time createTime,r.update_by updateBy,r.update_time updateTime,r.remark FROM sys_role AS r WHERE 1=1 " );
        List<Object> params = new ArrayList<>();
        return this.execQuery(sql.toString(), params.toArray());
    }

    @Override
    public List<Role> selectRoleListByUserId(int userId) {
        StringBuilder sql = new StringBuilder(
                "SELECT r.role_id roleId ,r.role_name roleName ,r.role_key roleKey ,r.role_sort roleSort ,r.status STATUS ,r.create_by createBy ,r.create_time createTime ,r.update_by updateBy ,r.update_time updateTime ,r.remark remark FROM sys_role r INNER JOIN sys_user_role ur ON r.role_id=ur.role_id INNER JOIN sys_user u ON ur.user_id = u.user_id where u.user_id=?" );
        return this.execQuery(sql.toString(), userId);

    }


}