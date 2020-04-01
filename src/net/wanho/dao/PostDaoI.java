package net.wanho.dao;

import net.wanho.po.Post;
import net.wanho.vo.*;
import java.util.List;	

/**
 * 岗位 数据层
 * 
 */
public interface PostDaoI 
{
	
	public List<Post> selectPostByUserId(Integer userId);
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
     * @return 岗位集合
     */
     
	public void selectPostByPage(Post post, Page page);
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
     * 批量删除岗位
     * 
     * @param postIds 需要删除的数据ID
     * @return 结果
     */
	 public int[] batchDeletePost(Integer[] postIds);

    List<Post> selectPostListUserId(int userId);
}