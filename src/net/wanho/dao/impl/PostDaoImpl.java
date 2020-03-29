package net.wanho.dao.impl;

import net.wanho.dao.PostDaoI;
import net.wanho.po.Post;
import net.wanho.vo.*;
import net.wanho.util.StringUtils;
import java.util.*;

/**
 * 岗位 数据层
 * 
 */
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDaoI {

	
	public List<Post> selectPostByUserId(Integer userId) {
		String sql = "SELECT p.post_id postId , p.post_code postCode , p.post_name postName , p.post_sort postSort , p.status STATUS , p.create_by createBy , p.create_time createTime , p.update_by updateBy , p.update_time updateTime , p.remark remark FROM sys_user u INNER JOIN sys_user_post up ON u.user_id=up.user_id INNER JOIN  sys_post p ON up.post_id=p.post_id WHERE u.user_id=?";
		return this.execQuery(sql, userId);
	}

	/**
	 * 查询岗位信息
	 * 
	 * @param postId
	 *            岗位ID
	 * @return 岗位信息
	 */
	public Post selectPostById(Integer postId) {
		StringBuilder sql = new StringBuilder(
				" select post_id postId , post_code postCode , post_name postName , post_sort postSort , status status , create_by createBy , create_time createTime , update_by updateBy , update_time updateTime , remark remark  from sys_post ");
		sql.append(" where post_id = ? ");
		return this.execQueryOne(sql.toString(), postId);
	}

	/**
	 * 查询岗位列表
	 * 
	 * @param post
	 *            岗位信息
	 * @return 岗位集合
	 */
	public List<Post> selectPostList(Post post) {
		StringBuilder sql = new StringBuilder(
				" select  post_id postId , post_code postCode , post_name postName , post_sort postSort , status status , create_by createBy , create_time createTime , update_by updateBy , update_time updateTime , remark remark  from sys_post where 1=1 ");
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotEmpty(post.getPostId())) {
			sql.append("AND post_id like concat('%',?, '%')");
			params.add(post.getPostId());
		}
		if (StringUtils.isNotEmpty(post.getPostCode())) {
			sql.append("AND post_code like concat('%',?, '%')");
			params.add(post.getPostCode());
		}
		if (StringUtils.isNotEmpty(post.getPostName())) {
			sql.append("AND post_name like concat('%',?, '%')");
			params.add(post.getPostName());
		}
		if (StringUtils.isNotEmpty(post.getPostSort())) {
			sql.append("AND post_sort like concat('%',?, '%')");
			params.add(post.getPostSort());
		}
		if (StringUtils.isNotEmpty(post.getStatus())) {
			sql.append("AND status like concat('%',?, '%')");
			params.add(post.getStatus());
		}
		if (StringUtils.isNotEmpty(post.getRemark())) {
			sql.append("AND remark like concat('%',?, '%')");
			params.add(post.getRemark());
		}
		return this.execQuery(sql.toString(), params.toArray());
	}

	/**
	 * 分页查询岗位列表
	 * 
	 * @param post
	 *            岗位信息
	 * @return 岗位集合
	 */

	public void selectPostByPage(Post post, Page page) {
		StringBuilder sql = new StringBuilder(
				//一定要取别名，以便查数据
				" select post_id postId , post_code postCode , post_name postName , post_sort postSort , status status , create_by createBy , create_time createTime , update_by updateBy , update_time updateTime , remark remark  from sys_post where 1=1 ");
		List<Object> params = new ArrayList<>();
		//查询语句都是模糊查询 AND post_id like concat('%',?, '%')"
		if (StringUtils.isNotEmpty(post.getPostId())) {
			//sql是查询语句，params是什么？
			sql.append("AND post_id like concat('%',?, '%')");
			params.add(post.getPostId());
		}
		if (StringUtils.isNotEmpty(post.getPostCode())) {
			sql.append("AND post_code like concat('%',?, '%')");
			params.add(post.getPostCode());
		}
		if (StringUtils.isNotEmpty(post.getPostName())) {
			sql.append("AND post_name like concat('%',?, '%')");
			params.add(post.getPostName());
		}
		if (StringUtils.isNotEmpty(post.getPostSort())) {
			sql.append("AND post_sort like concat('%',?, '%')");
			params.add(post.getPostSort());
		}
		if (StringUtils.isNotEmpty(post.getStatus())) {
			sql.append("AND status like concat('%',?, '%')");
			params.add(post.getStatus());
		}
		if (StringUtils.isNotEmpty(post.getRemark())) {
			sql.append("AND remark like concat('%',?, '%')");
			params.add(post.getRemark());
		}
		//order by page.getOrderColumn() + page.getOrderStyle() +limit 拼接对应页数据的where条件
		//where+=" limit "+page.getIndex()+","+page.getPageSize();
		sql.append(" order by  ").append(page.getOrderColumn()).append(" ").append(page.getOrderStyle())
				.append(" limit ?,?");
		page.setTotalItemNumber(selectRecordCount(post));
		params.add((page.getPageNo() - 1) * page.getPageSize());
		params.add(page.getPageSize());
		//将查询的结果封装到page对象（List,怎么会有两个参数）
		page.setData(this.execQuery(sql.toString(), params.toArray()));

	}

	/**
	 * private 岗位的总行数
	 *
	 */
	private Long selectRecordCount(Post post) {
		StringBuilder sql = new StringBuilder(" select count(0) from sys_post where 1=1 ");
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotEmpty(post.getPostId())) {
			sql.append("AND post_id like concat('%',?, '%')");
			params.add(post.getPostId());
		}
		if (StringUtils.isNotEmpty(post.getPostCode())) {
			sql.append("AND post_code like concat('%',?, '%')");
			params.add(post.getPostCode());
		}
		if (StringUtils.isNotEmpty(post.getPostName())) {
			sql.append("AND post_name like concat('%',?, '%')");
			params.add(post.getPostName());
		}
		if (StringUtils.isNotEmpty(post.getPostSort())) {
			sql.append("AND post_sort like concat('%',?, '%')");
			params.add(post.getPostSort());
		}
		if (StringUtils.isNotEmpty(post.getStatus())) {
			sql.append("AND status like concat('%',?, '%')");
			params.add(post.getStatus());
		}
		if (StringUtils.isNotEmpty(post.getRemark())) {
			sql.append("AND remark like concat('%',?, '%')");
			params.add(post.getRemark());
		}
		return ((Long) this.execQueryObject(sql.toString(), params.toArray()));
	}

	/**
	 * 新增岗位
	 * 
	 * @param post
	 *            岗位信息
	 * @return 结果
	 */
	public Long insertPost(Post post) {
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("");

		sql.append("insert into sys_post(");
		if (StringUtils.isNotEmpty(post.getPostId())) {
			sql.append("post_id,");
		}
		if (StringUtils.isNotEmpty(post.getPostCode())) {
			sql.append("post_code,");
		}
		if (StringUtils.isNotEmpty(post.getPostName())) {
			sql.append("post_name,");
		}
		if (StringUtils.isNotEmpty(post.getPostSort())) {
			sql.append("post_sort,");
		}
		if (post.getStatus().equals(0) || post.getStatus().equals(1)) {
			sql.append("status,");
		}
		if (StringUtils.isNotEmpty(post.getCreateBy())) {
			sql.append("create_by,");
		}
		if (StringUtils.isNotEmpty(post.getCreateTimeStr())) {
			sql.append("create_time,");
		}
		if (StringUtils.isNotEmpty(post.getRemark())) {
			sql.append("remark");
		}
		StringUtils.trimEndComma(sql);
		sql.append(")values(");
		if (StringUtils.isNotEmpty(post.getPostId())) {
			sql.append("?,");
			params.add(post.getPostId());
		}
		if (StringUtils.isNotEmpty(post.getPostCode())) {
			sql.append("?,");
			params.add(post.getPostCode());
		}
		if (StringUtils.isNotEmpty(post.getPostName())) {
			sql.append("?,");
			params.add(post.getPostName());
		}
		if (StringUtils.isNotEmpty(post.getPostSort())) {
			sql.append("?,");
			params.add(post.getPostSort());
		}
		if (post.getStatus().equals(0) || post.getStatus().equals(1)) {
			sql.append("?,");
			params.add(post.getStatus());
		}
		if (StringUtils.isNotEmpty(post.getCreateBy())) {
			sql.append("?,");
			params.add(post.getCreateBy());
		}
		if (StringUtils.isNotEmpty(post.getCreateTimeStr())) {
			sql.append("?,");
			params.add(post.getCreateTimeStr());
		}
		if (StringUtils.isNotEmpty(post.getRemark())) {
			sql.append("?");
			params.add(post.getRemark());
		}
		StringUtils.trimEndComma(sql);
		sql.append(")");
		return this.execInsert(sql.toString(), params.toArray());
	}

	/**
	 * 修改岗位
	 * 
	 * @param post
	 *            岗位信息
	 * @return 结果
	 */
	public Long updatePost(Post post) {
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder(" update sys_post set ");
		if (StringUtils.isNotEmpty(post.getPostCode())) {
			sql.append("post_code=?,");
			params.add(post.getPostCode());
		}
		if (StringUtils.isNotEmpty(post.getPostName())) {
			sql.append("post_name=?,");
			params.add(post.getPostName());
		}
		if (StringUtils.isNotEmpty(post.getPostSort())) {
			sql.append("post_sort=?,");
			params.add(post.getPostSort());
		}
		if (post.getStatus().equals(0) || post.getStatus().equals(1)) {
			sql.append("status=?,");
			params.add(post.getStatus());
		}
		if (StringUtils.isNotEmpty(post.getUpdateBy())) {
			sql.append("update_by=?,");
			params.add(post.getUpdateBy());
		}
		if (StringUtils.isNotEmpty(post.getUpdateTimeStr())) {
			sql.append("update_time=?,");
			params.add(post.getUpdateTimeStr());
		}
		if (StringUtils.isNotEmpty(post.getRemark())) {
			sql.append("remark=?");
			params.add(post.getRemark());
		}
		StringUtils.trimEndComma(sql);
		sql.append(" where post_id = ? ");
		params.add(post.getPostId());
		return new Long(this.execUpdate(sql.toString(), params.toArray()));
	}

	/**
	 * 批量删除岗位
	 * 
	 * @param postIds
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int[] batchDeletePost(Integer[] postIds) {
		/**
		 * String：char数组是final的，不可变，修改String时实际上是new一个新String对象返回，线程安全，频繁的增删操作时不建议使用
		 * StringBuffer：线程安全（StringBuffer中的方法中加synchronized锁），多线程建议使用这个，修改值时实际上是修改底层的char数组，相比String，开销更小
		 * StringBuilder：非线程安全的（StringBuilder中的方法中没加synchronized锁）, 单线程使用这个更快，修改值时实际上是修改底层的char数组，相比String，开销更小
		 * 取别名为了查数据库时取出名字
		 * 遍历出id,然后跳转到BaseDaoImpl的batch()方法及进行删除
		 */

		StringBuilder sql = new StringBuilder("delete from sys_post where post_id in (?)");
		Object[][] params = new Object[postIds.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { postIds[i] };
		}
		return this.batch(sql.toString(), params);
	}

}