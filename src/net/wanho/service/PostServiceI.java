package net.wanho.service;

import net.wanho.po.*;
import java.util.List;
import net.wanho.vo.Page;

/**
 * 岗位 服务层接口
 * 
 * @author 赵老师
 * @date 2019-05-28
 */
public interface PostServiceI 
{
	/**
     * 查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 岗位信息
     */
	public Post selectPostById(Integer postId);
	
	/**
     * 查询岗位列表
     * 
     * @param post 岗位信息
     * @return 岗位集合
     */
	public List<Post> selectPostList(Post post);
	
	
	/**
     * 分页查询岗位列表
     * 
     * @param post 岗位信息
     * @return 岗位相关Page
     */
	public void selectPostByPage(Post post, Page Page);
	
	
	/**
     * 新增岗位
     * 
     * @param post 岗位信息
     * @return 结果
     */
	public Long insertPost(Post post);
	
	/**
     * 修改岗位
     * 
     * @param post 岗位信息
     * @return 结果
     */
	public Long updatePost(Post post);
	
	/**
     * 保存岗位
     * 
     * @param post 岗位信息
     * @return 结果
     */
	public Long savePost(Post post);
	
	/**
     * 保存和修改岗位
     * 
     * @param post 岗位信息
     * @return 结果
     */
	public Long saveOrUpdatePost(Post post, User user);
	
	/**
     * 删除岗位信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int[] deletePostByIds(String ids);

	public List<Post> selectPostByUserId(Integer userId);

	
	
}
