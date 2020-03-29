package net.wanho.service.impl;

import java.util.Date;
import java.util.List;

import net.wanho.dao.PostDaoI;
import net.wanho.exception.DaoException;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Post;
import net.wanho.po.User;
import net.wanho.service.PostServiceI;
import net.wanho.util.Convert;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

/**
 * 岗位 服务层实现
 * 
 */
public class PostServiceImpl implements PostServiceI {
	private PostDaoI postDao = (PostDaoI) ObjectFactory.getObject("postDao");

	@Override
	public Post selectPostById(Integer postId) {
		// TODO Auto-generated method stub
		return postDao.selectPostById(postId);
	}

	@Override
	public List<Post> selectPostList(Post post) {
		// TODO Auto-generated method stub
		return postDao.selectPostList(post);
	}

	@Override
	public void selectPostByPage(Post post, Page page) {
		postDao.selectPostByPage(post, page);
	}

	@Override
	public Long insertPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updatePost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long savePost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long saveOrUpdatePost(Post post, User user) {
		Long key=0L;
		if(StringUtils.isNotEmpty(post.getPostId())){
			//修改
			post.setUpdateBy(user.getLoginName());
			post.setUpdateTime(new Date());
			try{
				key=postDao.updatePost(post);
			}catch(DaoException e){
				//自定义异常
				throw new  ServiceException("修改失败",e);
			}
		}else{
			//新增
			post.setCreateBy(user.getLoginName());
			post.setCreateTime(new Date());
			try{
				key=postDao.insertPost(post);
			}catch(DaoException e){
				throw new  ServiceException("新增失败",e);
			}
		}

		return key;
	
	}

	@Override
	public int[] deletePostByIds(String ids) {
		Integer [] idsArray=Convert.toIntArray(",", ids);
		// batch 分批处理
		return postDao.batchDeletePost(idsArray);
	}

	@Override
	public List<Post> selectPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.postDao.selectPostByUserId(userId);
	}

}