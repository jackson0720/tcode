package org.sing.tcode.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.dao.PostsDao;
import org.sing.tcode.entity.Forgood;
import org.sing.tcode.entity.Posts;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.PostsException;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.util.ConnUtils;
import org.sing.tcode.util.DateUtil;
import org.sing.tcode.util.PageBean;
import org.sing.tcode.util.PageBean2;



/**
 * 	帖子逻辑业务层
 * @author Chunkie Yeung
 *
 */
public class PostsService {
	
	/**
	 * 分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean<Posts> listPosts(int pageNum,int pageSize) {
		PostsDao dao = new PostsDao();
		try {
			 //查询总记录数
				int total = dao.count();
				//创建pageBean,封装分页信息
				PageBean<Posts> page = new PageBean<Posts>(pageNum,pageSize);
				page.setTotal(total);
				//设置当前页
				//page.setPageNum(pageNum);
				//设置每页的大小
				//page.setPageSize(pageSize);
				//调用dao包进行分页查询
				List<Posts> list = dao.listPosts(page);
				page.setList(list);
				return page;
			} catch (DaoException e) {
			e.printStackTrace();
			 throw new UserException(e.getMessage());
		}
	}
	
	
	public void deletePosts(String[]  ids) {
		PostsDao dao = new PostsDao();
		try {
			dao.deletePosts(ids);
		} catch(DaoException e) {
			e.getStackTrace();
			throw new UserException("SQL错误"+e.getMessage());
		}
	}
	
	
	public List<Posts> listPosts(){
		PostsDao dao = new PostsDao();
		try {
			return dao.listPosts();
		}catch(DaoException e) {
			e.getStackTrace();
			throw new UserException("SQL错误"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param posts
	 */
	public void addPosts(Posts posts) {
		PostsDao dao = new PostsDao();
		try {
			if(posts == null) {
				
			}
			else {
				dao.addPosts(posts);
			}
			
		}catch(DaoException e) {
			e.getStackTrace();
			throw new UserException("SQL错误"+e.getMessage());
		}
	}
	
	public List<Posts> listPostByName(String pid) {
		PostsDao dao = new PostsDao();
		try {
			return dao.listPostByName(pid);
		}catch(DaoException e) {
			e.getStackTrace();
			throw new UserException("SQL错误"+e.getMessage());
		}
	}
	
	public Posts viewPostByTheme(String ptheme){
		PostsDao dao = new PostsDao();
		try {
			return dao.viewPostByTheme(ptheme);
		}catch(DaoException e) {
			e.getStackTrace();
			throw new UserException("SQL错误"+e.getMessage());
		}
	}
	
	
	public List<Posts> listPostByAccount(String account){
		PostsDao dao = new PostsDao();
		try {
			return dao.listPostByAccount(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误"+e.getMessage());
		}
	}
	
	public Posts showPostByPid(String pid){
		PostsDao dao = new PostsDao();
		try {
			return dao.getPostByPid(pid);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误："+e.getMessage());
		}
	}
	
	/**
	 * 当前帖子下共有多少赞
	 * @param pid
	 * @return
	 */
	public int countGoods(String pid) {
		PostsDao dao = new PostsDao();
		try {
			return dao.CountGoods(pid);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误："+e.getMessage());
		}
	}
	
	/**
	 * 添加一个赞
	 * @param goods
	 */
	public void AddGoods(Forgood goods) {
		PostsDao dao = new PostsDao();
		try {
			dao.addGoods(goods);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误:"+e.getMessage());
		}
	}
	
	/**
	 * 根据当前帐号和当前帖子id删除此赞
	 * @param account
	 * @return 
	 */
	public String DelGoods(String account,String pid) {
		PostsDao dao = new PostsDao();
		try {
			dao.DelGoods(account,pid);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误:"+e.getMessage());
		}
		return pid;
	}
	
	public int StateGoods(Forgood good) {
		PostsDao dao = new PostsDao();
		try {
			return dao.stateGoods(good);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误："+e.getMessage());
		}
	}
	
	

	

}
